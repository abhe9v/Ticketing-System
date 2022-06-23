package com.example.tigercard.beans;

public class ZoneFareAndCap {
	private int fromZone;
	private int toZone;
	private int peakFare;
	private int nonPeakFare;
	private int dailyCap;
	private int weeklyCap;

	public ZoneFareAndCap(int fromZone, int toZone, int peakFare, int nonPeakFare, int dailyCap, int weeklyCap) {
		super();
		this.fromZone = fromZone;
		this.toZone = toZone;
		this.peakFare = peakFare;
		this.nonPeakFare = nonPeakFare;
		this.setDailyCap(dailyCap);
		this.setWeeklyCap(weeklyCap);
	}

	public int getFromZone() {
		return fromZone;
	}

	public void setFromZone(int fromZone) {
		this.fromZone = fromZone;
	}

	public int getToZone() {
		return toZone;
	}

	public void setToZone(int toZone) {
		this.toZone = toZone;
	}

	public int getPeakFare() {
		return peakFare;
	}

	public void setPeakFare(int peakFare) {
		this.peakFare = peakFare;
	}

	public int getNonPeakFare() {
		return nonPeakFare;
	}

	public void setNonPeakFare(int nonPeakFare) {
		this.nonPeakFare = nonPeakFare;
	}

	public int getDailyCap() {
		return dailyCap;
	}

	public void setDailyCap(int dailyCap) {
		this.dailyCap = dailyCap;
	}

	public int getWeeklyCap() {
		return weeklyCap;
	}

	public void setWeeklyCap(int weeklyCap) {
		this.weeklyCap = weeklyCap;
	}

	@Override
	public String toString() {
		return String.format("%d\t%d\t%d\t%d\t%d\t%d", fromZone, toZone, peakFare, nonPeakFare, dailyCap, weeklyCap);
	}
}
