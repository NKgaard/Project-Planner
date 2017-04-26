package dtu.student.pp;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import dtu.student.pp.data.activity.AbstractActivity;
import dtu.student.pp.data.comparators.IntervalSet;

public class Developer implements Serializable{
	private final static int MAX_INITIAL_LETTERS = 4;
	private final String initials;
	
	Developer(char[] developerInitials) {
		//checkValidity(developerInitials);
		int initialLetters = Math.min(MAX_INITIAL_LETTERS, developerInitials.length);
		char[] initials = new char[initialLetters];
		for(int i=0; i<initialLetters; i++)
			initials[i] = Character.toUpperCase(developerInitials[i]);
		
		this.initials = new String(initials);
	}
	
	Developer(String firstName, String lastName) {
		this(getInitials(firstName, lastName));
	}

	private static char[] getInitials(String firstName, String lastName) {
		firstName = firstName.replaceAll("[^A-Za-z0-9 ]", firstName); //Remove any non-letters
		
		String[] firstNames = firstName.trim().split("\\s+"); //Split names at whitespaces.
		int firstInitialLetters = Math.min(firstNames.length, MAX_INITIAL_LETTERS);
		
		char[] initials = new char[firstInitialLetters + 1];
		//Add first names to initials
		for(int i=0; i<firstInitialLetters-1; i++)
			initials[i] = firstNames[i].charAt(0);
		
		//Add last name initial as the last letter.
		if(lastName!=null)
			initials[firstInitialLetters] = lastName.trim().charAt(0);
			else
			initials[firstInitialLetters] = firstNames[firstInitialLetters].charAt(0);
		
		return initials;
	}

	@Override
	public int hashCode() {
		return initials.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		//Initials are never null. Compare as Strings.
		return initials.equals(obj.toString());
	}

	@Override
	public String toString() {
		return initials;
	}
}
