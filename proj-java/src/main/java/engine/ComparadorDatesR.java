package engine;

import java.util.Comparator;
import java.util.Map;
import java.time.LocalDate;

public class ComparadorDatesR implements Comparator<Map.Entry<Long,Resposta>> {
	public int compare (Map.Entry<Long,Resposta> map1, Map.Entry<Long,Resposta> map2) {
		LocalDate date1 = map1.getValue().getRespDate();
		LocalDate date2 = map2.getValue().getRespDate();
		int m = date2.compareTo(date1);
		if (m>0) return 1;
		if (m<0) return -1;
		return (map2.getKey().compareTo(map1.getKey()));
	}	
}