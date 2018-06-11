/**
 * Classe de utilizadores User contedo a informação relativa a um user.
 *
 * @author António Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @version 1.1
 */

package engine;

import java.io.Serializable;
import java.lang.Long;

public class User implements Serializable {
	private Long id;
	private String name;
	private String bio;
	private int rep;
	private int views;
	private int voteDif;
	private int nrPosts;

	/**
	 * Construtor por omissão da classe User.
	 * <p>
	 * Inicializa todos os parâmetros do objecto <code>User</code> associado a um valor default.
	 *
	 * @param id Parâmetro <code>Long</code> correspondente ao idêntificador do user.
	 *           inicializado a <code>null</code> de forma a evitar leitura de "falsos" <code>id</code>
	 *           na sua respectiva <code>HashMap</code>.
	 * @param name Parâmetro <code>String</code> correspondente ao username, inicializado
	 *             a uma <code>String</code> vazia.
	 * @param bio Parâmetro <code>String</code> correspondente ao bio, inicializado
	 *            a uma <code>String</code> vazia.
	 * @param rep Parâmetro <code>int</code> correspondente à reputação do user, inicializado
	 *            a 0 (valor mínimo possível).
	 * @param views Parâmetro <code>int</code> correspondente às views, inicializado
	 *            a 0 (valor mínimo possível).
	 * @param voteDif Parâmetro <code>int</code> correspodente à diferença entre os upvotes
	 *                e os downvotes, inicializado a 0 (valor default).
	 * @param nrPosts Parâmetro <code>int</code> correspondente ao número total
	 *                de posts efetuados pelo user, inicializado a 0 (valor mínimo possível).
	 * @see lang.Long
	 * @see lang.String
	 */
	public User() {
		this.id = null;
		this.name = "";
		this.bio = "";
		this.rep = 0;
		this.views = 0;
		this.voteDif = 0;
		this.nrPosts = 0;
	}

	/**
	 * Construtor parametrizado da classe User.
	 * <p>
	 * Inicializa cada parâmetro de um objecto da classe User segundo um conjunto de argumetos
	 * do mesmo tipo.
	 *
	 * @param id Parâmetro <code>Long</code> correspondente ao idêntificador do user, 
	 *           inicializado como sendo igual a <code>Long id</code> passado por argumento.
	 * @param name Parâmetro <code>String</code> correspondente ao username, inicializado
	 *             igual a uma <code>String name</code> passada por argumento.
	 * @param bio Parâmetro <code>String</code> correspondente ao bio, inicializado
	 *            igual a uma <code>String bio</code> passada por argumento.
	 * @param rep Parâmetro <code>int</code> correspondente à reputação do user, inicializado
	 *            igual a um <code>int</code> passado por argumento.
	 * @param views Parâmetro <code>int</code> correspondente às views, inicializado
	 *            igual a um <code>int views</code> passado por argumento.
	 * @param voteDif Parâmetro <code>int voteDif</code> correspodente à diferença entre os upvotes
	 *                e os downvotes inicializado igual a um <code>int voteDif</code>
	 *                passado por argumento.
	 * @param nrPosts Parâmetro <code>int nrPosts </code> correspondente ao número total de posts
	 *                efetuados pelo user, inicializado a 0 pois estes têm o objectivo de serem
	 *                incrementados atravéz de um método desta classe.
	 * @see lang.Long
	 * @see lang.String
	 * @see engine
	 */
	public User(Long id, String name, String bio, int rep, int views, int voteDif) {
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.rep = rep;
		this.views = views;
		this.voteDif = voteDif;
		this.nrPosts = 0;
	}

	/**
	 * Construtor por cópia da classe User.
	 * <p>
	 * Inicializa cada parâmetro de um objecto como sendo iguais aos parâmetros de um objecto
	 * <code>User user</code> passado por argumento, utiliza os métodos get da cpasse User.
	 *
	 * @param id Parâmetro <code>Long</code> correspondente ao idêntificador do user, 
	 *           inicializado como sendo igual ao <code>id</code> do objecto <code>User user</code>
	 *           passado por argumento.
	 * @param name Parâmetro <code>String</code> correspondente ao username, inicializado
	 *             igual ao <code>name</code> do objecto <code>User user</code> passado por argumento.
	 * @param bio Parâmetro <code>String</code> correspondente ao bio, inicializado
	 *            igual ao <code>bio</code> do objecto <code>User user</code> passado por arguemento.
	 * @param rep Parâmetro <code>int</code> correspondente à reputação do user, inicializado
	 *            igual à <code>rep</code> do objecto <code>User user</code> passado por argumento.
	 * @param views Parâmetro <code>int</code> correspondente às views, inicializado
	 *            igual às <code>views</code> do objecto <code>User user</code> passado por argumento.
	 * @param voteDif Parâmetro <code>int</code> correspodente à diferença entre os upvotes
	 *                e os downvotes inicializado igual a <code>voteDif</code> do objecto
	 *                <code>User user</code> passado por argumento.
	 * @param nrPosts Parâmetro <code>int</code> correspondente ao número total de posts
	 *                efetuados pelo user, inicializado igual ao <code>nrPosts</code>
	 *                do objecto <code>User user</code> passado por argumento.
	 * @see #getUserID()
	 * @see #getName()
	 * @see #getBio()
	 * @see #getRep()
	 * @see #getViews()
	 * @see #getVotes()
	 * @see #getNrPosts()
	 * @see lang.Long
	 * @see lang.String
	 * @see engine
	 */
	public User(User user) {
		this.id = user.getUserID();
		this.name = user.getName();
		this.bio = user.getBio();
		this.rep = user.getRep();
		this.views = user.getViews();
		this.voteDif = user.getVotes();
		this.nrPosts = user.getNrPosts();
	}


	// Getters
	/**
	 * Método de acesso ao parâmetro <code>id</code> do tipo <code>Long</code>.
	 * @return o <code>id</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 * @see lang.Long
	 */
	public Long getUserID() {
		return this.id;
	}

	/**
	 * Método de acesso ao parâmetro <code>name</code> do tipo <code>String</code>.
	 * @return o <code>name</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 * @see lang.String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Método de acesso ao parâmetro <code>bio</code> do tipo <code>String</code>.
	 * @return o <code>bio</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 * @see lang.String
	 */
	public String getBio() {
		return this.bio;
	}

	/**
	 * Método de acesso ao parâmetro <code>rep</code> do tipo <code>int</code>.
	 * @return o <code>rep</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 */
	public int getRep() {
		return this.rep;
	}

	/**
	 * Método de acesso ao parâmetro <code>views</code> do tipo <code>int</code>.
	 * @return o <code>views</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 */
	public int getViews() {
		return this.views;
	}

	/**
	 * Método de acesso ao parâmetro <code>voteDif</code> do tipo <code>int</code>.
	 * @return o <code>voteDif</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 */
	public int getVotes() {
		return this.voteDif;
	}

	/**
	 * Método de acesso ao parâmetro <code>nrPosts</code> do tipo <code>int</code>.
	 * @return o <code>nrPosts</code> da corrente classe para que outros métodos de outras
	 *         classes o possam aceder.
	 */
	public int getNrPosts() {
		return this.nrPosts;
	}

	// Setters
	/**
	 * Método de escrita do parâmetro <code>id</code> do tipo <code>Long</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>Long</code> como argumento e iguala o <code>id</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param id Parâmetro do tipo <code>Long</code> passado por argumento.
	 * @see lang.Long
	 */
	public void setUserID(Long id) {
		this.id = id;
	}

	/**
	 * Método de escrita do parâmetro <code>name</code> do tipo <code>String</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>String</code> como argumento e iguala o <code>name</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param name Parâmetro do tipo <code>String</code> passado por argumento.
	 * @see lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método de escrita do parâmetro <code>bio</code> do tipo <code>String</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>String</code> como argumento e iguala o <code>bio</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param bio Parâmetro do tipo <code>String</code> passado por argumento correspondente
	 *            a uma pequena descrição dada pelo utilizador sobre si mesmo.
	 * @see lang.String
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * Método de escrita do parâmetro <code>rep</code> do tipo <code>int</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>int</code> como argumento e iguala a <code>rep</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param rep Parâmetro do tipo <code>int</code> passado por argumento.
	 */
	public void setRep(int rep) {
		this.rep = rep;
	}

	/**
	 * Método de escrita do parâmetro <code>views</code> do tipo <code>int</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>int</code> como argumento e iguala as <code>views</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param views Parâmetro do tipo <code>int</code> passado por argumento.
	 */
	public void setViews(int views) {
		this.views = views;
	}

	/**
	 * Método de escrita do parâmetro <code>voteDif</code> do tipo <code>int</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>int</code> como argumento e iguala as <code>voteDif</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param voteDif Parâmetro do tipo <code>int</code> passado por argumento, correspondente
	 *                à diferença entre upvotes e downvotes do user.
	 */
	public void setVotes(int voteDif) {
		this.voteDif = voteDif;
	}

	/**
	 * Método de escrita do parâmetro <code>nrPosts</code> do tipo <code>int</code>.
	 * <p>
	 * Recebe um parâmetro do tipo <code>int</code> como argumento e iguala os <code>nrPosts</code>
	 *  da classe corrente ao parâmetro recebido por argumento.
	 *
	 * @param nrPosts Parâmetro do tipo <code>int</code> passado por argumento.
	 */
	public void incNrPosts() {
		this.nrPosts++;
	}

	/**
	 * Método de comparação da classe User.
	 * <p>
	 * Recebe como argumento um objecto de qualquer tipo que será o alvo da comparação
	 * com um user retornando um <code>boolean</code> como resultado da comparação.
	 * 
	 * @param obj Objecto que pode pertencer a qualquer classe. Este objecto será
	 *            o alvo da comparação.
	 * @return <code>true</code> caso os objectos sejam o mesmo (se ambos tiverem o mesmo
	 *         endereço de memória / apontador).
	 * @return <code>false</code> caso o objecto tenha apontador <code>null</code>.
	 * @return <code>false</code> se o objecto não for da classe User.
	 * @return <code>true</code> caso todos os parâmetros do objecto passado como
	 *         parâmetro forem iguais aos parâmetros do user ao qual este é comparado.
	 * @see lang.Object#getClass()
	 * @see lang.String#equals()
	 * @see lang.Long
	 * @see lang.String
	 * @see lang.Object
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		User u = (User) obj;
		return u.getUserID() == this.id
		&& u.getName().equals(name)
		&& u.getBio().equals(bio)
		&& u.getRep() == this.rep
		&& u.getViews() == this.views
		&& u.getVotes() == this.voteDif;
	}

	/**
	 * Método de cópia da classe user.
	 * Utiliza o construtor por cópia da classe para criar um novo User como sendo igual ao
	 *  <code>User</code> associado e retorna a cópia.
	 *
	 * @return um <code>user</code> que é uma cópia do <code>user</code> associado.
	 * @see #User(User)
	 */
	public User clone() {
		return new User(this);
	}
}
