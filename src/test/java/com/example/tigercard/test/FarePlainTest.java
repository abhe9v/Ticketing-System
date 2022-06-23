package com.example.tigercard.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.tigercard.FareService;
import com.example.tigercard.beans.CardEntry;

public class FarePlainTest {

	// peak
	@Test
	public void testPeakHour() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src/test/resources/peak_hour.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(30, cardEntries.get(cardEntries.size() - 1).getFareCalc());
	}

	// non peak
	@Test
	public void testNonPeakHour() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src/test/resources/non_peak_hour.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(25, cardEntries.get(cardEntries.size() - 1).getFareCalc());
	}

}
