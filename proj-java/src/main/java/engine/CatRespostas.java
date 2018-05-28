package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatRespostas {

	private HashMap<Long,Resposta> catResp;


	public CatRespostas() {
		this.catResp = new HashMap<Long,Resposta>();
	}

	public CatRespostas(HashMap<Long,Resposta> cat) {
		setCatResp(cat);
	}	

	public CatRespostas(CatRespostas cat) {
		this.catResp = new HashMap<Long,Resposta>();
		catResp.putAll(cat.getCatResp());
	}


	public Map<Long,Resposta> getCatResp() {
		return this.catResp.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}


	public Resposta getResp(Long id) {
		return this.catResp.get(id);
	}

	public long getSizeCatR(){
		return (long) this.catResp.size();
 	}

 	
	public void setCatResp(HashMap<Long,Resposta> cat) {
		this.catResp = (HashMap<Long,Resposta>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue().clone()));
	}

	public void insereResp(Resposta r) {
		this.catResp.put(r.getRespID(),r);
	}

	public boolean existeResp(Long id) {
		return this.catResp.containsKey(id);
	}

}