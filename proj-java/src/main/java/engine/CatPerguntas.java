package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatPerguntas {

	private HashMap<Long,Pergunta> catPerg;


	public CatPerguntas() {
		this.catPerg = new HashMap<Long,Pergunta>();
	}

	public CatPerguntas(HashMap<Long,Pergunta> cat) {
		setCatPerg(cat);
	}	

	public CatPerguntas(CatPerguntas cat) {
		this.catPerg = new HashMap<Long,Pergunta>();
		catPerg.putAll(cat.getCatPerg());
	}


	public Map<Long,Pergunta> getCatPerg() {
		return this.catPerg.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}


	public Pergunta getPergunta(Long id) {
		return this.catPerg.get(id);
	}

	public long getSizeCatP(){
		return (long) this.catPerg.size();
 	}

 	
	public void setCatPerg(HashMap<Long,Pergunta> cat) {
		this.catPerg = (HashMap<Long,Pergunta>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue().clone()));
	}

	public void inserePerg(Pergunta p) {
		this.catPerg.put(p.getPergID(),p);
	}

	public boolean existePerg(Long id) {
		return this.catPerg.containsKey(id);
	}

	public void clearCatPerg() {
		this.catPerg.clear();
	}

}