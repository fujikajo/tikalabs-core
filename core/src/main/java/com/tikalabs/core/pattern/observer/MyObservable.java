package com.tikalabs.core.pattern.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * Mit Java 9 wurden die Klassen Observer und Observable als veraltet markiert
 * (deprecated), weil sie als Teil der ursprünglichen Event-Modellierung in Java
 * betrachtet wurden, die modernen Design Prinzipien und Mustern nicht mehr
 * entspricht. Für die Beobachtung von Objekten und die Benachrichtigung von
 * Änderungen gibt es modernere und flexiblere Ansätze, die in Java 11 und
 * darüber hinaus verwendet werden können.
 * 
 * @author Kai Tittmann
 *
 */

public class MyObservable {
	private String value;
	private PropertyChangeSupport support;

	public MyObservable() {
		support = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		support.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		support.removePropertyChangeListener(pcl);
	}

	public void setValue(String value) {
		String oldValue = this.value;
		this.value = value;
		support.firePropertyChange("value", oldValue, value);
	}
}