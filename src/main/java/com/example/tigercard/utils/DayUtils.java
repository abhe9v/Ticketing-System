package com.example.tigercard.utils;

import com.example.tigercard.beans.Constants;

public class DayUtils {
	public static int dayNumber(String day) {
		switch (day) {
		case Constants.MONDAY:
			return 1;
		case Constants.TUESDAY:
			return 2;
		case Constants.WEDNESDAY:
			return 3;
		case Constants.THURSDAY:
			return 4;
		case Constants.FRIDAY:
			return 5;
		case Constants.SATURDAY:
			return 6;
		case Constants.SUNDAY:
			return 7;
		default:
			return -1;
		}
	}
}
