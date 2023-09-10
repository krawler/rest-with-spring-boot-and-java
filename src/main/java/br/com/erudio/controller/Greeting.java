package br.com.erudio.controller;

public class Greeting {
	
	private final long counter;
	private final String name;

	public Greeting(long counter, String name) {
		super();
		this.counter = counter;
		this.name = name;
	}

	public long getCounter() {
		return counter;
	}

	public String getName() {
		return name;
	}

}
