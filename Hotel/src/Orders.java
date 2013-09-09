
public class Orders {
	
private String name;
private String username;
private String persons;
private String roomtype;
private String days;
private String status;
private int id;


public Orders(){
	
}

public Orders(String name, String username, String persons, String roomtype, String days, String status) {
	super();
	this.name = name;
	this.username = username;
	this.persons = persons;
	this.roomtype = roomtype;
	this.days = days;
	this.status = status;
}
public Orders(String name, String username, String persons, String roomtype, String days, String status, int id) {
	super();
	this.name = name;
	this.username = username;
	this.persons = persons;
	this.roomtype = roomtype;
	this.days = days;
	this.status = status;
	this.id=id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPersons() {
	return persons;
}

public void setPersons(String persons) {
	this.persons = persons;
}

public String getRoomtype() {
	return roomtype;
}

public void setRoomtype(String roomtype) {
	this.roomtype = roomtype;
}

public String getDays() {
	return days;
}

public void setDays(String days) {
	this.days = days;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public void setId(int id) {
	this.id = id;
}
public int getId() {
	return id;
}

@Override
public String toString() {
	return "Orders [name=" + name + ", username=" + username + ", persons="
			+ persons + ", roomtype=" + roomtype + ", days=" + days
			+ ", status=" + status + "]";
}




}
