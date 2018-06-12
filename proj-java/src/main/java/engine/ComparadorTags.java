package engine;

import java.util.Comparator;
import java.util.Map;


public class ComparadorTags implements Comparator<Map.Entry<String,Tag>> {
	public int compare (Map.Entry<String,Tag> map1, Map.Entry<String,Tag> map2) {
		long m = (map1.getValue().getTagUses()) - (map2.getValue().getTagUses());
		if (m>0) return -1;
		if (m<0) return 1;
		return (map1.getKey().compareTo(map2.getKey()));
	}	
}