/**
 * 
 */
package com.rankfps.source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.conference.bean.Event;

/**
 * @author Felipe
 *
 */
public class MainClass {
	
	private static String INPUT_PATH = "C:/Users/Felipe/Documents/input.txt";
	private static String OUTPUT_PATH = "C:/Users/Felipe/Documents/output.txt";
	private static String LINE_SEPARATOR = "line.separator";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static void writeToOutputFile(String result) {
		File file = new File(OUTPUT_PATH);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(result);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Event> readInputFile() {
		List<Event> conferenceEvents = new ArrayList<Event>();
		try {
			FileReader file = new FileReader(INPUT_PATH);
			BufferedReader reader = new BufferedReader(file);
			
			Event event = null;
			String line = reader.readLine();
			event = stringToBean(line);
			if(event != null) {
				conferenceEvents.add(event);
			}
			while(line != null) {
				line = reader.readLine();
				event = stringToBean(line);
				if(event != null) {
					conferenceEvents.add(event);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conferenceEvents;
	}
	
	private static Event stringToBean(String line) {
		if(line != null) {
			String time = "";
			char[] letters = line.toCharArray();
			int index = 0;
			for(int i=0; i < letters.length; i++) {
				if(Character.isDigit(letters[i])) {
					time += letters[i];
					if(index == 0) {
						index = i;
					}
				}
			}
			
			Event event = null;
			if(index == 0) {
				event = new Event(line, DEFAULT_TIME);
			} else {
				event = new Event(line.substring(0, index), Integer.parseInt(time));
			}
			
			return event;
		}
		return null;
	}

}
