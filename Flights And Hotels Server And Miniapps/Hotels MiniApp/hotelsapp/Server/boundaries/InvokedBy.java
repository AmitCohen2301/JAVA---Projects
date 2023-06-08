package com.example.hotelsapp.Server.boundaries;


public class InvokedBy {
	protected UserId userId;

// Constructors
	public InvokedBy() {
		this.userId=new UserId();
	}
	
	public InvokedBy(UserId userId) {
		this.userId = userId;
	}

// Gets
	public UserId getUserId() {
		return userId;
	}

// Sets
	public void setUserId(UserId userId) {
		this.userId = userId;
	}
}