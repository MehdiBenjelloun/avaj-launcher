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

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}	
	
	public void updateConditions() {
		switch(weatherTower.getWeather(coordinates)){
			case "RAIN":
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): It's raining. Better watch out for lightings.");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude() + 5,
													this.coordinates.getHeight());
				break;	
			case "FOG":
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): Let's enjoy the good weather and take some pics");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude() + 1,
													this.coordinates.getHeight());
				break;	
			case "SUN":
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): This is hot!");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude() + 10,
													this.coordinates.getHeight() + 2);
				break;	
			case "SNOW":
				System.out.println("JetPlane#" + this.name + "(" + this.id + "): OMG! Winter is coming!");
				this.coordinates = new Coordinates( this.coordinates.getLongitude(),
													this.coordinates.getLatitude(),
													this.coordinates.getHeight() - 7);
				break;	
			default:
		}
		if (this.coordinates.getHeight() <= 0) {
			this.weatherTower.unregister((Flyable)this);
			System.out.print("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered to weather tower. ");
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
		System.out.println("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
	
}

