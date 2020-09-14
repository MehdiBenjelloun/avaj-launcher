/***
 *    ███╗   ███╗██████╗ ███████╗███╗   ██╗     ██╗███████╗██╗     ██╗
 *    ████╗ ████║██╔══██╗██╔════╝████╗  ██║     ██║██╔════╝██║     ██║
 *    ██╔████╔██║██████╔╝█████╗  ██╔██╗ ██║     ██║█████╗  ██║     ██║
 *    ██║╚██╔╝██║██╔══██╗██╔══╝  ██║╚██╗██║██   ██║██╔══╝  ██║     ██║
 *    ██║ ╚═╝ ██║██████╔╝███████╗██║ ╚████║╚█████╔╝███████╗███████╗███████╗
 *    ╚═╝     ╚═╝╚═════╝ ╚══════╝╚═╝  ╚═══╝ ╚════╝ ╚══════╝╚══════╝╚══════╝
 */
package fr.e42.avaj.simulator;
import fr.e42.avaj.simulator.Flyable;

import java.util.*;

public class Tower {

	private ArrayList<Flyable> observers = new ArrayList<>();
	
	public void register(Flyable flyable) {
		this.observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		this.observers.remove(flyable);
	}

	protected void conditionsChanged() {
		for (int i = 0 ; i < this.observers.size(); i++)
			this.observers.get(i).updateConditions();
	}
}
