package p3;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import p3.models.StandardRoom;
import p3.models.StandardRoom.RoomType;

/**
 * @author Eoghain Magee 40301916
 */
class TestSortingUtil {
	List<StandardRoom> rooms;
	StandardRoom room1;
	StandardRoom room2;
	StandardRoom room3;

	@BeforeEach
	void setUp() throws Exception {
		room1 = StandardRoom.of("Apex Grassmarket","Edinburgh",4.5,2200,125,2, RoomType.LUXURY);
		room2 = StandardRoom.of("Bullitt Hotel","Belfast",4.6,890,120,1,RoomType.DELUXE);
		room3 = StandardRoom.of("Europa Hotel","Belfast",4.5,3100,110,2,RoomType.LUXURY);
		rooms = List.of(room1, room2, room3);
	}

	@Test
	void testGetNumRooms() {
		assertEquals(3, RoomProcessor.getNumRooms(rooms));
	}

	@Test
	void testGetNumBeds() {
		assertEquals(5, RoomProcessor.getNumBeds(rooms));
	}

	@Test
	void testGetUniqueLocations() {
		assertEquals(2, RoomProcessor.getUniqueLocations(rooms).size());
	}
	
	@Test
	void testGetExpensiveRoomsInBelfast() {
		assertEquals(List.of(room3, room2), RoomProcessor.getExpensiveRoomsInBelfast(rooms));
	}

}
