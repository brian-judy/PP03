package PP03;

import java.util.Date;


public class PayPeriod {
	
	private int pID;
    private Date pStartDate, pEndDate;
    
    PayPeriod(int pID, Date startDate, Date endDate){

    	this.pID = pID;
    	this.pStartDate = startDate;
    	this.pEndDate = endDate;
    	
    }
    
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public Date getpStartDate() {
		return pStartDate;
	}
	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}
	public Date getpEndDate() {
		return pEndDate;
	}
	public void setpEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}
	
	@Override
	public String toString() {
		return String.valueOf(pID) + " " + String.valueOf(pStartDate) + " " + String.valueOf(pEndDate);
	}
    
    // 1- add the class constructor
    // 2- add the setters/getters methods
    // 3- add override method toString() 
    

	
}
