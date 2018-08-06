package com.company;

/**
 * Created by blachcat on 2/26/17.
 */
//Heap data structure, min heap, array based implementation
public class Heap{
    private int[] p;			//array for heap
    private int size;			//size of heap
    private int count;			//number of elements in heap

    //constructor of heap
    public Heap(int size)
    {
        this.size = size;		//initialize size
        this.p = new int[size+1];		//allocate array
        this.count = 0;				//initialize count
    }

    //inserts data into heap, assumes heap is not full
    public void insert(int data)
    {
        count++;				//increment count
        p[count] = data;		//insert data as last element
        int current = count;	//begin heap up
        while(current/2 >= 1 && p[current] > p[current/2])//<
        {											//heap up as long as current value
            swap(p, current, current/2);			//is less than parent value
            current = current/2;
        }
    }

    //deletes minimum value from heap
    public int deletemin()
    {
        int min;						//minimum value
        int current, left, right;		//current location,left/right children

        min = p[1];						//save the minimum for later return
        p[1] = p[count];				//put the last element at the root
        count = count - 1;				//decrement count
        current = 1;					//begin heap down

        while (true)					//heap down until root value reaches
        {								//appropriate location
            left = 2*current; right = 2*current + 1;		//find left/right child
            //locations
            //current node has two children
            if (left <= count && right <= count)
            {
                //current value is less than or equal to both children value
                if (p[current] >= p[left] && p[current] >= p[right])
                    break;									//heap down complete
                    //current value is greater than one of the children
                else
                {
                    if (p[left] >= p[right])				//left child is less
                    {										//than right child
                        swap(p, current, left);				//swap with left child
                        current = 2*current;
                    }
                    else
                    {										//right child is less
                        swap(p, current, right);			//than left child
                        current = 2*current + 1;			//swap with right child
                    }
                }
            }
            //current node has left child only
            else if (left <= count && right > count)
            {
                if (p[current] >= p[left])					//current value is less
                    break;									//than left child
                else										//heap down complete
                {
                    swap(p, current, left);					//otherwise swap with
                    current = 2*current;					//left child
                }
            }
            //current node has no children, heap down complete
            else
                break;
        }

        return min;						//return the minimum value
    }

    //swap two elements of array
    private void swap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}