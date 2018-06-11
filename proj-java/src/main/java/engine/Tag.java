package engine;

import java.io.Serializable;
import java.lang.Long;
import java.util.Map;
import java.util.HashMap;

public class Tag implements Serializable {
	private Long tagUses;
	

	public Tag() {
		this.tagUses = 0L;
		}

	public Tag(Long tagUses) {
		this.tagUses = tagUses;
		}

	public Tag(Tag tag) {
		this.tagUses = tag.getTagUses();
		}


	// Getters

	public Long getTagUses() {
		return this.tagUses;
	}


	// Setters

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
		return t.getTagUses() == this.tagUses;
		}

	public Tag clone() {
		return new Tag(this);
	}
}
