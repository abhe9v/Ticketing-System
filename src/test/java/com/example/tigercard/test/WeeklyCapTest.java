package com.example.tigercard.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.tigercard.FareService;
import com.example.tigercard.beans.CardEntry;

public class WeeklyCapTest {

	@Test
	public void testWeeklyCapSameZone11() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader
				.readInputEntries("src/test/resources/weekly_cap_same_zone_11.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(0, cardEntries.get(cardEntries.size() - 2).getFareCalc());
		Assert.assertEquals(30, cardEntries.get(cardEntries.size() - 1).getFareCalc());
	}

	@Test
	public void testWeeklyCapSameZone22() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader
				.readInputEntries("src/test/resources/weekly_cap_same_zone_22.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(0, cardEntries.get(cardEntries.size() - 2).getFareCalc());
		Assert.assertEquals(25, cardEntries.get(cardEntries.size() - 1).getFareCalc());
	}

	@Test
	public void testWeeklyCapMixZone() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src/test/resources/weekly_cap_mix_zone.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(0, cardEntries.get(cardEntries.size() - 3).getFareCalc());
		Assert.assertEquals(30, cardEntries.get(cardEntries.size() - 2).getFareCalc());
	}
}
