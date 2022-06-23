package com.example.tigercard;

import java.util.List;

import com.example.tigercard.beans.CardEntry;
import com.example.tigercard.dao.CardInputEntriesReader;

public class FareApp {

	// NOTE : Below is one example of input entries processing.
	// More examples are added in junit test package

	public static void main(String[] args) {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");

		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src\\test\\resources\\all_mix.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}
	}

}
