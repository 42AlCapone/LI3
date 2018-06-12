/**
 * Classe de armazenamento de Users, armazenamento sob a forma de um <code>HashMap<Long, User></code>.
 *
 * @author António Gomes
 * @author João Vieira
 * @autor Rui Vieira
 * @version 1.2
 */

package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatUsers {
	
	private HashMap<Long,User> catUsers;

	/**
	 * Contrutor por omissão da classe <code>CatUsers</code>.
	 * <p>
	 * Utiliza o construtor por omissao da classe <code>HashMap</code> de forma a inicializar o
	 *  parâmetro catUsers.
	 * <p>
	 * catUsers <code>HashMap<Long, User></code> sendo a chave o id do utilizador.
	 * @see java.util.HashMap
	 * @see java.lang.Long
	 */
	public CatUsers() {
		this.catUsers = new HashMap<Long,User>();
	}

	/** 
	 * Construtor parameterizado da classe <code>CatUsers</code>.
	 * <p>
	 * Utiliza o método {@link #setCatUsers} (ver abaixo).
	 *
	 * @param cat <code>HashMap</code> contedo cada user mapeado segundo o seu id.
	 * @see #setCatUsers
	 * @see java.util.HashMap
	 */
	public CatUsers(HashMap<Long,User> cat) {
		setCatUsers(cat);
	}	


	/** 
	 * Construtor por cópia da classe <code>CatUsers</code>.
	 * <p>
	 * Utiliza o contrutor por omissão da classe <code>HashMap</code> de forma a inicializar uma
	 *  <code>HashMap</code> nova e de seguida utiliza o método putAll(HashMap<key, Val>) que recebe
	 *  como argumento o resultado do método <code>getCatUsers() e insere todos os elementos deste
	 *  <code>HashMap<Long, User></code> e insere-os em <code>catUsers</code>
	 *
	 * @param cat Objecto da classe <code>CatUsers</code> correspondente ao objecto que se pretende copiar.
	 * @see #getCatUsers
	 * @see java.util.HashMap#HashMap()
	 * @see java.util.HashMap
	 */
	public CatUsers(CatUsers cat) {
		this.catUsers = new HashMap<Long,User>();
		catUsers.putAll(cat.getCatUsers());
	}

	/**
	 * Método de acesso ao parâmetro catUsers.
	 * <p>
	 * Atravéz de uma complexa cadeia de manipulações do parâmetro catUsers, retorna uma cópia da
	 * <code>catUsers</code> do objecto associado.
	 * <p>
	 * <code>this.catUsers.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap<Long, User> catUsers</code>.
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
	 * <code>e -> e.getValue().clone()</code> extrai o value do tipo <code>User</code> e clona esse
	 *  value atravéz do método <code>clone</code> da classe <code>User</code>.
	 *
	 * @return um <code>HashMap<Long,User></code> correspondente a uma cópia de <code>catUsers</code>.d	     * @see engine.Users.clone()
	 */
	public Map<Long,User> getCatUsers() {
		return this.catUsers.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}

	/**
	 * Método de acesso a um User expecífico presente no <code>HashMap<Long,User></code> a partir do id.
	 * <p>
	 * Utiliza o método <code>get(Key)</code> da classe HashMap aplicado a <code>catUsers</code> que
	 * mapeia o <code>HashMap</code> e retorna o User com o id igual ao passado por argumento.
	 *
	 * @param id id do user que se pretende mapear.
	 * @return O user cuja chave (id) corresponde ao id de argumento.
	 * @see java.util.HashMap#get
	 */
	public User getUser(Long id) {
		return this.catUsers.get(id);
	}

	/**
	 * Método de cálculo do tamanho de <code>catUsers</code> i.e. nº de users.
	 * <p>
	 * Utiliza o método size() da classe <code>HashMap</code> que retorna um valor int do tamanho
	 * de <code>catUsers</code> que de seguida é tranformado num <code>long</code>.
	 * 
	 * @return o resultado do método <code>size()</code> em formato <code>long</code>.
	 * @see java.util.HashMap#size()
	 */
	public long getSizeCatU(){
		return (long) this.catUsers.size();
 	}

 	/**
	 * Método de escrita do parâmetro <code>catUsers</code> de <code>CatUsers</code>.
	 * <p>
	 * Atravéz de um mecanismo semelhante ao descrito no método {@link #getCatUsers}
	 * este método iguala <code>catUsers</code> a uma cópia gerada a partir do <code>HashMap</code> cat.
	 * <p>
	 * <code>this.catUsers.entrySet()</code> cria um set iterável a partir de
	 *  <code>HashMap catUsers</code>.
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
	 * <code>e -> e.getValue().clone()</code> extrai o value do tipo <code>User</code> e clona esse
	 *  value atravéz do método <code>clone</code> da classe <code>User</code>.
	 * <p>
	 * this.catUsers = ... iguala <code>catUsers</code> ao resultado do processo anterior após à sua
	 * conversão em <code>HashMap</code>
	 */
	public void setCatUsers(HashMap<Long,User> cat) {
		this.catUsers = (HashMap<Long,User>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue().clone()));
	}

	/**
	 * Método de inserção de um User em catUsers.
	 * <p>
	 * Atravéz da utilização do método <code>put(key, value), que recebe como chave o id
	 * do <code>User u</code> extraido pelo método <code>getUserID()</code> e como valor 
	 * o <code>User u</code>, inserindo este elemento no <code>HashMap<Long,User> catUsers</code>
	 *
	 * @param u Utilizador a inseir.
	 */
	public void insereUser(User u) {
		this.catUsers.put(u.getUserID(),u);
	}

	/**
	 * Método de teste da existência de um User atravéz do seu id.
	 * <p>
	 * Retorna o resultado do método <code>containsKey</code> aplicado a catUsers que recebe como
	 * argumento o id do user a procurar.
	 *
	 * @param id Valor <code>Long</code> do user a procurar.
	 * @return true caso esse id exista em <code>catUsers</code>
	 * @return false caso não exista.
	 * @see java.util.HashMap#containsKey
	 * @see java.lang.Long
	 */
	public boolean existeUser(Long id) {
		return this.catUsers.containsKey(id);
	}

	/**
	 * Método de limpeza de catClears.
	 * <p>
	 * Aplica o método <code>clear()</code> a catUsers.
	 */
	public void clearCatUsers() {
		this.catUsers.clear();
	}



}
