package PP03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PayRoll {
	
	private String fileName;
	private PayRecord[] payRecords;
	
	private Employee[] employees;
	private  double totalNetPay;
	private  double avgNetPay;
	private int noOfCustomers;
	private int countPay = 0;
	private Status empStatus = null;
	private int empCount = 0;
	
	public PayRoll(String fileName, int n){
		
		this.fileName = fileName;
        this.noOfCustomers = n;
		
	}
	
	
   public void readFromFile(String fileName) throws FileNotFoundException, ParseException{
	   
	   double monthIncome = 0.0;
	   int noOfMonths = 0;
	   int rID = 0;
	   double payHours = 0.0;
	   double payRate = 0.0;
	   int pID = 0;
	   Date pStart = null;
	   Date pEnd = null;
		
		// read the initial data from PayRoll file to create the full 
	   // pay records with gross pay, taxes, and net pay, and then store it in PayRecord.txt file
	   File file = new File(fileName);
	   
	   Scanner sc = new Scanner(file);
	   
	   //sc.useDelimiter(",|\r\n");
	   
	 //create date formatter to convert the String to a date
	   SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
	   
	   while(sc.hasNext()) {
		   
		   //split the input text into an array
		   String input = sc.nextLine();
		   String[] splitInput = input.split(",");

		   //check if the first array index is employee or payrecord
		   if(splitInput[0].equals("employee")) {
			   int eID = Integer.parseInt(splitInput[1].trim());
			   String fName = splitInput[2].trim();
			   String lName = splitInput[3].trim();
			   String status = splitInput[4].trim();
			   String street = splitInput[5].trim();
			   int houseNumber = Integer.parseInt(splitInput[6].trim());
			   String city = splitInput[7].trim();
			   String state = splitInput[8].trim();
			   int zipCode = Integer.parseInt(splitInput[9].trim());

			   //set employee status based on status input
			   if(status.equals("FULLTIME"))
				   empStatus = Status.FullTime;
			   else if(status.equals("HOURLY"))
				   empStatus = Status.Hourly;
			   
			   //instantiate address object to use for the employee constructor
			   Address address = new Address(street, houseNumber, city, state, zipCode);
			   
			   employees[empCount] = new Employee(eID, empStatus, fName, lName, address);
			   
			   empCount++;
 
		   }
		   else if(splitInput[0].equals("payRecord")){
			   
			   String payType = splitInput[3].substring(splitInput[3].length() - 3, splitInput[3].length());
			   
			   if(payType.equals("<m>")) {
				   rID = Integer.parseInt(splitInput[1].trim());
				   int eID = Integer.parseInt(splitInput[2].trim());
				   monthIncome = Double.parseDouble(splitInput[3].trim().substring(0,splitInput[3].length() - 4));
				   noOfMonths = Integer.parseInt(splitInput[4].trim().substring(0,splitInput[4].length() - 4));
				   pID = Integer.parseInt(splitInput[5].trim());			   
				   pStart = formatter.parse(splitInput[6].trim());
				   pEnd = formatter.parse(splitInput[7].trim());
				   payHours = 0.0;
				   payRate = 0.0;
			   }else if(payType.equals("<h>")) {
				   rID = Integer.parseInt(splitInput[1].trim());
				   int eID = Integer.parseInt(splitInput[2].trim());
				   payHours = Double.parseDouble(splitInput[3].trim().substring(0,splitInput[3].length() - 4));
				   payRate = Double.parseDouble(splitInput[4].trim().substring(0,splitInput[4].length() - 4));
				   pID = Integer.parseInt(splitInput[5].trim());
				   pStart = formatter.parse(splitInput[6].trim());
				   pEnd = formatter.parse(splitInput[7].trim());
				   monthIncome = 0.0;
				   noOfMonths = 0;
			   }
			   
			   createPayRecord(rID, employees[empCount], monthIncome, noOfMonths, payHours, payRate, pID, pStart, pEnd);
			   
			   
		   }//end if/else
		   
	   }//end while
	   
	   sc.close();
		
	}//end readFromFile
   
   
   public void writeToFile() throws FileNotFoundException{
		
		// write employees' pay records to the PayRecord.txt file, it should add employee pay record to the current file data
	   File file = new File("PayRecord.txt");
	   
	   PrintWriter write = new PrintWriter(file);
	   
	   String out = "Pay Record\n" + "rID\t" + "Employee\t" + "Pay Period\t" + "Pay Hours\t" + "Pay Rate\t" + "Monthly Income\t" + "Number of Months\t" + 
	   "Tax\t" + "eID\t" + "Status\t" + "pID\t" + "Start Date\t" + "End Date";
	   
	   for(int i = 0; i < payRecords.length; i++) {
		   out += "\n" + payRecords[i].toString();
		   
		   write.print(out);
		   
		   write.close();
		   
	   }//end writeToFile
		
	} 
   
	public Employee createEmployee(int eID, Status empStatus, String fName, String lName, Address address){
		// creates a new Employee object and add it to the employees array, you need to pass parameters to this method
		Employee newEmployee = new Employee(eID, empStatus, fName, lName, address);
		return newEmployee;
		
	}
	
 
	public void createPayRecord(int rId, Employee employee, double monthlyIncome, int numberofMonths,double payHours, 
			double payRate, int pID, Date startDate, Date endDate){
		
		// creates a new PayRecord for an Employee object and add it to  the payRecords array, you need to pass parameters to this method
		//PayRecord record = new PayRecord(int id, Employee e, PayPeriod period, double hours, double rate);
		PayPeriod period = new PayPeriod (pID, startDate, endDate);
		
		// create a new PayRecord for an Employee object and add it to the payRecords array, you need to pass parameters to this method
		if (countPay < payRecords.length) {
			if( employee.getEmpStatus().equals(Status.Hourly)) {
				PayRecord payRecord = new PayRecord(rId, employee, period, payHours,payRate);
				payRecords[countPay] = payRecord;
				countPay++;
			}else if (employee.getEmpStatus().equals(Status.FullTime)) {
				PayRecord payRecord = new PayRecord(rId, employee, period, monthlyIncome, numberofMonths);
				payRecords[countPay] = payRecord;
				countPay++;
			}
		}
		
	}
	
	
    public  void displayPayRecord(JTextArea textArea){
		
		// it shows all payroll records for all currently added employee and the total net pay and average net pay in the GUI text area
    	// at should append data to text area, it must not overwrite data in the GUI text area
    	String output = "";
		
    	for( int i = 0; i < payRecords.length; i++) {
    		if(payRecords[i] != null) {
    			output += payRecords[i].toString() + "\n";
    		}
    	}
    	
    	textArea.setText(output);
    	
		
	}

    
   public double avgNetPay(){
		
		  	// returns the average of the total net pay of all added employees
	   double sum = 0.0;
	   double avg = 0.0;
	   
	   for(int j = 0; j < payRecords.length; j ++) {
		   sum += payRecords[j].netPay();
		   avg = sum / payRecords.length;
	   }
	   
	   return avg;
		
	}
    	

}
