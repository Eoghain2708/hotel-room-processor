package p3.models;

import java.util.Objects;

/**
 * @author Eoghain Magee 40301916
 */
public class StandardRoom extends Room {

	public enum RoomType {
		BASIC, LUXURY, DELUXE, SUITE;
	}
	
	private static int MIN_NUM_BEDS = 1;
	private static int MAX_NUM_BEDS = 8;
	
	



	private final double pricePerNight;
	private final int numBeds;
	private final RoomType roomType;
	
	private StandardRoom(String name, String location, double averageRating, int numRatings, double pricePerNight, int numBeds, RoomType roomType) {
		super(name, location, averageRating, numRatings);
		this.pricePerNight = validatePricePerNight(pricePerNight);
		this.numBeds = validateNumBeds(numBeds);
		this.roomType = validateRoomType(roomType);
	}
	
	public static StandardRoom of(String name, String location, double averageRating, int numRatings,
			double pricePerNight, int numBeds, RoomType roomType) {
		return new StandardRoom(name, location, averageRating, numRatings, pricePerNight, numBeds, roomType);
	}
	

	
	public final double getPricePerNight() {
		return pricePerNight;
	}



	public final int getNumBeds() {
		return numBeds;
	}



	public final RoomType getRoomType() {
		return roomType;
	}


	/**
	 * Returns itself after being validated to be within specified range
	 * @param val
	 * @return double (average rating)
	 * @throws IllegalArgumentException if invalid
	 */
	private double validatePricePerNight(double val) {
		if (val < 0) {
			throw new IllegalArgumentException("Price per night cannot be negative");
		}
		return val;
	}
	
	/**
	 * Returns itself after being validated to be within specified range
	 * @param val
	 * @return double (average rating)
	 * @throws IllegalArgumentException if invalid
	 */
	private int validateNumBeds(int val) {
		if (val < MIN_NUM_BEDS || val > MAX_NUM_BEDS) {
			throw new IllegalArgumentException("Number of beds must be between " + MIN_NUM_BEDS + " and " + MAX_NUM_BEDS);
		}
		return val;
	}
	
	
	/**
	 * Returns itself after being validated as not null
	 * @param val
	 * @return double (average rating)
	 * @throws NullPointerException if null
	 */
	private RoomType validateRoomType(RoomType val) {
		return Objects.requireNonNull(val);
	}

	// £

	@Override
	public void printToScreen() {
		System.out.printf("%-25s %-10s%n", "Name:", getName());
		System.out.printf("%-25s %-10s%n", "Location:", getLocation());
		System.out.printf("%-25s %-10s%n", "Rating:", getAverageRating());
		System.out.printf("%-25s %s%-10.2f%n", "Price:", "£", getPricePerNight());
		System.out.printf("%-25s %-10d%n", "Num beds:", getNumBeds());
		System.out.printf("%-25s %-10s%n", "Type:", getRoomType().toString());
	}

	@Override
	public String getCSVFormat() {
		return String.format("%s,%s,%.1f,%d,%.2f,%d,%s", getName(), getLocation(), getAverageRating(), getNumRatings(), getPricePerNight(), getNumBeds(), getRoomType());
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(numBeds, pricePerNight, roomType);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardRoom other = (StandardRoom) obj;
		return numBeds == other.numBeds
				&& Double.doubleToLongBits(pricePerNight) == Double.doubleToLongBits(other.pricePerNight)
				&& roomType == other.roomType;
	}
	
	@Override
	public String toString() {
		var sb = new StringBuilder(super.toString());
		sb.append("Standard Room Exclusive features: [")
		.append("Price per night: ").append(pricePerNight)
		.append(", Number of Beds: ").append(numBeds)
		.append("Room Type: ").append(roomType);
		
		return sb.toString();
	}
	
	

}
