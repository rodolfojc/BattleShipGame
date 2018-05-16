package versionOne;

//RODOLFO JOHAM CARVAJAL MARQUEZ
//STUDENT NUMBER 2017032

public class Player {

	private String name, email;
	private int age;
	int hit, miss;
	int score;

	public Player(String name, int age, String email, int score, int hit, int miss) {

		this.name = name;
		this.age = age;
		this.email = email;
		this.score = score;
		this.hit = hit;
		this.miss = miss;
		toString();
	}

	// METHODS TO GET/SET VARIABLES
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	// OVERRIDE THE METHOD toString WHICH RETURNS THE DETAILS OF THE PLAYER
	@Override
	public String toString() {
		return "The Player " + name + ", " + age + " years old, and email " + email + " has been created!";
	}

}
