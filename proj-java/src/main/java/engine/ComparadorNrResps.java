package engine;

import java.util.Comparator;
import java.util.Map;


public class ComparadorNrResps implements Comparator<Map.Entry<Long,Pergunta>> {
	public int compare (Map.Entry<Long,Pergunta> map1, Map.Entry<Long,Pergunta> map2) {
		int m = (map1.getValue().getNrResp()) - (map2.getValue().getNrResp());
		if (m>0) return -1;
		if (m<0) return 1;
		return (map1.getKey().compareTo(map2.getKey()));
	}	
}