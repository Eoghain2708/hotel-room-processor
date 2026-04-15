package p3.models;

import java.util.Objects;

/**
 * @author Eoghain Magee 40301916
 */
public abstract class Room implements Displayable {
	
	// separate in case one changes
	private final static int MAX_NAME_LENGTH = 40;
	private final static int MAX_LOCATION_LENGTH = 40;
	
	private final static double MIN_AVERAGE_RATING = 0;
	private final static double MAX_AVERAGE_RATING = 5;
	
	private final static int MIN_AMOUNT_OF_RATINGS = 0;
	private final static int MAX_AMOUNT_OF_RATINGS = 50_000;
	
	private final static int MIN_RATING_SCORE = 1;
	private final static int MAX_RATING_SCORE = 5;
	
	private final String name, location; // preferring immutability where possible 
	private double averageRating;
	private int numRatings;
	
	
	public Room(String name, String location, double averageRating, int numRatings) {

		this.name = validateName(name);
		this.location = validateLocation(location);
		this.averageRating = validateAverageRating(averageRating);
		this.numRatings = validateNumRatings(numRatings);
	}
	
	
	public final double getAverageRating() {
		return averageRating;
	}

	

	public final int getNumRatings() {
		return numRatings;
	}



	public final String getName() {
		return name;
	}



	public final String getLocation() {
		return location;
	}
	
	
	public void addRating(int val) {
		if (val <  MIN_RATING_SCORE || val > MAX_RATING_SCORE) {
			throw new IllegalArgumentException("Rating must be between " + MIN_RATING_SCORE + " and " + MAX_RATING_SCORE);
		}
		
		calculateAverageRating(val);;
		incrementRatings();
		
		System.out.println(numRatings);
	}
	
	/**
	 * Private method so as to avoid any incrementing outside of addRating
	 * Increments the amount of ratings by 1
	 */
	private void incrementRatings() {
		this.numRatings++;
	}
	
	/**
	 * Calculates average rating via specified formula (I hope)
	 * @param val: int
	 */
	private void calculateAverageRating(int val) {
		this.averageRating = ((averageRating * numRatings) + val) / (numRatings + 1);
	}

	/**
	 * Returns itself after being validated to be not null, blank, or over the specified number of characters long
	 * @param val
	 * @return String (name)
	 *  @throws IllegalArgumentException if invalid
	 */
	private String validateName(String val) {
		ensureNotNullOrBlank(val, "Name");
		
		if (val.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Name cannot be more than " + MAX_NAME_LENGTH + " characters long");
		}
		return val;
	}
	
	/**
	 * Returns itself after being validated to be not null, blank, or over the specified number of characters long
	 * @param val
	 * @return String (location)
	 * @throws IllegalArgumentException if invalid
	 */
	private String validateLocation(String val) {
		ensureNotNullOrBlank(val, "Location");
		
		if (val.length() > MAX_LOCATION_LENGTH) {
			throw new IllegalArgumentException("Name cannot be more than " + MAX_LOCATION_LENGTH + " characters long");
		}
		
		return val;
	}
	
	
	/**
	 * Returns itself after being validated to be within specified range
	 * @param val
	 * @return double (average rating)
	 * @throws IllegalArgumentException if invalid
	 */
	private double validateAverageRating(double val) {
		if (val < MIN_AVERAGE_RATING || val > MAX_AVERAGE_RATING) {
			throw new IllegalArgumentException("Average rating must be between " + MIN_AVERAGE_RATING + " and " + MAX_AVERAGE_RATING);
		}
		return val;
	}
	
	/**
	 * Returns itself after being validated to be within specified range
	 * @param val
	 * @return
	 * @throws IllegalArgumentException if invalid
	 */
	private int validateNumRatings(int val) {
		if (val < MIN_AMOUNT_OF_RATINGS || val > MAX_AMOUNT_OF_RATINGS) {
			throw new IllegalArgumentException("Number of ratings must be more than " + MIN_AMOUNT_OF_RATINGS + " and cannot exceed " + MAX_AMOUNT_OF_RATINGS);
		}
		return val;
	}
	
	/**
	 * Helper method to throw exception if String is null or blank
	 * @param val - the value being examined 
	 * @param field - the class attribute which the value is associated with, used in error message
	 * @throws IllegalArgumentException is val == null or val.isBlank() returns true
	 */
	private static void ensureNotNullOrBlank(String val, String field) {
		if (Objects.requireNonNull(val).isBlank()) {
			throw new IllegalArgumentException(field + " cannot be blank");
		}
	}


	@Override
	public int hashCode() {
		return Objects.hash(averageRating, location, name, numRatings);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return Double.doubleToLongBits(averageRating) == Double.doubleToLongBits(other.averageRating)
				&& Objects.equals(location, other.location) && Objects.equals(name, other.name)
				&& numRatings == other.numRatings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room [name: ");
		builder.append(name);
		builder.append(", location: ");
		builder.append(location);
		builder.append(", averageRating: ");
		builder.append(averageRating);
		builder.append(", numRatings: ");
		builder.append(numRatings);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
