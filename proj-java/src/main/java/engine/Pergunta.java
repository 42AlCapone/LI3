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
	private String tags;
	private int nrComments;
	private Set<Resposta> respostas;


	public Pergunta() {
		this.id = null;
		this.creationDate = null;
		this.score = 0;
		this.ownerID = null;
		this.title = "";
		this.tags = "";
		this.nrComments = 0;
		this.respostas = new HashSet<Resposta>();
	}

	public Pergunta(Long id, LocalDate creationDate, int score, Long ownerID, String title, 
						String tags, int nrComments, Set<Resposta> respostas) {
		this.id = id;
		this.creationDate = creationDate;
		this.score = score;
		this.ownerID = ownerID;
		this.title = title;
		this.tags = tags;
		this.nrComments = nrComments;
		this.respostas = respostas;
	}

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

	public Long getPergID() {
		return this.id;
	}

	public LocalDate getPergDate() {
		return this.creationDate;
	}

	public int getScoreP() {
		return this.score;
	}

	public Long getOwnerIDp() {
		return this.ownerID;
	}

	public String getTitle() {
		return this.title;
	}

	public String getTags() {
		return this.tags;
	}

	public int getCommentsP() {
		return this.nrComments;
	}

	public Set<Resposta> getRespostas() {
		return this.respostas;
	}

	public int getNrResp(){
		return this.respostas.size();
	}

	// Setters

	public void setPergID(Long id) {
		this.id = id;
	}

	public void setPergDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setScoreP(int score) {
		this.score = score;
	}

	public void setOwnerIDp(Long ownerID) {
		this.ownerID = ownerID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setCommentsP(int nrComments) {
		this.nrComments = nrComments;
	}

	public void setResp(Set<Resposta> respostas) {
		this.respostas = respostas;
	}

	public void setResposta(Resposta r) {
		this.respostas.add(r);	
		
	}


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

	public Pergunta clone() {
		return new Pergunta(this);
	}
}
