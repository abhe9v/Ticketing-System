package com.example.tigercard.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import com.example.tigercard.beans.CardEntry;

public class CardInputEntriesReader {

	/**
	 * Read input entries
	 * 
	 * @param entriesFile
	 * @return
	 */
	public static List<CardEntry> readInputEntries(String entriesFile) {
		String line = "";
		String splitBy = ",";
		List<CardEntry> cardEntries = new LinkedList<CardEntry>();
		boolean headerSkipped = false;
		try {

			BufferedReader br = new BufferedReader(new FileReader(entriesFile));
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(splitBy);
				if (!headerSkipped) {
					headerSkipped = true;
					continue;
				}
				cardEntries.add(new CardEntry(cols[0], Time.valueOf(cols[1]), Integer.valueOf(cols[2]),
						Integer.valueOf(cols[3]), 0, null));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return cardEntries;
	}

}
