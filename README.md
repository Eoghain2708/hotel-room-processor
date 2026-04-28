This is an exam specification, the code for which was written without access to the internet with a time limit of 3 hours.

Part 1 – Object-oriented programming, unit testing, and reading from file - 70%


## CSV file
Name,Location,Average rating,Number of ratings,Price per night,Number of beds,Room type<br/>
Apex Grassmarket,Edinburgh,4.5,2200,125,2,LUXURY<br/>
Bullitt Hotel,Belfast,4.6,890,120,1,DELUXE<br/>
Clayton Hotel Burlington,Dublin,4.4,2500,135,1,DELUXE<br/>
Dakota Hotel,Manchester,4.8,1100,165,1,DELUXE<br/>
Europa Hotel,Belfast,4.5,3100,110,2,LUXURY<br/>
Grand Hotel,Cardiff,4.3,1600,95,1,DELUXE<br/>
Hilton Canary Wharf,London,4.4,3200,160,2,LUXURY<br/>
Holiday Inn Express  ,Belfast   ,4.1,1200,85,2,LUXURY<br/>
Ibis Royal Mile,Edinburgh,4.2,3500,80,1,BASIC<br/>
Jurys Inn,Glasgow,4,2700,70,1,BASIC<br/>
Kimpton Clocktower,Manchester,4.7,3100,155,5,SUITE<br/>
Maldron Hotel Parnell,Dublin,4.1,1800,95,4,SUITE<br/>
Malmaison,Glasgow,4.5,1400,130,2,LUXURY<br/>
Motel One Royal Exchange,Manchester,4.5,2800,85,2,LUXURY<br/>
Premier Inn City Centre,Belfast,4.2,5600,65,1,BASIC<br/>
Radisson Blu,Glasgow,4.4,1900,110,1,DELUXE<br/>
Savoy Hotel,London,4.8,7200,380,1,DELUXE<br/>
Sleeperz Hotel,Cardiff,4.1,1100,60,2,LUXURY<br/>
St Pancras Renaissance,London,4.7,4100,210,3,SUITE<br/>
Temple Bar Inn,Dublin,4.3,2100,115,2,LUXURY<br/>
Ten Square Hotel,Belfast,4.4,980,105,1,DELUXE<br/>
The Balmoral,Edinburgh,4.9,4800,280,1,DELUXE<br/>
The Merchant Hotel,Belfast,4.8,2450,185,1,DELUXE<br/>
The Midland,Manchester,4.6,4200,140,1,DELUXE<br/>
The Ritz,London,4.9,8500,450,1,DELUXE<br/>
The Shelbourne,Dublin,4.8,3600,250,1,DELUXE<br/>
Titanic Hotel,Belfast,4.7,1850,145.5,4,SUITE<br/>
Titanic Hotel,Liverpool,4.7,2900,125,3,SUITE<br/>
Travelodge Covent Garden,London,3.9,12400,75,1,BASIC<br/>
YOTEL City Centre,Edinburgh,ZZZZ,1500,90,1,DELUXE<br/>


## Scenario:

You have been tasked to design, implement, and unit test part of an Accommodation Booking system. The
system is ultimately expected to support several specific accommodation types such as standard rooms,
apartments, villas, and glamping pods.
You are the first developer of the system and are tasked with designing and implementing the standard room
part of the system, although you are encouraged to design the system so that it can support the future
addition of other specific accommodation types.
All accommodation types will have the following attributes with the allowable values shown: <br/>

**Name:** A String of no more than 40 characters <br/>
**Location:** A String of no more than 40 characters <br/>
**Average rating:** A number between 0 and 5, e.g. 4.3 <br/>
**Number of ratings:** A whole number between 0 to 50,000 <br/>



**A standard room will contain the following attributes with the allowable values shown:**

Price per night: A non-negative monetary value, e.g. £55.00 <br/>
Number of beds: A number greater than 0 but less than 8 <br/>
Room type: Must be one of the following values: BASIC, LUXURY, DELUXE, SUITE <br/>


## Required Functionality 
At this time, the content of the other accommodation types is unspecified, but all accommodation types must
be rateable. This means that they must have the following method to allow the rating of the accommodation
to be updated appropriately: <br/>
<br/>
• addRating(int) <br/>
<br/>
This method will have one integer parameter which should be between 1 and 5 inclusive that represents the
new rating the accommodation has been given. If a new rating is outside the 1 to 5 range, the method should
throw an IllegalArgumentException. For valid values, the method should use the new rating to calculate and
update the average rating and also then increment the number of ratings. The calculation for a new average
rating is as follows: <br/>
<br/>
𝑁𝑒𝑤 𝑎𝑣𝑒𝑟𝑎𝑔𝑒 𝑟𝑎𝑡𝑖𝑛𝑔 = (𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑎𝑣𝑒𝑟𝑎𝑔𝑒 𝑟𝑎𝑡𝑖𝑛𝑔 × 𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑛𝑢𝑚𝑏𝑒𝑟 𝑜𝑓 𝑟𝑎𝑡𝑖𝑛𝑔𝑠) + 𝑛𝑒𝑤 𝑟𝑎𝑡𝑖𝑛𝑔 /
(𝑐𝑢𝑟𝑟𝑒𝑛𝑡 𝑛𝑢𝑚𝑏𝑒𝑟 𝑜𝑓 𝑟𝑎𝑡𝑖𝑛𝑔𝑠 + 1) <br/>
<br/>
Please note that the addRating() method should also increment the current number of ratings after this
calculation. It should also not be possible to update the average rating or number of ratings fields by calling
any other methods.
All accommodation types should also be displayable. This means that they must implement two key
functionalities:

printToScreen() : void Displays the accommodation details to the console precisely as
shown below:
Name :The Plaza <br/>
Location :New York <br/>
Rating :4.3 from 57 ratings <br/>
Price :£57.00 <br/>
Num Beds :3 <br/>
Type :BASIC <br/>


getCSVFormat() : String Returns a structured text representation of the accommodation in
the format shown in each data row of the rooms.csv file.


## Tasks: 
**1. OOP:**
Examine the rooms.csv file and determine the essential characteristics of a standard room within this
system. Each row of data shows the values that will need to be stored for a Standard Room.
Create the Java classes and other data structures necessary and suitable to store the Standard Room
data with all the attributes found in the rooms.csv file. When considering your design, thought should be
given to enabling code reusability, extensibility, and maintainability. Your design should be adaptable to
potentially include new accommodation types in the future.


**2. Unit Testing:**
Conduct simple unit tests for your class(es). For this assignment, focus your testing efforts on the
validation of the following fields and methods: <br/>
• Number of beds <br/>
• addRating(int): Write test cases to ensure that the method makes the correct updates.<br/>
You are NOT required to perform exhaustive testing of all methods and fields in your classes. The goal
of this section is to demonstrate your understanding of basic unit testing principles and your ability to
test specific data validation logic within your Standard Room class(es).
3. CSV Data Reading:<br/>
• Create a RoomProcessor.java class to read data from rooms.csv.<br/>
• Store the valid room objects in a LinkedList in the order they were read from the file. This will ensure<br/>
that the rooms are processed in the correct sequence in later tasks.<br/>
• Lines with data which violate the business rules should ideally be skipped and not stored in the list.<br/>
• You should clean (remove) any leading or trailing whitespace from all fields.<br/>
• While reading from the file, output each line of raw data read from the CSV file to the console for
debugging purposes.<br/>
• Once the file has been read, output a summary of how many lines of data have been read and also
how many lines were found to be valid. An example of the expected structure and formatting of the
output data (showing some dummy data) can be seen in Figure 1 at the end of this document.<br/>


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
Sample output:<br/>
Number of rooms is 5<br/>

2. displayNumBeds: Display the total number of beds available in the current list.
Sample output:<br/>
Number of beds available is 74<br/>

3. printAllRooms: Print the details for all rooms in the current list to the console in the format shown for
the printToScreen() method. Print a row of 20 asterisks between each room’s details, i.e.<br/>
“********************<br/>
4. displayNumLocations: Display the number of unique locations in the current List
Sample output (not necessarily correct number):<br/>
Number of Locations: 5<br/>

5. displayExpensiveRooms: Display details of the 2 most expensive rooms in Belfast with a rating of 4 or
more. Display the output order by price – low to high. Use the same output format as task 3.<br/>

6. writeCsvFile
• Create and write to a new CSV file named rooms_processed.csv in the project's root directory.<br/>
• Write the contents of each room in the same sequence they were initially read (or in the order of<br/>
your manually created objects), using a suitable collection that preserves order.<br/>
• You should use the getCSVFormat() method to format the string representation of each room. The
file should include a header row (the same as the rooms.csv file).<br/>
• Perform this file writing operation in a separate thread.<br/>
• The application should terminate gracefully after writing the rooms to the file.<br/>

