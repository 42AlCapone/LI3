package engine;

import java.util.Comparator;
import java.util.Map;
import java.time.LocalDate;

public class ComparadorDatesP implements Comparator<Map.Entry<Long,Pergunta>> {
	public int compare (Map.Entry<Long,Pergunta> map1, Map.Entry<Long,Pergunta> map2) {
		LocalDate date1 = map1.getValue().getPergDate();
		LocalDate date2 = map2.getValue().getPergDate();
		int m = date2.compareTo(date1);
		if (m>0) return 1;
		if (m<0) return -1;
		return (map2.getKey().compareTo(map1.getKey()));
	}	
}