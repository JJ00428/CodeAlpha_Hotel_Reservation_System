package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Room {
	private String roomtype;
	private boolean isreserved;
	private double roomprice;
	private List<LocalDate> reservedDates;
	private LocalDate [] date;
	
	
	
	public Room(String type, double price){
		this.roomtype = type;
		this.roomprice = price;
		this.isreserved = false;
		this.reservedDates = new ArrayList<>();
		this.date = new LocalDate [2];
	}


	
	
	public String getRoomtype() {
		return roomtype;
	}






	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}






	public boolean isIsreserved() {
		return isreserved;
	}






	public void setIsreserved(boolean isreserved) {
		this.isreserved = isreserved;
	}






	public double getRoomprice() {
		return roomprice;
	}






	public void setRoomprice(double roomprice) {
		this.roomprice = roomprice;
	}






	public List<LocalDate> getReservedDates() {
		return reservedDates;
	}




	public void setReservedDates(List<LocalDate> reservedDates) {
		this.reservedDates = reservedDates;
	}




	public void reserveroom(LocalDate checkin, LocalDate checkout){
		isreserved = true;
		this.reservedDates.add(checkin);
		this.reservedDates.add(checkout);
	}
	
	
	
	public void unreserveroom(LocalDate checkin, LocalDate checkout) {
        this.isreserved = false;
        this.reservedDates.remove(checkin);
        this.reservedDates.remove(checkout);
        
        /*for (int i = 0 ; i<reservedDates.size();i++){
        	if (reservedDates.get(i)==checkin || reservedDates.get(i)==checkout){
        		reservedDates.set(i, null);
        	}
        }*/
    }
	
	
	public void addReservedDate(LocalDate date) {
	    if (!reservedDates.contains(date)) {
	        reservedDates.add(date);
	    }
	}

    public void removeReservedDate(LocalDate date) {
        reservedDates.remove(date);
    }
	
	
	
	public String showdetails(LocalDate in, LocalDate out){
		//String in = this.reservedDates.get(i).toString();
		//String out = this.reservedDates.get(i+1).toString();
		Integer numofdays = in.until(out).getDays();
		double p = this.getRoomprice()*numofdays;
		String s = "Room Type: " + this.getRoomtype() + "   |   Price: $" + p +"   |  reservedDates : From (" + in + ") To (" + out + ")";
		return s;
	}
	
	public String showdetails2(int i) {
	    if (i >= reservedDates.size() || i + 1 >= reservedDates.size()) {
	        return ""; 
	    }
	    LocalDate in = reservedDates.get(i);
	    LocalDate out = reservedDates.get(i + 1);
	    Integer numofdays = in.until(out).getDays();
	    double p = this.getRoomprice() * numofdays;
	    String s = "Room Type: " + this.getRoomtype() + "   |   Price: $" + p + "   |  reservedDates : From (" + in.toString() + ") To (" + out.toString() + ")";
	    return s;
	}

}
