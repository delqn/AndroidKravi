package com.delqn.android.kravi;

public class ResultOfGuess {
	private int cows;
	private int bulls;
	private String guessedNumber;
	
	ResultOfGuess(int c, int b, String n) {
		cows = c;
		bulls = b;
		guessedNumber = n;
	}
	
	public int getCows() {
		return cows;
	}
	public void setCows(int cows) {
		this.cows = cows;
	}
	public int getBulls() {
		return bulls;
	}
	public void setBulls(int bulls) {
		this.bulls = bulls;
	}
	public String getGuessedNumber() {
		return guessedNumber;
	}
	public void setGuessedNumber(String guessedNumber) {
		this.guessedNumber = guessedNumber;
	}	
}
