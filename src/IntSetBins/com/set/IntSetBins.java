package com.set;

import java.util.ArrayList;
import java.util.Collections;

public class IntSetBins {
	/* A set implementation by simple bins.
	 * 
	 * @Attributes:
	 *	maxval: the maxval will be insert into the set.
	 *	maxelem: the size of the set.
	 *	element_num: number of elements in the set.
	 *	bucket: a list that each element is a arraylist to store element.
	 *
	 * @Method:
	 * IntSetBins(): Constructor. Build the initial empty set.
	 * insert(): insert an integer into the set if there is not same one. 
	 * report(): sort the elements in the set.
	 */
	
    private ArrayList<ArrayList<Integer>> bucket;
    private int element_num;
    private int maxval;
    private int maxelem;
    private int bucket_size;


    public IntSetBins(int maxelements, int maxval) {
    	/* Set initialization.
    	 * 
    	 * Use 10 buckets to store the inserted elements.
    	 * Each bucket contains a int array.
    	 */

        this.maxelem = maxelements;
        this.maxval = maxval;
        this.element_num = 0;
        this.bucket_size = maxval/10;
        if (bucket_size == 0)
        	bucket_size = 1;  // when maxval less than 10;
        
        this.bucket = new ArrayList<ArrayList<Integer>>(10);
        for (int i = 0; i < 10; i++)
        	bucket.add(new ArrayList<Integer>());
    }

    public void insert(int element) {
    	/* Insert int into set.
    	 * 
    	 * Use range of (maxval/10) as key.
    	 */

    	if (element >= this.maxval)
    		return;
    	else if (element < 0)
    		return;
    	else if (this.element_num >= this.maxelem)
    		return;

    	int index = element / bucket_size;
    	ArrayList<Integer> bucket_elem = bucket.get(index);
    	if (bucket_elem.contains(element))
    		return;
    	bucket_elem.add(element);
    	this.element_num++;
	}

    public int[] report() {
    	/* Sort the element in the set.
    	 * 
    	 * @Return:
    	 * 	int array which contains sorted set elements.
    	 */

        int[] array = new int[this.element_num];
        int pos = 0;
        for (ArrayList<Integer> list : this.bucket) {
        	if (list.isEmpty())
        		continue;
        	Collections.sort(list);
        	for (int num : list)
        		array[pos++] = num;
        }
        if (array.length == 0)
        	return null;
        return array;
    }

    public int size() {
    	// return the number of elements in the set.
        return this.element_num;
    }
}