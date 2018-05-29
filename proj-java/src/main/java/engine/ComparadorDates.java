package engine;

import java.util.Comparator;
import java.util.Map;
import java.time.LocalDate;

public class ComparadorDates implements Comparator<LocalDate> {
	public int compare (LocalDate date1, LocalDate date2) {
		return date1.compareTo(date2);
	}	
}