/**
 * Classe Resposta contendo a informação relativa a uma resposta.
 *
 * @author António Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @Version 1.2
 */

package engine; 

import java.io.Serializable;
import java.time.LocalDate;


public class Resposta implements Serializable {
	private Long id;
	private Long parentID;
	private LocalDate creationDate;
	private int score;
	private Long ownerID;
	private int nrComments;
	private double rate;


	/**
	 * Construtor por omissão da classe Resposta.
	 *
	 * Inicializa cada parametro da classe resposta a <code>null</code>.
	 *  ou a um valor default.
	 * <p>
	 * id Parâmetro <code>Long</code> inicializado a <code>null</code> para 
	 *           evitar leituras de ids "falsos" na respectiva <code>HashMap</code>.
	 * <p>
	 * parentID Parâmetro <code>Long</code> inicializado a <code>null</code> para
	 *                 evitar leituras de ids "falsos" na respectiva <code>HashMap</code>.
	 * <p>
	 * creationDate Data sob o formato da classe {@link LocalDate} nativa do java.
	 * <p>
	 * score parâmetro <code>int</code> inicializado a 0 (valor mínimo possível)
	 * <p>
	 * ownerID Parâmetro <code>Long</code> ininializado a <code>null</code> para
	 *                evitar a leitura de ids "falsos" na respectiva <code>HashMap</code>.
	 * <p>
	 * nrComments Parâmetro <code>int</code> inicializado a 0 (valor mínimo possível)
	 * <p>
	 * rate Parâmetro <code>double</code> inicializado a 0.0 (valor mínimo possível)
	 * @see java.time.LocalDate
	 * @see java.lang.Long
	 */
	public Resposta() {
		this.id = null;
		this.parentID = null; 
		this.creationDate = null;
		this.score = 0;
		this.ownerID = null;
		this.nrComments = 0;
		this.rate = 0.0;
	}

	/**
	 * Construtor parameterizado da classe Resposta.
	 * Inicializa cada parâmetro da classe resposta segundo um parâmetro do mesmo tipo
	 *  pasado por argumento.
	 * 
	 * @param id Parâmetro <code>Long</code> inicializado a <code>Long id</code>.
	 * @param parentID Parâmetro <code>Long</code> inicializado a <code>Long ParentID</code>.
	 * @param creationDate Data sob o formato da classe {@link java.time.LocalDate} nativa do java.
	 * @param score Parâmetro <code>int</code> inicializado a <code>int score</code>.
	 * @param ownerID Valor<code>Long</code> ininializado a <code>Long ownerID</code>.
	 * @param nrComments Valor <code>int</code> inicializado a <code>int nrComments</code>.
	 * @param rate Valor <code>double</code> inicializado a <code>double rate</code>.
	 * @see java.time.LocalDate
	 * @see java.lang.Long
	 */
	public Resposta(Long id, Long parentID, LocalDate creationDate, int score, Long ownerID, int nrComments, double rate) {
		this.id = id;
		this.parentID = parentID;
		this.creationDate = creationDate;
		this.score = score;
		this.ownerID = ownerID;
		this.nrComments = nrComments;
		this.rate = rate;
	}

	/**
	 * Construtor por cópia da classe Resposta.
	 * Inicializa cada parametro da classe resposta a <code>null</code>.
	 * <p>
	 * id Parâmetro <code>Long</code> inicializado igual a <code>id</code> 
	 *           da resposta <code>resp</code>.
	 * <p> 
	 * parentID Parâmetro <code>Long</code> inicializado igual a <code>ParentID</code>
	 *                 da resposta <code>resp</code>.
	 * <p>
	 * creationDate Data sob o formato da classe {@link java.time.LocalDate} nativa do java.
	 * <p>
	 * score Parâmetro <code>int</code> inicializado igual a <code>score</code>
	 *              da resposta <code>resp</code>.
	 * <p>
	 * ownerID Parâmetro <code>Long</code> ininializado igual a <code>ownerID</code>
	 *                da resposta <code>resp</code>.
	 * <p>
	 * nrComments Parâmetro <code>int</code> inicializado igual a <code>nrComments</code>
	 *                   da resposta <code>resp</code>.
	 * <p>
	 * rate parâmetro <code>double</code> inicializado igual a <code>double rate</code>
	 *             da resposta <code>resp</code>.
	 * @param resp resposta alvo da cópia.
	 * @see #getRespID()
	 * @see #getParentID()
	 * @see #getRespDate()
	 * @see #getScoreR()
	 * @see #getOwnerIDr()
	 * @see #getCommentsR()
	 * @see #getRate()
	 * @see java.time.LocalDate
	 * @see java.lang.Long
	 *
	 */
	public Resposta(Resposta resp) {
		this.id = resp.getRespID();
		this.parentID = resp.getParentID();
		this.creationDate = resp.getRespDate();
		this.score = resp.getScoreR();
		this.ownerID = resp.getOwnerIDr();
		this.nrComments = resp.getCommentsR();
		this.rate = resp.getRate();
	}	


	// Getters

	/**
	 * Método de acesso ao parâmetro <code>id</code>.
	 *
	 * @return o <code>id</code> da corrente classe para que métodos 
	 *          de outras classes o possam aceder.
	 * @see java.lang.Long
	 */
	public Long getRespID() {
		return this.id;
	}

	/**
	 * Método de acesso ao parâmetro <code>parentID</code>.
	 *
	 * @return o <code>parentID</code> da corrente classe para que métodos 
	 *          de outras classes o possam aceder.
	 * @see java.lang.Long
	 */
	public Long getParentID() {
		return this.parentID;
	}

	/**
	 * Método de acesso ao parâmetro <code>creationDate</code>.
	 *
	 * @return a <code>creationDate</code> da corrente classe para que métodos 
	 *          de outras classes o possam aceder.
	 * @see java.time.LocalDate
	 */
	public LocalDate getRespDate() {
		return this.creationDate;
	}

	/**
	 * Método de acesso ao parâmetro <code>score</code>.
	 *
	 * @return o <code>score</code> da corrente classe para que métodos 
	 *          de outras classes o possam aceder.
	 */
	public int getScoreR() {
		return this.score;
	} 

	/**
	 * Método de acesso ao parâmetro <code>ownerID</code>.
	 *
	 * @return o <code>ownerID da corrente classe para que métodos
	 *          de outras classes o possam aceder.
	 * @see java.lang.Long
	 */
	public Long getOwnerIDr() {
		return this.ownerID;
	}

	/**
	 * Método de acesso ao parâmetro <code>nrComments</code>.
	 *
	 * @return o <code>nrComments</code> da corrente classe para que métodos
	 *          de outras c\\lasses o possam aceder.
	 */
	public int getCommentsR() {
		return this.nrComments;
	}

	/**
	 * Método de acesso ao parâmetro <code>rate</code>.
	 *
	 * @return o <code>rate</code> da corrente classe para que métodos
	 *          de outras classes o possam aceder.
	 */
	public double getRate() {
		return this.rate;
	}

	// Setters

	/**
	 * Método de escrita do parâmetro <code>id</code>.
	 * <p>
	 * Recebe <code>Long id</code> como parâmetro e define o <code>id</code> da resposta
	 *  corrente como sendo igual a este parâmetro.
	 *
	 * @param id Parâmetro <code>Long</code> passado por argumento.
	 * @see java.lang.Long
	 */
	public void setRespID(Long id) {
		this.id = id;
	}

	/**
	 * Método de escrita do parâmetro <code>parentID</code>.
	 * <p>
	 * Recebe <code>Long parentID</code> como parâmetro e define o <code>parentID</code> 
	 *  da resposta corrente como sendo igual a este parâmetro.
	 *
	 * @param parentID Parâmetro <code>Long</code> passado por argumento.
	 * @see java.lang.Long
	 */
	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	/**
	 * Método de escrita do parâmetro <code>creationDate</code>.
	 * <p>
	 * Recebe <code>LocalDate creationDate</code> como parâmetro e escreve a <code>creationDate</code>
	 *  da resposta corrente como sendo igual a este parâmetro.
	 *
	 * @param creationDate Parâmetro da classe <code>LocalDate</code> passado por argumento.
	 * @see java.time.LocalDate
	 */
	public void setRespDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Método de escrita do parâmetro <code>score</code>.
	 * <p>
	 * Recebe <code>int score</code> como parâmetro e escreve o parâmetro <code>score</code>
	 *  da resposta corrente como sendo igual a este parâmetro.
	 *
	 * @param score Parâmetro <code>int</code> passado por argumento.
	 */
	public void setScoreR(int score) {
		this.score = score;
	}

	/**
	 * Método de escrita do parâmetro <code>ownerID</code>.
	 * <p>
	 * Recebe <code>Long ownerID</code> como parâmetro e define o <code>ownerID</code> da resposta
	 *  corrente como sendo igual a este parâmetro.
	 *
	 * @param ownerID Parâmetro <code>Long</code> passado por argumento.
	 * @see java.lang.Long
	 */
	public void setOwnerIDr(Long ownerID) {
		this.ownerID = ownerID;
	}

	/**
	 * Método de escrita do parâmetro <code>nrComments</code>.
	 * <p>
	 * Recebe <code>int nrComments</code> como parâmetro e define o <code>nrComments</code> da resposta
	 *  corrente como sendo igual a este parâmetro.
	 *
	 * @param nrComments Parâmetro <code>Long</code> passado por argumento.
	 */
	public void setCommentsR(int nrComments) {
		this.nrComments = nrComments;
	}

	/**
	 * Método de escrita do parâmetro <code>rate</code>.
	 * <p>
	 * Recebe <code>double rate</code> como parâmetro e define o <code>rate</code> da resposta
	 *  corrente como sendo igual a este parâmetro.
	 *
	 * @param rate Parâmetro <code>dobule</code> passado por argumento.
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * Método de comparação entre um objecto obj e outro da classe Resposta.
	 * <p>
	 * Comparação efectuada a partir da comparação de todos os parâmetros de cada classe.
	 * Caso ambos os objectos sejam da mesma classe e todos o seus parâmetros sejam iguais
	 *  então pode-se declarar que ambos os objectos são iguais.
	 *
	 * @param obj Objecto genérico <code>Object</code> para ser comparado.
	 * @return true em caso de os objectos serem iguais
	 * @return false caso obj seja igual a <code>null</code>
	 *  ou no caso de ambos os objectos serem de classes diferentes.
	 * @return true no caso de todas as comparações entre parâmetros serem true.
	 * @see java.lang.Long
	 * @see java.time.LocalDate
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Resposta r = (Resposta) obj;
		return r.getRespID() == this.id
		&& r.getParentID() == this.parentID
		&& r.getRespDate() == this.creationDate
		&& r.getScoreR() == this.score
		&& r.getOwnerIDr() == this.ownerID
		&& r.getCommentsR() == this.nrComments
		&& r.getRate() == this.rate;
	}

	/**
	 * Método de cópia da classe Resposta.
	 * <p>
	 * Utiliza o construtor por cópia para criar uma Resposta igual e retorna essa resposta.
	 *
	 * @return Resposta nova criada pelo construtor por cópia Resposta(Resposta).
	 * @see Resposta#Resposta(Resposta).
	 */
	public Resposta clone() {
		return new Resposta(this);
	}
}
