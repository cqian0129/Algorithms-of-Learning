package com.company;

/**
 * Created by blachcat on 2/26/17.
 */
//Hash table class, uses chaining for collision resoution,
//key is string type, value is Object type
public class HashTableChain
{
    //Entry of hash table, contains key-value
    private class Entry
    {
        private String key;				//key of entry
        private Object value;			//value of entry
        private Entry next;				//next entry in list

        //Constructor of Entry
        public Entry(String key, Object value, Entry next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry[] table;				//hash table array
    private int size;					//size of array
    private int count;					//number of data in table
    private final int INITIAL_CAPACITY = 100;		//initial size of array
    private final double LOAD_THRESHOLD = 2.0;		//load threshold

    //Constructor of hash table
    public HashTableChain()
    {
        this.size = INITIAL_CAPACITY;
        this.count = 0;
        this.table = new Entry[size];
    }

    //Method searches for a key and returns its value
    public Object find(String key)
    {
        int initial;
        Object value = null;

        initial = Math.abs(hash(key))%size;			//find location of chain
        Entry temp =  table[initial];

        while (temp != null)						//probe the chain
        {
            if (key.equals(temp.key))				//if key is found
            {
                value = temp.value;
                break;
            }
            else
                temp = temp.next;
        }

        return value;								//return value
    }

    //Method measures the average chain length
    public double averageLen()
    {
        int count_key = 0;

        for (int i = 0; i < size; i++)			//go thru table
        {
            Entry temp = table[i];

            while (temp != null)					//go thru each chain
            {
                count_key++;
                temp = temp.next;
            }
        }

        System.out.println("count_key: " + count_key);
        return ((double)count_key/size);
    }

    //Method inserts a key-value into hash table
    public void insert(String key, Object value)
    {
        int initial;

        if ((double)count/size >= LOAD_THRESHOLD)	//if load exceeds threshold
            rehash();								//then rehash

        initial = Math.abs(hash(key))%size;			//find location of chain
        Entry temp = table[initial];

        while (temp != null)						//probe the chain
        {
            if (key.equals(temp.key))				//if key already exists
            {
                temp.value = value;					//replace old with new value
                break;
            }
            else
                temp = temp.next;					//otherwise keep probing
        }

        if (temp == null)							//if key does not exist
        {
            table[initial] = new Entry(key, value, table[initial]);
            count = count + 1;						//insert key-value at beginning of chain
        }
    }

    //Method searches for a key and deletes the key-value entry
    public void delete(String key)
    {
        int initial;

        initial = Math.abs(hash(key))%size;
        Entry temp = table[initial];				//find location of chain
        Entry prev = null;

        while (temp != null)						//probe the chain
        {
            if (key.equals(temp.key))				//if key is found
            {
                if (temp ==  table[initial])
                    table[initial] = temp.next;
                else 								//delete key-value entry
                    prev.next = temp.next;
                count = count - 1;
                break;
            }
            else
            {
                prev = temp;							//otherwise keep probing
                temp = temp.next;
            }
        }
    }

    //Method rehashes hash table
    private void rehash()
    {
        Entry[] oldTable = table;					//save old table
        int oldSize = size;

        table = new Entry[2*oldSize];
        size = 2 * oldSize;							//create new table with twice size
        count = 0;

        for (int i = 0; i < oldSize; i++)			//go thru table
        {
            Entry temp = oldTable[i];
            while (temp != null)					//go thru each chain
            {
                insert(temp.key, temp.value);		//insert key-value from old table
                temp = temp.next;					//into enw table
            }
        }
    }


    //Method computes hash value of a key
    private int hash(String key)
    {
        return key.hashCode();						//use built in hash value of string
    }
/*
    //Method computes hash value of a key(product of six characters)
    private int hash(String key)
    {
        char[] item = new char[6];

        for (int i = 0; i < 6; i++)
        {
            item[i] = key.charAt(i);
        }

        int product = 1;

        for (int i = 0; i < 6; i++)
        {
            product = product * (int)item[i];
        }

        return product;
    }


    //Method computes hash value of a key(example: hash("AA55bb")="A"*"A"*"b"*"b" + "5"*31^1 + "5"*31^0)
    private int hash (String key)
    {
        char[] item = new char[6];

        for (int i = 0; i < 6; i++)
        {
            item[i] = key.charAt(i);
        }

        int product = 1;
        int sum = 0;
        int total = 0;

        product = product*(int)item[0]*(int)item[1]*(int)item[4]*(int)item[5];
        sum = sum + (int)item[2]*31 + (int)item[3];

        total = sum + product;

        return total;
    }
*/
}


