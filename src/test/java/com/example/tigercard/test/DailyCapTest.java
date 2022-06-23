package com.example.tigercard.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.example.tigercard.FareService;
import com.example.tigercard.beans.CardEntry;

public class DailyCapTest {

	@Test
	public void testDailyCapSameZone11() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src/test/resources/daily_cap_same_zone_11.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(0, cardEntries.get(cardEntries.size() - 1).getFareCalc());
	}

	@Test
	public void testDailyCapSameZone22() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src/test/resources/daily_cap_same_zone_22.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(15, cardEntries.get(cardEntries.size() - 2).getFareCalc());
	}

	@Test
	public void testDailyCapMixZone() {
		FareService fareService = new FareService();
		fareService.initFareConfigs("src/main/resources/PeakHourSettings.json",
				"src/main/resources/ZoneFareAndCapSettings.json");
		List<CardEntry> cardEntries = CardInputEntriesReader.readInputEntries("src/test/resources/daily_cap_mix_zone.csv");

		for (CardEntry entry : cardEntries) {
			fareService.process(entry);
		}

		Assert.assertEquals(5, cardEntries.get(cardEntries.size() - 1).getFareCalc());
	}

}
