package com.tikalabs.core.pattern.example;

import java.util.Observable;

class MyObservable2 extends Observable {
	private String value;

	public void setValue(String value) {
		this.value = value;
		notifyObservers(value);
	}
}