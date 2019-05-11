package com.ymu.javase.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 带时区时间。
 * https://docs.oracle.com/javase/tutorial/datetime/iso/timezones.html
 * @author xinxiamu
 *
 */
public class ZonedDateTimeDemo {
	
	public static void main(String[] args) {
		
		//当前美国时间
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/New_York"));
		System.out.println(">>>>:" + zonedDateTime.toString());
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String str = formatter2.format(zonedDateTime);
		System.out.println("=====:" + str);
		
		//显示各个时区时间
		Set<String> allZones = ZoneId.getAvailableZoneIds();
		LocalDateTime dt = LocalDateTime.now();

		// Create a List using the set of zones and sort it.
		List<String> zoneList = new ArrayList<String>(allZones);
		Collections.sort(zoneList);

		for (String s : zoneList) {
		    ZoneId zone = ZoneId.of(s);
		    ZonedDateTime zdt = dt.atZone(zone);
		    ZoneOffset offset = zdt.getOffset();
		    int secondsOfHour = offset.getTotalSeconds() % (60 * 60);
		    String out = String.format("%35s %10s%n", zone, offset);

		    // Write only time zones that do not have a whole hour offset
		    // to standard out.
		    if (secondsOfHour != 0) {
		        System.out.printf(out);
		    }
		
		}
	}
}
