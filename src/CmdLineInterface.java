/**
 *  CmdLineInterface - This class provides the Command Line usage capabilities to the package
 * 
 * @author      Pau Coma Ramriez 	<paucoma @ gmail.com>
 * @version     140430  	        (current version number of program)
 * @since       140430      	    (the version of the package this class was first added to)
 */

package com.paucoma.cossimdiff;

import com.paucoma.cossimdiff.CsdElement;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
import java.util.Scanner;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.cli.ParseException;

import java.util.Arrays;

import java.util.Map;
import java.util.HashMap;

import java.util.SortedSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Collections;

public class CmdLineInterface {
	private String _baseStr = null;
	private List<String> _compareStrs = new ArrayList<String>();
	private String _outFilePath = null;

	// Command line options container object
	private static Options _options = null;
	// Specific CLI options defined
	private static final Option BASE_STRING =  new Option(
		"b","base",true,
		"base string which is compared to all other inputs");
	private static final Option INPUT_FILE_LIST =  new Option(
		"f","input-file",true,
		"text file containing strings to compare against");
	private static final Option OUTPUT_FILE_OPTION = new Option(
		"o","output-file",true,
		"write output to csv file instead of stdout");

	private CommandLine _cmd = null; // Command Line arguments
	
	static{
		_options = new Options();
		_options.addOption(BASE_STRING);
		_options.addOption(INPUT_FILE_LIST);
		_options.addOption(OUTPUT_FILE_OPTION);		
	}

	public static void main(String[] args) {
		CmdLineInterface myObj = new CmdLineInterface(args);
	}
	//We use a map to remove the duplicates
    private Map<String,Double> getComparison(){
    	Map<String,Double> myRet = new HashMap<String,Double>();
    	CsdElement myBase = new CsdElement(_baseStr);
    	//List<CsdElement> myElements = new ArrayList<CsdElement>();
        for(String myStr : _compareStrs){
            //myElements.add(new CsdElement(myStr));
            CsdElement myCompare = new CsdElement(myStr);
            myRet.put(myStr,myBase.compareTo(myCompare));
        }
    	return myRet;
    }
    private List<Map.Entry<String, Double>> getKeySortedByValue(Map<String,Double> myMap){
		List<Map.Entry<String, Double>> entryList = 
			new ArrayList<Map.Entry<String, Double>>(myMap.entrySet());
	    Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {
	        @Override
	        public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
	            return o1.getValue().compareTo(o2.getValue());
	        }
	    });
	    return entryList;
	}
	public List<String> getFileInput(String myFilePath){
		List<String> myList = new ArrayList<String>();
		try{
			Scanner s = new Scanner(new File(myFilePath));
			while (s.hasNextLine()){
		    	myList.add(s.nextLine());
			}
			s.close();
		}catch(FileNotFoundException e){
			System.out.println("Input file not found: double check path");
			e.printStackTrace();
			System.exit(1);
		}
		return myList;
	}
	/**
	 * Validate and set command line arguments.
	 * Exit after printing usage if anything is astray
	 * @param args String[] args as featured in public static void main()
	 */
	private void loadArgs(String[] args){
		//System.out.println(Arrays.asList(args));
		CommandLineParser myParser = new PosixParser();
		try{
			_cmd = myParser.parse(_options, args);
		}catch(ParseException e){
			System.err.println("Error parsing arguments");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Check for mandatory arguments
		if(!_cmd.hasOption(BASE_STRING.getOpt())){
			HelpFormatter myFormatter = new HelpFormatter();
			myFormatter.printHelp(
				"java -jar cosSimDiff.jar "
				+"[options][<compare-strings>]"
				,"Mandatory option is -"+BASE_STRING.getOpt()+",--"+BASE_STRING.getLongOpt()+" <arg>"
				, _options,"");
			System.exit(1);
		}else{
			_baseStr = _cmd.getOptionValue(BASE_STRING.getOpt());
		}
		if (_cmd.hasOption(INPUT_FILE_LIST.getOpt())){
			//System.out.println(_cmd.getOptionValue(INPUT_FILE_LIST.getOpt()));
			_compareStrs.addAll(
				getFileInput(_cmd.getOptionValue(
					INPUT_FILE_LIST.getOpt())));
		}
		if (_cmd.hasOption(OUTPUT_FILE_OPTION.getOpt())){
			_outFilePath = _cmd.getOptionValue(
						OUTPUT_FILE_OPTION.getOpt());
			try{
				File tmpFile = new File(
					_outFilePath);
				if(tmpFile.exists())
					System.out.println(
						"\t!Warning: "+_outFilePath+" will be overwritten");
			}catch(NullPointerException e){

			}
		}
		for(String myStr : _cmd.getArgs()){
			_compareStrs.add(myStr);
		}
	}
	public CmdLineInterface(String[] args){
		this.loadArgs(args);
		Map<String,Double> nonSortedMap = getComparison();
		for (Entry<String,Double> entry : getKeySortedByValue(nonSortedMap)){
			System.out.println(entry.getValue()+"\t"+entry.getKey());	
		}
		//System.out.println(getComparison());
	}
}