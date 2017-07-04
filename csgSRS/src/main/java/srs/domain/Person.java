package srs.domain;

public abstract class Person {
	private String id; 
	private String name; 
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


	public Person(String id,String name,String password){
		this.name = name;
		this.id = id;
		this.setPassword(password);
	}
	public Person(){
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString(); 


	public void display() {
		System.out.println("用户信息:");
		System.out.println("\t姓名:  " + this.getName());
		System.out.println("\t编号.:  " + this.getId());
	}

}
