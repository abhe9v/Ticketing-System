package com.example.tigercard;

import java.io.FileReader;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.tigercard.beans.Constants;
import com.example.tigercard.beans.PeakHourSetting;
import com.example.tigercard.beans.ZoneFareAndCap;

public class FareConfig {

	Map<String, List<PeakHourSetting>> peakHourSettings = new HashMap<String, List<PeakHourSetting>>();
	Map<String, ZoneFareAndCap> fareMap = new HashMap<String, ZoneFareAndCap>();

	public void loadZoneFare(String zoneFareSettingsFile) {

		JSONParser jparser = new JSONParser();

		try (FileReader reader = new FileReader(zoneFareSettingsFile)) {
			JSONObject obj = (JSONObject) jparser.parse(reader);

			// can be looped on keys as well
			JSONObject obj11 = (JSONObject) obj.get(Constants.ZONE_1_1);
			Long peak11 = (Long) obj11.get(Constants.PEAK);
			Long offpeak11 = (Long) obj11.get(Constants.OFF_PEAK);
			Long daily11 = (Long) obj11.get(Constants.DAILY_CAP);
			Long weekly11 = (Long) obj11.get(Constants.WEEKLY_CAP);
			fareMap.put(Constants.ZONE_1_1, new ZoneFareAndCap(1, 1, peak11.intValue(), offpeak11.intValue(),
					daily11.intValue(), weekly11.intValue()));

			JSONObject obj12 = (JSONObject) obj.get(Constants.ZONE_1_2);
			Long peak12 = (Long) obj12.get(Constants.PEAK);
			Long offpeak12 = (Long) obj12.get(Constants.OFF_PEAK);
			Long daily12 = (Long) obj12.get(Constants.DAILY_CAP);
			Long weekly12 = (Long) obj12.get(Constants.WEEKLY_CAP);
			fareMap.put(Constants.ZONE_1_2, new ZoneFareAndCap(1, 2, peak12.intValue(), offpeak12.intValue(),
					daily12.intValue(), weekly12.intValue()));

			JSONObject obj21 = (JSONObject) obj.get(Constants.ZONE_2_1);
			Long peak21 = (Long) obj21.get(Constants.PEAK);
			Long offpeak21 = (Long) obj21.get(Constants.OFF_PEAK);
			Long daily21 = (Long) obj21.get(Constants.DAILY_CAP);
			Long weekly21 = (Long) obj21.get(Constants.WEEKLY_CAP);
			fareMap.put(Constants.ZONE_2_1, new ZoneFareAndCap(2, 1, peak21.intValue(), offpeak21.intValue(),
					daily21.intValue(), weekly21.intValue()));

			JSONObject obj22 = (JSONObject) obj.get(Constants.ZONE_2_2);
			Long peak22 = (Long) obj22.get(Constants.PEAK);
			Long offpeak22 = (Long) obj22.get(Constants.OFF_PEAK);
			Long daily22 = (Long) obj22.get(Constants.DAILY_CAP);
			Long weekly22 = (Long) obj22.get(Constants.WEEKLY_CAP);
			fareMap.put(Constants.ZONE_2_2, new ZoneFareAndCap(2, 2, peak22.intValue(), offpeak22.intValue(),
					daily22.intValue(), weekly22.intValue()));

		} catch (Exception e) {
			// logger can be added
			// exception hierarchy can be created
			System.out.println(e);
		}

	}

	public void loadPeakHourTimings(String fareAndCapFile) {

		JSONParser jparser = new JSONParser();

		try (FileReader reader = new FileReader(fareAndCapFile)) {
			JSONObject obj = (JSONObject) jparser.parse(reader);

			for (Iterator dayIterator = obj.keySet().iterator(); dayIterator.hasNext();) {
				String day = (String) dayIterator.next();
				JSONArray peakHourArray = (JSONArray) obj.get(day);
				Iterator<JSONObject> peakhour = peakHourArray.iterator();
				List<PeakHourSetting> dayPeakHourSettings = new LinkedList<PeakHourSetting>();
				while (peakhour.hasNext()) {
					JSONObject peakTimeset = peakhour.next();
					PeakHourSetting ps = new PeakHourSetting(day,
							Time.valueOf((String) peakTimeset.get(Constants.FROM)),
							Time.valueOf((String) peakTimeset.get(Constants.TO)));
					dayPeakHourSettings.add(ps);
				}

				peakHourSettings.put(day, dayPeakHourSettings);
			}

		} catch (Exception e) {
			// logger can be added
			// exception hierarchy can be created
			System.out.println(e);
		}

	}

	public Map<String, List<PeakHourSetting>> getPeakHourSettings() {
		return peakHourSettings;
	}

	public void setPeakHourSettings(Map<String, List<PeakHourSetting>> peakHourSettings) {
		this.peakHourSettings = peakHourSettings;
	}

	public Map<String, ZoneFareAndCap> getFareMap() {
		return fareMap;
	}

	public void setFareMap(Map<String, ZoneFareAndCap> fareMap) {
		this.fareMap = fareMap;
	}

}
