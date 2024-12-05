package model;

import java.util.Objects;

public class Invitation {
	
	private int invitationNum;
	private User userInvitationBelongTo;
	private Event eventInvitationBelongTo;
	private int numOfSeatsInInvitation;
	private boolean seatTogether;
	private int numOfSpaceSeatsNear;
	
	// Constructors
	public Invitation(User userInvitationBelongTo, Event eventInvitationBelongTo, int numOfSeatsInInvitation, boolean seatTogether) throws Exception {
		if(!setUserInvitationBelongTo(userInvitationBelongTo))
			throw new Exception("The user that the invitation belong to is not valid! \nIt should not be null!");
		if(!setEventInvitationBelongTo(eventInvitationBelongTo))
			throw new Exception("The event that the invitation belong to is not valid! \nIt should not be null!");
		if(!setNumOfSeatsInInvitation(numOfSeatsInInvitation))
			throw new Exception("The num of seats in invitation is not valid! \nIt should be bigger than 0!");
		this.invitationNum = eventInvitationBelongTo.getEventNextInvitationNum();
		this.seatTogether = seatTogether;
		this.numOfSpaceSeatsNear = 0;
	}
	
	public Invitation(User userInvitationBelongTo, Event eventInvitationBelongTo, int numOfSeatsInInvitation, boolean seatTogether, int numOfSpaceSeatsNear) 
			throws Exception {
		if(!setUserInvitationBelongTo(userInvitationBelongTo))
			throw new Exception("The user that the invitation belong to is not valid! \nIt should not be null!");
		if(!setEventInvitationBelongTo(eventInvitationBelongTo))
			throw new Exception("The event that the invitation belong to is not valid! \nIt should not be null!");
		if(!setNumOfSeatsInInvitation(numOfSeatsInInvitation))
			throw new Exception("The num of seats in invitation is not valid! \nIt should be bigger than 0!");
		if(!setNumOfSpaceSeatsNear(numOfSpaceSeatsNear))
			throw new Exception("The num of space seats near in invitation is not valid! \nIt should be 0 or above!");
		this.invitationNum = eventInvitationBelongTo.getEventNextInvitationNum();
		this.seatTogether = seatTogether;
		this.numOfSpaceSeatsNear = numOfSpaceSeatsNear;
	}
	
	public Invitation(int invitationNum, User userInvitationBelongTo, Event eventInvitationBelongTo, int numOfSeatsInInvitation, boolean seatTogether, 
			int numOfSpaceSeatsNear) throws Exception {
		if(!setInvitationNum(invitationNum))
			throw new Exception("The num of invitation in invitation is not valid! \nIt should be bigger than 0!");
		if(!setUserInvitationBelongTo(userInvitationBelongTo))
			throw new Exception("The user that the invitation belong to is not valid! \nIt should not be null!");
		if(!setEventInvitationBelongTo(eventInvitationBelongTo))
			throw new Exception("The event that the invitation belong to is not valid! \nIt should not be null!");
		if(!setNumOfSeatsInInvitation(numOfSeatsInInvitation))
			throw new Exception("The num of seats in invitation is not valid! \nIt should be bigger than 0!");
		if(!setNumOfSpaceSeatsNear(numOfSpaceSeatsNear))
			throw new Exception("The num of space seats near in invitation is not valid! \nIt should be 0 or above!");
		this.seatTogether = seatTogether;
		this.numOfSpaceSeatsNear = numOfSpaceSeatsNear;
	}

	// Gets
	public int getInvitationNum() {
		return invitationNum;
	}
	
	public User getUserInvitationBelongTo() {
		return userInvitationBelongTo;
	}

	public Event getEventInvitationBelongTo() {
		return eventInvitationBelongTo;
	}
	
	public int getNumOfSeatsInInvitation() {
		return numOfSeatsInInvitation;
	}
	
	public boolean getSeatTogether() {
		return seatTogether;
	}
	
	public int getNumOfSpaceSeatsNear() {
		return numOfSpaceSeatsNear;
	}
	
	// Sets
	public boolean setInvitationNum(int invitationNum) {
		if(invitationNum < 1)
			return false;
		
		this.invitationNum = invitationNum;
		return true;
	}
	
	public boolean setUserInvitationBelongTo(User userInvitationBelongTo) {
		if(userInvitationBelongTo == null)
			return false;
		
		this.userInvitationBelongTo = userInvitationBelongTo;
		return true;
	}

	public boolean setEventInvitationBelongTo(Event eventInvitationBelongTo) {
		if(eventInvitationBelongTo == null)
			return false;
		
		this.eventInvitationBelongTo = eventInvitationBelongTo;
		return true;
	}

	public boolean setNumOfSeatsInInvitation(int numOfSeatsInInvitation) {
		if(numOfSeatsInInvitation <= 0)
			return false;
		
		this.numOfSeatsInInvitation = numOfSeatsInInvitation;
		return true;
	}

	public boolean setSeatTogether(boolean seatTogether) {
		this.seatTogether = seatTogether;
		return true;
	}
	
	public boolean setNumOfSpaceSeatsNear(int numOfSpaceSeatsNear) {
		if(numOfSpaceSeatsNear < 0)
			return false;
		
		this.numOfSpaceSeatsNear = numOfSpaceSeatsNear;
		return true;
	}
		
	// Equals
	@Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) 
        	return true;
        if ((otherObj == null) || (getClass() != otherObj.getClass())) 
        	return false;
        Invitation otherInvitation = (Invitation) otherObj;
        return (invitationNum == otherInvitation.invitationNum) && (Objects.equals(eventInvitationBelongTo, otherInvitation.eventInvitationBelongTo));
    }

	// Hash code
    @Override
    public int hashCode() {
        return Objects.hash(invitationNum, eventInvitationBelongTo);
    }
		
	// Print invitation
	public void printInvitation() {
		System.out.println("Invitation num:" + invitationNum + ", Belong to: " + userInvitationBelongTo.getUserName() + 
				", To event: " + eventInvitationBelongTo.getEventName() + ", Number of tickets in invitation: " + numOfSeatsInInvitation + 
				", Seat together: " + seatTogether + ", Space that need to keep: " + numOfSpaceSeatsNear);
	}
	
	// To string
	@Override
	public String toString() {
		return "Invitation num: " + invitationNum + ", Belong to: " + userInvitationBelongTo.getUserName() + ", To event: " + eventInvitationBelongTo.getEventName() +
				", Number of tickets in invitation: " + numOfSeatsInInvitation + ", Seat together: " + seatTogether + 
				", Space that need to keep: " + numOfSpaceSeatsNear;
	}
}