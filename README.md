Part 1 – Object-oriented programming, unit testing, and reading from file - 70%


**Scenario:**

You have been tasked to design, implement, and unit test part of an Accommodation Booking system. The
system is ultimately expected to support several specific accommodation types such as standard rooms,
apartments, villas, and glamping pods.
You are the first developer of the system and are tasked with designing and implementing the standard room
part of the system, although you are encouraged to design the system so that it can support the future
addition of other specific accommodation types.
All accommodation types will have the following attributes with the allowable values shown:

Name: A String of no more than 40 characters
Location: A String of no more than 40 characters
Average rating: A number between 0 and 5, e.g. 4.3
Number of ratings: A whole number between 0 to 50,000



**A standard room will contain the following attributes with the allowable values shown:**

Price per night: A non-negative monetary value, e.g. £55.00
Number of beds: A number greater than 0 but less than 8
Room type: Must be one of the following values: BASIC, LUXURY, DELUXE, SUITE


**Required Functionality**
At this time, the content of the other accommodation types is unspecified, but all accommodation types must
be rateable. This means that they must have the following method to allow the rating of the accommodation
to be updated appropriately:
• addRating(int)
This method will have one integer parameter which should be between 1 and 5 inclusive that represents the
new rating the accommodation has been given. If a new rating is outside the 1 to 5 range, the method should
throw an IllegalArgumentException. For valid values, the method should use the new rating to calculate and
update the average rating and also then increment the number of ratings. The calculation for a new average
rating is as follows:
𝑁𝑒𝑤 𝑎𝑣𝑒𝑟𝑎𝑔𝑒 𝑟𝑎𝑡𝑖𝑛𝑔 = (𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑎𝑣𝑒𝑟𝑎𝑔𝑒 𝑟𝑎𝑡𝑖𝑛𝑔 × 𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑛𝑢𝑚𝑏𝑒𝑟 𝑜𝑓 𝑟𝑎𝑡𝑖𝑛𝑔𝑠) + 𝑛𝑒𝑤 𝑟𝑎𝑡𝑖𝑛𝑔
𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑛𝑢𝑚𝑏𝑒𝑟 𝑜𝑓 𝑟𝑎𝑡𝑖𝑛𝑔𝑠 + 1
Please note that the addRating() method should also increment the current number of ratings after this
calculation. It should also not be possible to update the average rating or number of ratings fields by calling
any other methods.
All accommodation types should also be displayable. This means that they must implement two key
functionalities:

printToScreen() : void Displays the accommodation details to the console precisely as
shown below:
Name :The Plaza
Location :New York
Rating :4.3 from 57 ratings
Price :£57.00
Num Beds :3
Type :BASIC


getCSVFormat() : String Returns a structured text representation of the accommodation in
the format shown in each data row of the rooms.csv file.


**Tasks:**
**1. OOP:**
Examine the rooms.csv file and determine the essential characteristics of a standard room within this
system. Each row of data shows the values that will need to be stored for a Standard Room.
Create the Java classes and other data structures necessary and suitable to store the Standard Room
data with all the attributes found in the rooms.csv file. When considering your design, thought should be
given to enabling code reusability, extensibility, and maintainability. Your design should be adaptable to
potentially include new accommodation types in the future.


**2. Unit Testing:**
Conduct simple unit tests for your class(es). For this assignment, focus your testing efforts on the
validation of the following fields and methods:
• Number of beds
• addRating(int): Write test cases to ensure that the method makes the correct updates.
You are NOT required to perform exhaustive testing of all methods and fields in your classes. The goal
of this section is to demonstrate your understanding of basic unit testing principles and your ability to
test specific data validation logic within your Standard Room class(es).
3. CSV Data Reading:
• Create a RoomProcessor.java class to read data from rooms.csv.
• Store the valid room objects in a LinkedList in the order they were read from the file. This will ensure
that the rooms are processed in the correct sequence in later tasks.
• Lines with data which violate the business rules should ideally be skipped and not stored in the list.
• You should clean (remove) any leading or trailing whitespace from all fields.
• While reading from the file, output each line of raw data read from the CSV file to the console for
debugging purposes.
• Once the file has been read, output a summary of how many lines of data have been read and also
how many lines were found to be valid. An example of the expected structure and formatting of the
output data (showing some dummy data) can be seen in Figure 1 at the end of this document.


**Part 2 – Data processing, JCF and Threads – 30%**
Note: If you are unable to successfully implement the file reading and data storage in Part 1, you may manually
declare and instantiate a series of StandardRoom objects (use at least the first 5 rows of data from the rooms.csv
file to create your objects) and store them in a LinkedList in the order they are shown in the file. This will allow
you to proceed with the tasks in Part 2. However, please be aware that marks will still be allocated to the
successful implementation of Part 1.
For each of the data processing tasks in Part 2, it is recommended that you implement separate methods within
your RoomProcessor class to encapsulate the logic for each specific task. Each method should then be called
from the main method in your RoomProcessor class to demonstrate the output to the console.
PLEASE NOTE, THERE IS NO EXPECTATION OR REQUIREMENT FOR YOU TO IMPLEMENT A MENU SYSTEM
WITHIN YOUR PROGRAM. YOU CAN ASSUME THAT ANY MENU SYSTEM FOR THE BOOKING SYSTEM WOULD BE
ADDED IN THE FUTURE.
**Tasks:**
1. displayNumRooms: Display the number of rooms available in the current list.
Sample output:
Number of rooms is 5

3. displayNumBeds: Display the total number of beds available in the current list.
Sample output:
Number of beds available is 74

P3 2025-2026
4
3. printAllRooms: Print the details for all rooms in the current list to the console in the format shown for
the printToScreen() method. Print a row of 20 asterisks between each room’s details, i.e.
“********************”
4. displayNumLocations: Display the number of unique locations in the current List
Sample output (not necessarily correct number):
Number of Locations: 5

5. displayExpensiveRooms: Display details of the 2 most expensive rooms in Belfast with a rating of 4 or
more. Display the output order by price – low to high. Use the same output format as task 3.

6. writeCsvFile
• Create and write to a new CSV file named rooms_processed.csv in the project's root directory.
• Write the contents of each room in the same sequence they were initially read (or in the order of
your manually created objects), using a suitable collection that preserves order.
• You should use the getCSVFormat() method to format the string representation of each room. The
file should include a header row (the same as the rooms.csv file).
• Perform this file writing operation in a separate thread.
• The application should terminate gracefully after writing the rooms to the file.

