/**
 *  CsdElement2 - This class defines an element that will be used for Comparison
 * 
 * @author      Pau Coma Ramriez 	<paucoma @ gmail.com>
 * @version     140429  	        (current version number of program)
 * @since       140329      	    (the version of the package this class was first added to)
 */

package com.paucoma.cossimdiff;

import com.paucoma.cossimdiff.CsdElement;
import java.util.List;
import java.util.ArrayList;

public class CsdElement2 extends CsdElement {

	public static void main(String[] args) {
        
        	List<CsdElement2> myElements = new ArrayList<CsdElement2>();
            for(String myStr : args){
                myElements.add(new CsdElement2(myStr));
            }
            for(int i=0;i<myElements.size();i++){
		       	CsdElement2 myElemA = myElements.get(i);
				for(int j=i+1;j<myElements.size();j++){
					CsdElement2 myElemB = myElements.get(j);
					System.out.println(myElemA.compareTo(myElemB));
				}
		    }
	}

	public CsdElement2(){
	}
	public CsdElement2(String myIn){
		this.set(myIn);
	}
	@Override
	protected String[] toCanonicalForm(String myObj){
		ArrayList<String> myRet = new ArrayList<String>();
		char[] myChars = myObj.toCharArray();
		for(int i=0; i<myChars.length-1; i=i+1){
			myRet.add(String.valueOf(myChars[i])+String.valueOf(myChars[i+1]));
		}
		String[] myStrArr = new String[myRet.size()];
		System.out.println(myRet);
	    return myRet.toArray(myStrArr);
	}
}