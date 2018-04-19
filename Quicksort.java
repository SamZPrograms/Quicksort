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
 * @author Archi
 */
public class Quicksort {
    
    
    public static final int CUTOFF = 3;
    public static final int COUNT = 10;
    private static final int NUM_ITEMS = 1000;
    
    /**
     * Quicksort algorithm.
     * @param args the command line arguments
     */
    public static <AnyType extends Comparable<? super AnyType>> 
    void main(String[] args) throws IOException {
    
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        BufferedWriter unsortedOutput = null;
        BufferedWriter sortedOutput = null;
       
        
        System.out.println("Enter a number to create and sort an array of that size: ");
        
        int size = scan.nextInt();
        scan.close();
        
        Integer[] firstElement = new Integer[size];
        Integer[] randomElement = new Integer[size];
        Integer[] randomMedianElementOfThree = new Integer[size];
        Integer[] medianElementOfThree = new Integer[size];
        
        try {
            File file = new File("unsorted.txt");
            unsortedOutput = new BufferedWriter(new FileWriter(file));
            
            unsortedOutput.write("Unsorted array:\n");
            
            int rand = 0;
            String number;
            for(int i = 0; i < firstElement.length; i++){
                rand = random.nextInt(size);
                number = String.valueOf(rand);
                firstElement[i] = rand;
                randomElement[i] = rand;
                randomMedianElementOfThree[i] = rand;
                medianElementOfThree[i] = rand;

                unsortedOutput.write(number);
                if(i != firstElement.length - 1)
                    unsortedOutput.write(", ");
            }
        } catch ( IOException e ) {
            System.out.println("Could not open 'unsorted' file correctly");
        } 
        
        long start = System.currentTimeMillis();        
        quicksort1(firstElement);
        long firstElementTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        quicksort2(randomElement);
        long randomElementTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        quicksort3(randomMedianElementOfThree);
        long randomMedianElementOfThreeTime = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        quicksort4(medianElementOfThree);
        long medianElementOfThreeTime = System.currentTimeMillis() - start;
        
        try {
            File file = new File("sorted.txt");
            sortedOutput = new BufferedWriter(new FileWriter(file));
            
            
            String number;
            sortedOutput.write("Quicksort by first pivot:\n");
            for(int i = 0; i < firstElement.length; i++) {
                number = String.valueOf(firstElement[i]);
                sortedOutput.write(number);
                if(i != firstElement.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nFirst pivot time in milliseconds: " + firstElementTime + "\n\n");
            
            sortedOutput.write("Quicksort by random pivot:\n");
            for(int i = 0; i < randomElement.length; i++) {
                number = String.valueOf(randomElement[i]);
                sortedOutput.write(number);
                if(i != randomElement.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nRandom pivot time in milliseconds: " + randomElementTime + "\n\n");
            
            sortedOutput.write("Quicksort by median of 3 random pivots:\n");
            for(int i = 0; i < randomMedianElementOfThree.length; i++) {
                number = String.valueOf(randomMedianElementOfThree[i]);
                sortedOutput.write(number);
                if(i != randomMedianElementOfThree.length - 1)
                    sortedOutput.write(", ");
            }
            sortedOutput.write("\nMedian of 3 random pivots time in milliseconds: " + randomMedianElementOfThreeTime + "\n\n");
            
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
        
        
        sortedOutput.close();
        unsortedOutput.close();
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
    AnyType randomMedianElementPivot(AnyType []a, int right, int left, int rand1, int rand2, int rand3) {

        if(a[rand2].compareTo(a[rand1]) < 0)
            swapReferences(a, rand1, rand2);
        if(a[rand3].compareTo(a[rand1]) < 0)
            swapReferences(a, rand1, rand3);
        if(a[rand3].compareTo(a[rand2]) < 0)
            swapReferences(a, rand2, rand3);

        // Place pivot at position right - 1
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
     * Uses median-of-three partitioning and a cutoff of 10.
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
     * Uses median-of-three partitioning and a cutoff of 10.
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
     * Uses median-of-three partitioning and a cutoff of 10.
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
                        
            AnyType pivot = randomMedianElementPivot(a, right, left, rand1, rand2, rand3);

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
    
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort1(AnyType[] a)
    {
        quicksort1(a, 0, a.length - 1);
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort2(AnyType[] a)
    {
        Random random = new Random();
        quicksort2(a, 0, a.length - 1);
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
    void quicksort3(AnyType[] a)
    {
        quicksort3(a, 0, a.length - 1);
    }
    
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
