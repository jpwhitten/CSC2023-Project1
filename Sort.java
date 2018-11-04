/*****************************************************/
/*** Purpose:                                      ***/
/***                                               ***/
/***     Initial Author: Jason Steggles 20/09/15   ***/
/***     Extended by: Your Name    Date            ***/
/*****************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Sort {

/** Size of array **/
private int size;

/** Number of used elements in array **/
private int usedSize;

/** Array of integers **/
private int[] A;

/** Global variables for counting sort comparisons **/
public int compIS; /** Global comparison count for Insertion Sort **/
public int compQS; /** Global comparison count for Quicksort **/
public int compNewS; /** Global comparison count for new sort **/

/*****************/
/** Constructor **/
/*****************/
Sort(int max)
{
    /** Initialiase global sort count variables **/
    compIS = 0;
    compQS = 0;
    compNewS = 0;
    
    /** Initialise size variables **/
    usedSize = 0;
    size = max;
    
    /** Create Array of Integers **/
    A = new int[size];
}

//Method returns the boolean result of its parameter and increments compIS by 1 
private boolean comparisonIS(Boolean x) {
	compIS++;
	return x; 
}

//Method returns the boolean result of its parameter and increments compQS by 1 
private boolean comparisonQS(Boolean x) {
	compQS++;
	return x; 
}

//Insertion sort
public void insertionsort() {
	//loop from index 1 to the last index of an array
	for(int i = 1; i < usedSize; i++) { 
		//declare a key
		int key = A[i];
		int j = i;
		//find position for key in array (increment comparison count)
		while(j > 0 && comparisonIS(key < A[j-1])) {
			A[j] = A[j-1] ;
			j--;
		}
		//insert key value into its sorted index
		A[j] = key;
	}
}

//Quicksort
public void quicksort() {
	//Call quicksort method with parameters
	quicksort(0, usedSize-1);	
}

//Quicksort method taking two parameters (smallest index and largest index of array you would like sorted) 
private void quicksort(int left, int right) {
	//initialise and declare variables
	int leftIndex = left;
	int rightIndex = right;
	int pivot;
	//if right and left pointers have not crossed partition and call quicksort on each partition
	if(rightIndex > leftIndex) {
		pivot = partition(leftIndex, rightIndex);
		quicksort(leftIndex, pivot - 1);
		quicksort(pivot + 1, rightIndex);
	}
}

//Partition method for quicksort
private int partition(int left, int right) {
	//initialise pointers and pivot
	int pivotValue = A[right];
	int pointerLeft = left;
	int pointerRight = right;
	//while the pointers have not crossed
	while(pointerLeft < pointerRight) {
		//If the value of the element at the left pointer is less than the value of the element at the pivot then increment the left pointer
		while(comparisonQS(A[pointerLeft] < pivotValue)) {
			pointerLeft++;
		}
		//If the right pointer has not reached the left element of the array and the value of the element at the right pointer is greater than or equal to the value of the element at the pivot then reduce value of right pointer by one
		while(pointerRight > left && comparisonQS(A[pointerRight] >= pivotValue)) {
			pointerRight--;
		}
		//If the pointers have not crossed then swap the elements in the array that they point to
		if(pointerLeft < pointerRight) {
			swap(pointerLeft, pointerRight);
		}
	}
	//swap the elements in the array at the left pointer and at the right
	swap(pointerLeft, right);
	return pointerLeft;
	
}

//Swapping method
private void swap(int x, int y) {
	int temp = A[x];
	A[x] = A[y];
	A[y] = temp;
}

//New sort
public void newSort() {
	//set initial starting point to 0
	int pos = 0;
	//while pos is less then the size of the array
	while(pos < usedSize) {
		//find the element with the minimum value from the index pos to the end of the array
		int min = findMinFrom(pos);
		//loop from pos to the end of the array
		for(int i = pos ; i < usedSize; i++) {
			compNewS++;
			//if the element at index i is the minimum value then swap its index with the element at index pos, then increment pos
			if(A[i] == min) {
				swap(i, pos);
				pos++;
			}
		}
	}
}

private int findMinFrom(int pos) {
	//initially set min to be element at index pos
	int min = A[pos];
	//search for new minimum from index pos +1 to the final index of  the array
	for(int i = pos + 1; i < usedSize; i++) {
		compNewS++;
		//If element at current index is less than current minimum then set it as the new minimum
		if(A[i] < min) {
			min = A[i];
		}
	}
	return min;
}

/*********************************************/
/*** Read a file of integers into an array ***/
/*********************************************/
public void readIn(String file)
{
   try
   {
       /** Initialise loop variable **/
       usedSize = 0;
       
       /** Set up file for reading **/
       FileReader reader = new FileReader(file);
       Scanner in = new Scanner(reader);
       
       /** Loop round reading in data while array not full **/
       while(in.hasNextInt() && (usedSize < size))
       {
           A[usedSize] = in.nextInt();
           usedSize++;
       }
       
    }
    catch (IOException e)
    {
       System.out.println("Error processing file " + file);
    }
}

/**********************/
/*** Display array  ***/
/**********************/
public void display(int line, String header)
{
    /*** Integer Formatter - three digits ***/
    NumberFormat FI = NumberFormat.getInstance();
    FI.setMinimumIntegerDigits(3);

    /** Print header string **/
    System.out.print("\n"+header);

    /** Display array data **/
    for (int i=0;i<usedSize;i++)
    {
        /** Check if new line is needed **/
        if (i%line == 0) 
        { 
            System.out.println(); 
        }
        
        /** Display an array element **/
        System.out.print(FI.format(A[i])+" ");
    }
}

public void displayInput(int line, String header)
{
    /** Print header string **/
    System.out.print("\n"+header);

    System.out.println("\nArray size: " + usedSize);

    /** Display array data **/
    for (int i=0;i<usedSize;i++)
    {
        /** Check if new line is needed **/
        if (i%line == 0) 
        { 
            System.out.println(); 
        }
        
        /** Display an array element **/
        System.out.print(A[i]+", ");
    }
}

} /** End of Sort Class **/