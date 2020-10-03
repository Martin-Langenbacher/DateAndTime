package de.telekom.datetime;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


import java.time.format.FormatStyle;


import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.format.DateTimeFormatter.*;
import static java.time.format.FormatStyle.SHORT;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class dateAndTime {

	public static void main(String[] args) throws InterruptedException, ParseException {
		
		final DayOfWeek sunday = DayOfWeek.SUNDAY;
		final Month february = Month.FEBRUARY;
		
		final DayOfWeek friday = sunday.plus(5);
		final Month december = february.plus(10);
		
		System.out.println("1) Start: Day & Time: ");
		System.out.println(friday);
		System.out.println(december);
		System.out.println("***=====================================================***");
		
		
		// Class: --2-- MonthDay, YearMonth and Year
		System.out.println();
		System.out.println("2) MonthDay, YearMonth and Year: ");
		
		// ISO-Format: Month, then Day
		final MonthDay february7th = MonthDay.of(Month.FEBRUARY, 7);
		final YearMonth february2020 = YearMonth.of(2020,  Month.FEBRUARY);
		
		final Year year = Year.of(2017);
		final boolean isLeapYear = year.isLeap();
		
		System.out.println("MonthDay:   " + february7th);
		System.out.println("YearMonth:  " + february2020);
		System.out.println("Year:       " + year + " / isLeap? " + isLeapYear);
		
		System.out.println("***=====================================================***");
		
		
		
		// Class: --3-- Instant
		System.out.println();
		System.out.println("3) Instant: ");
		
		final Instant now = Instant.now();
		
		final Instant departureTime = Instant.parse("2016-03-07T12:30:00Z");
		final Instant expectedArrivalTime = departureTime.plus(5,  ChronoUnit.HOURS);
		final Instant arrival = expectedArrivalTime.plus(Duration.ofMinutes(7));
		
		
		System.out.println("now:         " + now);
		System.out.println("departure:   " + departureTime);
		System.out.println("expected:    " + expectedArrivalTime);
		System.out.println("arrival:     " + arrival);
		
		System.out.println("***=====================================================***");
				
				
		// Class: --4-- Duration
		System.out.println();
		System.out.println("4) Duration: ");
		
		final Duration durationFromNanos = Duration.ofNanos(7);
		final Duration durationFromSecs = Duration.ofSeconds(15);
		final Duration durationFromMinutes = Duration.ofMinutes(30);
		final Duration durationFromHours = Duration.ofHours(45);
		final Duration durationFromDays = Duration.ofDays(60);
		
		System.out.println("From Nanos:     " + durationFromNanos);
		System.out.println("From Secs:      " + durationFromSecs);
		System.out.println("From Minutes:   " + durationFromMinutes);
		System.out.println("From Hours:     " + durationFromHours);
		System.out.println("From Days:      " + durationFromDays);
		
		System.out.println("***xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx***");
		System.out.println();
		
		// b) duration creation example
		System.out.println("4) Duration: CREATION");
		final Instant now2 = Instant.now();
		final Instant silvester2013b = Instant.parse("2013-12-31T00:00:00Z");
		final Instant myBirthday2015 = Instant.parse("2015-02-07T00:00:00Z");
		
		final Duration duration1 = Duration.between(now2, silvester2013b);
		final Duration duration2 = Duration.between(silvester2013b, myBirthday2015);
		
		System.out.println(now + " -- " + silvester2013b + ": " + duration1);
		System.out.println(silvester2013b + " -- " + myBirthday2015 + ": " + duration2);
		
		
		System.out.println("***xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx***");
		System.out.println();
		
		// c) duration calculation example
		System.out.println("4) Duration: CALCULATION");
		final Instant christmas2013 = Instant.parse("2013-12-24T00:00:00Z");
		final Instant silvester2013 = Instant.parse("2013-12-31T00:00:00Z");
		final Instant jdk8Release = Instant.parse("2014-03-18T00:00:00Z");
		
		// comparison values
		System.out.println("Christmas -> Silvester:      " + Duration.between(christmas2013, silvester2013));
		System.out.println("Silvester --> JDK 8 Release: " + Duration.between(silvester2013, jdk8Release));
		
		// calculations
		final Instant calcSilvester_1 = christmas2013.plus(Duration.ofDays(7));
		final Instant calcSilvester_2 = christmas2013.plus(7, ChronoUnit.DAYS);
		System.out.println(calcSilvester_1);
		System.out.println(calcSilvester_2);
		
		
		System.out.println("***=====================================================***");
		System.out.println("***=====================================================***");
		System.out.println("***=====================================================***");
		
		
		
		// Class: --5-- ChronoUnit
		System.out.println();
		System.out.println("5) ChronoUnit: ");
		
		// Start now, arrival in 5h
		final Instant departureTime2 = Instant.now();
		final Instant arrivalTime = departureTime2.plus(5, ChronoUnit.HOURS);
		
		System.out.println("departure now:      " + departureTime2);
		System.out.println("arrival now: +5h    " + arrivalTime);
		
		
		// calculation of differences:
		final long inBetweenHours = ChronoUnit.HOURS.between(departureTime2, arrivalTime);
		final long inBetweenMinutes = ChronoUnit.MINUTES.between(departureTime2, arrivalTime);
		
		System.out.println("inBetweenHours:     " + inBetweenHours);
		System.out.println("inBetweenMinutes:   " + inBetweenMinutes);
		
		
		System.out.println("***=====================================================***");
		
		
		
		// Class: --6-- LocalDate, LocalTime and LocalDateTime
		System.out.println();
		System.out.println("6) LocalDate, LocalTime and LocalDateTime: ");
		
		// LocalDate
		final LocalDate michasBirthday = LocalDate.of(1971,  Month.FEBRUARY,  7);
		final LocalDate barbarasBirthday = michasBirthday.plusYears(2)
				.plusMonths(1)
				.plusDays(17);
		
		System.out.println("michasBirthday:    " + michasBirthday);
		System.out.println("barbarasBirthday:  " + barbarasBirthday);
		
		// LocalTime
		final LocalTime atTen = LocalTime.of(10, 00, 00);
		final LocalTime tenFifteen = atTen.plusMinutes(15);
		final LocalTime breakfastTime = tenFifteen.minusHours(2);
		
		System.out.println("\natTen:             " + atTen);
		System.out.println("tenFifteen:        " + tenFifteen);
		System.out.println("breakfastTime:     " + breakfastTime);
		
		// LocalDateTime
		final LocalDateTime jdk8Release2 = LocalDateTime.of(2014, 3, 18, 8, 30);
		System.out.println("\njdk8Release:       " + jdk8Release2);
		System.out.printf("jdk8Release:       %s.%s.%s\n", jdk8Release2.getDayOfMonth(),
				jdk8Release2.getMonthValue(),
				jdk8Release2.getYear());
		
		
		System.out.println("***=====================================================***");			
		

		
		
		// Class: --7-- Period
		System.out.println();
		System.out.println("7) Period: ");
		
		final Period oneYear_sixMonth_ThreeDays = Period.ofYears(1).withMonths(6).withDays(3);
		final Period twoMonths_OneWeek_ThreeDays = Period.ofMonths(2).ofWeeks(1).ofDays(3);
		final Period twoMonth_TenDays = Period.ofMonths(2).withDays(10);
		final Period sevenWeeks = Period.ofWeeks(7);
		final Period threeDays = Period.ofDays(3);
		
		System.out.println("1 year 6 month ...:   " + oneYear_sixMonth_ThreeDays);
		System.out.println("Surprise just 3 days: " + twoMonths_OneWeek_ThreeDays);
		System.out.println("2 months 10 days:     " + twoMonth_TenDays);
		System.out.println("sevenWeeks            " + sevenWeeks);
		System.out.println("threeDays:            " + threeDays);
		
		System.out.println();
		System.out.println("-->>>>>> PeriodCalculationExample: ");
		
		final LocalDateTime start = LocalDateTime.of(1971,  2,  7,  10,  11);
		
		final Period thirtyOneDays = Period.ofDays(31);
		final Period fourWeeks = Period.ofWeeks(4);
		final Period oneMonth = Period.ofMonths(1);
		
		System.out.println("7.2.1971 + 31 Tage:   " + start.plus(thirtyOneDays));
		System.out.println("7.2.1971 + 4 Wochen:  " + start.plus(fourWeeks));
		System.out.println("7.2.1971 + 1 Monat:   " + start.plus(oneMonth));
		
		System.out.println("***===============================================================***");				
		
		
		
		
		
		
		
		// Class: --8-- ZonedDateTime
		System.out.println();
		System.out.println("8) ZonedDateTime: ");
		
		final ZonedDateTime now3 = ZonedDateTime.now();
		
		final ZonedDateTime nowButChangedTime = now3.withHour(11).withMinute(44);
		
		final ZonedDateTime dateAndTime = nowButChangedTime.withYear(2008).withMinute(9).withDayOfMonth(29);
		
		final ZonedDateTime dateAndTime2 = nowButChangedTime.withYear(2008).
				withMonth(Month.SEPTEMBER.getValue()).
				withDayOfMonth(29).
				withZoneSameInstant(ZoneId.of("GMT"));
		
		System.out.println("now:           " + now3);
		System.out.println("-> 11:44:      " + nowButChangedTime);
		System.out.println("-> 29.9.2008:  " + dateAndTime);
		System.out.println("-> 29.9.2008:  " + dateAndTime2);
		
		System.out.println("***===============================================================***");				
		
		
		
		
		// Class: --9-- ZoneId and ZoneOffset
		System.out.println();
		System.out.println("9) ZoneId and ZoneOffset: ");
		
		final Stream<String> zoneIdNames = Stream.of("Asia/Chungking", "Africa/Nairobi", "America/Los_Angeles");
		
		zoneIdNames.forEach(zoneIdName -> 
		{
			final ZoneId zoneId = ZoneId.of(zoneIdName);
			final LocalTime now4 = LocalTime.now(zoneId);
			
			System.out.println(zoneIdName + ": " + now4);
		});
		
		final Set<String> allZones = ZoneId.getAvailableZoneIds();
		final Predicate<String> inEurope = name -> name.startsWith("Europe/");
		final List<String> someFromEurope = allZones.stream().filter(inEurope).limit(5).collect(Collectors.toList());
		
		System.out.println("\nSome timezones in europe:");
		someFromEurope.forEach(System.out::println);
		
		
		
		System.out.println("***xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx***");
		System.out.println();
		
		// *) ZoneOffsetExample
		System.out.println("==> ZoneOffsetExample: ");
		final Stream<String> zoneIdNames2 = Stream.of("Europe/Berlin", "America/Los_Angeles", "Australia/Adelaide", "Europe/Paris", "Asia/Jakarta", "America/New_York"); 
		
		zoneIdNames2.forEach(zoneIdName -> {
			final ZoneId zoneId = ZoneId.of(zoneIdName);
			final LocalDateTime ldt = LocalDateTime.now();
			final ZonedDateTime zdt = ldt.atZone(zoneId);
			final ZoneOffset offset = zdt.getOffset();
			
			System.out.format("offset for '%s' is %s\n", zoneId, offset);
			
		});
				
				
				/*
	ACT - Australia/Darwin
    AET - Australia/Sydney
    AGT - America/Argentina/Buenos_Aires
    ART - Africa/Cairo
    AST - America/Anchorage
    BET - America/Sao_Paulo
    BST - Asia/Dhaka
    CAT - Africa/Harare
    CNT - America/St_Johns
    CST - America/Chicago
    CTT - Asia/Shanghai
    EAT - Africa/Addis_Ababa
    ECT - Europe/Paris
    IET - America/Indiana/Indianapolis
    IST - Asia/Kolkata
    JST - Asia/Tokyo
    MIT - Pacific/Apia
    NET - Asia/Yerevan
    NST - Pacific/Auckland
    PLT - Asia/Karachi
    PNT - America/Phoenix
    PRT - America/Puerto_Rico
    PST - America/Los_Angeles
    SST - Pacific/Guadalcanal
    VST - Asia/Ho_Chi_Minh

The map is unmodifiable.
				*/
				
				
				
		
		
		
		
		System.out.println("***=====================================================***");			
		
		
		
		
		
		

		
		// Class: --10-- Clock
		System.out.println();
		System.out.println("10) Clock: ");
		
		printClockAndMillis(Clock.systemUTC()); 
		printClockAndMillis(Clock.systemDefaultZone()); 
		
		
		
		System.out.println("***xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx***");
		System.out.println();
		
		// *) Additional Clock example
		System.out.println("==> Additional Clock example: ");
		final Clock clock1 = Clock.systemUTC();
		final Clock clock2 = Clock.systemDefaultZone();
		final Clock clock3 = Clock.fixed(Instant.now(), ZoneId.of("Asia/Hong_Kong"));
		
		printClocks(clock1, clock2, clock3);
		
		Thread.sleep(3000);
		System.out.println("\nAfter 3 s\n");
		
		printClocks(clock1, clock2, clock3);
		
		System.out.println("***=====================================================***");	
		
		
		

		
		// Class: --11-- Formatting and Parsing
		System.out.println();
		System.out.println("11) Time: Formatting and Parsing: ");
		
		final DateTimeFormatter ddMMyyyyFormat = ofPattern("dd.MM.yyyy");
		final DateTimeFormatter italian_dMMMMyy = ofPattern("d.MMMM y", Locale.ITALIAN);
		final DateTimeFormatter shortGerman = ofLocalizedDateTime(SHORT).withLocale(Locale.GERMAN);
		
		final String customPattern = "'Der 'dd'. Tag im 'MMMM' im Jahr 'yy'.'";
		final DateTimeFormatter customFormat = ofPattern(customPattern);
		
		final Map<String, DateTimeFormatter> formatters = new LinkedHashMap<>();
		formatters.put("BASIC_ISO_DATE",  BASIC_ISO_DATE);
		formatters.put("ISO_DATE_TIME", ISO_DATE_TIME);
		formatters.put("dd.MM.yyyy",  ddMMyyyyFormat);
		
		formatters.put("d.MMMM y (it)", italian_dMMMMyy);
		formatters.put("SHORT_GERMAN",  shortGerman);
		formatters.put("CUSTOM_FORMAT",  customFormat);
		
		System.out.println("Formatting:\n");
		applyFormatters(LocalDateTime.of(2014, Month.MARCH, 28, 1, 2, 3), formatters);
		// applyFormatters(LocalDateTime.of(2014,  Month.AUGUST,  28,  1,  2, 3), formatters);
		// applyFormatters(LocalDateTime.of(2014, Month.MAY, 28, 1, 2, 3, 4), formatters);
		// applyFormatters(LocalDateTime.of(2014, 5, 28, 1, 2, 3), formatters);
		
		
		System.out.println("\nParsing:\n");
		
		final LocalDate fromIsoDate = LocalDate.parse("1971-02-07");
		String testString = "31.03.2020";
		final LocalDate fromCustomPattern = LocalDate.parse(testString, ddMMyyyyFormat);
		String testString2 = "18.03.14 11:12";
		//String testString2 = "18.03.14, 11:12"; // --> ohne Komma funktioniert es hier nicht ... (hat es einmal funktioniert?)
		final LocalDateTime fromShortGerman = LocalDateTime.parse(testString2, shortGerman);
		
		System.out.println("From ISO Date:        " + fromIsoDate);
		System.out.println("From custom pattern:  " + fromCustomPattern + "; AND with the imput String of: " +testString);
		System.out.println("From short german:    " + fromShortGerman + "; ( --> Imput-String: " +testString2 +").");
		
		
		
		
		// Class: --12a-- Formatting and Parsing
		System.out.println();
		System.out.println("12a) SimpleDateFormat - für Anpassungen... ");
		
		// Neues Date-Objekt erzeugen
		final Date jetztUndHeute = new Date();
		
		
		
		
		
		// In String wandeln und als Vergleichswert ausgeben
		final String dateString = now.toString();
		System.out.println("Now: " +dateString);
		
		// Definition des passenden Datumsformats
		final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		// final String pattern = "EEE MMM dd HH:mm:ss zzz yyyy"; // --> does not work - maybe in the US???
		final SimpleDateFormat formatML = new SimpleDateFormat(pattern, Locale.GERMANY);
		System.out.println("************************************************************************************");
		
		// Versuche die Ausgabe von Date.toString() zu parsen
		final Date parsedML = formatML.parse(dateString);
		System.out.println("Parsed date: " + parsedML);
		
		// Prüfe, ob das Format auch bei der Ausgabe korrekt arbeitet
		System.out.println("Formatted date: " + formatML.format(jetztUndHeute));
		System.out.println("************************************************************************************");
		
		
		
		// Class: --12b-- Formatting and Parsing
		System.out.println();
		System.out.println("12b) SimpleDateFormat - für Anpassungen... ");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date inputDate = jetztUndHeute;
        String resultForSimpleDateFormat = dateFormat.format(inputDate);
        System.out.println("DateFormat: ---> SimpleDateFormat: " + dateFormat.format(inputDate));
        System.out.println("Result (same with variable): " + resultForSimpleDateFormat);
		
		
        
        
        
        
        
        
        
        
     // Class: --13-- Formatting and Parsing
    System.out.println();
    System.out.println("************************************************************************************");
	System.out.println("13) Weitere Beispiele:  G U T !   ==> HEUTE...");

		
	LocalDate nowML = LocalDate.now();
	System.out.println( nowML );          // 2014-03-21
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "d. MMMM yyyy" );
	String nowAsString = nowML.format( formatter );
	System.out.println( nowAsString );  // 21. März 2014
	LocalDate nowAgain = LocalDate.parse( nowAsString, formatter );
	System.out.println( nowAgain );     // 2014-03-21
	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	
	Date todayTest = new Date();
	System.out.println("Today is: " + todayTest + "  -->(complete format)");
	
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	String dateTest = DATE_FORMAT.format(todayTest);
	System.out.println("Today is dd-MM-yyyy format: " + dateTest + "   -->(specific defined format)");
			
			
	
	System.out.println("\n\n*========================> E N D <=============================*");	
	System.out.println("*========================> E N D <=============================*");	
	System.out.println("*========================> E N D <=============================*");	
	
	
	
	System.out.println("\n");
	System.out.println("\n");	
	System.out.println("\n*========================> Test from here on !  ==========>>>>");	
	System.out.println("\n");
	LocalDateTime testNowTime = LocalDateTime.now();
	System.out.println("Now: " +testNowTime);
	System.out.println("Minus one day: " +testNowTime.minusDays(1));
	System.out.println("Minus one day and one hour: " +testNowTime.minusDays(1).minusHours(1));
	
	System.out.println("\nToday, but minus 1 day at 00:00");
	LocalDateTime rightTimeToPrint = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	System.out.println(rightTimeToPrint);
	
	// Martin new: out of the Java book from O'Reilly
	System.out.println("\n");
	System.out.println("\n ML: ========================> Martin-new...  !  ==========>>>>");	
	System.out.println();
	
	Calendar c = Calendar.getInstance();
	c.set(2020,  9, 3, 14, 49);
	
	System.out.println("Datum erstellt / eingegeben:    " + c.getTime());
	long tag1 = c.getTimeInMillis();
	tag1 += 1000 * 60 * 60;
	c.setTimeInMillis(tag1);
	System.out.print("neue Stunde: " + c.get(c.HOUR_OF_DAY));
	System.out.println(" / neue Stunde (static way): " + c.get(Calendar.HOUR_OF_DAY)); // static way...
	
	c.add(c.DATE,  35); // add 35 days
	System.out.println("35 Tage hinzurechnen:           " + c.getTime());
	
	c.roll(c.DATE, 35);
	System.out.println("35 Tage rollieren:              " + c.getTime() + "  (Datum wird 35 Tage vorgedreht, aber der Monat bleibt unverändert!)");
	
	c.set(c.DATE, 1);
	System.out.println("auf 1 setzen:                   " + c.getTime());
	
	System.out.println("get(c.DATE): " + c.get(c.DATE));
	System.out.println("get(Calendar.MONTH): " + c.get(Calendar.MONTH));
	System.out.println("get(c.YEAR): " + c.get(c.YEAR));
	System.out.println("get(c.HOUR ----> ):  " + c.get(c.HOUR));
	System.out.println("get(c.HOUR_OF_DAY): " + c.get(c.HOUR_OF_DAY));
	System.out.println("get(c.MILLISECOND): " + c.get(c.MILLISECOND));
	System.out.println("get(c.MINUTE): " + c.get(c.MINUTE));
	System.out.println("get(c.ZONE_OFFSET): " + c.get(c.ZONE_OFFSET) + "; ohne Sommerzeitberücksichtigung, gegenüber GMT in Millisekunden...");
	System.out.println("get(c.DAY_OF_WEEK): " + c.get(c.DAY_OF_WEEK));
	System.out.println("get(c.DAY_OF_YEAR): " + c.get(c.DAY_OF_YEAR));
	System.out.println("get(c.WEEK_OF_YEAR): " + c.get(c.WEEK_OF_YEAR));
	System.out.println("get(c.WEEK_OF_MONTH): " + c.get(c.WEEK_OF_MONTH));
	System.out.println("get(c.DAY_OF_WEEK_IN_MONTH): " + c.get(c.DAY_OF_WEEK_IN_MONTH));
	System.out.println("get(c.DAY_OF_WEEK): " + c.get(c.DAY_OF_WEEK));
	System.out.println("get(c.WEDNESDAY): " + c.get(c.WEDNESDAY));
	System.out.println("get(c.WEEK_OF_YEAR): " + c.get(c.WEEK_OF_YEAR));
	
	System.out.println();
	System.out.println(String.format("%tc",  new Date()) + " (now)");
	System.out.println(String.format("%tc",  c) + "  (date / time from above ...)");
	System.out.println(String.format("%tr",  c) + "                  (only the time)");
	
	System.out.println(String.format("%tA, %td. %tB", c, c, c));
	System.out.println(String.format("%tA, %<td. %<tB", c));
	
	System.out.println("\nSome numbers (with formatting): ");
	int eins = 20455662;
	double zwei = 100567321.266707;
	String s = String.format("Die zwei Zahlen sind %,d (int) und %,.1f (double).",  eins, zwei);
	System.out.println(s);
	
	}
	
	

	
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Remark:  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	
	/*
	//Convert LocalDateTime to Time Zone ISO8601 String

	LocalDateTime ldt = LocalDateTime.now(); 
	ZonedDateTime zdt = ldt.atZone(ZoneOffset.UTC); //you might use a different zone
	String iso8601 = zdt.toString();

	//Convert from ISO8601 String back to a LocalDateTime

	String iso8601 = "2016-02-14T18:32:04.150Z";
	ZonedDateTime zdt = ZonedDateTime.parse(iso8601);
	LocalDateTime ldt = zdt.toLocalDateTime();
	
	*/
	
	// https://github.com/sharmasha2nk/Java8/blob/master/src/com/java8/feature/date/DateTime.java
	
	
	
	//************************************************************************************************************************	
	// methods...
	private static void applyFormatters(final LocalDateTime base, final Map<String, DateTimeFormatter> formatters) {
		System.out.println("Starting on: " + base);
		formatters.forEach((name, formatter) -> {
			System.out.println("applying '" + name + "': " + base.format(formatter));
		});
	}

	
	
	
	private static void printClocks(final Clock... clocks) {
		
		for (final Clock clock : clocks) {
			System.out.println("LocalTime: " + LocalTime.now(clock));
		}
	}



	private static void printClockAndMillis(final Clock clock) {
		System.out.println(clock + " / ms: " + clock.millis());
	}

}








