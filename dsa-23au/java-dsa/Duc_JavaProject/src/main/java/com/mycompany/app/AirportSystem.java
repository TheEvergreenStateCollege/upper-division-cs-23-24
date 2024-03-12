package com.mycompany.app;

import java.util.*;

public class AirportSystem {
	Scanner input = new Scanner(System.in);
	Queue<Plane> waitlist = new LinkedList<Plane>();
	Queue<Plane> runway = new LinkedList<Plane>();
	ArrayList<Plane> parkinglot = new ArrayList<Plane>();
	ArrayList<Plane> parkingID = new ArrayList<Plane>();
	Stack<Plane> SOSrunway = new Stack<Plane>();
	Stack<Plane> SOSparking = new Stack<Plane>();
	
	int runwaycap;
	int space;
	int IDspace;
	Plane temp;
	Plane newplane;
	Plane skymarshal;
	Plane emergency;
	
	AirportSystem() {
		skymarshal = new Plane("SM911", 6, 000, 000);
		emergency = new Plane(911,"SM911");
		int runwayNum = 10;
		runwaycap = runwayNum;
		int parking = 20;
		space = parking;
		IDspace = parking;
		parkingID.add(emergency);
		parkinglot.add(emergency);
	}
	
	AirportSystem(String emergency) {
		System.out.println("This is an "+emergency);
		System.out.println("Referred to safety protocol immediately");
		System.out.println("All sky marshal/public enforcer are required to deployed under code SOS911");
		int runwayNum = 15;
		runwaycap = runwayNum;
		int parking = 25;
		space = parking;
		IDspace = parking;
	}
	
	public void enterRunway(Plane newplane, int parking, char choice) {
		if(runway.size() < runwaycap) {
			runway.add(newplane);
			SOSrunway.add(newplane);
			enterParking(newplane, parking, choice);
		}
		else {
			System.out.println("The runway is full, putting them on the waitlist...");
			System.out.println("Suggest stalling method");
			waitlist.add(newplane);
			waitlistCheck(newplane, parking, choice);
		}
	}
	
	public void enterParking(Plane newplane, int parking, char choice) {
		if(parkinglot.size() < space) {
			runway.remove();
			SOSrunway.remove(newplane);
			if ((choice == 'y') || (choice == 'Y')) {
				parkinglot.set(parking, newplane);
				SOSparking.set(parking, newplane);
				int mark = parkinglot.indexOf(newplane);
				this.newplane = new Plane(mark, newplane.flightNumber);
				parkingID.add(this.newplane);
			}
			else {
				parkinglot.add(newplane);
				SOSparking.add(newplane);
				int mark = parkinglot.indexOf(newplane);
				this.newplane = new Plane(mark, newplane.flightNumber);
				parkingID.add(this.newplane);
			}
		}
		else {
			System.out.println("The parking lot is full!!");
			System.out.println("Suggesting stay on the runway");
			waitlistParking(newplane, parking, choice);
		}
		airportStatus();
	}
	
	public void exitParking(String flightCode, Plane newplane, int ID) {
		boolean search = planeSearch(flightCode, newplane);
		System.out.println("=====================");
		System.out.println("\tID search result");
		ID_Search(ID);
		
		if (search) {
			System.out.println("Search found");
		}
		
		System.out.println("Cojoining with controller, is the tag correct?");
		char confirm = input.next().charAt(0);
		boolean choice = Confirmation(confirm);
		
		if (search && choice) {
			parkingID.remove(ID);
			parkinglot.remove(ID);
			SOSparking.remove(newplane);
			
			if (runway.size() < runwaycap) {
				runway.add(newplane);
				exitRunway(newplane);
			}
			else {
				while(!runway.isEmpty()) {
					if (runway.size() < runwaycap) {
						System.out.println("runway is unclogged, proceed to add "+newplane.flightNumber+" into runway");
						runway.add(newplane);
						exitRunway(newplane);
					}
				}
			}
		}
	}
	
	public void exitRunway(Plane newplane) {
		System.out.println(newplane.flightNumber+" is requested to be launch on nearest available runway. Proceed?");
		char confirm = input.next().charAt(0);
		boolean selection = Confirmation(confirm);
		
		if (selection) {
			System.out.println("Plane will launched in approximate 10 minutes. Wish they have a safe flight.");
			runway.remove();
			SOSrunway.remove(newplane);
		}
		airportStatus();
	}
	
	public void emergencyExit() {
		runway.clear();
		parkinglot.clear();
		parkingID.clear();
		waitlist.clear();
		
		System.out.println("All planes are instructed to leave the parking and park at the hangar or fly away to their own measure");
		System.out.println("Planes that leave to hangar will be instructed by controller, planes that leave for the runway will be waited to initiated by the ATC");
		System.out.println("When all planes set, press backslash(\\) to set the plane to runway by \"first in last out\" order");
		char confirm = input.next().charAt(0);
		if (confirm == '\\') {
			for(int i = 0; i < SOSparking.size(); i++) {
				SOSparking.pop();
			}
			
			System.out.println("parking space left: "+SOSparking.size());
			System.out.println("Runway status: "+SOSrunway.size());
			airportStatus();
		}
		else {
			while (confirm != '\\') {
				System.out.println("Try Again");
				emergencyExit();
			}
		}
		System.out.println("When all planes leave the parking, Press backslash(\\) again to initiate departure for all airplane");
		char confirmAgain = input.next().charAt(0);
		if (confirmAgain == '\\') {
			System.out.println("Initiation complete. Goodbye");
			for(int i = 0; i < SOSrunway.size(); i++) {
				SOSrunway.pop();
			}
			System.out.println("parking space left: "+SOSparking.size());
			System.out.println("Runway status: "+SOSrunway.size());
			airportStatus();
		}
		else {
			while (confirmAgain != '\\') {
				System.out.println("Try Again");
				emergencyExit();
			}
		}
	}
	
	public void waitlistCheck(Plane newplane, int parking, char choice) {
		while(!waitlist.isEmpty()) {
			if(runway.size() < runwaycap) {
				runway.add(newplane);
				SOSrunway.add(newplane);
				break;
			}
		}
		waitlist.poll();
		enterParking(newplane, parking, choice);
		System.out.println("Updating scenario: ");
		airportStatus();
	}
	
	public void waitlistParking(Plane newplane, int parking, char choice) {
		while(!runway.isEmpty()) {
			if(parkinglot.size() < space) {
				System.out.println("Stalling plane: "+runway.peek());
				break;
			}
		}
		enterParking(newplane, parking, choice);
		System.out.println("Updating scenario: ");
		airportStatus();
	}
	
	public void airportStatus() {
		System.out.println("Waitlist status: "+waitlist.size()+" plane(s) are waiting on the sky right now");
		System.out.println("Runway status: "+runway.size()+" plane(s) are waiting to departure/arrival right now");
		System.out.println("Parking lot status: "+parkinglot.size()+" plane(s) are parking right now");
		System.out.println("Parking space (ID) used: "+parkingID.size());
	}
	
	public void emergencyList() {
		System.out.println("Emergency runway status: "+SOSrunway.size()+" are using the runway now");
		System.out.println("Emergency parking status: "+SOSparking.size()+" are using the parking now");
	}
	
	public boolean planeSearch(String flightCode, Plane newplane) {
		if (flightCode.compareTo(newplane.flightNumber) == 0) {
				System.out.println("Flight Number: "+newplane.flightNumber);
				System.out.println("Number of Passenger: "+newplane.passenger);
				System.out.println("Departure time: "+newplane.departure);
				System.out.println("Arrival time: "+newplane.arrival);
				return true;
			}
			else {
				System.out.println("Data not found!");
				return false;
			}	
		
	}
	
	public void ID_Search(int ID) { 
		//let the user choose the parking, which then print the plane alongside that location
		//user can confirm by yes or no method
		if (ID < 20) {
			temp = parkingID.get(ID);
			System.out.println(toString());
		}
		else {
			System.out.println("Invalid parking space");
		}
		
	}
	
	@Override
	public String toString() {
		//Override toString method to display ID information
		return "Parking number: "+temp.parkingIndex+"\nFlight code: "+temp.flightNumber;
	}
	
	public boolean Confirmation(char control) {
		if ((control == 'y') || (control == 'Y')) {
			System.out.println("Confirmation acquired. You may proceed");
			return true;
		}
		else if ((control == 'n') || (control == 'N')) {
			System.out.println("Confirmation terminated.");
			return false;
		}
		return false;
	}
	
}