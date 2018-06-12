/**
 * Classe expecífica de contagem de utilizações de tags.
 *
 * @author António Gomes
 * @author João Vieira
 * @author Rui Vieira
 * @version 1.1
 */

package engine;

import java.io.Serializable;
import java.lang.Long;
import java.util.Map;
import java.util.HashMap;

public class Tag implements Serializable {
	private String name;
	private Long tagUses;
	
	/**
	 * Construtor por omissão da classe <code>Tag</code>
	 * <p>
	 * Iguala <code>tagUses</code> a 0 em <code>Long</code>
	 *
	 * @see java.lang.Long
	 */
	public Tag() {
		this.name = null;
		this.tagUses = 0L;
		}


	
	/**
	 * Construtor parametrizado da classe <code>Tag</code>
	 * <p>
	 * Iguala <code>tagUses</code> a um argumento <code>Long tagUses</code>
	 *
	 * @see java.lang.Long
	 */
	public Tag(String name, Long tagUses) {
		this.name = name;
		this.tagUses = tagUses;
		}

	/**
	 * Construtor por cópia da classe <code>Tag</code>
	 * <p>
	 * Iguala <code>tagUses</code> a <code>tagUses</code> do objecto <code>Tag tag</code> passado
	 * por argutmento atravéz do uso do método getTagUses();
	 * 
	 * @see #getTagUses()
	 * @see java.lang.Long
	 */
	public Tag(Tag tag) {
		this.name = tag.getTagName();
		this.tagUses = tag.getTagUses();
		}


	// Getters


	/**
	 * Método de acesso a <code>tagUses</code>.
	 * 
	 * @return As tagUses do objecto associado.
	 */

	public Long getTagUses() {
		return this.tagUses;
	}

	public String getTagName() {
		return this.name;
	}

	// Setters
	public void setTagName(String name) {
		this.name = name;
	}

	/**
	 * Método de escrita das <code>tagUses</code>.
	 * <p>
	 * Iguala as <code>tagUses</code> a um <code>Long uses</code> passado por argumento.
	 *
	 * @see java.lang.Long
	 */
	public void setTagUses(Long uses) {
		this.tagUses = uses;
	}

	/**
	 * Método de incremento das <code>tagUses</code>
	 * <p>
	 * Incrementa as <code>tagUses</code> por um.
	 */
	public void incTagUse() {
		this.tagUses++;
	}
	
	/**
	 * Método de comparação entre um objecto e uma <code>Tag</code> (tag use).
	 * @return true se <code>obj</code> for igual à tag associada
	 * @return true caso <code>obj</code> seja uma <code>Tag</code> não nula e caso as 
	 * <code>tagUses</code> do objecto forem iguais às tagUses da <code>Tag</code> associada.
	 * @return false caso a comparação das <code>tagUses</code> falhar.
	 * @return false caso <code>obj</code> for nulo.
	 * @return false caso <code>obj</code> não pertença à classe <code>Tag</code>
	 * @see java.lang.Object#getClass()
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Tag t = (Tag) obj;
		return t.getTagName()==this.name 
		&& t.getTagUses() == this.tagUses;
		}

	/**
	 * Método de cópia da classe <code>Tag</code>.
	 * <p>
	 * Atravéz da utilização do construtor por cópia <code>Tag(Tag)</code>
	 * este método retorna uma nova tag que é uma exata cópia da <code>Tag</code> associada.
	 *
	 * @return Uma <code>Tag</code> que é uma cópia da <code>Tag</code> associada.
	 * @see #Tag(Tag)
	 */
	public Tag clone() {
		return new Tag(this);
	}
}
