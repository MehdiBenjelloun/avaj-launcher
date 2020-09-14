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
import fr.e42.avaj.simulator.Coordinates;
import fr.e42.avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}	

	public void updateConditions() {
		switch(weatherTower.getWeather(coordinates)){
			case "RAIN":
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): The water slides along the propellers ~");
				this.coordinates = new Coordinates( this.coordinates.getLongitude() + 5,
													this.coordinates.getLatitude(),
													this.coordinates.getHeight());
				break;	
			case "FOG":
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): Need to clear windshield...");
				this.coordinates = new Coordinates( this.coordinates.getLongitude() + 1,
													this.coordinates.getLatitude(),
													this.coordinates.getHeight());
				break;	
			case "SUN":
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): this is hot.");
				this.coordinates = new Coordinates( this.coordinates.getLongitude() + 10,
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() + 2);
				break;	
			case "SNOW":
				System.out.println("Helicopter#" + this.name + "(" + this.id + "): My rotor is going to freeze!");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() - 12);
				break;	
			default:
		}
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister((Flyable)this);
			System.out.print("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered to weather tower. ");
			System.out.println("Current position : " + this.coordinates.getLongitude() + "::" + this.coordinates.getLatitude() + "::" + this.coordinates.getHeight());
		}
		else if (this.coordinates.getHeight() > 100)
			this.coordinates = new Coordinates( this.coordinates.getLongitude(),
												this.coordinates.getLatitude(),
												100);
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		weatherTower.register((Flyable)this);
		System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
	
}

