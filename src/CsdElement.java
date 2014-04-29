/**
 *  CsdElement - This class defines an element that will be used for Comparison
 * 
 * @author      Pau Coma Ramriez 	<paucoma @ gmail.com>
 * @version     140429  	        (current version number of program)
 * @since       140329      	    (the version of the package this class was first added to)
 */

package com.paucoma.cossimdiff;

import com.paucoma.cossimdiff.String_csd;
import com.paucoma.cossimdiff.Math_csd;
import java.util.Set;
import java.util.Map;

import java.util.HashSet;
import java.util.Iterator;

public class CsdElement {

	private String m_Object;
	public Set<String> _Basis = null;
	private String[] m_Canonical;
	public Map<String,Integer> _Coordinates = null;
	public double _magnitude;

	public static void main(String[] args) {
        
        	Set<CsdElement> myElements = new HashSet<CsdElement>();
            for(String myStr : args){
                myElements.add(new CsdElement(myStr));
            }
            /*
            for(int i=0;i<myElements.size();i++){
            	CsdElement myElem = myElements.get()){
            	System.out.println(myElem);
            	for(CsdElement myOther : myElements){
            		System.out.println(myElem.compareTo(myOther));
            	}
            }*/
            Iterator<CsdElement> itA = myElements.iterator();
            Iterator<CsdElement> itB;
            while(itA.hasNext()){
		       	CsdElement myElemA = itA.next();
				itB = myElements.iterator();
				while(itB.hasNext()){
					CsdElement myElemB = itB.next();
					System.out.println(myElemA.compareTo(myElemB));
				}
		    }
        //}

/*
    	CsdElement myA = new CsdElement("Pau");
    	CsdElement myB = new CsdElement("Paola");
    	System.out.println(myA);
    	System.out.println(myB);
    	System.out.println("A->B = "+myA.compareTo(myB));
    	System.out.println("B->A = "+myB.compareTo(myA));*/
	}

	public void set(String myIn){
		m_Object = myIn;
		this.precalculate();
	}
	//Pre: that m_object!=null
	//Post: performs the calculations that can be done before comparing.
	private void precalculate(){
		m_Canonical = String_csd.toCanonicalForm(m_Object);
		_Basis = String_csd.getBasis(m_Canonical);
		_Coordinates = String_csd.getCoordinates(m_Canonical);
		_magnitude = Math_csd.magnitude(String_csd.getVectorOfBasis(_Coordinates,_Basis));
	}
	//Pre: Other CSD element needs to be precalculated
	public double compareTo(CsdElement myOther){
		double myResult = 0;
		Set<String> myUnionB = String_csd.getUnionBasis(this._Basis, myOther._Basis);
		int[] myVector = String_csd.getVectorOfBasis(this._Coordinates, myUnionB);
		int[] otherVector = String_csd.getVectorOfBasis(myOther._Coordinates, myUnionB);
		int myDotProd = Math_csd.dotProduct(myVector, otherVector);
		myResult = myDotProd/(this._magnitude * myOther._magnitude);
		return myResult;
	}
	public CsdElement(){
	}
	public CsdElement(String myIn){
		this.set(myIn);
	}
	@Override
	public String toString() {
		String result = "\n["+m_Object+":";
		result += "\n\t|-Basis    :"+_Basis;
		result += "\n\t`-Magnitude:"+_magnitude;
		return result;
	}
}