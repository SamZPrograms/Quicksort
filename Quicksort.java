/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner; 

/**
 *
 * @author Samuel Zrna
 */
public class Quicksort {

    // global cutoff variable
    private static final int CUTOFF = 3;
    
    /**
     * Quicksort algorithm.
     * @param args the command line arguments
     */
    public static <AnyType extends Comparable<? super AnyType>> 
    void main(String[] args) throws IOException {
    
        Scanner scan = new Scanner(System.in);  // input scanner to read user input
        Random random = new Random();           // random obj to generate random integers
        BufferedWriter unsortedOutput = null;   // unsorted buffered writer
        BufferedWriter sortedOutput = null;     // sorted buffered writer
       
        // promt the user
        System.out.println("Enter a number to create and sort an array of that size: ");
        int size = scan.nextInt();  // store user input into int size
        scan.close();               // close scanner
        
        // initialization of 4 arrays with size of size 
        Integer[] firstElement = new Integer[size];
        Integer[] randomElement = new Integer[size];
        Integer[] randomMedianElementOfThree = new Integer[size];
        Integer[] medianElementOfThree = new Integer[size];
        
        // try-catch block to safely create and write to file
        try {
            // unsortedOutput now can be used to write to "unsorted.txt"
            unsortedOutput = new BufferedWriter(new FileWriter(new File("unsorted.txt")));
            
            unsortedOutput.write("Unsorted array:\n");
            
            // initializing variables used in the loop
            int rand = 0;
            String number;
            
            // for-loop to load each array with the same random numbers
            for(int i = 0; i < firstElement.length; i++){
                rand = random.nextInt(size);                // gets rand int b/t 0 and size - 1
                number = String.valueOf(rand);              // number is the rand but of type String
                firstElement[i] = rand;
                randomElement[i] = rand;
                randomMedianElementOfThree[i] = rand;
                medianElementOfThree[i] = rand;

                unsortedOutput.write(number);               // writes the number to unsorted
                if(i != firstElement.length - 1)            // if last pass, don't write ", "
                    unsortedOutput.write(", ");
            }
        } catch ( IOException e ) {
            System.out.println("Could not open 'unsorted' file correctly");
        } 
        
        /*
            quicksort1
            Here we call the first quicksort method which uses the first element
            as the pivot.
            I also keep track of how long it takes using System.currentTimeMillis().
        */
        long start = System.currentTimeMillis();        
        quicksort1(firstElement);
        long firstElementTime = System.currentTimeMillis() - start;
        System.out.println("First pivot time in milliseconds: " + firstElementTime + "\n");
        
        /*
            quicksort2
            Here we call the second quicksort which uses a random element
            as the pivot.
            I also keep track of how long it takes using System.currentTimeMillis().
        */
        start = System.currentTimeMillis();
        quicksort2(randomElement);
        long randomElementTime = System.currentTimeMillis() - start;
        System.out.println("Random pivot time in milliseconds: " + randomElementTime + "\n");
        
        /*
            quicksort3
            Here we call the third quicksort method which uses the median of
            three randomly selected elements as the pivot.
            I also keep track of how long it takes using System.currentTimeMillis().
        */
        start = System.currentTimeMillis();
        quicksort3(randomMedianElementOfThree);
        long randomMedianElementOfThreeTime = System.currentTimeMillis() - start;
        System.out.println("Median of 3 random pivots time in milliseconds: " + randomMedianElementOfThreeTime + "\n");
        
        /*
            quicksort4
            Here we call the fourth quicksort method which uses the median of
            the first, middle and last element as the pivot.
            I also keep track of how long it takes using System.currentTimeMillis().
        */
        start = System.currentTimeMillis();
        quicksort4(medianElementOfThree);
        long medianElementOfThreeTime = System.currentTimeMillis() - start;
        System.out.println("Median of left-center-right pivot time in milliseconds: " + medianElementOfThreeTime + "\n");
        
        // try-catch block to safely create and write to file
        try {
            // sortedOutput now can be used to write to "sorted.txt"
            sortedOutput = new BufferedWriter(new FileWriter(new File("sorted.txt")));
            
            // first pivot
            // loops through sorted array and writes it to sorted.txt
            String number;
            sortedOutput.write("Quicksort by first pivot:\n");
            for(int i = 0; i < firstElement.length; i++) {
                number = String.valueOf(firstElement[i]);
                sortedOutput.write(number);
                if(i != firstElement.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nFirst pivot time in milliseconds: " + firstElementTime + "\n\n");
            
            // random pivot
            // loops through sorted array and writes it to sorted.txt
            sortedOutput.write("Quicksort by random pivot:\n");
            for(int i = 0; i < randomElement.length; i++) {
                number = String.valueOf(randomElement[i]);
                sortedOutput.write(number);
                if(i != randomElement.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nRandom pivot time in milliseconds: " + randomElementTime + "\n\n");
            
            // median of 3 random elements as the pivot
            // loops through sorted array and writes it to sorted.txt
            sortedOutput.write("Quicksort by median of 3 random pivots:\n");
            for(int i = 0; i < randomMedianElementOfThree.length; i++) {
                number = String.valueOf(randomMedianElementOfThree[i]);
                sortedOutput.write(number);
                if(i != randomMedianElementOfThree.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nMedian of 3 random pivots time in milliseconds: " + randomMedianElementOfThreeTime + "\n\n");
            
            // median of first, middle and last elements as the pivot
            // loops through sorted array and writes it to sorted.txt
            sortedOutput.write("Quicksort by median of left-center-right pivot:\n");
            for(int i = 0; i < medianElementOfThree.length; i++) {
                number = String.valueOf(medianElementOfThree[i]);
                sortedOutput.write(number);
                if(i != medianElementOfThree.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nMedian of left-center-right pivot time in milliseconds: " + medianElementOfThreeTime + "\n\n");
            
        } catch ( IOException e ) {
            System.out.println("Could not open 'sorted' file correctly");
        } 
        
        // close buffered writters
        sortedOutput.close();
        unsortedOutput.close();
    }
    
    /**
     * Internal quicksort method finds the median of 3 random elements.     
     * @param a an array of Comparable items.
     * @param right the right-most index of the subarray.
     * @param rand1 a random index between left and right
     * @param rand2 a random index between left and right
     * @param rand3 a random index between left and right
     */
    public static <AnyType extends Comparable<? super AnyType>>
    AnyType randomMedianElementPivot(AnyType []a, int right, int rand1, int rand2, int rand3) {

        if(a[rand2].compareTo(a[rand1]) < 0)
            swapReferences(a, rand1, rand2);
        if(a[rand3].compareTo(a[rand1]) < 0)
            swapReferences(a, rand1, rand3);
        if(a[rand3].compareTo(a[rand2]) < 0)
            swapReferences(a, rand2, rand3);

        // Place pivot at position right
        swapReferences(a, rand2, right);
        return a[right];
    }
    
    /**
     * Return median of left, center, and right.
     * Order these and hide the pivot.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    AnyType medianElementPivot(AnyType []a, int left, int right)
    {
        
        int center = (left + right) / 2;
        
        if(a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if(a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if(a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }
    
    /**
     * Internal quicksort method that makes recursive calls.
     * Uses first element to do the  partitioning and a cutoff of 3.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort1(AnyType[] a, int left, int right)
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = a[left];
            swapReferences(a, left, right);
            
            // Begin partitioning
            int i = left - 1, j = right;
            for( ; ; )
            {
                while((a[++i].compareTo(pivot) < 0) && right > i) { }
                while((a[--j].compareTo(pivot) > 0) && left < j) { }
                if(i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right);   // Restore pivot

            quicksort1(a, left, i - 1);    // Sort small elements
            quicksort1(a, i + 1, right);   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }
    
    /**
     * Internal quicksort method that makes recursive calls.
     * Uses a random element to do the partitioning and a cutoff of 3.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort2(AnyType[] a, int left, int right)
    {
        if(left + CUTOFF <= right)
        {
            Random random = new Random();
            int rand = random.nextInt(right - left) + left;
            
            AnyType pivot = a[rand];
            
            if(rand != right)
                swapReferences(a, rand, right);
            
            // Begin partitioning
            int i = left - 1, j = right;
            for( ; ; )
            {
                while(a[++i].compareTo(pivot) < 0 && right > i) { }
                while(a[--j].compareTo(pivot) > 0 && left < j) { }
                if(i < j) 
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right);   // Restore pivot

            quicksort2(a, left, i - 1);    // Sort small elements
            quicksort2(a, i + 1, right);   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }
    
    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three of randomly selected elements for the partitioning 
     * and a cutoff of 3.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort3(AnyType[] a, int left, int right)
    {
        if(left + CUTOFF <= right )
        {
            Random random = new Random();
            
            int rand1 = random.nextInt(right - left) + left;
            int rand2 = random.nextInt(right - left) + left;
            int rand3 = random.nextInt(right - left) + left;
                        
            AnyType pivot = randomMedianElementPivot(a, right, rand1, rand2, rand3);

            // Begin partitioning
            int i = left - 1, j = right;
            for( ; ; )
            {
                while(a[++i].compareTo(pivot) < 0 && right > i) { }
                while(a[--j].compareTo(pivot) > 0 && left < j) { }
                if( i < j )
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences(a, i, right);   // Restore pivot

            quicksort3(a, left, i - 1);    // Sort small elements
            quicksort3(a, i + 1, right);   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }
    
    /**
     * Internal quicksort method that makes recursive calls.
     * Uses median-of-three partitioning and a cutoff of 10.
     * @param a an array of Comparable items.
     * @param left the left-most index of the subarray.
     * @param right the right-most index of the subarray.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort4(AnyType[] a, int left, int right)
    {
        if( left + CUTOFF <= right )
        {
            AnyType pivot = medianElementPivot(a, left, right);
            
            // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 && right > i ) { }
                while( a[ --j ].compareTo( pivot ) > 0 && left < j) { }
                if(i < j)
                    swapReferences( a, i, j );
                else
                    break;
            }

            swapReferences(a, i, right - 1);   // Restore pivot

            quicksort4(a, left, i - 1);    // Sort small elements
            quicksort4(a, i + 1, right);   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort(a, left, right);
    }
    
    /**
     * Internal quicksort method that calls the overloaded quicksort call.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort1(AnyType[] a)
    {
        quicksort1(a, 0, a.length - 1);
    }
    
    /**
     * Internal quicksort method that calls the overloaded quicksort call.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort2(AnyType[] a)
    {
        Random random = new Random();
        quicksort2(a, 0, a.length - 1);
    }
    
    /**
     * Internal quicksort method that calls the overloaded quicksort call.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort3(AnyType[] a)
    {
        quicksort3(a, 0, a.length - 1);
    }
    
    /**
     * Internal quicksort method that calls the overloaded quicksort call.
     * @param a an array of Comparable items.
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort4(AnyType[] a)
    {
        quicksort4( a, 0, a.length - 1);
    }
       
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType [ ] a, int left, int right )
    {
        for( int p = left + 1; p <= right; p++ )
        {
            AnyType tmp = a[ p ];
            int j;

            for( j = p; j > left && tmp.compareTo( a[ j - 1 ] ) < 0; j-- )
                a[ j ] = a[ j - 1 ];
            a[ j ] = tmp;
        }
    }
    
    /**
     * Method to swap to elements in an array.
     * @param a an array of objects.
     * @param index1 the index of the first object.
     * @param index2 the index of the second object.
     */
    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2)
    {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }
}
