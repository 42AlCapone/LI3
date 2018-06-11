package engine;

import java.util.Comparator;
import java.util.Map;


public class ComparadorRep implements Comparator<Map.Entry<Long,User>> {
	public int compare (Map.Entry<Long,User> map1, Map.Entry<Long,User> map2) {
		int m = (map1.getValue().getRep()) - (map2.getValue().getRep());
		if (m>0) return -1;
		if (m<0) return 1;
		return (map1.getKey().compareTo(map2.getKey()));
	}	
}