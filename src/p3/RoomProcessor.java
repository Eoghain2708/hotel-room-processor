package p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import p3.models.Room;
import p3.models.StandardRoom;
import p3.models.StandardRoom.RoomType;


/**
 * @author Eoghain Magee 40301916
 */
public class RoomProcessor {
	
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// showing all methods
		 var roomsFromFile = getRoomsFromFile("src/rooms.csv");
		 
		 printAllRooms(roomsFromFile);
		
		 displayNumRooms(roomsFromFile);
			
		 displayNumBeds(roomsFromFile);
		
		 displayNumLocations(roomsFromFile);
		
		 displayExpensiveRooms(roomsFromFile);
		
		 Runnable r = () -> {
			System.out.println("Writing on new thread");
			writeRoomsToFile(List.copyOf(roomsFromFile), "rooms_processed.csv");
			System.out.println("Done");
		 };
		
		 Thread t = new Thread(r);
		 	t.start();
		 try {
			t.join();
		 } catch (InterruptedException e) {
			t.interrupt();
		 }
		 
		 // System.exit(0); uncomment to have application (hopefully) terminate after writing Rooms to file, or keep commented for menu
		
		
		// menu
		 
		while (true) {
			printMenu();
			
			int input = getInput();
			switch (input) {
			case 7:
				System.exit(0);
			case 1:
				displayNumBeds(roomsFromFile);
				break;
			case 2:
				displayNumLocations(roomsFromFile);
				break;
			case 3:
				displayExpensiveRooms(roomsFromFile);
				break;
			case 4:
				displayNumRooms(roomsFromFile);
				break;
			case 5:
				Runnable runnable = () -> {
					System.out.println("Writing on new file");
					writeRoomsToFile(List.copyOf(roomsFromFile), "src/rooms_processed.csv");
					System.out.println("Done!");
				};
				Thread thread = new Thread(runnable);
				thread.start();
				try {
					t.join();
				} catch (InterruptedException e) {
					t.interrupt();
				}
				break;
			case 6:
				printAllRooms(roomsFromFile);
			}
		}
		
		
		
	}
	
	/**
	 * Reads a file and returns a LinkedList of StandardRooms, skipping invalid rows
	 * @param fileName - Path of the file being processed
	 * @return List<StandardRoom> (LinkedList)
	 */
	public static List<StandardRoom> getRoomsFromFile(String fileName) {
		var result = new LinkedList<StandardRoom>();
		int attempted = 0;
		int successful = 0;
		String line;
		try (var br = new BufferedReader(new FileReader(fileName))) {
			br.readLine(); // skip headers
			while ((line = br.readLine()) != null) {
				System.out.println("Line currently being processed: " + line);
				try {
					attempted++;
					var fields = line.split(",");
					// TODO add .strip
					var name = fields[0].strip();
					var location = fields[1].strip();
					var tempAvgRating = fields[2].strip();
					var tempNumRatings = fields[3].strip();
					var tempPricePerNight = fields[4].strip();
					var tempNumBeds = fields[5].strip();
					var tempRoomType = fields[6].strip();
					
					// parse values
					double avgRating = Double.parseDouble(tempAvgRating);
					int numRatings = Integer.parseInt(tempNumRatings);
					double pricePerNight = Double.parseDouble(tempPricePerNight);
					int numBeds = Integer.parseInt(tempNumBeds);
					RoomType roomType = RoomType.valueOf(tempRoomType.toUpperCase());
					
					// attempt to make object
					StandardRoom room = StandardRoom.of(name, location, avgRating, numRatings, pricePerNight, numBeds, roomType);
					
					if (result.add(room)) {
						successful++;
						System.out.println("Room added successfully!");
					}
			
				} catch (IllegalArgumentException | NullPointerException e) {
					System.err.println("Cannot create object with this line!");
				}
			}
		} catch (IOException e) {
			System.err.println("Error writing to file");
			e.printStackTrace();
		}
		System.out.println("Attempted: " + attempted);
		System.out.println("Successful: " + successful);
		return result;
	}
	
	
	/**
	 * Takes in a Collection of any object which is a Room or extends Room and returns its size
	 * @param rooms Collection<? extends Room>
	 * @return int value corresponding to number of rooms in the param
	 */
	public static int getNumRooms(Collection<? extends Room> rooms) {
		if (rooms == null) {
			return 0;
		}
		return rooms.size();
	}
	
	/**
	 * Displays the number of rooms in a given collection of any type of Room
	 * @param rooms
	 */
	public static void displayNumRooms(Collection<? extends Room> rooms) {
		
		if (collectionIsNullOrEmpty(rooms)) {
			System.out.println("No rooms here.");
			return;
		}
		
		System.out.println("Number of rooms: " + getNumRooms(rooms));
	}
	
	
	/**
	 * Takes in a Collection of any object which is a Room or extends Room and returns the number of beds in all rooms combined
	 * @param rooms
	 * @return
	 */
	public static int getNumBeds(Collection<? extends StandardRoom> rooms) {
		if (collectionIsNullOrEmpty(rooms)) {
			System.out.println("No rooms, no beds");
		}
		int currentNumBeds = 0;
		for (var room : rooms) {
			currentNumBeds += room.getNumBeds();
		}
		return currentNumBeds;
	}
	
	
	/** 
	 * Displays number of beds in a given collection of roooms
	 * @param rooms
	 */
	public static void displayNumBeds(Collection<? extends StandardRoom> rooms) {
		System.out.println("Number of beds available: " + getNumBeds(rooms));
	}
	
	// print details of each room
	public static void printAllRooms(Collection<? extends Room> rooms) {
		for (var room : rooms) {
			room.printToScreen();
			System.out.println("*".repeat(20));
		}
		
	}
	
	/**
	 * Takes in a collection of any type of Room and returns a Set of all locations
	 * @param rooms
	 * @return Set<String> locations
	 */
	public static Set<String> getUniqueLocations(Collection<? extends Room> rooms) {
		if (collectionIsNullOrEmpty(rooms)) {
			return Collections.emptySet();
		}
		
		return rooms.stream()
				.map(Room::getLocation)
				.collect(Collectors.toSet());
	}
	
	
	/**
	 * Displays number of unique locations in a given collection of rooms
	 * @param room
	 */
	public static void displayNumLocations(Collection<? extends Room> room) {
		int uniqueLocations = getUniqueLocations(room).size();
		System.out.println("Number of Locations: " + uniqueLocations);
	}
	

	/**
	 * Takes in a collection of StandardRooms or a subclass of StandardRooms and returns a list of the most expensive rooms in Belfast in ascending order
	 * @param <T extends StandardRoom>
	 * @param rooms
	 * @return List<T>
	 */
	// T extends StandardRoom instead of room or else pricePerNight inaccessible 
	public static <T extends StandardRoom> List<T> getExpensiveRoomsInBelfast(Collection<T> rooms) {
		if (collectionIsNullOrEmpty(rooms)) {
			return Collections.emptyList();
		}
		
		return rooms.stream()
				.filter(room -> room.getLocation().equalsIgnoreCase("belfast"))
				.filter(room -> room.getAverageRating() >= 4)
				.sorted(Comparator.comparingDouble(room -> room.getPricePerNight())) // low - high
				.distinct()
				.collect(Collectors.toList()); 
		// use Collectors.toList() because .toList() is immutable and I want 
		// to find only the top 2. I can't do .limit(2) at the moment because this would give me the two cheapest. I'll reverse them in the displaying method
	}
	
	
	/**
	 * Takes a collection of StandardRooms or a subclass of StandardRoom and displays the two most expensive rooms in Belfast
	 * @param <T extends StandardRoom>
	 * @param rooms
	 */
	public static <T extends StandardRoom> void displayExpensiveRooms(Collection<T> rooms) {
		var mostExpensiveRooms = getExpensiveRoomsInBelfast(rooms);
		
		// final element will be most expensive
		int lastElement = mostExpensiveRooms.size() -1;
		int secondLastElement = lastElement -1;
		
		StandardRoom secondMostExpensive = mostExpensiveRooms.get(secondLastElement);
		StandardRoom mostExpensive = mostExpensiveRooms.get(lastElement);
		
		
		secondMostExpensive.printToScreen();
		mostExpensive.printToScreen();
		
	}
	
	/**
	 * Takes a List of Rooms and a filename/path and writes a csv file consisting of each room
	 * @param rooms: List<Room>
	 * @param fileName: String
	 */
	public static void writeRoomsToFile(List<Room> rooms, String fileName) {
		try (var bw = new BufferedWriter(new FileWriter(fileName))) {
			bw.write(getHeaders());
			bw.newLine();
			for (var room : rooms) {
				bw.write(room.getCSVFormat());
				bw.newLine();
			}
		} catch (IOException e) {
			System.err.println("Error writing to file");
		}
	}
	
	private static String getHeaders() {
		return "Name,Location,Average rating,Number of ratings,Price per night,Number of beds,Room type";
	}
	
	private static <T> boolean collectionIsNullOrEmpty(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}
	
	
	
	// menu stuff
	private static void printMenu() {
		System.out.println("1. Display number of total beds in list of rooms");
		System.out.println("2. Display number of unique locations in list of rooms");
		System.out.println("3. Display 2 most expensive rooms in Belfast");
		System.out.println("4. Display total number of rooms in list");
		System.out.println("5. Write rooms to file rooms_processed.csv");
		System.out.println("6. Print all rooms");
		System.out.println("7. Quit");
	}
	
	private static int getInput() {
		while (true) {
			try {
				int input = Integer.parseInt(s.nextLine());
				if (input > 0 && input < 7) {
					return input;
				} else {
					System.out.println("Pick a number on the menu");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid number");
			}
		}
	}
}
