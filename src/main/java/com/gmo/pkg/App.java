package com.gmo.pkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{

	public static void main(String[] args) {
		// read the input file
		String filePath;
		System.out.print("Enter absolute path of file: ");
		try (Scanner scanner = new Scanner(System.in)) {
			filePath = scanner.nextLine().trim();
		}
		HashMap<String, Integer> resultingMap = null;
		
		try {
			resultingMap = new App().readDataFrequency(filePath);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		resultingMap.forEach((key, value) -> System.out.println(value + " " + key));
	}
	
	public HashMap<String, Integer> readDataFrequency(String filePath) throws IOException{
		
		File file = new File(filePath);
	    String data = null;
		try ( InputStream is = new FileInputStream(file)){
			data = readFromInputStream(is);
		} catch (IOException e) {
			System.out.println(e.getClass());
			throw new IOException("Exception caught in reading the file");
		}
		
		// convert the string to adequate data string with no special characters and store in a list
		String words = data.replaceAll("[^a-zA-Z]", ",");
		words = words.replaceAll("(,)\\1+",",");
		List<String> dataList = Stream.of(words.split(",")).collect(Collectors.toList());
		
		//Calculate frequency of each word and store in a map
		HashMap<String, Integer> freqMap = new HashMap<String, Integer>();
		for (String word : dataList) {
			word = word.toLowerCase();
			if (freqMap.containsKey(word)) {
				Integer value = freqMap.get(word);
				freqMap.put(word, ++value);
			} else {
				freqMap.put(word, 1);
			}
		}
		
		//Method call to sort the map wrt frequency
		HashMap<String, Integer> sortedMap = sortByFreq(freqMap);
		return sortedMap;
		
	}
	

	// method to read data from the text file
	protected static String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		}
		return sb.toString();
	}
	
	//method to sort the hashmap by values
	protected static HashMap<String, Integer> sortByFreq(Map<String, Integer> wordCounts) {

	        // Create a list from elements of HashMap 
	        List<Map.Entry<String, Integer> > list = 
	               new ArrayList<Map.Entry<String, Integer> >(wordCounts.entrySet()); 
	  
	        // Sort the list 
	        Collections.sort(list, new Comparator<Entry<String, Integer>>() {

				@Override
				public int compare(Map.Entry<String, Integer> e1,  
                        Map.Entry<String, Integer> e2)  {
					return (e2.getValue()).compareTo(e1.getValue()); 
				}
			});
	        
	        list = list.stream().limit(20).collect(Collectors.toList());
	          
	        // put data from sorted list to hashmap  
	        HashMap<String, Integer> tempMap = new LinkedHashMap<String, Integer>(); 
	        for (Map.Entry<String, Integer> entry : list) { 
	        	tempMap.put(entry.getKey(), entry.getValue()); 
	        } 
	        return tempMap; 
	    }
}
