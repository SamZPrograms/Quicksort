/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.util.ArrayList;
import java.util.Random;

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
    void main(String[] args) {
        
        int[] listSizes = {10}; //, 1000, 5000, 10000, 50000};
        Random random = new Random();
        
        
//        Integer[] a = {7, 9, 8, 4, 3, 6, 10};
//        for(int i = 0; i < a.length; i++) {
//            System.out.print(a[i] + ", "); 
//        }
        Integer[] a = new Integer[listSizes[0]];
        
        for(int i = 0; i < a.length; i++){
            a[i] = random.nextInt(100);
            System.out.print(a[i] + ", ");
        }
        
        
        
        
        quicksort1(a);
        
        System.out.println();
        
        for(int i = 0; i < a.length; i++){
            System.out.print(a[i] + ", ");
        }
        
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
    AnyType randomMedianElementPivot(AnyType []a, int rand1, int rand3) {
        
        int rand2 = (rand1 + rand3) / 2;
        
        if(a[rand2].compareTo(a[rand1]) < 0)
            swapReferences(a, rand1, rand2);
        if(a[rand3].compareTo(a[rand1]) < 0)
            swapReferences(a, rand1, rand3 );
        if(a[rand3].compareTo(a[rand2]) < 0)
            swapReferences(a, rand2, rand3);

        // Place pivot at position right - 1
        swapReferences(a, rand2, rand3);
        return a[rand3];
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
            
            System.out.println();
            
            for(int k = 0; k < a.length; k++)
                System.out.print(a[k] + ", ");
            
            System.out.println();
            
            
            // Begin partitioning
            int i = left - 1, j = right;
            for( ; ; )
            {
                while((a[++i].compareTo(pivot) < 0) && right > i) { }
                while((a[--j].compareTo(pivot) > 0) && left < j) { }
                if(i < j) {
                    System.out.println("pivot: " + pivot);
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    swapReferences(a, i, j);
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    System.out.println("\n");
                    for(int k = 0; k < a.length; k++)
                        System.out.print(a[k] + ", ");
                    System.out.println();
                }
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
            int rand = random.nextInt(a.length);
            AnyType pivot = a[rand];
            
            swapReferences(a, rand, right);
            
            // Begin partitioning
            int i = left - 1, j = right;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 && right > i ) { }
                while( a[ --j ].compareTo( pivot ) > 0 && left < j) { }
                if( i < j ) {
                    System.out.println("pivot: " + pivot);
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    swapReferences(a, i, j);
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    System.out.println("\n");
                    for(int k = 0; k < a.length; k++)
                        System.out.print(a[k] + ", ");
                    System.out.println();
                }
                else
                    break;
            }

            swapReferences(a, i, right);   // Restore pivot

            quicksort2( a, left, i - 1);    // Sort small elements
            quicksort2( a, i + 1, right);   // Sort large elements
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
            
            int rand1 = random.nextInt(a.length);
            int rand2 = random.nextInt(a.length);
            int rand3 = random.nextInt(a.length);
            
            if(a[rand2].compareTo(a[rand1]) < 0)
                swapReferences(a, left, rand2);
            if(a[rand3].compareTo(a[rand1]) < 0)
                swapReferences(a, left, rand3);
            if(a[rand3].compareTo(a[rand2]) < 0)
                swapReferences(a, rand2, right);
            
            // Place pivot at position right - 1
            swapReferences(a, rand2, right - 1);
             
            
//            if(a[center].compareTo(a[left]) < 0)
//                swapReferences(a, left, center);
//            if(a[right].compareTo(a[left]) < 0)
//                swapReferences(a, left, right);
//            if(a[right].compareTo(a[center]) < 0)
//                swapReferences(a, center, right);
//
//            // Place pivot at position right - 1
//            swapReferences(a, center, right - 1);
//            return a[right - 1];
//            
            AnyType pivot = a[right - 1];

            System.out.println("pivot: " + pivot);
            System.out.println();
            System.out.println();
            
            // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 && right > i ) { }
                while( a[ --j ].compareTo( pivot ) > 0 && left < j) { }
                if( i < j ) {
                    System.out.println("pivot: " + pivot);
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    swapReferences( a, i, j );
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    System.out.println("\n");
                    for(int k = 0; k < a.length; k++)
                        System.out.print(a[k] + ", ");
                    System.out.println();
                }
                    
                else
                    break;
            }

            swapReferences(a, i, right - 1);   // Restore pivot

            quicksort3(a, left, i - 1);    // Sort small elements
            quicksort3(a, i + 1, right);   // Sort large elements
        }
        else  // Do an insertion sort on the subarray
            insertionSort( a, left, right );
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
            
            System.out.println(pivot);

            // Begin partitioning
            int i = left, j = right - 1;
            for( ; ; )
            {
                while( a[ ++i ].compareTo( pivot ) < 0 && right > i ) { }
                while( a[ --j ].compareTo( pivot ) > 0 && left < j) { }
                if(i < j){
                    System.out.println("pivot: " + pivot);
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    swapReferences( a, i, j );
                    System.out.println("i-j: " + i +"-" + j);
                    System.out.println("a[i]-a[j]: " + a[i] +"-" + a[j]);
                    System.out.println("\n");
                    for(int k = 0; k < a.length; k++)
                        System.out.print(a[k] + ", ");
                    System.out.println();
                }
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
