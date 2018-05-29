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


	public User() {
		this.id = null;
		this.name = "";
		this.bio = "";
		this.rep = 0;
		this.views = 0;
		this.voteDif = 0;
		this.nrPosts = 0;
	}

	public User(Long id, String name, String bio, int rep, int views, int voteDif) {
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.rep = rep;
		this.views = views;
		this.voteDif = voteDif;
		this.nrPosts = 0;
	}

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
