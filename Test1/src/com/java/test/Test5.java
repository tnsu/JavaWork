package com.java.test;

import javax.xml.crypto.Data;

public class Test5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
class  TicketReservation{
	private int uid;
	int movieId;
	int seatId;
	Data regDate;
	public TicketReservation(int uid, int movieId, int seatId, Data regDate) {
		super();
		this.uid = uid;
		this.movieId = movieId;
		this.seatId = seatId;
		this.regDate = regDate;
	}
	public TicketReservation() {
		super();
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public Data getRegDate() {
		return regDate;
	}
	public void setRegDate(Data regDate) {
		this.regDate = regDate;
	}
	
}
}
