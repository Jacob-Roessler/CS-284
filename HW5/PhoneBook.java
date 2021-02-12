//Jacob Roessler
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PhoneBook {
	// Data fields
	public Map<Character, BSFamilyTree> directory;

	/**
     	* Creates a new phone book with an empty directory.
     	*/
	public PhoneBook() {
		this.directory = new HashMap<Character, BSFamilyTree>();
		
		for(char letter = 'A'; letter <= 'Z'; letter++) {
			directory.put(letter, new BSFamilyTree());
		}
		
	}

	/*
	 * Returns the instance of BSFamilyTree at the indicated letter
	 * Must accept lowercase letters as well as uppercase letters
	 */
	public BSFamilyTree getFamilyTree(char letter) {
		if(!Character.isLetter(letter)) 
			throw new IllegalArgumentException("Not a letter.");
		
		letter = Character.toUpperCase(letter);
		return directory.get(letter);
	}

	/*
	 * Adds a FamilyTreeNode to the PhoneBook
	 */
	public void addFamily(String lastName) {
		getFamilyTree(lastName.charAt(0)).addFamilyTreeNode(lastName);
	}

	/*
	 * Adds a Person to the PhoneBook
	 * If a FamilyTreeNode with the given last name doesn't currently exist, create the FamilyTreeNode
	 */
	public void addPerson(String lastName, String firstName, String phoneNumber) {
		BSFamilyTree letter = getFamilyTree(lastName.charAt(0));
		Person toAdd = new Person(lastName, firstName, phoneNumber);
		for( Map.Entry<Character, BSFamilyTree> entry : directory.entrySet()) {
			if(entry.getValue().doesNumberExist(phoneNumber))
				throw new IllegalArgumentException("Number already exists!");
		}
		
		if(!letter.doesFamilyExist(lastName))
			this.addFamily(lastName);
			
		letter.getFamilyTreeNode(lastName).addFamilyMember(toAdd);
	}

	/*
	 * Finds the phone number of a person
	 * Returns 'Does not exist.' if not found.
	 */
	public String getPhoneNumber(String lastName, String firstName) {
		BSFamilyTree letter = getFamilyTree(lastName.charAt(0));
		if(letter.doesFamilyExist(lastName)) {
			FamilyTreeNode family = letter.getFamilyTreeNode(lastName);
			for(Person member: family.getMembers()) {
				if(member.getFirstName().equals(firstName) && member.getLastName().equals(lastName)) {
					return member.getPhoneNumber();
				}
			}
		}
		return "Does not exist.";
	}

    	/**
     	* String representation of PhoneBook
     	*/
	public String toString() {
		StringBuilder s = new StringBuilder();
		for( Map.Entry<Character, BSFamilyTree> entry : directory.entrySet()) {
			s.append(entry.getKey() + "\n");
			s.append(entry.getValue());
		}
		return s.toString();
	}
}
