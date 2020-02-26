package algorithms.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateConverter {

	public static void testDates() {
		ZoneId zoneId = ZoneId.of("Africa/Tunis");
		LocalDate today = LocalDate.now(zoneId);

		ZonedDateTime zdtStart = today.atStartOfDay(zoneId);
		ZonedDateTime zdtStop = today.plusDays(1).atStartOfDay(zoneId);
		
		// zdtStart.toString() = 2020-01-30T00:00+01:00[Africa/Tunis] 
		// zdtStop.toString()  = 2020-01-31T00:00+01:00[Africa/Tunis] 

		//See the same moments in UTC.
		Instant start = zdtStart.toInstant() ;
		Instant stop = zdtStop.toInstant() ;
		
		//start.toString() = 2020-01-29T23:00:00Z
		//stop.toString() = 2020-01-30T23:00:00Z
		
		// Calendar to Instant
	      Instant time = Calendar.getInstance().toInstant();
	      System.out.println(time);

	}

	public Date getStartOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 0, 0, 0);
		return calendar.getTime();
	}

	public Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}

	public static Date atStartOfDay(Date date) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
		return localDateTimeToDate(startOfDay);
	}

	public static Date atEndOfDay(Date date) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
		return localDateTimeToDate(endOfDay);
	}

	private static LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}
