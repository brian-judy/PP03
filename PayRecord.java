package PP03;


public class PayRecord {
	
	private int rID;
    private Employee employee;
    private PayPeriod payPeriod;
    private TaxIncome payTax;
    
    private double payHours;
    private double payRate;
    
    private double monthlyIncome;
    private int numMonths;
    private double grossPay = 0.0;
    
       
    
    public static final int REG_HOURS = 40;
    public static final double OT_RATE = 1.25;
    
    // pay record constructor for hourly employee
    public PayRecord(int id, Employee e, PayPeriod period, double hours, double rate){
    	
    	this.rID = id;
    	this.employee = e;
    	this.payPeriod = period;
    	this.payHours = hours;
    	this.payRate = rate;
    	this.monthlyIncome = 0;
    	this.numMonths = 0;
    	this.payTax = new TaxIncome();
  
    }
    
    // pay record constructor for full time employee
    public PayRecord(int id, Employee e, PayPeriod period, double mIncome, int mNum){
 	
 	this.rID = id;
 	this.employee = e;
 	this.payPeriod = period;
 	this.payHours = 0;
 	this.payRate = 0;
 	this.monthlyIncome = mIncome;
 	this.numMonths = mNum;
 	this.payTax = new TaxIncome();

 }

    

  // 1- add setters and getters methods
  // 2- add override method toString()
  // 3- complete the code in the following methods: grossPay() and netPay()
    
    public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PayPeriod getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriod payPeriod) {
		this.payPeriod = payPeriod;
	}

	public TaxIncome getPayTax() {
		return payTax;
	}

	public void setPayTax(TaxIncome payTax) {
		this.payTax = payTax;
	}

	public double getPayHours() {
		return payHours;
	}

	public void setPayHours(double payHours) {
		this.payHours = payHours;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getMontlyIncome() {
		return monthlyIncome;
	}

	public void setMontlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public int getNumMonths() {
		return numMonths;
	}

	public void setNumMonths(int numMonths) {
		this.numMonths = numMonths;
	}

	public static int getRegHours() {
		return REG_HOURS;
	}

	public static double getOtRate() {
		return OT_RATE;
	}
	
	

	// complete the code to compute the gross pay for the employee based on the employee status
	public double grossPay(){
		Status status = employee.getEmpStatus();
		if(status == Status.FullTime)
			grossPay = numMonths * monthlyIncome;
		else if(status == Status.Hourly) {
			if(payHours <= 40) 
				grossPay = payHours * payRate;
			else if(payHours > 40)
				grossPay = (40 * payRate) + ((payHours - 40) * OT_RATE);
		}
		return grossPay;
	}
    
  // complete the code in this method to compute the net pay of the employee after taxes (state and federal)
     public double netPay(){
    	 double incomeTax = payTax.compIncomeTax(grossPay);
    	 double grossPay = grossPay();
    	 double netPay = grossPay - incomeTax; 
		  return netPay;
  }
  
 	@Override
 	public String toString() {
 		return String.valueOf(rID) + " " + String.valueOf(employee) + " " + String.valueOf(payPeriod) + " " + String.valueOf(payHours) + " "
 				+ String.valueOf(payRate) + " " + String.valueOf(monthlyIncome) + " " + String.valueOf(numMonths) + " " + String.valueOf(payTax)
 				+ " " + employee.toString() + " " + payPeriod.toString();
 	}
}
