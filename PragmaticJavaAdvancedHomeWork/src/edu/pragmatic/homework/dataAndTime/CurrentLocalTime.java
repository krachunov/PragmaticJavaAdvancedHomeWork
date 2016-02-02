package edu.pragmatic.homework.dataAndTime;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class CurrentLocalTime {
	public static void main(String[] args) {

		printAllTime();
		// System.out.println(convertirAFecha("10:58+0900"));
	}

	private static void printAllTime() {
		Set<String> allZones = new TreeSet<String>(ZoneId.getAvailableZoneIds());

		LocalDateTime dt = LocalDateTime.now();

		for (String currentZone : allZones) {
			ZoneId zone = ZoneId.of(currentZone);
			ZonedDateTime zdt = dt.atZone(zone);
			ZoneOffset offSet = zdt.getOffset();

			LocalTime nowInUtc = LocalTime.now(offSet);

			System.out.printf(String.format("%35s  %10s %15s%n", zone, offSet,
					nowInUtc));
		}
	}

	public static ZonedDateTime convertirAFecha(String fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
		LocalDate date = LocalDate.parse(fecha, formatter);

		ZonedDateTime resultado = date.atStartOfDay(ZoneId.systemDefault());
		return resultado;
	}
}
