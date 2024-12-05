package model;

import java.util.Objects;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Seat {
	
	private int seatRowNum; // The row number of the seat
	private int seatNum; // The number of the seat in the row
	private int seatPrice; // The price of the seat
	private boolean seatIsTaken; // Someone is sit in the seat
	private boolean seatIsAvailable; // Is seat is available to sit
	private boolean seatIsExist; // Is seat is exist
	private Invitation seatInvitationBelongTo; // The invitation that sit belong to
	private Event seatEventBelongTo; // The event that seat belong to
	
	// Constructor 
	public Seat(int seatRowNum, int seatNum, int seatPrice, Event seatEventBelongTo) throws Exception {
		if(!setSeatRowNum(seatRowNum))
			throw new Exception("Seat row num is not valid! \nIt should be bigger than 0!");
		if(!setSeatNum(seatNum))
			throw new Exception("Seat num is not valid! \nIt should be bigger than 0!");
		if(!setSeatPrice(seatPrice))
			throw new Exception("Seat price is not valid! \nIt should be 0 or bigger!");
		if(!setSeatEventBelongTo(seatEventBelongTo))
			throw new Exception("Seat event is not valid! \nIt should not be null!");
		seatIsTaken = false;
		seatIsAvailable = true;
		if(seatPrice == 0) {
			seatIsExist = false;
			seatIsAvailable = false;
		}
		else
			seatIsExist = true;
		seatInvitationBelongTo = null;
	}
	
	public Seat(int seatRowNum, int seatNum, int seatPrice, boolean seatIsTaken, boolean seatIsAvailable, boolean seatIsExist, Invitation seatInvitationBelongTo, 
			Event seatEventBelongTo) throws Exception {
		if(!setSeatRowNum(seatRowNum))
			throw new Exception("Seat row num is not valid! \nIt should be bigger than 0!");
		if(!setSeatNum(seatNum))
			throw new Exception("Seat num is not valid! \nIt should be bigger than 0!");
		if(!setSeatPrice(seatPrice))
			throw new Exception("Seat price is not valid! \nIt should be 0 or bigger!");
		if(!setSeatIsTaken(seatIsTaken))
			throw new Exception("Seat is taken is invalid!");
		if(!setSeatIsAvailable(seatIsAvailable))
			throw new Exception("Seat is available is invalid!");
		if(!setSeatIsExist(seatIsExist))
			throw new Exception("Seat is exist is invalid!");
		if(!setSeatInvitationBelongTo(seatInvitationBelongTo))
			throw new Exception("Seat invitation is invalid!");
		if(!setSeatEventBelongTo(seatEventBelongTo))
			throw new Exception("Seat event is not valid! \nIt should not be null!");
	}
	
	public Seat(Seat otherSeat) {
		setSeatRowNum(otherSeat.seatRowNum);
		setSeatNum(otherSeat.seatNum);
		setSeatPrice(otherSeat.seatPrice);
		setSeatIsTaken(otherSeat.seatIsTaken);
		setSeatIsAvailable(otherSeat.seatIsAvailable);
		setSeatIsExist(otherSeat.seatIsExist);
		setSeatInvitationBelongTo(otherSeat.seatInvitationBelongTo);
		setSeatEventBelongTo(otherSeat.seatEventBelongTo);
	}
	
	// Gets
	public int getSeatRowNum() {
		return seatRowNum;
	}

	public int getSeatNum() {
		return seatNum;
	}
	
	public int getSeatPrice() {
		return seatPrice;
	}
	
	public boolean getSeatIsTaken() {
		return seatIsTaken;
	}
	
	public boolean getSeatIsAvailable() {
		return seatIsAvailable;
	}
	
	public boolean getSeatIsExist() {
		return seatIsExist;
	}
	
	public Invitation getSeatInvitationBelongTo() {
		return seatInvitationBelongTo;
	}
	
	public Event getSeatEventBelongTo() {
		return seatEventBelongTo;
	}
	
	// Sets
	public boolean setSeatRowNum(int seatRowNum) {
		if(seatRowNum <= 0)
			return false;
		
		this.seatRowNum = seatRowNum;
		return true;
	}

	public boolean setSeatNum(int seatNum) {
		if(seatNum <= 0)
			return false;
		
		this.seatNum = seatNum;
		return true;
	}
	
	public boolean setSeatPrice(int seatPrice) {
		if(seatPrice < 0)
			return false;
		
		this.seatPrice = seatPrice;
		return true;
	}

	public boolean setSeatIsTaken(boolean seatIsTaken) {
		this.seatIsTaken = seatIsTaken;
		return true;
	}
	
	public boolean setSeatIsAvailable(boolean seatIsAvailable) {
		this.seatIsAvailable = seatIsAvailable;
		return true;
	}
	
	public boolean setSeatIsExist(boolean seatIsExist) {
		this.seatIsExist = seatIsExist;
		return true;
	}

	public boolean setSeatInvitationBelongTo(Invitation seatInvitationBelongTo) {
		this.seatInvitationBelongTo = seatInvitationBelongTo;
		return true;
	}
	
	public boolean setSeatEventBelongTo(Event seatEventBelongTo) {
		if(seatEventBelongTo == null)
			return false;
		
		this.seatEventBelongTo = seatEventBelongTo;
		return true;
	}
	
	// Equals
	@Override
	public boolean equals(Object otherObj) {
	    if (this == otherObj) 
	    	return true;
	    if ((otherObj == null) || (getClass() != otherObj.getClass())) 
	        	return false;
	    Seat otherSeat = (Seat)otherObj;
	    return (seatRowNum == otherSeat.seatRowNum) && (seatNum == otherSeat.seatNum) && (Objects.equals(seatEventBelongTo, otherSeat.seatEventBelongTo));
	}

	// Hash code
	@Override
	public int hashCode() {
		return Objects.hash(seatRowNum, seatNum, seatEventBelongTo);
	}
	
	// Create legend item
    public static HBox createLegendItem(String status, Color color) {
        Rectangle rect = new Rectangle(20, 20);
        rect.setFill(color);
        Text label = new Text(status);
        HBox legendItem = new HBox(5, rect, label); // 5 is the spacing between the rectangle and the label
        return legendItem;
    }
	
	// Get seat as rectangle
    public Rectangle getSeatAsRectangle(int rowToLabelInGrid, GridPane gridPane) {
        Rectangle rect = new Rectangle(20, 20);
        Label labelOfRect = new Label();
        labelOfRect.setId("labelOfRect");
        labelOfRect.setVisible(false);
        if(!seatIsExist) { // Seat not exist
        	rect.setFill(Color.TRANSPARENT);
        	labelOfRect.setText("");
        }
        else if(seatInvitationBelongTo != null) { // Seat is taken
        	rect.setFill(Color.RED);
        	labelOfRect.setText("Price: " + seatPrice);
        }
        else if(seatIsAvailable) { // Seat available
        	rect.setFill(Color.GREEN);
        	labelOfRect.setText("Price: " + seatPrice);
        }
        else { // Seat is not available
        	rect.setFill(Color.GRAY);
        	labelOfRect.setText("Price: " + seatPrice);
        }
        
        gridPane.add(labelOfRect, 0, rowToLabelInGrid);
        
        // Display text
        rect.setOnMouseEntered(e -> {
        	labelOfRect.setVisible(true);
        });
        
        // Not display text
        rect.setOnMouseExited(e -> {
        	labelOfRect.setVisible(false);
        });
        
        return rect;
    }
	
	// Print seat
	public void printSeat() {
		System.out.print("Seat row: " + seatRowNum + ", Seat num: " + seatNum + ", Seat event: " + seatEventBelongTo.getEventName());
		if(!seatIsExist)
			System.out.print(", Is not exist \n");
		else if(seatInvitationBelongTo != null)
			System.out.print(", Taken by: " + seatInvitationBelongTo.getUserInvitationBelongTo().getUserName() + "\n");
		else if(seatIsAvailable)
			System.out.print(", Is free \n");
		else
			System.out.print(", Is not available \n");
	}
	
	// To string
	@Override
	public String toString() {
		String str = "Seat row: " + seatRowNum + ", Seat num: " + seatNum + ", Seat event: " + seatEventBelongTo.getEventName();
		if(!seatIsExist)
			str += ", Is not exist";
		else if(seatInvitationBelongTo != null)
			str += ", Is taken";
		else if(seatIsAvailable)
			str += ", Is free";
		else
			str += ", Is not available";
		return str;
	}
}