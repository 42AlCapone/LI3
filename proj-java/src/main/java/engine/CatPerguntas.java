/**
 * Classe de armazenamento de Perguntas sob a forma de um HashMap Long Pergunta.
 *
 * @author António Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @version 1.2
 */

package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatPerguntas {

	private HashMap<Long,Pergunta> catPerg;

	
	/**
	 * Construtor por omissão da classe CatPerguntas.
	 * <p>
	 * Utiliza o construtor por omissão de HashMap para criar um novo HashMap default.
	 * <p>
	 * Neste HashMap a chave é o id da Pergunta e o Valor é a Pergunta em si.
	 * 
	 * @see java.util.HashMap#HashMap<k,v>()
	 * @see java.util.HashMap
	 * @see engine.Resposta
	 */
	public CatPerguntas() {
		this.catPerg = new HashMap<Long,Pergunta>();
	}

	/**
	 * Construtor parametrizado da classe CatPerguntas.
	 * <p>
	 * Utiliza o método <code>setCatPerg(HashMap<Long,Pergunta>) para defenir cat como sendo
	 * a catPerg do objecto associado.
	 *
	 * @param cat HashMap ao qual catResp será igual.
	 * @see #setCatPerg(HashMap<Long,Pergunta>)
	 */
	public CatPerguntas(HashMap<Long,Pergunta> cat) {
		setCatPerg(cat);
	}	

	/**
	 * Construtor por cópia da classe CatPerguntas.
	 * <p>
	 * Cria uma nova HashMap<Long,Pergunta> que será catPerg do objecto associado.
	 * De seguida utiliza o método putAll para inserir todos os elementos de cat na nova catPerg.
	 * <p>
	 * putAll recebe como argumento o resultado de getCatPerg que é a catPerg do objecto associado
	 * cat.
	 *
	 * @param cat Objecto CatPerguntas fonte da cópia.
	 * @see #getCatPerg()
	 * @see java.util.HashMap#putAll
	 * @see java.util.HashMap
	 */
	public CatPerguntas(CatPerguntas cat) {
		this.catPerg = new HashMap<Long,Pergunta>();
		catPerg.putAll(cat.getCatPerg());
	}

	 	/**
	 * Método de acesso do parâmetro <code>catPerg</code> de <code>CatPerguntas</code>.
	 * <p>
	 * Este método retorna uma cópia de catPerg.
	 * <p>
	 * <code>this.catPerg.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<Long,Pergunta> catPerg</code>.
	 * <p>
	 * <code>stream()</code> alimenta os métodos seguintes com cada elemtento do <code>entrySet</code>
	 *  um a um (em stream).
	 * <p>
	 * <code>collect(collectors)</code> recolhe os elementos lançados pela stream aplicando-lhe o
	 *  collector passado como argumento.
	 * <p>
	 * <code>collectors.toMap(key, value)</code> cria um collector que insere todos os elementos
	 *  colecionados num <code>Map</code>.
	 * <p>
	 * <code>e -> e.getKey()</code> extrai a chave do elemento e retorna uma cópia da chave desse elemento.
	 * <p>
	 * <code>e -> e.getValue().clone()</code> extrai o value do tipo <code>Pergunta</code> e clona esse
	 *  value atravéz do método <code>clone</code> da classe <code>Pergunta</code>.
	 *
	 * @return Uma cópia de catPerg gerada pelo mecanismo acima descrito.
	 * @see java.util.Map#entrySet()
	 * @see java.util.stream#stream()
	 * @see java.util.stream.Collectors#collect
	 * @see java.util.stream.Collectors#toMap
	 * @see java.util.Map#getKey()
	 * @see java.util.Map#getValue()
	 * @see engine.Pergunta.clone()
	 */
	public Map<Long,Pergunta> getCatPerg() {
		return this.catPerg.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}


	/**
	 * Método de acesso a uma determinada paegunta atravéz do seu ID.
	 * <p>
	 * Utilizando o método get(key) associado a catPerg para retornar a Resposta correspondente
	 * à chave id.
	 *
	 * @param id id da Pergunta que se prentende procurar.
	 * @return A pergunta correspondente à chave id (resultado de get(key)).
	 * @see java.util.HashMap#get
	 * @see java.lang.Long
	 * @see java.util.HashMap
	 */
	public Pergunta getPergunta(Long id) {
		return this.catPerg.get(id);
	}

	/**
	 * Método de cálculo do tamanho de catPerg i.e. o número de respostas que contém.
	 * <p>
	 * Utilizando o método size() associado a catPerg, é retornado o seu resultado sobre a forma long.
	 *
	 * @return o tamanho de catPerg (<code>this.catPerg.size()</code> sob a forma de long.
	 * @see java.util.HashMap#size()
	 */
	public long getSizeCatP(){
		return (long) this.catPerg.size();
 	}

 	/**
	 * Método de escrita do parâmetro <code>catPerg</code> de <code>CatPerguntas</code>.
	 * <p>
	 * Este método iguala catPerg a uma cópia de cat passado por argumento.
	 * <p>
	 * <code>this.catResp.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<Long,Pergunta> catPerg</code>.
	 * <p>
	 * <code>stream()</code> alimenta os métodos seguintes com cada elemtento do <code>entrySet</code>
	 *  um a um (em stream).
	 * <p>
	 * <code>collect(collectors)</code> recolhe os elementos lançados pela stream aplicando-lhe o
	 *  collector passado como argumento.
	 * <p>
	 * <code>collectors.toMap(key, value)</code> cria um collector que insere todos os elementos
	 *  colecionados num <code>Map</code>.
	 * <p>
	 * <code>e -> e.getKey()</code> extrai a chave do elemento e retorna uma cópia da chave desse elemento.
	 * <p>
	 * <code>e -> e.getValue().clone()</code> extrai o value do tipo <code>Pergunta</code> e clona esse
	 *  value atravéz do método <code>clone</code> da classe <code>Pergunta</code>.
	 * <p>
	 * catPerg é igualada à cópia criada pelo processo acima descrito após a sua conversão em HashMap.
	 *
	 * @param cat HashMap a inserir em catPerg.
	 * @see java.util.Map#entrySet()
	 * @see java.util.stream#stream()
	 * @see java.util.stream.Collectors#collect
	 * @see java.util.stream.Collectors#toMap
	 * @see java.util.Map#getKey()
	 * @see java.util.Map#getValue()
	 * @see engine.Pergunta.clone()
	 */
	public void setCatPerg(HashMap<Long,Pergunta> cat) {
		this.catPerg = (HashMap<Long,Pergunta>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue().clone()));
	}

	/**
	 * Método de inserção de uma Perunta em catPerg.
	 * <p>
	 * O método put(key value) é associado a catPerg introduzindo o par key value.
	 * <p>
	 * Key é o id da Resposta passada por argumento, esta key é obtida pelo método getPergID.
	 * <p>
	 * Value é a resposta em si.
	 *
	 * @param r resposta a inserir.
	 * @see engine.Pergunta#getPergID()
	 * @see java.util.HashMap#put
	 */
	public void inserePerg(Pergunta p) {
		this.catPerg.put(p.getPergID(),p);
	}

	/**
	 * Método de teste da existência de uma Pergunta em catPerg.
	 * <p>
	 * Retorna o resultado de aplicar containsKey com o argumento do id da Pergunta a procurar
	 * (passado por argumento em existePerg(Long id) a catPerg.
	 *
	 * @param id o id a ser testado.
	 * @return o resultado de containsKey(id) true se existir false se não existir.
	 * @see java.util.HashMap#containsKey
	 * @see java.util.HashMap
	 */
	public boolean existePerg(Long id) {
		return this.catPerg.containsKey(id);
	}

	/**
	 * Método de libertação de catPerg.
	 * <p>
	 * Aplica clear() a catPerg.
	 *
	 * @see java.util.HashMap#clear()
	 */
	public void clearCatPerg() {
		this.catPerg.clear();
	}

}
