package engine;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.TreeSet;
import java.util.Set;

public class Pergunta extends Resposta implements Serializable {
	private long id;
	private LocalDate creationDate;
	private int score;
	private long ownerID;
	private String title;
	private String tags;
	private int nrComments;
	private TreeSet<Resposta> respostas;


	public Pergunta() {
		this.id = 0;
		this.creationDate = null;
		this.score = 0;
		this.ownerID = 0;
		this.title = "";
		this.tags = "";
		this.nrComments = 0;
		this.respostas = new TreeSet<Resposta>();
	}

	public Pergunta(long id, LocalDate creationDate, int score, long ownerID, String title, String tags, int nrComments, TreeSet<Resposta> respostas) {
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
		this.score = perg.getScore();
		this.ownerID = perg.getOwnerID();
		this.title = perg.getTitle();
		this.tags = perg.getTags();
		this.nrComments = perg.getComments();
		this.respostas = new TreeSet<Resposta>();
	}


	// Getters

	public long getPergID() {
		return this.id;
	}

	public LocalDate getPergDate() {
		return this.creationDate;
	}

	public int getScore() {
		return this.score;
	}

	public long getOwnerID() {
		return this.ownerID;
	}

	public String getTitle() {
		return this.title;
	}

	public String getTags() {
		return this.tags;
	}

	public int getComments() {
		return this.nrComments;
	}

	public TreeSet<Resposta> getRespostas() {
		return this.respostas;
	}

	// Setters

	public void setPergID(long id) {
		this.id = id;
	}

	public void setPergDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setComments(int nrComments) {
		this.nrComments = nrComments;
	}

	public void setTreeResp(TreeSet<Resposta> respostas) {
		this.respostas = respostas;
	}


	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Pergunta p = (Pergunta) obj;
		return p.getPergID() == this.id
		&& p.getPergDate() == this.creationDate
		&& p.getScore() == this.score
		&& p.getOwnerID() == this.ownerID
		&& p.getTitle().equals(this.title)
		&& p.getTags().equals(this.tags)
		&& p.getComments() == this.nrComments;
	}

	public Pergunta clone() {
		return new Pergunta(this);
	}
}
