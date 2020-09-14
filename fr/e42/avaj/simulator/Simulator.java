/***
 *    ███╗   ███╗██████╗ ███████╗███╗   ██╗     ██╗███████╗██╗     ██╗
 *    ████╗ ████║██╔══██╗██╔════╝████╗  ██║     ██║██╔════╝██║     ██║
 *    ██╔████╔██║██████╔╝█████╗  ██╔██╗ ██║     ██║█████╗  ██║     ██║
 *    ██║╚██╔╝██║██╔══██╗██╔══╝  ██║╚██╗██║██   ██║██╔══╝  ██║     ██║
 *    ██║ ╚═╝ ██║██████╔╝███████╗██║ ╚████║╚█████╔╝███████╗███████╗███████╗
 *    ╚═╝     ╚═╝╚═════╝ ╚══════╝╚═╝  ╚═══╝ ╚════╝ ╚══════╝╚══════╝╚══════╝
 */
package fr.e42.avaj.simulator;
import fr.e42.avaj.simulator.WeatherTower;
import fr.e42.avaj.simulator.Flyable;
import fr.e42.avaj.simulator.ErrorArgumentException;
import fr.e42.avaj.simulator.AircraftFactory;

import java.util.*;
import java.io.*;

public class Simulator {

	private static File outputFile;
	private static PrintStream stream;
	private static WeatherTower weatherTower;
	private static ArrayList<Flyable> flyables;

	public static void main(String[] arg) {

		if (arg.length != 1) {
			System.out.println("Usage : java fr.e42.avaj.simulator.Simulator [file]");
			return ;
		}
		BufferedReader r = null;
		String l = null;
		int simulations = 0;

		Flyable flyable = null;
		String type;
		String name;
		int longitude;
		int latitude;
		int height;

		try {
			outputFile = new File("simulation.txt");
			stream = new PrintStream(outputFile);
			weatherTower = new WeatherTower();
			flyables = new ArrayList<>();

			r = new BufferedReader(new FileReader(arg[0]));
			l = r.readLine();
			if (l == null)
				throw new Exception("It's empty.");
			if (l.split(" ").length != 1)
				throw new ErrorArgumentException("Error in the first line. Good work.");
			simulations = Integer.parseInt(l.split(" ")[0]);
			if (simulations < 0)
				throw new ErrorArgumentException("Invalid value of simulations.");
			while ((l = r.readLine()) != null) {
				String[] parameters = l.split(" ");	
				if (parameters.length != 5)
					throw new ErrorArgumentException("Invalid number of data in a line. Moreover, an empty line is not accepted.");
				type = parameters[0];
				name = parameters[1];
				longitude = Integer.parseInt(parameters[2]);
				latitude = Integer.parseInt(parameters[3]);
				height = Integer.parseInt(parameters[4]);
				if (height < 0)
					throw new ErrorArgumentException(height + "Coordinate value height cannot be negative.");
				flyable = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
				flyables.add(flyable);
			}
		} catch (FileNotFoundException e){
			System.out.println("File not detected. Not found, not accessible or whatever.");
			return ;
		} catch (ErrorArgumentException e) {
			System.out.println(e);
			return ;
		} catch (IOException e) {
			System.out.println("Error I/O.");
			return ;
		} catch (NumberFormatException e){
			System.out.println("Invalid Numbers.");
			return ;
		} catch (OutOfMemoryError e) {
			System.out.println("\"Il faut user de l'infini comme de tout, avec mesure.\" - Jean Sarment	");
		} catch (Exception e){
			System.out.println("Wonderful. " + e);
			return ;
		} 
		System.setOut(stream);
		for (Flyable f : flyables)
			f.registerTower(weatherTower);
		while (simulations-- > 0)
			weatherTower.changeWeather();
	}

}

