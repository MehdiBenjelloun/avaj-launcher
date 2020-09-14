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

public class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}	
	
	public void updateConditions() {
		switch(weatherTower.getWeather(coordinates)){
			case "RAIN":
				System.out.println("Baloon#" + this.name + "(" + this.id + "): The water slides along the swellable fabric ~");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() - 5);
				break;	
			case "FOG":
				System.out.println("Baloon#" + this.name + "(" + this.id + "): The view is limited with this weather.");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() - 3);
				break;	
			case "SUN":
				System.out.println("Baloon#" + this.name + "(" + this.id + "): This is hot.");
				this.coordinates = new Coordinates( this.coordinates.getLongitude() + 2,
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() + 4);
				break;	
			case "SNOW":
				System.out.println("Baloon#" + this.name + "(" + this.id + "): It's a snow balloon.");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() - 15);
				break;	
			default:
		}
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister((Flyable)this);
			System.out.print("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered to weather tower. ");
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
		System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
	
}

