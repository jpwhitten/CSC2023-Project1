
/*************************************************/
/*** Simple test class for Sort class          ***/
/***                                           ***/
/*** Author: Jason Steggles    20/09/2015      ***/
/*************************************************/


public class TestSort
{
	public static void main(String[] args) 
    {
        Sort sortTest = new Sort(100);
        
        
        
        /** Read in test data into array **/
        sortTest.readIn("test5.txt");
        
        //sortTest.insertionsort();
        sortTest.quicksort();
        sortTest.newSort();
        
        /** Display comparison counters **/
        //System.out.println("\n\nInsertion sort comparison counter: " + sortTest.compIS);
        System.out.println("Quicksort comparison counter: " + sortTest.compQS);
        System.out.println("New sort comparison counter: " + sortTest.compNewS);
        
        /** Display array **/
        sortTest.display(10,"Array of Integers");
        //sortTest.displayInput(10, "Unsorted Array");
    }
    
} /** End of Test class **/