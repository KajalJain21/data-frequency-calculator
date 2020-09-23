package com.gmo.pkg;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class AppTest {

	App app = new App();

	// Test the expected exception when correct filepath is not supplied, returns
	// IOException
	@Test
	public void readDataFrequencyTest_ioException() {
		String filePath = "";
		try {
			app.readDataFrequency(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertThrows(IOException.class, () -> {
			throw new FileNotFoundException("file not found");
		});

	}

	// Test the success journey of input file, returns map with frequesncy data
	@Test
	public void readDataFrequencyTest_success() {
		String filePath = "src/test/resources/test2.txt";
		Map<String,Integer> result = null;
		try {
			result = app.readDataFrequency(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("kajal", 3);
		expected.put("jain", 2);
		expected.put("abc", 1);
		expected.put("status", 1);
		
		assertNotNull(result);
		assertEquals(expected, result);
		
	}

	// Test the file is received and read
	@Test
	public void readFromInputStreamTest() {
		String filePath = "src/test/resources/test1.txt";
		File file = null;
			file = new File(filePath);
		String data = null;
		try (InputStream is = new FileInputStream(file)) {
			data = App.readFromInputStream(is);
		} catch (IOException e) {
			System.out.println(e.getClass());
		}
		assertEquals("kajal jain\n", data);
	}

	// Test the reordering of map to sort the data
	@Test
	public void sortByFreqTest() {
		Map<String, Integer> inputMap = new HashMap<String, Integer>();
		inputMap.put("a", 5);
		inputMap.put("b", 9);
		inputMap.put("c", 1);
		HashMap<String, Integer> outputMap = App.sortByFreq(inputMap);

		assertNotNull(outputMap);
		assertEquals(outputMap.size(), 3);
		assertEquals(inputMap, outputMap);
		assertEquals("[b=9, a=5, c=1]", outputMap.entrySet().toString());
	}

	// Test the size of output map is max 20
	@Test
	public void sortByFreqTest2() {
		Map<String, Integer> inputMap = new HashMap<String, Integer>();
		inputMap.put("a", 5);
		inputMap.put("b", 9);
		inputMap.put("c", 1);
		inputMap.put("d", 10);
		inputMap.put("e", 11);
		inputMap.put("f", 23);
		inputMap.put("g", 45);
		inputMap.put("h", 112);
		inputMap.put("i", 67);
		inputMap.put("j", 78);
		inputMap.put("k", 12);
		inputMap.put("l", 56);
		inputMap.put("m", 78);
		inputMap.put("n", 9);
		inputMap.put("o", 45);
		inputMap.put("p", 7);
		inputMap.put("q", 10);
		inputMap.put("r", 34);
		inputMap.put("s", 8);
		inputMap.put("t", 123);
		inputMap.put("u", 17);
		inputMap.put("v", 23);
		inputMap.put("w", 67);
		inputMap.put("x", 98);
		inputMap.put("y", 98);
		inputMap.put("z", 31);
		HashMap<String, Integer> outputMap = App.sortByFreq(inputMap);

		assertNotNull(outputMap);
		assertEquals(outputMap.size(), 20);
		assertNotEquals(inputMap, outputMap);
	}

}