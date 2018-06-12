/**
 * Classe de armazenamento de Respostas sob a forma de um HashMap de id - Resposta
 *
 * @author António Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @version 1.1
 */

package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatRespostas {

	private HashMap<Long,Resposta> catResp;

	/**
	 * Construtor por omissão da classe CatRespostas.
	 * <p>
	 * Utiliza o construtor por omissão de HashMap para criar um novo HashMap default.
	 * <p>
	 * Neste HashMap a chave é o id da Resposta e o Valor é a resposta em si.
	 * 
	 * @see java.util.HashMap#HashMap<k,v>()
	 * @see java.util.HashMap
	 * @see engine.Resposta
	 */
	public CatRespostas() {
		this.catResp = new HashMap<Long,Resposta>();
	}

	/**
	 * Construtor parametrizado da classe CatRespostas.
	 * <p>
	 * Utiliza o método <code>setCatResp(HashMap<Long,Resposta>) para defenir cat como sendo
	 * a catResp do objecto associado.
	 *
	 * @param cat HashMap ao qual catResp será igual.
	 * @see #setCatResp(HashMap<Long,Resposta>)
	 */
	public CatRespostas(HashMap<Long,Resposta> cat) {
		setCatResp(cat);
	}	

	/**
	 * Construtor por cópia da classe CatRespostas.
	 * <p>
	 * Cria uma nova HashMap<Long,Resposta> que será catPerg do objecto associado.
	 * De seguida utiliza o método putAll para inserir todos os elementos de cat na nova catResp.
	 * <p>
	 * putAll recebe como argumento o resultado de getCatResp que é a catResposta do objecto associado
	 * cat.
	 *
	 * @param cat Objecto CatRespostas fonte da cópia.
	 * @see #getCatResp()
	 * @see java.util.HashMap#putAll
	 * @see java.util.HashMap
	 */
	public CatRespostas(CatRespostas cat) {
		this.catResp = new HashMap<Long,Resposta>();
		catResp.putAll(cat.getCatResp());
	}

	
 	/**
	 * Método de acesso do parâmetro <code>catResp</code> de <code>CatRespostas</code>.
	 * <p>
	 * Este método retorna uma cópia de catResp.
	 * <p>
	 * <code>this.catResp.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<Long,Resposta> catResp</code>.
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
	 * <code>e -> e.getValue().clone()</code> extrai o value do tipo <code>Resposta</code> e clona esse
	 *  value atravéz do método <code>clone</code> da classe <code>Resposta</code>.
	 *
	 * @return Uma cópia de catResp gerada pelo mecanismo acima descrito.
	 * @see java.util.Map#entrySet()
	 * @see java.util.stream#stream()
	 * @see java.util.stream.Collectors#collect
	 * @see java.util.stream.Collectors#toMap
	 * @see java.util.Map#getKey()
	 * @see java.util.Map#getValue()
	 * @see engine.Resposta.clone()
	 */
	public Map<Long,Resposta> getCatResp() {
		return this.catResp.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}

	/**
	 * Método de acesso a uma determinada resposta atravéz do seu ID.
	 * <p>
	 * Utilizando o método get(key) associado a catResp para retornar a Resposta correspondente
	 * à chave id.
	 *
	 * @param id id da Resposta que se prentende procurar.
	 * @return A resposta correspondente à chave id (resultado de get(key)).
	 * @see java.util.HashMap#get
	 * @see java.lang.Long
	 * @see java.util.HashMap
	 */
	public Resposta getResp(Long id) {
		return this.catResp.get(id);
	}

	/**
	 * Método de cálculo do tamanho de catResp i.e. o número de respostas que contém.
	 * <p>
	 * Utilizando o método size() associado a catResp, é retornado o seu resultado sobre a forma long.
	 *
	 * @return o tamanho de catResp (<code>this.catResp.size()</code> sob a forma de long.
	 * @see java.util.HashMap#size()
	 */
	public long getSizeCatR(){
		return (long) this.catResp.size();
 	}

 	
 	/**
	 * Método de escrita do parâmetro <code>catResp</code> de <code>CatRespostas</code>.
	 * <p>
	 * Este método iguala catResp a uma cópia de cat passado por argumento.
	 * <p>
	 * <code>this.catResp.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<Long,Resposta> catResp</code>.
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
	 * <code>e -> e.getValue().clone()</code> extrai o value do tipo <code>Resposta</code> e clona esse
	 *  value atravéz do método <code>clone</code> da classe <code>Resposta</code>.
	 * <p>
	 * catResp é igualada à cópia criada pelo processo acima descrito após a sua conversão em HashMap.
	 *
	 * @param cat HashMap a inserir em catResp.
	 * @see java.util.Map#entrySet()
	 * @see java.util.stream#stream()
	 * @see java.util.stream.Collectors#collect
	 * @see java.util.stream.Collectors#toMap
	 * @see java.util.Map#getKey()
	 * @see java.util.Map#getValue()
	 * @see engine.Resposta.clone()
	 */
	public void setCatResp(HashMap<Long,Resposta> cat) {
		this.catResp = (HashMap<Long,Resposta>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue().clone()));
	}

	/**
	 * Método de inserção de uma Resposta em catResp.
	 * <p>
	 * O método put(key value) é associado a catResp introduzindo o par key value.
	 * <p>
	 * Key é o id da Resposta passada por argumento, esta key é obtida pelo método getRespID.
	 * <p>
	 * Value é a resposta em si.
	 *
	 * @param r resposta a inserir.
	 * @see engine.Resposta#getRespID()
	 * @see java.util.HashMap#put
	 */
	public void insereResp(Resposta r) {
		this.catResp.put(r.getRespID(),r);
	}

	/**
	 * Método de teste da existência de uma Reposta em catResp.
	 * <p>
	 * Retorna o resultado de aplicar containsKey com o argumento do id da Resposta a procurar
	 * (passado por argumento em existeResp(Long id) a catResp.
	 *
	 * @param id o id a ser testado.
	 * @return o resultado de containsKey(id) true se existir false se não existir.
	 * @see java.util.HashMap#containsKey
	 * @see java.util.HashMap
	 */
	public boolean existeResp(Long id) {
		return this.catResp.containsKey(id);
	}

	/**
	 * Método de libertação de catResp.
	 * <p>
	 * Aplica clear() a catResp.
	 *
	 * @see java.util.HashMap#clear()
	 */
	public void clearCatResp() {
		this.catResp.clear();
	}

}
