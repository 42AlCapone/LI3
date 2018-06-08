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


	public Resposta() {
		this.id = null;
		this.parentID = null; 
		this.creationDate = null;
		this.score = 0;
		this.ownerID = null;
		this.nrComments = 0;
		this.rate = 0.0;
	}

	public Resposta(Long id, Long parentID, LocalDate creationDate, int score, Long ownerID, int nrComments, double rate) {
		this.id = id;
		this.parentID = parentID;
		this.creationDate = creationDate;
		this.score = score;
		this.ownerID = ownerID;
		this.nrComments = nrComments;
		this.rate = rate;
	}

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

	public Long getRespID() {
		return this.id;
	}

	public Long getParentID() {
		return this.parentID;
	}

	public LocalDate getRespDate() {
		return this.creationDate;
	}

	public int getScoreR() {
		return this.score;
	} 

	public Long getOwnerIDr() {
		return this.ownerID;
	}

	public int getCommentsR() {
		return this.nrComments;
	}

	public double getRate() {
		return this.rate;
	}

	// Setters

	public void setRespID(Long id) {
		this.id = id;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}

	public void setRespDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setScoreR(int score) {
		this.score = score;
	}

	public void setOwnerIDr(Long ownerID) {
		this.ownerID = ownerID;
	}

	public void setCommentsR(int nrComments) {
		this.nrComments = nrComments;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}


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

	public Resposta clone() {
		return new Resposta(this);
	}
}
