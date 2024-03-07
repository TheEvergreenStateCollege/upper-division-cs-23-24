package com.mycompany.app;

public class Plane {
	int passenger;
	int parkingIndex;
	String flightNumber;
	int arrival, departure;  //without time format, would be helpful to find the way
	
	Plane(String flight, int passenger, int departure, int arrival) {
		//I know this is not a good practice but I don't have any other ID choice for these
		flightNumber = flight;
		this.passenger = passenger;
		this.departure = departure;
		this.arrival = arrival;
	}
	
	Plane(int parkingIndex, String flight) {
		flightNumber = flight;
		this.parkingIndex = parkingIndex;
	}
	
	/* 24 hour format: not used/understandable currently
	inputColon = userInput.indexOf(":");
	timeHour = Integer.parseInt(userInput.substring(0, inputColon));
	timeMinute = Integer.parseInt(userInput.substring(inputColon + 1, userInput.length()));

	int timeHour;      // Time of travel hour (24 hour format)
	int timeMinute;    // Time of travel minute
	int inputColon;

	Output hour adjusting for am/pm format
		    if (timeHour == 0) {
		         System.out.print("12:");
		      }
		    else if (timeHour <= 12) {
		         System.out.print(timeHour + ":");
		      }
		    else {
		         System.out.print((timeHour - 12) + ":");
		      }
		   
		      // Output minute with formatting (discussed elsewhere) to
		      // print two digits for minutes.
		      System.out.print(String.format("%02d", timeMinute));
	*/
}

