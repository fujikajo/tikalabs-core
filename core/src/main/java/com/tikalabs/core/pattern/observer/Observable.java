package com.tikalabs.core.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 2. Das Observer-Muster mit eigenen Schnittstellen
 * 
 * Eine andere Möglichkeit ist die Implementierung des Observer-Musters mit
 * eigenen Schnittstellen und Klassen. Dies bietet maximale Flexibilität und
 * erlaubt es, das Muster genau auf die eigenen Bedürfnisse anzupassen. *
 * 
 * @author tittm
 *
 */

class Observable {
	private List<Observer> observers = new ArrayList<>();

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	protected void notifyObservers(Object arg) {
		for (Observer o : observers) {
			o.update(arg);
		}
	}
}
