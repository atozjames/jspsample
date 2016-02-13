package com.mycheckup;




public class PersonMethodChaing {

	private String name;
	private int age;
	 
	// In addition to having the side-effect of setting the attributes in question, 
	// the setters return "this" (the current Person object) to allow for further chained method calls. 
	 
	public PersonMethodChaing setName(String name) {
	    this.name = name;
	    return this;
	} 
	 
	public PersonMethodChaing setAge(int age) {
	    this.age = age;
	    return this;
	} 
	 
	public void introduce() { 
	    System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
	} 
	 
	// Usage: 
	public static void main(String[] args) {
		PersonMethodChaing person = new PersonMethodChaing();
	    // Output: Hello, my name is Peter and I am 21 years old. 
	    person.setName("Peter").setAge(21).introduce();
	} 


}
