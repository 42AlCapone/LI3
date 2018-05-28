package engine;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.Long;

public class CatUsers {
	
	private HashMap<Long,User> catUsers;


	public CatUsers() {
		this.catUsers = new HashMap<Long,User>();
	}

	public CatUsers(HashMap<Long,User> cat) {
		setCatUsers(cat);
	}	

	public CatUsers(CatUsers cat) {
		this.catUsers = new HashMap<Long,User>();
		catUsers.putAll(cat.getCatUsers());
	}


	public Map<Long,User> getCatUsers() {
		return this.catUsers.entrySet().stream().collect(Collectors.toMap(e->e.getKey(),e->e.getValue().clone()));
	}


	public User getUser(Long id) {
		return this.catUsers.get(id);
	}

	public long getSizeCatU(){
		return (long) this.catUsers.size();
 	}

 	
	public void setCatUsers(HashMap<Long,User> cat) {
		this.catUsers = (HashMap<Long,User>) cat.entrySet().stream().collect(Collectors.toMap(c->c.getKey(),c->c.getValue().clone()));
	}

	public void insereUser(User u) {
		this.catUsers.put(u.getUserID(),u);
	}

	public boolean existeUser(Long id) {
		return this.catUsers.containsKey(id);
	}





}