/**
 *  String_csd - This class gathers static generic functions to be used.
 * 
 * @author      Pau Coma Ramriez 	<paucoma @ gmail.com>
 * @version     140429  	        (current version number of program)
 * @since       140329      	    (the version of the package this class was first added to)
 */

package com.paucoma.cossimdiff;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

//TODO : the toCanonicalForm function could be transformed to use an object that impelements an iterator.

public class String_csd {

	public static void main(String[] args) {
    	//adiqFormulas myFormulas = new adiqFormulas();
	}
	/** Generic Function 
	 * 
	 * @return Object[]: Array of Objects that define the base of an object. e.g. Characters from a String
	 * @param mySt String to be split
	 */
	public static String[] toCanonicalForm(String myObj){
		ArrayList<String> myRet = new ArrayList<String>();
		char[] myChars = myObj.toCharArray();
		for(int i=0; i<myChars.length; i=i+1){
			myRet.add(String.valueOf(myChars[i]));
		}
		String[] myStrArr = new String[myRet.size()];
	    return myRet.toArray(myStrArr);
	}
	/** Coordinates specifier
	 *      :Coordinates are defined as multiples of the dimensions which are defined by the canonicals
	 * 
	 * @return myCoordinates a Map where you have the coordinates
	 * @param myCanonicals Array of occurances in each dimension/basis.
	 */
	public static Map<String,Integer> getCoordinates(String[] myCanonicals){
	    Map<String,Integer> myCoordinates = new HashMap<String,Integer>();
	    for(String myStr : myCanonicals){
	    	Integer myCount = 1;
	    	if(myCoordinates.containsKey(myStr)){
	    		myCount = myCoordinates.get(myStr);
	    		myCount++;
	    	}
	    	myCoordinates.put(myStr,myCount);
	    }
	    return myCoordinates;
	}
	/** Facilitator function
	 *    Objective is to reduce code later on.
	 */
	public static Map<String,Integer> getCoordinates(String myObj){
		return getCoordinates(toCanonicalForm(myObj));
	}
	/** Basis is a set of dimensions used to represent a vector in a vector space.
	 *	  It defines the axes of the vector space.
	 */
	public static Set<String> getBasis(Map<String,Integer> myIn){
		return myIn.keySet();
	}
	// @param myIn : Assuming that this is in Canonical Form
	public static Set<String> getBasis(String[] myIn){
		Set<String> myOut = new HashSet<String>();
		for(String myStr : myIn){
			myOut.add(myStr);
		}
		return myOut;
	}
	public static Set<String> getBasis(String myIn){
		return getBasis(toCanonicalForm(myIn));
	}
	/**
	 *
	 */
	public static Set<String> getUnionBasis(Set<String> myIn1, Set<String> myIn2){
		Set<String> myOut = new HashSet<String>();
		myOut.addAll(myIn1);
		myOut.addAll(myIn2);
		return myOut;
	}
	/**
	 *	  
	 * @param myIn : Coordinates of the object
	 * @
	 */
	//TODO : add check to see if the given Basis is a superset of the Coordinates
	public static int[] getVectorOfBasis(Map<String,Integer> myIn, Set<String> myBasis){
		Set<Integer> myOut = new LinkedHashSet<Integer>();
		Set<String> myOrderedBasis = new TreeSet<String>(myBasis);
		for(String myStr : myOrderedBasis){
			int myVal = 0;
			if(myIn.containsKey(myStr)){
				myVal = myIn.get(myStr);
			}
			myOut.add(myVal);
		}
		Integer[] intArr = new Integer[myOut.size()];
		intArr = myOut.toArray(intArr);
		int[] myInts = new int[myOut.size()];
		for(int i=0;i<intArr.length;i++){
			myInts[i]=intArr[i].intValue();
		}
		return myInts;
	}
}