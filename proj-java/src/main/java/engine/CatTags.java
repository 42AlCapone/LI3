package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatTags {
	
	private HashMap<String,Long> catTags;


	public CatTags() {
		this.catTags = new HashMap<String,Long>();
	}

	public CatTags(HashMap<String,Long> cat) {
		setCatTags(cat);
	}	

	public CatTags(CatTags cat) {
		this.catTags = new HashMap<String,Long>();
		catTags.putAll(cat.getCatTags());
	}


	public Map<String,Long> getCatTags() {
		return this.catTags.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
	}


	public Long getIDTag(String tag) {
		return this.catTags.get(tag);
	}

	public long getSizeCatT(){
		return (long) this.catTags.size();
 	}

 	
	public void setCatTags(HashMap<String,Long> cat) {
		this.catTags = (HashMap<String,Long>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
	}

	public void insereTag(String tag, Long id) {
		this.catTags.put(tag,id);
	}

	public boolean existeTag (String tag) {
		return this.catTags.containsKey(tag);
	}

	public void clearCatTags() {
		this.catTags.clear();
	}
}
