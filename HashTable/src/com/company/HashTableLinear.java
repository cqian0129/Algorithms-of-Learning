package com.company;

/**
 * Created by blachcat on 2/26/17.
 */
//Hash table class, uses linear probing for collision resoution,
//key is string type, value is Object type
public class HashTableLinear
{
    //Entry of hash table, contains key-value
    private class Entry
    {
        private String key;				//key of entry
        private Object value;			//value of entry

        //Constructor of Entry
        public Entry(String key, Object value)
        {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;				//hash table array
    private int size;					//size of array
    private int count;					//number of data in table
    private final int INITIAL_CAPACITY = 100;	//initial size of array
    private final double LOAD_THRESHOLD = 0.75;		//load threshold
    private final Entry DELETED = new Entry(null, null);	//deleted entry

    //Constructor of hash table
    public HashTableLinear()
    {
        this.size = INITIAL_CAPACITY;
        this.count = 0;
        this.table = new Entry[size];
    }

    //Method searches for a key and returns its value
    public Object find(String key)
    {
        int i, initial, current;
        Object value = null;

        initial = Math.abs(hash(key))%size;			//start probing

        for (i = 0; i < size; i++)					//probing loop
        {
            current = (initial + i) % size;			//current probe location

            if (table[current] == null)				//if empty slot stop
                break;
            else if (key.equals(table[current].key))	//if key is found
            {
                value = table[current].value;			//save value and stop
                break;
            }
            else;									//otherwise keep probing
        }

        return value;								//return value
    }

    //Method inserts a key-value into hash table
    public void insert(String key, Object value)
    {
        int i, initial, current;

        if ((double)count/size >= LOAD_THRESHOLD)	//if load exceeds threshold
            rehash();								//then rehash

        initial = Math.abs(hash(key))%size;			//start probing

        for (i = 0; i < size; i++)					//probing loop
        {
            current = (initial + i) % size;			//current probe location

            if (table[current] == null)				//if empty slot found
            {
                table[current] = new Entry(key, value);
                count = count + 1;					//insert key-value
                break;
            }
            else if (key.equals(table[current].key))		//if key already exists
            {
                table[current].value = value;			//replace old with new value
                break;
            }
            else;									//otherwise keep probing
        }
    }

    //Method searches for a key and deletes the key-value entry
    public void delete(String key)
    {
        int i, initial, current;

        initial = Math.abs(hash(key))%size;			//start probing

        for (i = 0; i < size; i++)					//probing loop
        {
            current = (initial + i) % size;			//current probe location

            if (table[current] == null)				//if empty slot stop
                break;
            else if (key.equals(table[current].key))	//if key is found
            {
                table[current] = DELETED;			//mark slot as DELETED
                break;
            }
            else;									//otherwise keep probing
        }
    }

    //Method rehashes hash table
    private void rehash()
    {
        Entry[] oldTable = table;					//save old table
        int oldSize = size;

        table = new Entry[2*oldSize];
        size = 2 * oldSize;							//create new table with
        count = 0;									//twice size

        //insert key-value from old table into new table, skip empty
        //slots and DELETED
        for (int i = 0; i < oldSize; i++)
            if (oldTable[i] != null && oldTable[i].key != null)
                insert(oldTable[i].key, oldTable[i].value);
    }

    //Method computes hash value of a key
    private int hash(String key)
    {
        return key.hashCode();						//use built in hash value of string
    }
}

