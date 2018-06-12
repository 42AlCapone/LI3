/**
 * Classe de Perguntas contendo informação relativa a uma perguta e suas respostas ou user associado.
 *
 * @author Antómio Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @Version 1.4
 */

package engine;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.HashSet;
import java.util.Comparator;
import java.lang.Long;

public class Pergunta implements Serializable {
	private Long id;
	private LocalDate creationDate;
	private int score;
	private Long ownerID;
	private String title;
	private Set<String> tags;
	private int nrComments;
	private Set<Resposta> respostas;

	/**
	 * Construtor por ommissão da classe <code>Pergunta</code>.
	 * <p>
	 * Inicializa todos os parâmetros do objecto da classe Pergunta a um valor default.
	 * <p>
	 * id Parâmetro do tipo <code>Long</code> inicializado a <code>null</code>
	 * de forma a evitar leituras falsas na respectiva <code>HashMap</code>.
	 * <p>
	 * creationDate Parâmetro do tipo <code>LocalDate</code> inicializado a <code>null</code>.
	 * <p>
	 * score Parâmetro <code>int</code> inicializado a 0 (valor mínimo).
	 * onwerID Parâmetro <code>Long</code> inicializado a <code>null</code> de forma
	 *                a evitar leituras falsas na respectiva <code>HashMap</code>.
	 * <p>
	 * title Parâmetro do tipo <code>String</code> correspondente ao título da pergunta,
	 *              inicializado a uma <code>String</code> vazia.
	 * <p>
	 * tags Parâmetro correspondente às tags associadas à pergunta, as tags são armazenadas
	 *             na mesma <code>String</code>, esta é inicializada a uma <code>String</code> vazia.
	 * <p>
	 * nrComments Parâmetro do tipo <code>int</code> correspondente ao total de comentários
	 *                   que a pergunta recebeu, inicializado a 0 (valor mínimo).
	 * <p>
	 * respostas Parâmetro do tipo <code>HashSet</code> de objectos <code>Resposta</code>
	 *                  inicializado atravéz do construtor {@link java.util.HashSet#HashSet}, 
	 *                  construtor por omissão da classe <code>HashSet</code>.
	 * @see java.lang.Long
	 * @see java.lang.String
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public Pergunta() {
		this.id = null;
		this.creationDate = null;
		this.score = 0;
		this.ownerID = null;
		this.title = "";
		this.tags = new HashSet<String>();
		this.nrComments = 0;
		this.respostas = new HashSet<Resposta>();
	}

	/**
	 * Construtor parametrizado da classe <code>Pergunta</code>.
	 * <p>
	 * Inicializa todos os parâmetros do objecto da classe Pergunta igual a parâmetros
	 *  do mesmo tipo passados por argumento.
	 *
	 * @param id Parâmetro do tipo <code>Long</code> inicializado a um <code>Long id</code>
	 *           passado por argumento.
	 * @param creationDate Parâmetro do tipo <code>LocalDate</code> inicializado igual a 
	 *                     uma <code>LocalDate creationDate</code> passada por argumento.
	 * @param score Parâmetro <code>int</code> inicializado igual a um <code>int score</code>
	 *              passado por argumento.
	 * @param ownerID Parâmetro <code>Long</code> inicializado a um <code>Long ownerID</code>
	 *                pasado por argumento.
	 * @param title Parâmetro do tipo <code>String</code> correspondente ao título da pergunta,
	 *              inicializado igual a um <code>String title</code> passado por argumento.
	 * @param tags Parâmetro correspondente às tags associadas à pergunta, as tags são armazenadas
	 *             na mesma <code>String</code>, esta é inicializada a uma <code>String tags</code>
	 *             passada por argumento.
	 * @param nrComments Parâmetro do tipo <code>int</code> correspondente ao total de comentários
	 *                   que a pergunta recebeu, inicializado igual a um <code>int nrComments</code>
	 *                   passado por argumento.
	 * @param respostas Parâmetro do tipo <code>HashSet</code> de objectos <code>Resposta</code>
	 *                  inicializado igual a um <code>set<Resposta></code> passada por arguemnto.
	 *                  esta igualdade torna o apontador para o parâmetro respostas do objecto
	 *                  pergunta associado igual ao apontador de <code>Set<Resposta> respostas</code>
	 *                  passado por argumento.
	 * @see java.lang.Long
	 * @see java.lang.String
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public Pergunta(Long id, LocalDate creationDate, int score, Long ownerID, String title, 
						HashSet<String> tags, int nrComments, Set<Resposta> respostas) {
		this.id = id;
		this.creationDate = creationDate;
		this.score = score;
		this.ownerID = ownerID;
		this.title = title;
		this.tags = tags;
		this.nrComments = nrComments;
		this.respostas = respostas;
	}

	/**
	 * Construtor por cópia da classe <code>Pergunta</code>.
	 * <p>
	 * Inicializa todos os parâmetros do objecto da classe Pergunta igual aos parâmetros
	 *  de uma Pergunta passada por argumento. utiliza os métodos get da classe para aceder aos
	 *  parâmetros da pergunta de argumento.
	 * <p>
	 * id Parâmetro do tipo <code>Long</code> inicializado a um <code>Long id</code>,
	 *           parâmetro do objecto <code>Pergunta perg</code> passado por argumento.
	 * <p>
	 * creationDate Parâmetro do tipo <code>LocalDate</code> inicializado igual a 
	 *                     uma <code>LocalDate creationDate</code>, parâmetro da do objecto
	 *                     <code>Pergunta perg</code> passado por argumento.
	 * <p>
	 * score Parâmetro <code>int</code> inicializado igual a um <code>int score</code>,
	 *              parâmetro do objecto <code>Pergunta perg</code> passado por argumento.
	 * <p>
	 * onwerID Parâmetro <code>Long</code> inicializado a um <code>Long ownerID</code>,
	 *                parâmetro do objecto <code>Pergunta perg</code> pasado por argumento.
	 * <p>
	 * title Parâmetro do tipo <code>String</code> correspondente ao título da pergunta,
	 *              inicializado igual a um <code>String title</code>, parâmetro do objecto
	 *              <code>Pergunta perg</code> passado por argumento.
	 * <p>
	 * tags Parâmetro correspondente às tags associadas à pergunta, as tags são armazenadas
	 *             na mesma <code>String</code>, esta é inicializada a uma <code>String tags</code>,
	 *             parâmetro do objecto <code>Pergunta perg</code> passada por argumento.
	 * <p>
	 * nrComments Parâmetro do tipo <code>int</code> correspondente ao total de comentários
	 *                   que a pergunta recebeu, inicializado igual a um <code>int nrComments</code>,
	 *                   parâmetro do objecto <code>Pergunta perg</code> passado por argumento.
	 * <p>
	 * respostas Parâmetro do tipo <code>HashSet</code> de objectos <code>Resposta</code>
	 *                  inicializado igual a um <code>set<Resposta></code>, parâmetro do objecto
	 *                  <code>Pergunta perg</code> passada por arguemnto.
	 *
	 * @see #getPergID()
	 * @see #getPergDate()
	 * @see #getScoreP()
	 * @see #getOwnerIDp()
	 * @see #getTitle()
	 * @see #getTags()
	 * @see #getCommentsP()
	 * @see #getRespostas()
	 * @see java.lang.Long
	 * @see java.lang.String
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public Pergunta(Pergunta perg) {
		this.id = perg.getPergID();
		this.creationDate = perg.getPergDate();
		this.score = perg.getScoreP();
		this.ownerID = perg.getOwnerIDp();
		this.title = perg.getTitle();
		this.tags = perg.getTags();
		this.nrComments = perg.getCommentsP();
		this.respostas = perg.getRespostas();
	}

	// Getters
	/**
	 * Método de acesso ao parâmetro <code>id</code> do tipo <code>Long</code> da classe Pergunta.
	 * <p>
	 * Retorna o um <code>Long</code> correspondente ao <code>id</code> relativo ao objecto associado.
	 *
	 * @return o <code>id</code> do objecto associado.
	 * @see java.lang.Long
	 */
	public Long getPergID() {
		return this.id;
	}

	/**
	 * Método de acesso ao parâmetro <code>creationDate</code> do tipo <code>LocalDate</code> 
	 * da classe Pergunta.
	 * <p>
	 * Retorna o um <code>LocalDate</code> correspondente ao <code>creationDate</code> relativo 
	 * ao objecto associado.
	 *
	 * @return o <code>creationDate</code> do objecto associado.
	 * @see java.time.LocalDate
	 */
	public LocalDate getPergDate() {
		return this.creationDate;
	}

	public int getScoreP() {
		return this.score;
	}

	/**
	 * Método de acesso ao parâmetro <code>ownerID</code> do tipo <code>Long</code> da classe Pergunta.
	 * <p>
	 * Retorna o um <code>Long</code> correspondente ao <code>ownerID</code> relativo ao objecto associado.
	 *
	 * @return o <code>ownerID</code> do objecto associado.
	 * @see java.lang.Long
	 */
	public Long getOwnerIDp() {
		return this.ownerID;
	}

	/**
	 * Método de acesso ao parâmetro <code>title</code> do tipo <code>String</code> da classe Pergunta.
	 * <p>
	 * Retorna o uma <code>String</code> correspondente ao <code>title</code> relativo ao objecto associado.
	 *
	 * @return o <code>title</code> do objecto associado.
	 * @see java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}


	/**
	 * Método de acesso ao parâmetro <code>tag</code> do tipo <code>String</code> da classe Pergunta.
	 * <p>
	 * Retorna o uma <code>String</code> correspondente à string de <code>tags</code> relativas 
	 *  ao objecto associado, esta string contém todas as tags associadas à pergunta.
	 *
	 * @return as <code>tags</code> do objecto associado.
	 * @see java.lang.String
	 */
	public Set<String> getTags() {
		return this.tags;
	}

	/**
	 * Método de acesso ao parâmetro <code>nrComments</code> do tipo <code>int</code> da classe Pergunta.
	 * <p>
	 * Retorna o um <code>int</code> correspondente ao <code>nrComments</code> relativo ao objecto 
	 * associado.
	 *
	 * @return o <code>nrComments</code> do objecto associado.
	 */
	public int getCommentsP() {
		return this.nrComments;
	}

	/**
	 * Método de acesso ao parâmetro <code>respostas</code> do tipo <code>Set<Resposta></code> 
	 * da classe Pergunta.
	 * <p>
	 * Retorna o um <code>Set<Resposta></code> correspondente ao set <code>respostas</code> relativo ao 
	 * objecto associado. 
	 *
	 * @return o set <code>respostas</code> do objecto associado, o set é do tipo <code>HashSet</code>.
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public Set<Resposta> getRespostas() {
		return this.respostas;
	}

	/**
	 * Método de cálculo do tamanho do set <code>respostas</code>, set pertencente à classe pergunta.
	 * <p>
	 * Atravéz do método size() da classe Set, <code>getNrResp()</code> retorna o resultado de 
	 *  aplicar size() associado a uma pergunta.
	 *
	 * @return Um <code>int</code> correspondente ao resultado de <code>size()</code> aplicado ao set
	 *         <code>respostas</code>
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public int getNrResp(){
		return this.respostas.size();
	}

	// Setters

	/**
	 * Método de escrita do parâmetro <code>id</code> do tipo <code>Long</code>.
	 * <p>
	 * Recebe um <code>Long id</code> como argumento e torna o <code>id</code> da pergunta 
	 *  associada igual a este.
	 *
	 * @param id Parâmetro do tipo <code>Long</code> passado por argumento.
	 * @see java.lang.Long
	 */
	public void setPergID(Long id) {
		this.id = id;
	}

	/**
	 * Método de escrita do parâmetro <code>creationDate</code> do tipo <code>LocalDate</code>.
	 * <p>
	 * Recebe um <code>LocalDate creationDate</code> como argumento e torna o <code>creationDate</code>
	 * da pergunta associada igual a este.
	 *
	 * @param creationDate Parâmetro do tipo <code>LocalDate</code> passado por argumento.
	 * @see java.time.LocalDate
	 */
	public void setPergDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Método de escrita do parâmetro <code>score</code> do tipo <code>int</code>.
	 * <p>
	 * Recebe um <code>int score</code> como argumento e torna o <code>score</code> da pergunta 
	 *  associada igual a este.
	 *
	 * @param score Parâmetro do tipo <code>int</code> passado por argumento.
	 */
	public void setScoreP(int score) {
		this.score = score;
	}

	/**
	 * Método de escrita do parâmetro <code>ownerID</code> do tipo <code>Long</code>.
	 * <p>
	 * Recebe um <code>Long ownerID</code> como argumento e torna o <code>ownerID</code> da pergunta 
	 *  associada igual a este.
	 *
	 * @param ownerID Parâmetro do tipo <code>Long</code> passado por argumento.
	 * @see java.lang.Long
	 */
	public void setOwnerIDp(Long ownerID) {
		this.ownerID = ownerID;
	}

	/**
	 * Método de escrita do parâmetro <code>title</code> do tipo <code>String</code>.
	 * <p>
	 * Recebe um <code>String title</code> como argumento e torna o <code>title</code> da pergunta 
	 *  associada igual a este.
	 *
	 * @param title Parâmetro do tipo <code>String</code> passado por argumento.
	 * @see java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	public void setHashTags(HashSet<String> tags) {
		this.tags = tags;
	}

	/**
	 * Método de escrita do parâmetro <code>tags</code> do tipo <code>String</code>.
	 * <p>
	 * Recebe uma <code>String tags</code> como argumento e torna as <code>tags</code> da pergunta 
	 *  associada igual a esta.
	 *
	 * @param tag Parâmetro do tipo <code>String</code> passado por argumento. Todas as tags da
	 *             pergunta estão na mesma string.
	 * @see java.lang.String
	 */
	public void setTag (String tag){
		this.tags.add(tag);
	}

	/**
	 * Método de escrita do parâmetro <code>nrComments</code> do tipo <code>int</code>.
	 * <p>
	 * Recebe um <code>int nrComments</code> como argumento e torna o <code>nrComments</code> 
	 *  da pergunta associada igual a este.
	 *
	 * @param nrComments Parâmetro do tipo <code>int</code> passado por argumento.
	 */

	public void setCommentsP(int nrComments) {
		this.nrComments = nrComments;
	}

	/**
	 * Método de escrita do parâmetro <code>respostas</code> do tipo <code>Set<Resposta></code>.
	 * <p>
	 * Recebe um <code>Set<Resposta> respostas</code> como argumento e torna o <code>id</code> da 
	 *  pergunta associada igual a este.
	 *
	 * @param respostas Parâmetro do tipo <code>Set<Resposta> respostas</code> passado por argumento.
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public void setResp(Set<Resposta> respostas) {
		this.respostas = respostas;
	}

	/**
	 * Método de inserção de uma <code>resposta</code> no <code>Set<Resposta></code>.
	 * <p>
	 * Esta inserção é efectuada atravéz da utilização do método add(Resposta) da classe Set aplicado
	 *  ao parâmetro <code>respostas</code> do objecto associado.
	 *
	 * @param r <code>Resposta</code> passada como parâmetro.
	 * @see java.util.HashSet#add
	 * @see java.util.HashSet
	 * @see java.util.Set
	 */
	public void setResposta(Resposta r) {
		this.respostas.add(r);	
		
	}

	/**
	 * Método de comparação entre objectos do tipo pergunta e um objecto <code>obj</code> passado
	 *  por argumento.
	 *  <p>
	 * Retorna <code>true</code> caso <code>obj</code> seja igual à pergunta, i.e. se ambos forem o
	 *  mesmo apontador ou caso todos os parâmetros de obj sejam iguais a cada parâmetro da pergunta.
	 *
	 * @param obj <code>Object</code> alvo da comparação.
	 * @return <code>true</code> caso <code>obj</code> seja igual à pergunta (mesmo objecto).
	 * @return <code>true</code> caso todos os parâmetros de <code>obj</code> forem iguais aos parâmetros
	 *         de <code>obj</code>.
	 * @return <code>false</code> caso <code>obj</code> seja nulo ou caso <code>obj</code> pertença
	 *         a uma classe diferente de pergunta.
	 * @return <code>false</code> caso alguma das comparações entre parâmetros retornar <code>false</code>  	 
	 * @see java.lang.Long
	 * @see java.lang.String
	 * @see java.lang.Object
	 * @see java.util.Set
	 * @see java.util.HashSet
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Pergunta p = (Pergunta) obj;
		return p.getPergID() == this.id
		&& p.getPergDate() == this.creationDate
		&& p.getScoreP() == this.score
		&& p.getOwnerIDp() == this.ownerID
		&& p.getTitle().equals(this.title)
		&& p.getTags().equals(this.tags)
		&& p.getCommentsP() == this.nrComments;
	}
	/**
	 * Método de cópia da classe pergunta.
	 * <p>
	 * Utiliza o construtor por cópia {@link #Pergunta(Pergunta)} para criar uma pergunta que é uma
	 *  cópia da pergunta associada.
	 *
	 * @return <code>Pergunta(this)</code> sendo uma cópia da pergunta associada.
	 * @see #Pergunta(Pergunta)
	 */
	public Pergunta clone() {
		return new Pergunta(this);
	}
}
