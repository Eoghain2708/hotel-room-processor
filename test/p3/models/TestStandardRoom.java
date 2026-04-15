package p3.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import p3.models.StandardRoom.RoomType;


/**
 * @author Eoghain Magee 40301916
 */
class TestStandardRoom {
	
	String blank;
	String validName;
	String nameTooLong;
	
	String validLocation;
	String locationTooLong;
	
	double validAverageRating;
	double negativeAverageRating;
	double averageRatingTooHigh;
	
	
	int negativeNum;
	
	int validNumRatings;
	int numRatingsTooHigh;
	
	double validPricePerNight;
	double negativePricePerNight;
	
	int validNumBeds;
	int numBedsTooHigh;
	
	RoomType validRoomType;
	
	StandardRoom test;
	

	@BeforeEach
	void setUp() throws Exception {
		
		blank = "";
		validName = "the house";
		nameTooLong = ".".repeat(41);
		
		validLocation = "belfast";
		locationTooLong = ".".repeat(41);
		
		validAverageRating = 3;
		negativeAverageRating = -1;
		averageRatingTooHigh = 6;
		
		negativeNum = -1;
		
		validNumRatings = 25_000;
		numRatingsTooHigh = 60_000;
		
		validPricePerNight = 100;
		negativePricePerNight = -50;
		
		validNumBeds = 4;
		numBedsTooHigh = 9;
		
		validRoomType = RoomType.DELUXE;
		
		test = StandardRoom.of(validName, validLocation, validAverageRating, validNumRatings,
				validPricePerNight, validNumBeds, validRoomType);
	}

	@Test
	void rejectsInvalidName() {
		assertThrows(NullPointerException.class, () -> {
			StandardRoom.of(null, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(blank, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(nameTooLong, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
	}
	
	@Test
	void rejectsInvalidLocation() {
		assertThrows(NullPointerException.class, () -> {
			StandardRoom.of(validName, null, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, blank, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, locationTooLong, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
	}
	
	@Test
	void rejectsInvalidAvgRating() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, negativeAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, averageRatingTooHigh, validNumRatings,
					validPricePerNight, validNumBeds, validRoomType);
		});
	}
	
	@Test
	void rejectsInvalidNumRatings() {
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, negativeNum,
					validPricePerNight, validNumBeds, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, numRatingsTooHigh,
					validPricePerNight, validNumBeds, validRoomType);
		});
	}
	
	
	@Test 
	void rejectsInvalidPricePerNight() {
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, validNumRatings,
					negativePricePerNight, validNumBeds, validRoomType);
		});
	}
	
	@Test
	void rejectsInvalidNumBeds() {
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, numBedsTooHigh, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, negativeNum, validRoomType);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, 0, validRoomType);
		});
	}
	
	@Test
	void rejectsNullRoomType() {
		assertThrows(NullPointerException.class, () -> {
			StandardRoom.of(validName, validLocation, validAverageRating, validNumRatings,
					validPricePerNight, validNumBeds, null);
		});
	}
	
	@Test
	void testGetPricePerNight() {
		assertEquals(test.getPricePerNight(), validPricePerNight);
	}

	@Test
	void testGetNumBeds() {
		assertEquals(test.getNumBeds(), validNumBeds);
	}

	@Test
	void testGetRoomType() {
		assertEquals(test.getRoomType(), validRoomType);
	}

	@Test
	void testGetAverageRating() {
		assertEquals(test.getAverageRating(), validAverageRating);
	}

	@Test
	void testGetNumRatings() {
		assertEquals(test.getNumRatings(), validNumRatings);
	}

	@Test
	void testGetName() {
		assertEquals(test.getName(), validName);
	}

	@Test
	void testGetLocation() {
		assertEquals(test.getLocation(), validLocation);
	}

	@Test
	void testAddRatingHappy() {
		double average = 4;
		int numberOfRatings = 1;
		StandardRoom t = StandardRoom.of(validName, validLocation, average, numberOfRatings,
				validPricePerNight, validNumBeds, validRoomType);
		t.addRating(2);
		assertEquals(2, t.getNumRatings());
		assertEquals(3, t.getAverageRating());
	}
	
	@Test
	void testAddRatingSad() {
		double average = 4;
		int numberOfRatings = 1;
		StandardRoom t = StandardRoom.of(validName, validLocation, average, numberOfRatings,
				validPricePerNight, validNumBeds, validRoomType);
		assertThrows(IllegalArgumentException.class, () -> {
			t.addRating(6);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			t.addRating(0);
		});
	}

}
