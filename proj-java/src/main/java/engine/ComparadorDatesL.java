package engine;

import java.util.Comparator;
import java.util.Map;
import java.time.LocalDate;

public class ComparadorDatesL implements Comparator<Map.Entry<Long,LocalDate>> {
	public int compare (Map.Entry<Long,LocalDate> map1, Map.Entry<Long,LocalDate> map2) {
		LocalDate date1 = map1.getValue();
		LocalDate date2 = map2.getValue();
		int m = date2.compareTo(date1);
		if (m>0) return 1;
		if (m<0) return -1;
		return (map2.getKey().compareTo(map1.getKey()));
	}	
}