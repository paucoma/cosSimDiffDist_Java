/**
 *  Math_csd - This class gathers static generic functions to be used for calculations.
 * 
 * @author      Pau Coma Ramriez 	<paucoma @ gmail.com>
 * @version     140429  	        (current version number of program)
 * @since       140329      	    (the version of the package this class was first added to)
 */

package com.paucoma.cossimdiff;

//import java.text.NumberFormat;
//import java.util.Locale;
import java.lang.Math;

public class Math_csd {

	public static void main(String[] args) {
    	//adiqFormulas myFormulas = new adiqFormulas();
	}
	public static double dotProduct(double[] a, double[] b){
		if(a.length != b.length){
			throw new IllegalArgumentException("The dimensions have to be equal!");
		}
		double sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i] * b[i];
		}
		return sum;
	}
	public static int dotProduct(int[] a, int[] b){
		if(a.length != b.length){
			throw new IllegalArgumentException("The dimensions have to be equal!");
		}
		int sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i] * b[i];
		}
		return sum;
	}
	public static Integer dotProduct(Integer[] a, Integer[] b){
		if(a.length != b.length){
			throw new IllegalArgumentException("The dimensions have to be equal!");
		}
		Integer sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i] * b[i];
		}
		return sum;
	}
	public static Double magnitude(Integer[] myIn){
		Integer mySum = 0;
		for(Integer a : myIn){
			mySum += a*a;
		}
		return Math.sqrt(mySum);
	}
	public static double magnitude(int[] myIn){
		int mySum = 0;
		for(int a : myIn){
			mySum += a*a;
		}
		return Math.sqrt(mySum);
	}
	public static double magnitude(double[] myIn){
		double mySum = 0;
		for(double a : myIn){
			mySum += a*a;
		}
		return Math.sqrt(mySum);
	}

}