package engine;

import java.util.Comparator;
import java.util.Map;


public class ComparadorScore implements Comparator<Map.Entry<Long,Resposta>> {
	public int compare (Map.Entry<Long,Resposta> map1, Map.Entry<Long,Resposta> map2) {
		int m = (map1.getValue().getScoreR()) - (map2.getValue().getScoreR());
		if (m>0) return -1;
		if (m<0) return 1;
		return (map1.getKey().compareTo(map2.getKey()));
	}	
}