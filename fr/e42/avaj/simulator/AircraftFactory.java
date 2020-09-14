/***
 *    ███╗   ███╗██████╗ ███████╗███╗   ██╗     ██╗███████╗██╗     ██╗
 *    ████╗ ████║██╔══██╗██╔════╝████╗  ██║     ██║██╔════╝██║     ██║
 *    ██╔████╔██║██████╔╝█████╗  ██╔██╗ ██║     ██║█████╗  ██║     ██║
 *    ██║╚██╔╝██║██╔══██╗██╔══╝  ██║╚██╗██║██   ██║██╔══╝  ██║     ██║
 *    ██║ ╚═╝ ██║██████╔╝███████╗██║ ╚████║╚█████╔╝███████╗███████╗███████╗
 *    ╚═╝     ╚═╝╚═════╝ ╚══════╝╚═╝  ╚═══╝ ╚════╝ ╚══════╝╚══════╝╚══════╝
 */
package fr.e42.avaj.simulator;
import fr.e42.avaj.simulator.Aircraft;
import fr.e42.avaj.simulator.Flyable;
import fr.e42.avaj.simulator.JetPlane;
import fr.e42.avaj.simulator.Helicopter;
import fr.e42.avaj.simulator.Baloon;
import fr.e42.avaj.simulator.Coordinates;
import fr.e42.avaj.simulator.ErrorArgumentException;

public class AircraftFactory {

	static public Flyable newAircraft( String type,
								String name,
								int longitude,
								int latitude,
								int height ) throws ErrorArgumentException {

		Coordinates c = new Coordinates(longitude, latitude, height);
		Aircraft a = null;
		switch (type) {
			case "JetPlane":
				a = new JetPlane(name, c);
				break ;
			case "Helicopter":
				a = new Helicopter(name, c);
				break ;
			case "Baloon":
				a = new Baloon(name, c);
				break ;
			default:
				throw new ErrorArgumentException("Invalid type of aircraft.");

		}	

		return (Flyable)a;	
	}
}
