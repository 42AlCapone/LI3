/**
 * Classe de utilizadores User contedo a informação relativa a um user.
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
	 * <code>User user</code> passado por argumento.
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

	public Long getUserID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getBio() {
		return this.bio;
	}

	public int getRep() {
		return this.rep;
	}

	public int getViews() {
		return this.views;
	}

	public int getVotes() {
		return this.voteDif;
	}

	public int getNrPosts() {
		return this.nrPosts;
	}

	// Setters

	public void setUserID(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setRep(int rep) {
		this.rep = rep;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public void setVotes(int voteDif) {
		this.voteDif = voteDif;
	}

	public void incNrPosts() {
		this.nrPosts++;
	}


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

	public User clone() {
		return new User(this);
	}
}
