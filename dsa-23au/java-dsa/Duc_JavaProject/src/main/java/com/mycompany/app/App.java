package com.mycompany.app;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    //This is the ATCmainsystem main code to regulate airport traffic flow that I did back in DSA
    //The code is below, check README.md first

//public class ATCmainsystem {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		AirportSystem airport = new AirportSystem();
		ArrayList<Plane> database = new ArrayList<Plane>();
		ArrayList<String> user = new ArrayList<String>();
		ArrayList<String> pass = new ArrayList<String>();
		
		boolean isRunning = true;
		String username;
		String password;
		String flight;
		int passenger;
		int arrival;
		int departure;
		int operation;
		
		System.out.println("Welcome to ATC main terminal");
		System.out.print("Username: ");
		username = input.nextLine();
		System.out.print("Password: ");
		password = input.nextLine();
		
		System.out.println("Login success");
		user.add(username);
		pass.add(password);
		
		while(isRunning) {
			System.out.println("============================================");
			System.out.println();
			System.out.println();
			System.out.println("Choose your operation (Select by number): ");
			System.out.println("*Keep in mind that option 4 and 5 will automatically excute upon selecting \"Departure\" operation*");
			System.out.println("1. Arrival/Departure (Use when a plane requesting arrival/departure to airport)");
			System.out.println("2. Internal database (Accessing and view private plane database)");
			System.out.println("3. Airport status (Showing current information of waitlist, runways, and parking lot)");
			System.out.println("4. Plane inquiry (Search for planes in the database)");
			System.out.println("5. Parking space inquiry (Track which plane using the parking lot, or find out if there's any)");
			System.out.println("6. Emergency Code (Use to deploy sky marshal or when emergency instruction is necessary)");
			System.out.println("7. Emergency usage (Tracking the use of backup runway and parking lot in case of emergency)");
			System.out.println("8. Logoff (Log out the system)");
			operation = input.nextInt();
			input.nextLine();
			
			if (operation == 1) {
				System.out.println("Arrival or Departure (0 for Arrival, 1 for Departure)");
				int choice = input.nextInt();
				input.nextLine();
				
				if (choice == 0) {
					System.out.println("Flight number?");
					flight = input.nextLine();
					System.out.println("Numbers of passenger: ");
					passenger = input.nextInt();
					System.out.println("Departure time(In 24 hours format, no punctuations): ");
					departure = input.nextInt();
					System.out.println("Arrival time from previous airport(In 24 hours format, no punctuations): ");
					arrival = input.nextInt();
				
					Plane newplane = new Plane(flight, passenger, departure, arrival);
					database.add(newplane);
					
					System.out.println("Do you want to manually add planes in any designated parking lot? (Press Y to continue)");
					char selection = input.next().charAt(0);
					
					if ((selection == 'y') || (selection == 'Y')) {
						System.out.print("Choose a parking lot that you want to assign the plane to: ");
						int parking = input.nextInt();
						airport.enterRunway(newplane, parking - 1, selection);
					}
					else {
						airport.enterRunway(newplane, 0, 'N');
					}
					
					System.out.println("The plane has parked in. Arrival success");
					System.out.println("Choose an another method of operation or exit(Press any key)");
				}
				else if (choice == 1) {
					System.out.print("Flight number of the plane: ");
					flight = input.nextLine();
					System.out.print("Parking space ID that the plane is using: ");
					int ID = input.nextInt();
					
					airport.exitParking(flight, database.get(ID - 1), ID);
					database.remove(database.get(ID - 1));
					System.out.println("Choose an another method of operation or exit(Press any key)");
				}
			}
			else if (operation == 2) {
				System.out.println("These are the list of planes available:");
				if (database.size() == 0) {
					System.out.println("There are no plane added in");
					System.out.println("Choose an another method of operation or exit(Press any key)");
				}
				for (int i = 0; i < database.size(); i++) {
					Plane data = database.get(i);
					System.out.println(data.flightNumber);
				}
			}
			else if (operation == 3) {
				System.out.println("Airport current status: ");
				airport.airportStatus();
				System.out.println("Choose an another method of operation or exit(Press any key)");
			}
			else if (operation == 4) {
				if (database.size() == 0) {
					System.out.println("There are no plane to search");
					System.out.println("Choose an another method of operation or exit(Press any key)");
					continue;
				}
				for (int i = 0; i < database.size(); i++) {
					Plane data = database.get(i);
					int lineNumber = database.indexOf(data) + 1;
					System.out.println(lineNumber+". "+data.flightNumber);
					System.out.println();
				}
				System.out.print("Tag number");
				System.out.print("(insert the line number that the flight number append to): ");
				System.out.println();
				int number = input.nextInt();
				Plane tag = database.get(number - 1);
				System.out.println("\tPlane information");
				airport.planeSearch(tag.flightNumber, tag);
			}
			else if (operation == 5) {
				System.out.println("What parking space number you want to search for? ");
				int space = input.nextInt();
				airport.ID_Search(space);
				System.out.println("Choose an another method of operation or exit(Press any key)");
			}
			else if (operation == 6) {
				AirportSystem emergency = new AirportSystem("emergency");
				emergency.emergencyList();
				emergency.emergencyExit();
			}
			else if (operation == 7) {
				airport.emergencyList();
			}
			else if (operation == 8) {
				System.out.println("You have logging off. Goodbye *peep peep*");
				user.remove(username);
				pass.remove(password);
				isRunning = false;
			}
			else {
				System.out.println("Are you sure you want to go back? (Press Y if you want to go back, else, press any key to shutdown)");
				char confirm = input.next().charAt(0);
				if ((confirm == 'y') || (confirm == 'Y')) {
					isRunning = true;
				}
				else {
					System.out.println("Initiating shutdown");
					isRunning = false;
				}
			}
		}
		input.close();
	}
//}

}
