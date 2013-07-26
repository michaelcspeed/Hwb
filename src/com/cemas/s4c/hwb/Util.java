package com.cemas.s4c.hwb;


public class Util {

	private static int findMinutes(int currentTime) {
		return (int) Math.floor(currentTime / 60);
	}

	private static int findSeconds(int currentTime) {
		return currentTime % 60;
	}

    public static String getTimeFormatted(int currentTime) {
		return String.format("%02d", findMinutes(currentTime)) + ":"
				+ String.format("%02d", findSeconds(currentTime));
	}
}
