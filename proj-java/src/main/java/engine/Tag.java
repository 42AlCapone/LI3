package engine;

import java.io.Serializable;
import java.lang.Long;
import java.util.Map;
import java.util.HashMap;

public class Tag implements Serializable {
	private String name;
	private Long tagUses;
	

	public Tag() {
		this.name = null;
		this.tagUses = 0L;
		}

	public Tag(String name, Long tagUses) {
		this.name = name;
		this.tagUses = tagUses;
		}

	public Tag(Tag tag) {
		this.name = tag.getTagName();
		this.tagUses = tag.getTagUses();
		}


	// Getters

	public String getTagName() {
		return this.name;
	}

	public Long getTagUses() {
		return this.tagUses;
	}


	// Setters
	public void setTagName(String name) {
		this.name = name;
	}

	public void setTagUses(Long uses) {
		this.tagUses = uses;
	}

	public void incTagUse() {
		this.tagUses++;
	}
	

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Tag t = (Tag) obj;
		return t.getTagName()==this.name 
		&& t.getTagUses() == this.tagUses;
		}

	public Tag clone() {
		return new Tag(this);
	}
}
