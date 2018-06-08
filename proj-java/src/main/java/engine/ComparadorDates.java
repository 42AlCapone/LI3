package engine;

import java.util.Comparator;
import java.util.Map;
import java.time.LocalDate;

public class ComparadorDates implements Comparator<Map.Entry<Long,Pergunta>> {
	public int compare (Map.Entry<Long,Pergunta> map1, Map.Entry<Long,Pergunta> map2) {
		
		LocalDate date1 = map1.getValue().getPergDate();
		LocalDate date2 = map2.getValue().getPergDate();
		return date2.compareTo(date1);
	}	
}