/**
 * Classe de armazenamento de tags sob a forma de um HashMap String Long.
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

public class CatTags {
	
	private HashMap<String,Long> catTags;

	/**
	 * Construtor por omissão da classe CatTags.
	 * <p>
	 * Utiliza o construtor por omissão de HashMap para criar um novo HashMap default.
	 * <p>
	 * Neste HashMap a chave é o id da tag e o Valor é a tag em si.
	 * 
	 * @see java.util.HashMap#HashMap<k,v>()
	 * @see java.util.HashMap
	 * @see engine.Tags
	 */
	public CatTags() {
		this.catTags = new HashMap<String,Long>();
	}

	/**
	 * Construtor parametrizado da classe CatTags.
	 * <p>
	 * Utiliza o método <code>setCatTag(HashMap<String,Long>) para defenir cat como sendo
	 * a catTags do objecto associado.
	 *
	 * @param cat HashMap ao qual catTags será igual.
	 * @see #setCatTag(HashMap<String,Long>)
	 */
	public CatTags(HashMap<String,Long> cat) {
		setCatTags(cat);
	}
	
	/**
	 * Construtor por cópia da classe CatTags.
	 * <p>
	 * Cria uma nova HashMap<String,Long> que será catTags do objecto associado.
	 * De seguida utiliza o método putAll para inserir todos os elementos de cat na nova catTags.
	 * <p>
	 * putAll recebe como argumento o resultado de getCatTags que é a catTags do objecto associado
	 * cat.
	 *
	 * @param cat Objecto CatTags fonte da cópia.
	 * @see #getCatTags()
	 * @see java.util.HashMap#putAll
	 * @see java.util.HashMap
	 */
	public CatTags(CatTags cat) {
		this.catTags = new HashMap<String,Long>();
		catTags.putAll(cat.getCatTags());
	}

 	/**
	 * Método de acesso do parâmetro <code>catTags</code> de <code>CatTags</code>.
	 * <p>
	 * Este método retorna uma cópia de catTags.
	 * <p>
	 * <code>this.catPerg.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<Long,String> catTags</code>.
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
	 * <code>e -> e.getValue()</code> extrai o value do tipo <code>Long</code>.
	 *
	 * @return Uma cópia de catPerg gerada pelo mecanismo acima descrito.
	 * @see java.util.Map#entrySet()
	 * @see java.util.stream#stream()
	 * @see java.util.stream.Collectors#collect
	 * @see java.util.stream.Collectors#toMap
	 * @see java.util.Map#getKey()
	 * @see java.util.Map#getValue()
	 */
	public Map<String,Long> getCatTags() {
		return this.catTags.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
	}

	/**
	 * Método de acesso ao id de uma tag a partir de uma tag expecífica.
	 * <p> 
	 * Utiliza o método get associado a catTags para ober o id da tag passada por arguemnto.
	 * @param tag  tag a ser procurada.
	 * @return o id da tag procurada.
	 * @see java.util.HashMap#get(value)
	 */
	public Long getIDTag(String tag) {
		return this.catTags.get(tag);
	}

	/**
	 * Método de cálculo do tamanho de catTags i.e. o número de respostas que contém.
	 * <p>
	 * Utilizando o método size() associado a catTags, é retornado o seu resultado sobre a forma long.
	 *
	 * @return o tamanho de catTags (<code>this.catTags.size()</code> sob a forma de long.
	 * @see java.util.HashMap#size()
	 */
	public long getSizeCatT(){
		return (long) this.catTags.size();
 	}

 	/**
	 * Método de escrita do parâmetro <code>catTags</code> de <code>CatTags</code>.
	 * <p>
	 * Este método iguala catPerg a uma cópia de cat passado por argumento.
	 * <p>
	 * <code>this.catTags.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<String,Long> catTags</code>.
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
	 * <code>e -> e.getValue()</code> extrai o value do tipo <code>Long</code>.
	 * <p>
	 * catTags é igualada à cópia criada pelo processo acima descrito após a sua conversão em HashMap.
	 *
	 * @param cat HashMap a inserir em catTags.
	 * @see java.util.Map#entrySet()
	 * @see java.util.stream#stream()
	 * @see java.util.stream.Collectors#collect
	 * @see java.util.stream.Collectors#toMap
	 * @see java.util.Map#getKey()
	 * @see java.util.Map#getValue()
	 */ 	
	public void setCatTags(HashMap<String,Long> cat) {
		this.catTags = (HashMap<String,Long>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue()));
	}

	/**
	 * Função de inserção de elementos <String,Long> em catTags.
	 * <p>
	 * Utiliza o métod put(key, value) para iserir o par tag id em catTags.
	 *
	 * @param tag tag a ser inseirda como chave.
	 * @param id Long a ser inserido com valor.
	 * @see java.util.HashMap#put(key, value).
	 */
	public void insereTag(String tag, Long id) {
		this.catTags.put(tag,id);
	}

	/**
	 * Método de teste da existência de uma Pergunta em catTags.
	 * <p>
	 * Retorna o resultado de aplicar containsKey com o argumento do tag da Pergunta a procurar
	 * (passado por argumento em existeTag(String tag) a catTags.
	 *
	 * @param tag o tag a ser testado.
	 * @return o resultado de containsKey(tag) true se existir false se não existir.
	 * @see java.util.HashMap#containsKey
	 * @see java.util.HashMap
	 */
	public boolean existeTag (String tag) {
		return this.catTags.containsKey(tag);
	}

	/**
	 * Método de libertação de catTags.
	 * <p>
	 * Aplica clear() a catTags.
	 *
	 * @see java.util.HashMap#clear()
	 */
	public void clearCatTags() {
		this.catTags.clear();
	}
}
