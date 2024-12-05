package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Event {
	
	private String eventName;
	private String eventCity;
	private String eventNeighborhood;
	private int eventNeighborhoodNum;
	private int eventYear;
	private int eventMonth;
	private int eventDay;
	private int eventHour;
	private int eventMinute;
	private int eventNumOfRows;
	private int eventMaxNumOfSeatsInRow;
	private int eventNextInvitationNum;
	private Seat[][] eventSeats;
	private Set<Invitation> eventInvitations;
	
	private final int HEURISTICS_SEATS_AROUND = 10;
	
	// Constructor
	public Event(String eventName, String eventCity, String eventNeighborhood, int eventNeighborhoodNum, int eventYear, int eventMonth, int eventDay, int eventHour
			, int eventMinute, int eventNumOfRows, int eventMaxNumOfSeatsInRow, int[][] seatPrices) throws Exception {
		if(!setEventName(eventName))
			throw new Exception("The name of the event is not valid! \nIt should contain at least one letter, and contain letters, spaces and numbers only!");
		if(!setEventCity(eventCity))
			throw new Exception("The city of the event is not valid! \nIt should begin with letter, and contain only letters and spaces!");
		if(!setEventNeighborhood(eventNeighborhood))
			throw new Exception("The neighborhood of the event is not valid! \nIt should begin with letter, and contain only letters and spaces!");
		if(!setEventNeighborhoodNum(eventNeighborhoodNum))
			throw new Exception("The neighborhood number of the event is not valid! \nIt should be bigger than 0!");
		if(!setEventDate(eventYear, eventMonth, eventDay, eventHour, eventMinute))
			throw new Exception("The date of the event is not valid! \nIt should be in the future and valid!");
		if(!setEventNumOfRows(eventNumOfRows))
			throw new Exception("The number of rows in the event is not valid! \nIt should be bigger than 0!");
		if(!setEventMaxNumOfSeatsInRow(eventMaxNumOfSeatsInRow))
			throw new Exception("The max number of seats in row in the event is not valid! \nIt should be bigger than 0!");
		eventNextInvitationNum = 1;
		eventSeats = new Seat[eventNumOfRows][eventMaxNumOfSeatsInRow];
		for(int rowNum = 0; rowNum < eventNumOfRows; rowNum++)
			for(int seatNum = 0; seatNum < eventMaxNumOfSeatsInRow; seatNum++)
				eventSeats[rowNum][seatNum] = new Seat(rowNum + 1, seatNum + 1, seatPrices[rowNum][seatNum], this);
		eventInvitations = new HashSet<Invitation>();
	}
	
	public Event(String eventName, String eventCity, String eventNeighborhood, int eventNeighborhoodNum, int eventYear, int eventMonth, int eventDay, int eventHour
			, int eventMinute, int eventNumOfRows, int eventMaxNumOfSeatsInRow, int eventNextInvitationNum, int[][] seatPrices) throws Exception {
		if(!setEventName(eventName))
			throw new Exception("The name of the event is not valid! \nIt should contain at least one letter, and contain letters, spaces and numbers only!");
		if(!setEventCity(eventCity))
			throw new Exception("The city of the event is not valid! \nIt should begin with letter, and contain only letters and spaces!");
		if(!setEventNeighborhood(eventNeighborhood))
			throw new Exception("The neighborhood of the event is not valid! \nIt should begin with letter, and contain only letters and spaces!");
		if(!setEventNeighborhoodNum(eventNeighborhoodNum))
			throw new Exception("The neighborhood number of the event is not valid! \nIt should be bigger than 0!");
		if(!setEventDate(eventYear, eventMonth, eventDay, eventHour, eventMinute))
			throw new Exception("The date of the event is not valid! \nIt should be in the future and valid!");
		if(!setEventNumOfRows(eventNumOfRows))
			throw new Exception("The number of rows in the event is not valid! \nIt should be bigger than 0!");
		if(!setEventMaxNumOfSeatsInRow(eventMaxNumOfSeatsInRow))
			throw new Exception("The maax number of seats in row in the event is not valid! \nIt should be bigger than 0!");
		if(!setEventNextInvitationNum(eventNextInvitationNum))
			throw new Exception("The number of next invitation in the event is not valid! \nIt should be bigger than 0!");
		eventSeats = new Seat[eventNumOfRows][eventMaxNumOfSeatsInRow];
		for(int rowNum = 0; rowNum < eventNumOfRows; rowNum++)
			for(int seatNum = 0; seatNum < eventMaxNumOfSeatsInRow; seatNum++)
				eventSeats[rowNum][seatNum] = new Seat(rowNum + 1, seatNum + 1, seatPrices[rowNum][seatNum], this);
		eventInvitations = new HashSet<Invitation>();
	}
	
	public Event(Event otherEvent) throws Exception {
		setEventName(otherEvent.eventName);
		setEventCity(otherEvent.eventCity);
		setEventNeighborhood(otherEvent.eventNeighborhood);
		setEventNeighborhoodNum(otherEvent.eventNeighborhoodNum);
		setEventDate(otherEvent.eventYear, otherEvent.eventMonth, otherEvent.eventDay, otherEvent.eventHour, otherEvent.eventMinute);
		setEventNumOfRows(otherEvent.eventNumOfRows);
		setEventMaxNumOfSeatsInRow(otherEvent.eventMaxNumOfSeatsInRow);
		setEventNextInvitationNum(otherEvent.eventNextInvitationNum);
		eventSeats = new Seat[eventNumOfRows][eventMaxNumOfSeatsInRow];
		for(int rowNum = 0; rowNum < eventNumOfRows; rowNum++)
			for(int seatNum = 0; seatNum < eventMaxNumOfSeatsInRow; seatNum++)
				eventSeats[rowNum][seatNum] = new Seat(otherEvent.eventSeats[rowNum][seatNum]);
		eventInvitations = new HashSet<Invitation>();
		for(Invitation invitation : otherEvent.eventInvitations)
			eventInvitations.add(invitation);
	}

	// Gets
	public String getEventName() {
		return eventName;
	}
	
	public String getEventCity() {
		return eventCity;
	}
	
	public String getEventNeighborhood() {
		return eventNeighborhood;
	}
	
	public int getEventNeighborhoodNum() {
		return eventNeighborhoodNum;
	}
	
	public int getEventYear() {
		return eventYear;
	}
	
	public int getEventMonth() {
		return eventMonth;
	}
	
	public int getEventDay() {
		return eventDay;
	}
	
	public int getEventHour() {
		return eventHour;
	}
	
	public int getEventMinute() {
		return eventMinute;
	}
	
	public int getEventNumOfRows() {
		return eventNumOfRows;
	}

	public int getEventMaxNumOfSeatsInRow() {
		return eventMaxNumOfSeatsInRow;
	}
	
	public int getEventNextInvitationNum() {
		int invitaionNum = eventNextInvitationNum;
		eventNextInvitationNum += 1;
		return invitaionNum;
	}
	
	public int getEventNextInvitationNumWithoutIncrease() {
		return eventNextInvitationNum;
	}
	
	public Seat[][] getEventSeats() {
		return eventSeats;
	}
	
	public Seat[][] getCopyOfSeats() {
		Seat seatsCopy[][] = new Seat[eventNumOfRows][eventMaxNumOfSeatsInRow];
		
		for(int rowIndex = 0; rowIndex < eventNumOfRows; rowIndex++)
			for(int seatIndex = 0; seatIndex < eventMaxNumOfSeatsInRow; seatIndex++)
				seatsCopy[rowIndex][seatIndex] = new Seat(eventSeats[rowIndex][seatIndex]);
		
		return seatsCopy;
	}
	
	public Set<Invitation> getEventInvitations() {
		return eventInvitations;
	}
	
	// Sets
	public boolean setEventName(String eventName) {
		if(!CheckInputs.checkEventName(eventName))
			return false;
		
		this.eventName = eventName;
		return true;
	}

	public boolean setEventCity(String eventCity) {
		if(!CheckInputs.checkName(eventCity))
			return false;
		
		this.eventCity = eventCity;
		return true;
	}
	
	public boolean setEventNeighborhood(String eventNeighborhood) {
		if(!CheckInputs.checkName(eventNeighborhood))
			return false;
		
		this.eventNeighborhood = eventNeighborhood;
		return true;
	}
	
	public boolean setEventNeighborhoodNum(int eventNeighborhoodNum) {
		if(!CheckInputs.checkNeighborhoodNum(eventNeighborhoodNum))
			return false;
		
		this.eventNeighborhoodNum = eventNeighborhoodNum;
		return true;
	}
	
	public boolean setEventDate(int eventYear, int eventMonth, int eventDay, int eventHour, int eventMinute) {
		if(!CheckInputs.checkFutureAndValidDate(eventYear, eventMonth, eventDay, eventHour, eventMinute))
			return false;
		
		this.eventYear = eventYear;
		this.eventMonth = eventMonth;
		this.eventDay = eventDay;
		this.eventHour = eventHour;
		this.eventMinute = eventMinute;
		return true;
	}

	public boolean setEventNumOfRows(int eventNumOfRows) {
		if(eventNumOfRows <= 0)
			return false;
		
		this.eventNumOfRows = eventNumOfRows;
		return true;
	}

	public boolean setEventMaxNumOfSeatsInRow(int eventMaxNumOfSeatsInRow) {
		if(eventMaxNumOfSeatsInRow <= 0)
			return false;
		
		this.eventMaxNumOfSeatsInRow = eventMaxNumOfSeatsInRow;
		return true;
	}
	
	public boolean setEventNextInvitationNum(int eventNextInvitationNum) {
		if(eventNextInvitationNum <= 0)
			return false;

		this.eventNextInvitationNum = eventNextInvitationNum;
		return true;
	}

	public boolean setEventSeats(Seat[][] eventSeats) {
		this.eventSeats = eventSeats;
		return true;
	}
	
	public boolean setEventInvitations(Set<Invitation> eventInvitations) {
		this.eventInvitations = eventInvitations;
		return true;
	}
	
	// Calculate target function value
	private int calculateTargetFunctionValue(int numOfLoses, int[] resultsForFoundPlace) {
		if(resultsForFoundPlace[0] == 0) // Invitation leaves no place around
			return (10 * numOfLoses) - (5 * Math.max(eventNumOfRows, eventMaxNumOfSeatsInRow)) - (resultsForFoundPlace[1]);
		return (10 * numOfLoses) - (5 * resultsForFoundPlace[0]) - (resultsForFoundPlace[1]);
	}
    
	// Calculate loses around seat
	private int calculateLosesAroundSeat(int rowIndex, int seatIndex, int numOfSeatsToAllocate, int numOfSpaceSeats) {
		int sumOfLoses = 0;
		
		// Move on rows around
        for(int rowIndexNum = rowIndex - numOfSpaceSeats; rowIndexNum <= rowIndex + numOfSpaceSeats; rowIndexNum++) {
			
        	// Row in range to check and valid
        	if((rowIndexNum >= 0) && (rowIndexNum < eventNumOfRows)) {
        		
        		// Move on columns around
		        for(int seatIndexNum = seatIndex - numOfSpaceSeats; seatIndexNum < seatIndex + numOfSeatsToAllocate + numOfSpaceSeats; seatIndexNum++) {
		        	
		        	// Column in range to check and valid
		            if((seatIndexNum >= 0) && (seatIndexNum < eventMaxNumOfSeatsInRow)) {
		            	
		            	// Seat will be space
		            	if((rowIndexNum != rowIndex) || (seatIndexNum < seatIndex) || (seatIndexNum >= seatIndex + numOfSeatsToAllocate)) {
			            	
		            		// Seat is available
			                if(eventSeats[rowIndexNum][seatIndexNum].getSeatIsAvailable())
			                	sumOfLoses += eventSeats[rowIndexNum][seatIndexNum].getSeatPrice();
		            	}
		            }
		        }
			}
		}
		
		return sumOfLoses;
	}
	
	// Get number of free seats to right from position
	private int getNumberOfFreeSeatsToRightFromPosition(int rowIndex, int seatIndex) {
		int freeSeats = 0;
		int numOfSeatsToRight = 1;
		
		if((rowIndex < 0) || (rowIndex >= eventNumOfRows) || (seatIndex < 0) || (seatIndex >= eventMaxNumOfSeatsInRow)) // Out of seats
			return 0;
		
		while(seatIndex + numOfSeatsToRight < eventMaxNumOfSeatsInRow) { // Move until get to end of row
			// There is empty seat near
			if((!eventSeats[rowIndex][seatIndex + numOfSeatsToRight].getSeatIsTaken()) && (eventSeats[rowIndex][seatIndex + numOfSeatsToRight].getSeatIsAvailable())) {
				freeSeats++;
				numOfSeatsToRight++;
			}
			// Get to unavailable seat or occupied seat
			else
				return freeSeats;
		}
		
		return freeSeats;
	}
	
	// Get number of free seats to left from position
	private int getNumberOfFreeSeatsToLeftFromPosition(int rowIndex, int seatIndex) {
		int freeSeats = 0;
		int numOfSeatsToLeft = 1;
		
		if((rowIndex < 0) || (rowIndex >= eventNumOfRows) || (seatIndex < 0) || (seatIndex >= eventMaxNumOfSeatsInRow)) // Out of seats
			return 0;
		
		while(seatIndex - numOfSeatsToLeft >= 0) { // Move until get to begin of row
			// There is empty seat near
			if((!eventSeats[rowIndex][seatIndex - numOfSeatsToLeft].getSeatIsTaken()) && (eventSeats[rowIndex][seatIndex - numOfSeatsToLeft].getSeatIsAvailable())) {
				freeSeats++;
				numOfSeatsToLeft++;
			}
			// Get to unavailable seat or occupied seat
			else
				return freeSeats;
		}
		
		return freeSeats;
	}
	
	// Get number of free seats to up from position
	private int getNumberOfFreeSeatsToUpFromPosition(int rowIndex, int seatIndex) {
		int freeSeats = 0;
		int numOfSeatsToUp = 1;
		
		if((rowIndex < 0) || (rowIndex >= eventNumOfRows) || (seatIndex < 0) || (seatIndex >= eventMaxNumOfSeatsInRow)) // Out of seats
			return 0;
		
		while(rowIndex + numOfSeatsToUp < eventNumOfRows) { // Move until get to upper row
			// There is empty seat near
			if((!eventSeats[rowIndex + numOfSeatsToUp][seatIndex].getSeatIsTaken()) && (eventSeats[rowIndex + numOfSeatsToUp][seatIndex].getSeatIsAvailable())) {
				freeSeats++;
				numOfSeatsToUp++;
			}
			// Get to unavailable seat or occupied seat
			else
				return freeSeats;
		}
		
		return freeSeats;
	}
	
	// Get number of free seats to down from position
	private int getNumberOfFreeSeatsToDownFromPosition(int rowIndex, int seatIndex) {
		int freeSeats = 0;
		int numOfSeatsToDown = 1;
		
		if((rowIndex < 0) || (rowIndex >= eventNumOfRows) || (seatIndex < 0) || (seatIndex >= eventMaxNumOfSeatsInRow)) // Out of seats
			return 0;
		
		while(rowIndex - numOfSeatsToDown >= 0) { // Move until get to bottom row
			// There is empty seat near
			if((!eventSeats[rowIndex - numOfSeatsToDown][seatIndex].getSeatIsTaken()) && (eventSeats[rowIndex - numOfSeatsToDown][seatIndex].getSeatIsAvailable())) {
				freeSeats++;
				numOfSeatsToDown++;
			}
			// Get to unavailable seat or occupied seat
			else
				return freeSeats;
		}
		
		return freeSeats;
	}
	
	// Update number of seats that remain near and number of places with 0 available seats next to them
	private void updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(int[] reaturnValues, int numOfEmptySeats) {
		// First place: minimum number of seats that remain near
		// Second place: number of places with 0 available seats next to them
		
		if(numOfEmptySeats == 0) // Found 0 empty seats near
			reaturnValues[1]++;
		else { // Found empty seats near
			if(reaturnValues[0] == 0) // There were 0 empty seats until now
				reaturnValues[0] = numOfEmptySeats;
			else if(reaturnValues[0] > numOfEmptySeats) // There were empty seats until now and they were bigger than number of empty seats that found
				reaturnValues[0] = numOfEmptySeats;
		}
	}
	
	// Get minimum number of seats that remain near and number of places with 0 available seats next to them
	private int[] getMinNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(int rowIndex, int seatIndex, int numOfSeatsToAllocate, int numOfSpaceSeats) {
		// First place: minimum number of seats that remain near
		// Second place: number of places with 0 available seats next to them
		int reaturnValues[] = new int[2];
		reaturnValues[0] = 0;
		reaturnValues[1] = 0;
		
		// Check spaces to left and right (by move on rows)
		for(int numOfPlacesToMove = 0; numOfPlacesToMove <= numOfSpaceSeats; numOfPlacesToMove++) {
			int rightSpacesMoveUp = getNumberOfFreeSeatsToRightFromPosition(rowIndex + numOfPlacesToMove, seatIndex + numOfSeatsToAllocate + numOfSpaceSeats - 1);
			int rightSpacesMoveDown = getNumberOfFreeSeatsToRightFromPosition(rowIndex - numOfPlacesToMove, seatIndex + numOfSeatsToAllocate + numOfSpaceSeats - 1);
			int leftSpacesMoveDown = getNumberOfFreeSeatsToLeftFromPosition(rowIndex - numOfPlacesToMove, seatIndex - numOfSpaceSeats);
			int leftSpacesMoveUp = getNumberOfFreeSeatsToLeftFromPosition(rowIndex + numOfPlacesToMove, seatIndex - numOfSpaceSeats);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, rightSpacesMoveUp);
			if(numOfPlacesToMove != 0) // Not moving, (same row as the row of seats)
				updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, rightSpacesMoveDown);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, leftSpacesMoveDown);
			if(numOfPlacesToMove != 0) // Not moving, (same row as the row of seats)
				updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, leftSpacesMoveUp);
		}
		
		// Check spaces to up and down not in range of seats (by move on columns)
		for(int numOfPlacesToMove = 1; numOfPlacesToMove <= numOfSpaceSeats; numOfPlacesToMove++) {
			int upSpacesWhenMoveRight = getNumberOfFreeSeatsToUpFromPosition(rowIndex + numOfSpaceSeats, seatIndex + numOfSeatsToAllocate + numOfPlacesToMove - 1);
			int upSpacesWhenMoveLeft = getNumberOfFreeSeatsToUpFromPosition(rowIndex + numOfSpaceSeats, seatIndex - numOfPlacesToMove);
			int downSpacesMoveRight = getNumberOfFreeSeatsToDownFromPosition(rowIndex - numOfSpaceSeats, seatIndex + numOfSeatsToAllocate + numOfPlacesToMove - 1);
			int downSpacesMoveLeft = getNumberOfFreeSeatsToDownFromPosition(rowIndex - numOfSpaceSeats, seatIndex - numOfPlacesToMove);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, upSpacesWhenMoveRight);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, upSpacesWhenMoveLeft);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, downSpacesMoveRight);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, downSpacesMoveLeft);
		}
		
		// Check spaces to up and down in range of seats (by move on columns)
		for(int numOfPlacesToMove = 0; numOfPlacesToMove < numOfSeatsToAllocate; numOfPlacesToMove++) {
			int upSpacesWhenMoveRight = getNumberOfFreeSeatsToUpFromPosition(rowIndex + numOfSpaceSeats, seatIndex + numOfPlacesToMove);
			int downSpacesMoveRight = getNumberOfFreeSeatsToDownFromPosition(rowIndex - numOfSpaceSeats, seatIndex + numOfPlacesToMove);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, upSpacesWhenMoveRight);
			updateNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(reaturnValues, downSpacesMoveRight);
		}
		
		return reaturnValues;
	}
    
	// Set invitation in position to number of seats
    private void setInvitationInPositionToNumberOfSeats(int rowIndex, int seatIndex, Invitation invitation, int numOfSeatsToAllocate) {
        for(int seatNum = seatIndex; seatNum < seatIndex + numOfSeatsToAllocate; seatNum++) {
            eventSeats[rowIndex][seatNum].setSeatInvitationBelongTo(invitation);
            eventSeats[rowIndex][seatNum].setSeatIsTaken(true);
        }
    }
    
	// Set surrounding seats as unavailable
    private void setSurroundingSeatsAsUnavailable(int rowIndex, int seatIndex, int numOfSeatsToAllocate, int numOfSpaceSeats) {
        for(int rowIndexNum = rowIndex - numOfSpaceSeats; rowIndexNum <= rowIndex + numOfSpaceSeats; rowIndexNum++) { // Move on rows around
			
        	// Row in range to check and valid
        	if((rowIndexNum >= 0) && (rowIndexNum < eventNumOfRows)) {
        		
        		// Move on columns around
		        for(int colIndexNum = seatIndex - numOfSpaceSeats; colIndexNum < seatIndex + numOfSeatsToAllocate + numOfSpaceSeats; colIndexNum++) {
		        	
		        	// Column in range to check and valid
		            if((colIndexNum >= 0) && (colIndexNum < eventMaxNumOfSeatsInRow)) {
		            	
		            	// Seat is not taken
		                if(!eventSeats[rowIndexNum][colIndexNum].getSeatIsTaken())
		                	eventSeats[rowIndexNum][colIndexNum].setSeatIsAvailable(false);
		            }
		        }
			}
		}
    }
	
    // Allocate invitation
    public void allocateInvitation(Set<PlaceForInvitation> placesForInvitation, Invitation invitationToAllocate) {
    	for(PlaceForInvitation places : placesForInvitation) {
			setInvitationInPositionToNumberOfSeats(places.getRowIndex(), places.getSeatIndex(), invitationToAllocate, places.getNumOfSeats());
			setSurroundingSeatsAsUnavailable(places.getRowIndex(), places.getSeatIndex(), places.getNumOfSeats(), invitationToAllocate.getNumOfSpaceSeatsNear());
    	}
    }
	
	// Check if can allocate number of seats with space around position
    private boolean checkIfCanAllocateNumOfSeatsWithSpaceAroundPosition(int rowIndex, int seatIndex, int numOfSeatsToAllocate, int numOfSpaceSeats, int seatPrice) {    	
    	// Check place is valid
    	if((rowIndex < 0) || (rowIndex >= eventNumOfRows) || (seatIndex < 0) || (seatIndex >= eventMaxNumOfSeatsInRow))
    		return false;
    	
    	// Check that there is enough empty seats to allocate people
        for(int colNum = seatIndex; colNum < seatIndex + numOfSeatsToAllocate; colNum++) {
        	// Can't seat here
        	if(seatPrice == -1) { // Don't care about price 
        		if((colNum >= eventMaxNumOfSeatsInRow) || (!eventSeats[rowIndex][colNum].getSeatIsAvailable()) || (eventSeats[rowIndex][colNum].getSeatIsTaken()))
        			return false;
        	}
        	else { // Care about price
        		if((colNum >= eventMaxNumOfSeatsInRow) || (!eventSeats[rowIndex][colNum].getSeatIsAvailable()) || (eventSeats[rowIndex][colNum].getSeatIsTaken()) ||
        				eventSeats[rowIndex][colNum].getSeatPrice() != seatPrice)
        			return false;
        	}
        }
        
        // Check that there is enough empty seats to allocate spaces around
        for(int rowIndexNum = rowIndex - numOfSpaceSeats; rowIndexNum < rowIndex + numOfSeatsToAllocate + numOfSpaceSeats; rowIndexNum++) { // Move on rows around
			
        	// Row in range to check and valid
        	if((rowIndexNum >= 0) && (rowIndexNum < eventNumOfRows)) {
        		
        		// Move on columns around
		        for(int colIndexNum = seatIndex - numOfSpaceSeats; colIndexNum < seatIndex + numOfSeatsToAllocate + numOfSpaceSeats; colIndexNum++) {
		        	
		        	// Column in range to check and valid
		            if((colIndexNum >= 0) && (colIndexNum < eventMaxNumOfSeatsInRow)) {
		            	
		            	// Seat is taken
		                if(eventSeats[rowIndexNum][colIndexNum].getSeatIsTaken())
		                    return false;
		            }
		        }
			}
		}
        
        return true;
    }
    
    // Search place according to target function
    private PlaceForInvitation searchPlaceAccordingToTargetFunction(int numOfSeatsToAllocate, int numOfSpaceSeats, int seatPrice) {
		PlaceForInvitation foundPlace = null;
		int rowIndexOfFirstPlace = 0;
		int rowIndexOfBestPlace = 0;
		int seatIndexOfFirstPlace = 0;
		int seatIndexOfBestPlace = 0;
		int numOfLosesOfBestPlace = 0;
		int numOfLosesOfPlaceToCheck = 0;
		int[] resultsOfBestPlace = null;
		int[] resultsOfPlaceToCheck = null;
		int targetFunctionValueOfBestPlace = 0;
		int targetFunctionValueOfPlaceToCheck = 0;
		boolean foundFirstPlace = false;
		
		// Find first place that can be allocate
		for(int rowIndex = 0; rowIndex < eventNumOfRows; rowIndex++) { // Move on every row
			for(int seatIndex = 0; seatIndex < eventMaxNumOfSeatsInRow; seatIndex++) { // Move on every seat
				if(checkIfCanAllocateNumOfSeatsWithSpaceAroundPosition(rowIndex, seatIndex, numOfSeatsToAllocate, numOfSpaceSeats, seatPrice)) { // Can put invitation
					rowIndexOfFirstPlace = rowIndex;
					rowIndexOfBestPlace = rowIndex;
					seatIndexOfFirstPlace = seatIndex;
					seatIndexOfBestPlace = seatIndex;
					numOfLosesOfBestPlace = calculateLosesAroundSeat(rowIndex, seatIndex, numOfSeatsToAllocate, numOfSpaceSeats); // Get number of loses of first place
					resultsOfBestPlace = getMinNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(rowIndex, seatIndex, numOfSeatsToAllocate, 
							numOfSpaceSeats); // Get results in first place
					targetFunctionValueOfBestPlace = calculateTargetFunctionValue(numOfLosesOfBestPlace, resultsOfBestPlace); // Get target function value of first place
					foundFirstPlace = true;
					break;
				}
			}
			if(foundFirstPlace)
				break;
		}
		
		// Not found place to invitation
		if(!foundFirstPlace)
			return null;
		
		System.out.println("First place that can allocate " + numOfSeatsToAllocate + " seats: in row=" + (rowIndexOfBestPlace + 1) + " seat=" + (seatIndexOfBestPlace + 1) + 
				", target function value=" + targetFunctionValueOfBestPlace);
		
		// Optimize the place according to target function value, check places around (heuristics)
		System.out.println("Try to optimize place according to target function value, check places that are " + HEURISTICS_SEATS_AROUND + " places around");
		for(int rowIndex = rowIndexOfFirstPlace - HEURISTICS_SEATS_AROUND; rowIndex < rowIndexOfFirstPlace + HEURISTICS_SEATS_AROUND; rowIndex++) { // Move on rows around
			for(int seatIndex = seatIndexOfFirstPlace - HEURISTICS_SEATS_AROUND; seatIndex < seatIndexOfFirstPlace + HEURISTICS_SEATS_AROUND; seatIndex++) { // Move on seats around
				if(checkIfCanAllocateNumOfSeatsWithSpaceAroundPosition(rowIndex, seatIndex, numOfSeatsToAllocate, numOfSpaceSeats, seatPrice)) { // Can put invitation
					numOfLosesOfPlaceToCheck = calculateLosesAroundSeat(rowIndex, seatIndex, numOfSeatsToAllocate, numOfSpaceSeats); // Get number of loses of place to check
					resultsOfPlaceToCheck = getMinNumOfSeatsThatRemainNearAndNumOfPlacesWith0AvailableSeatsNextToThem(rowIndex, seatIndex, numOfSeatsToAllocate, 
							numOfSpaceSeats); // Get results of place to check
					targetFunctionValueOfPlaceToCheck = calculateTargetFunctionValue(numOfLosesOfPlaceToCheck, resultsOfPlaceToCheck); // Get target function value place to check
					System.out.println("Can allocate " + numOfSeatsToAllocate + " seats in row=" + (rowIndex + 1) + " seat=" + (seatIndex + 1) + 
							", target function value=" + targetFunctionValueOfPlaceToCheck + " (numOfLoses=" + numOfLosesOfPlaceToCheck + ", minPlaceToOrder=" +
							resultsOfPlaceToCheck[0] + ", numOf0Places=" + resultsOfPlaceToCheck[1] + ")");
					if(targetFunctionValueOfPlaceToCheck < targetFunctionValueOfBestPlace) { // Place to check is better than best place until now
						System.out.println("It's better than best target function value until now (" + targetFunctionValueOfBestPlace + "), so it's the best place for now");
						rowIndexOfBestPlace = rowIndex;
						seatIndexOfBestPlace = seatIndex;
						numOfLosesOfBestPlace = numOfLosesOfPlaceToCheck;
						resultsOfBestPlace = resultsOfPlaceToCheck;
						targetFunctionValueOfBestPlace = targetFunctionValueOfPlaceToCheck;
					}
					else
						System.out.println("But it's not better than best target function value until now (" + targetFunctionValueOfBestPlace + ")");
				}
			}
		}
		
		foundPlace = new PlaceForInvitation();
		foundPlace.setRowIndex(rowIndexOfBestPlace);
		foundPlace.setSeatIndex(seatIndexOfBestPlace);
		foundPlace.setNumOfSeats(numOfSeatsToAllocate);
		return foundPlace;
    }
    
	// Try to search seat together with the price
	private boolean tryToSearchSeatTogetherWithThePrice(int numOfSeatsToAllocate, int numOfSpaceSeats, int seatPrice, Set<PlaceForInvitation> placesForInvitation) {
		PlaceForInvitation placeForInvitation = searchPlaceAccordingToTargetFunction(numOfSeatsToAllocate, numOfSpaceSeats, seatPrice);
		if(placeForInvitation == null) // Not found place
			return false;
		placesForInvitation.add(placeForInvitation);
		return true;
	}
	
	// Try to search not seat together with the price
	private boolean tryToSearchNotSeatTogetherWithThePrice(int numOfSeatsToAllocate, int numOfSpaceSeats, int seatPrice, Set<PlaceForInvitation> placesForInvitation) 
			throws Exception {
        int numOfSeatsInInvitationThatAllocated = 0;
        int numOfSeatsTryToAllocate = numOfSeatsToAllocate;
        Seat[][] copyOfSeats = getCopyOfSeats();
        
		while((numOfSeatsTryToAllocate > 0) && (numOfSeatsInInvitationThatAllocated < numOfSeatsToAllocate)) {
			PlaceForInvitation bestPlace = searchPlaceAccordingToTargetFunction(numOfSeatsTryToAllocate, numOfSpaceSeats, seatPrice);
			if(bestPlace == null) // Not found place for some of seats
				numOfSeatsTryToAllocate--;
			else { // Found place for some of seats
				placesForInvitation.add(bestPlace);
				User garbageUser = new User("garbage", "garbage@garbage.com", "000-0000000");
				Invitation garbageInvitation = new Invitation(garbageUser, this, numOfSeatsToAllocate, false);
				setInvitationInPositionToNumberOfSeats(bestPlace.getRowIndex(), bestPlace.getSeatIndex(), garbageInvitation, bestPlace.getNumOfSeats());
				setSurroundingSeatsAsUnavailable(bestPlace.getRowIndex(), bestPlace.getSeatIndex(), numOfSeatsToAllocate, numOfSpaceSeats);
				numOfSeatsInInvitationThatAllocated += bestPlace.getNumOfSeats();
				numOfSeatsTryToAllocate = numOfSeatsToAllocate - numOfSeatsInInvitationThatAllocated;
			}
		}
		
		eventSeats = copyOfSeats;
		
		if(numOfSeatsInInvitationThatAllocated != numOfSeatsToAllocate)
			return false;
		
		return true;
	}
	
	// Try to allocate invitation
	public Set<PlaceForInvitation> tryToAllocateInvitation(Invitation invitation, int seatPrice) throws Exception {
		Set<PlaceForInvitation> placesForInvitation = new HashSet<PlaceForInvitation>();
		
		// Allocate all together with the price
		System.out.println("-----Try to find seats together with the price you asked");
		boolean allocateSeatTogetherWithThePriceSucceed = tryToSearchSeatTogetherWithThePrice(invitation.getNumOfSeatsInInvitation(), invitation.getNumOfSpaceSeatsNear(), 
				seatPrice, placesForInvitation);
		if(allocateSeatTogetherWithThePriceSucceed) { // Succeed to allocate seat together with the price you asked
			System.out.println("-----Found seats together with the price you asked\n");
			return placesForInvitation;
		}
		System.out.println("-----Not found seats together with the price you asked\n");
		
		// Allocate not together with the price
		System.out.println("-----Try to find seats not together with the price you asked");
		placesForInvitation.clear();
		boolean allocateSeatNotTogetherWithThePriceSucceed = tryToSearchNotSeatTogetherWithThePrice(invitation.getNumOfSeatsInInvitation(), invitation.getNumOfSpaceSeatsNear(), 
				seatPrice, placesForInvitation);
		if(allocateSeatNotTogetherWithThePriceSucceed) { // Succeed to allocate seat not together with the price you asked
			System.out.println("-----Found seats not together with the price you asked\n");
			return placesForInvitation;
		}
		System.out.println("-----Not found seats not together with the price you asked\n");
		
		// Allocate all together with all prices
		System.out.println("-----Try to find seats together with any price");
		placesForInvitation.clear();
		boolean allocateSeatTogetherWithAllPricesSucceed = tryToSearchSeatTogetherWithThePrice(invitation.getNumOfSeatsInInvitation(), invitation.getNumOfSpaceSeatsNear(), 
				-1, placesForInvitation);
		if(allocateSeatTogetherWithAllPricesSucceed) { // Succeed to allocate seat together with any price
			System.out.println("-----Found seats together with any price\n");
			return placesForInvitation;
		}
		System.out.println("-----Not found seats together with any price\n");
		
		// Allocate not together with all prices
		System.out.println("-----Try to find seats not together with any price");
		placesForInvitation.clear();
		boolean allocateSeatNotTogetherWithAllPricesSucceed = tryToSearchNotSeatTogetherWithThePrice(invitation.getNumOfSeatsInInvitation(), invitation.getNumOfSpaceSeatsNear(), 
				-1, placesForInvitation);
		if(allocateSeatNotTogetherWithAllPricesSucceed) { // Succeed to allocate seat not together with any price
			System.out.println("-----Found seats not together with any price\n");
			return placesForInvitation;
		}
		System.out.println("-----Not found seats not together with any price\n");
		
		// Not found seats
		return null;
	}
	
	// Equals
	@Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) 
        	return true;
        if ((otherObj == null) || (getClass() != otherObj.getClass())) 
        	return false;
        Event otherEvent = (Event) otherObj;
        return ((Objects.equals(eventName, otherEvent.eventName)) && (Objects.equals(eventCity, otherEvent.eventCity)) && 
        		(Objects.equals(eventNeighborhood, otherEvent.eventNeighborhood)) && (Objects.equals(eventNeighborhoodNum, otherEvent.eventNeighborhoodNum)) &&
        		(Objects.equals(eventYear, otherEvent.eventYear)) && (Objects.equals(eventMonth, otherEvent.eventMonth)) &&
        		(Objects.equals(eventDay, otherEvent.eventDay)) && (Objects.equals(eventHour, otherEvent.eventHour)) &&
        		(Objects.equals(eventMinute, otherEvent.eventMinute)));
    }

	// Hash code
    @Override
    public int hashCode() {
        return Objects.hash(eventName, eventCity, eventNeighborhood, eventNeighborhoodNum, eventYear, eventMonth, eventDay, eventHour, eventMinute);
    }
	
    // Print summery of event
    public void printSummeryOfEvent() {
    	System.out.println("Event name: " + eventName + ", Event address: " + eventNeighborhood + " " + eventNeighborhoodNum + " in " + eventCity + ", " +
    			"Event date: " + (eventDay > 9 ? eventDay : "0" + eventDay) + "." + (eventMonth > 9 ? eventMonth : "0" + eventMonth) + "." +
    			eventYear + " at " + (eventHour > 9 ? eventHour : "0" + eventHour) + ":" + (eventMinute > 9 ? eventMinute : "0" + eventMinute));
    }
    
    // Get summery of event
    public String getSummeryOfEvent() {
    	return "Event name: " + eventName + ", Event address: " + eventNeighborhood + " " + eventNeighborhoodNum + " in " + eventCity + ", " +
    			"Event date: " + (eventDay > 9 ? eventDay : "0" + eventDay) + "." + (eventMonth > 9 ? eventMonth : "0" + eventMonth) + "." +
    			eventYear + " at " + (eventHour > 9 ? eventHour : "0" + eventHour) + ":" + (eventMinute > 9 ? eventMinute : "0" + eventMinute);
    }
    
	// Print event
	public void printEvent() {
		System.out.println("Event name: " + eventName + ", Event address: " + eventNeighborhood + " " + eventNeighborhoodNum + " in " + eventCity + ", " +
    			"Event date: " + (eventDay > 9 ? eventDay : "0" + eventDay) + "." + (eventMonth > 9 ? eventMonth : "0" + eventMonth) + "." + 
				eventYear + " at " + (eventHour > 9 ? eventHour : "0" + eventHour) + ":" + (eventMinute > 9 ? eventMinute : "0" + eventMinute) + ", " +
    			"Seats(T = Taken, F = Free, U = Unavailable):");
		int lengthOfLastRow = String.valueOf(eventNumOfRows).length();
		for(int rowNum = 0; rowNum < eventNumOfRows; rowNum++) { // Move on every row
			System.out.print("Row num ");
			System.out.format("%" + lengthOfLastRow + "d", rowNum + 1); // Print row number according to the length of the biggest row
			System.out.print(": ");
			for(int seatNum = 0; seatNum < eventMaxNumOfSeatsInRow; seatNum++) { // Move on every seat in row
				Seat currentSeat = eventSeats[rowNum][seatNum];
				if(currentSeat.getSeatIsTaken())
					System.out.print("T ");
				else if(currentSeat.getSeatIsAvailable())
					System.out.print("F ");
				else
					System.out.print("U ");
			}
			System.out.print("\n");
		}
		System.out.println();
	}
	
	// To string
	@Override
	public String toString() {
		String str = "";
		str += "Event name: " + eventName + ", Event address: " + eventNeighborhood + " " + eventNeighborhoodNum + " - " + eventCity + ", " +
    			"Event date: " + (eventDay > 9 ? eventDay : "0" + eventDay) + "." + (eventMonth > 9 ? eventMonth : "0" + eventMonth) + "." + 
				eventYear + " at " + (eventHour > 9 ? eventHour : "0" + eventHour) + ":" + (eventMinute > 9 ? eventMinute : "0" + eventMinute) + ", " +
				"Seats(T = Taken, F = Free, U = Unavailable): \n";
		int lengthOfLastRow = String.valueOf(eventNumOfRows).length();
		for(int rowNum = 0; rowNum < eventNumOfRows; rowNum++) { // Move on every row
			str += "Row num ";
			str += String.format("%" + lengthOfLastRow + "d", rowNum + 1); // Add row number according to the length of the biggest row
			str += ": ";
			for(int seatNum = 0; seatNum < eventMaxNumOfSeatsInRow; seatNum++) { // Move on every seat in row
				Seat currentSeat = eventSeats[rowNum][seatNum];
				if(currentSeat.getSeatIsTaken())
					str += "T ";
				else if(currentSeat.getSeatIsAvailable())
					str += "F ";
				else
					str += "U ";
			}
			str += "\n";
		}
		str += "\n";
		return str;
	}
}