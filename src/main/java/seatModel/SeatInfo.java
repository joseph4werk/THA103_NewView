package seatModel;

public class SeatInfo {
	private String userName;
	private String seatNumber;
	private String seatType;

	public SeatInfo(String userName, String seatNumber, String seatType) {
		this.userName = userName;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}

	public String getUserName() {
		return userName;
	}

	public String getSeatType() {
		return seatType;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	
}
