
package com.example.tigercard;

import java.util.List;

import com.example.tigercard.beans.CardEntry;
import com.example.tigercard.beans.Constants;
import com.example.tigercard.beans.PeakHourSetting;
import com.example.tigercard.beans.ZoneFareAndCap;
import com.example.tigercard.utils.DayUtils;

public class FareService {

	private FareConfig fareConfig;
	private int dailyTotal = 0;
	private int weeklyTotal = 0;
	private String prevDay = null;
	private boolean mixZoneInDay = false;
	private boolean mixZoneInWeek = false;

	/**
	 * Initialize configurations viz fare, cap, peak hours etc.
	 * 
	 * @param peakHourSettingsFile
	 * @param zoneFareAndCapFile
	 */
	public void initFareConfigs(String peakHourSettingsFile, String zoneFareAndCapFile) {
		fareConfig = new FareConfig();
		fareConfig.loadPeakHourTimings(peakHourSettingsFile);
		fareConfig.loadZoneFare(zoneFareAndCapFile);
	}

	/**
	 * Calculate fare per swipe using configurations. Processed Entry has details
	 * about which rules were applied while deciding fare.
	 * 
	 * @param entry Entry to process
	 * @return calculated fare
	 */
	public int process(CardEntry entry) {

		if (prevDay == null) {
			entry.setInfo("First entry");
		} else if (!entry.getDay().equals(prevDay)) {
			mixZoneInDay = false;
			dailyTotal = 0;
			entry.setInfo("New day");

			if (DayUtils.dayNumber(prevDay) > DayUtils.dayNumber(entry.getDay())) { // @TODO need to check actual
																					// week once we have timestamps
				mixZoneInWeek = false;
				weeklyTotal = 0;
				entry.setInfo("New week");
			}
		}

		if (entry.getFromZone() != entry.getToZone()) {
			mixZoneInDay = true;
			mixZoneInWeek = true;
		}

		prevDay = entry.getDay();

		processEntry(entry, mixZoneInDay, mixZoneInWeek);
		System.out.println(entry);
		System.out
				.println("------------------------------------------------------------------------------------------");
		return entry.getFareCalc();
	}

	private void processEntry(CardEntry entry, Boolean mixZoneInDay, Boolean mixZoneInWeek) {

		int plainfare = calculatePlainFare(entry);

		int fareAfterDailyCap = applyDailyCap(entry, plainfare, mixZoneInDay);

		int fareAfterWeeklyCap = applyWeeklyCap(entry, fareAfterDailyCap, mixZoneInWeek);

		if (fareAfterDailyCap != fareAfterWeeklyCap) {
			dailyTotal += fareAfterWeeklyCap;
			weeklyTotal += fareAfterWeeklyCap;
			entry.setInfo("Weekly cap applied. (Totals: D " + dailyTotal + ", W " + weeklyTotal + ")");
			entry.setFareCalc(fareAfterWeeklyCap);
		} else if (fareAfterDailyCap != plainfare) {
			dailyTotal += fareAfterDailyCap;
			weeklyTotal += fareAfterDailyCap;
			entry.setInfo("Daily cap applied. (Totals: D " + dailyTotal + ", W " + weeklyTotal + ")");
			entry.setFareCalc(fareAfterDailyCap);
		} else {
			entry.setFareCalc(plainfare);
			dailyTotal += plainfare;
			weeklyTotal += plainfare;
			if (entry.getInfo() == null) {
				entry.setInfo("(Totals: D " + dailyTotal + ", W " + weeklyTotal + ")");
			} else {
				entry.setInfo(entry.getInfo() + ". (Totals: D " + dailyTotal + ", W " + weeklyTotal + ")");
			}
		}

	}

	private int applyDailyCap(CardEntry entry, int plainfare, Boolean mixZoneInDay) {
		String key = entry.getFromZone() + "-" + entry.getToZone();
		int dailyCap = fareConfig.getFareMap().get(key).getDailyCap();

		if (mixZoneInDay) {
			dailyCap = fareConfig.getFareMap().get(Constants.MIX_ZONE_KEY).getDailyCap();
		}

		int cappedTotal = dailyTotal + plainfare;
		if (cappedTotal > dailyCap) {
			cappedTotal = dailyCap - dailyTotal;
		} else {
			cappedTotal = plainfare;
		}

		return cappedTotal;
	}

	private int applyWeeklyCap(CardEntry entry, int fareAfterDailyCap, Boolean mixZoneInWeek) {
		String key = entry.getFromZone() + "-" + entry.getToZone();
		int weeklyCap = fareConfig.getFareMap().get(key).getWeeklyCap();

		if (mixZoneInWeek) {
			weeklyCap = fareConfig.getFareMap().get(Constants.MIX_ZONE_KEY).getWeeklyCap();
		}

		int cappedTotal = weeklyTotal + fareAfterDailyCap;
		if (cappedTotal > weeklyCap) {
			cappedTotal = weeklyCap - weeklyTotal;
		} else {
			cappedTotal = fareAfterDailyCap;
		}

		return cappedTotal;
	}

	private int calculatePlainFare(CardEntry entry) {
		String key = entry.getFromZone() + "-" + entry.getToZone();
		ZoneFareAndCap fare = fareConfig.getFareMap().get(key);
		int plainfare = fare.getNonPeakFare();

		if (isPeakHourEntry(entry)) {
			plainfare = fare.getPeakFare();
			entry.setInfo("Peak time");
		} else {
			entry.setInfo("Non peak time");
		}

		return plainfare;
	}

	private boolean isPeakHourEntry(CardEntry entry) {
		String key = entry.getFromZone() + "-" + entry.getToZone();
		List<PeakHourSetting> dayPeakHours = fareConfig.getPeakHourSettings().get(entry.getDay());

		for (PeakHourSetting ps : dayPeakHours) {
			if (ps.isPeakHourEntry(entry)) {
				return true;
			}
		}

		return false;
	}

}
