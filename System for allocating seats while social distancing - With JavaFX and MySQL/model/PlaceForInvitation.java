package model;

public class PlaceForInvitation {
	
	private int rowIndex;
	private int seatIndex;
	private int numOfSeats;
	
	// Constructor
	public PlaceForInvitation() {
		rowIndex = 0;
		seatIndex = 0;
		numOfSeats = 0;
	}
	
	// Gets
	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getSeatIndex() {
		return seatIndex;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	// Sets
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	public void setSeatIndex(int seatIndex) {
		this.seatIndex = seatIndex;
	}
	
	public void setNumOfSeats(int numOfSeats) {
		this.numOfSeats = numOfSeats;
	}
}