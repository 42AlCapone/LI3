package engine;

import java.io.Serializable;
import java.time.LocalDate;

public class Resposta implements Serializable {
	private long id;
	private long parentID;
	private LocalDate creationDate;
	private int score;
	private long ownerID;
	private int nrComments;
	private double rate;


	public Resposta() {
		this.id = 0;
		this.parentID = 0;
		this.creationDate = null;
		this.score = 0;
		this.ownerID = 0;
		this.nrComments = 0;
		this.rate = 0.0;
	}

	public Resposta(long id, long parentID, LocalDate creationDate, int score, long ownerID, int nrComments, double rate) {
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
		this.score = resp.getScore();
		this.ownerID = resp.getOwnerID();
		this.nrComments = resp.getComments();
		this.rate = resp.getRate();
	}	


	// Getters

	public long getRespID() {
		return this.id;
	}

	public long getParentID() {
		return this.parentID;
	}

	public LocalDate getRespDate() {
		return this.creationDate;
	}

	public int getScore() {
		return this.score;
	} 

	public long getOwnerID() {
		return this.ownerID;
	}

	public int getComments() {
		return this.nrComments;
	}

	public double getRate() {
		return this.rate;
	}

	// Setters

	public void setRespID(long id) {
		this.id = id;
	}

	public void setParentID(long parentID) {
		this.parentID = parentID;
	}

	public void setRespDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setOwnerID(long ownerID) {
		this.ownerID = ownerID;
	}

	public void setComments(int nrComments) {
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
		&& r.getScore() == this.score
		&& r.getOwnerID() == this.ownerID
		&& r.getComments() == this.nrComments
		&& r.getRate() == this.rate;
	}

	public Resposta clone() {
		return new Resposta(this);
	}
}
