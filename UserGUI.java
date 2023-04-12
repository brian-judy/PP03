package PP03;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserGUI extends JFrame implements ActionListener{

static PayRoll payRoll;
static Employee employee;
static PayRecord payRecord;
static PayPeriod period;
static Address addressInput;
static boolean gotCorrect;
static int a=0;
static int numberOfPeople = 0,countEmployee=0,countPayRecord=0;
static Status status;

// declare all GUI components below
private JButton addEmployBtn,addPayRecordBtn,displayBtn,closeBtn;
private JComboBox<String> stateList;

// UI RadioButton
private JRadioButton fullTimeBtn,hourlyBtn;
ButtonGroup group;

// UI label
private JLabel
	employeeLabel,employeeIdLabel,lastNameLabel,firstNameLabel,statusLabel,addressLabel
	,streetLabel,houseNumberLabel,cityLabel,
	stateLabel,zipCodeLabel,payPeriodLabel,payPeriodIdLabel,startDateLabel,endDateLabel
	,payRecordLabel,payRecordIdLabel,monthlyIncomeLabel,numberOfMonthLabel,payHoursLabel,
	payRateLabel,displayLabel;
private JTextField
	employeeIdTxt,lastNameTxt,firstNameTxt,streetTxt,houseNumberTxt,cityTxt,
	zipCodeTxt,payPeriodIdTxt,startDateTxt,endDateTxt,payRecordIdTxt,monthlyIncomeTxt,
	numberOfMonthTxt,payHoursTxt,payRateTxt;

// UI TextArea&ScrollPane
private static JTextArea textArea;
private JScrollPane jp;

UserGUI(int nPersons){//constructor

	//Initialize the components
	initComponenet();

	//Organize the GUI components
	doTheLayout();

	//Add the action listeners GUI buttons(add, sort, and display)
	addEmployBtn.addActionListener(this);
	addEmployBtn.setActionCommand("addEmploy");
	addPayRecordBtn.addActionListener(this);
	addPayRecordBtn.setActionCommand("addPayRecord");
	displayBtn.addActionListener(this);
	displayBtn.setActionCommand("display");
	closeBtn.addActionListener(this);
	closeBtn.setActionCommand("close");
	fullTimeBtn.addActionListener(this);;
	hourlyBtn.addActionListener(this);
}//End of constructor

private void initComponenet(){
	// initialize all user interface components
	
	//labels
	employeeLabel=new JLabel("Employee: ");
	employeeLabel.setForeground(Color.BLUE);
	employeeIdLabel=new JLabel("ID: ");
	lastNameLabel=new JLabel("Last Name: ");
	firstNameLabel=new JLabel("First Name: ");
	statusLabel=new JLabel("Employee Status: ");
	addressLabel=new JLabel("Employee Address: ");
	streetLabel=new JLabel("Street: ");
	houseNumberLabel=new JLabel("H/Apt Number: ");
	cityLabel=new JLabel("City: ");
	stateLabel=new JLabel("State: ");
	zipCodeLabel=new JLabel("Zip Code: ");
	payPeriodLabel=new JLabel("Pay Period: ");
	payPeriodLabel.setForeground(Color.blue);
	payPeriodIdLabel=new JLabel("ID: ");
	startDateLabel=new JLabel("Start Date: ");
	endDateLabel=new JLabel("End Date: ");
	payRecordLabel=new JLabel("Pay Record: ");
	payRecordLabel.setForeground(Color.BLUE);
	payRecordIdLabel=new JLabel("ID: ");
	monthlyIncomeLabel=new JLabel("Monthly Income: ");
	numberOfMonthLabel=new JLabel("Number of Months: ");
	payHoursLabel=new JLabel("Pay Hours: ");
	payRateLabel=new JLabel("Pay Rate: ");
	displayLabel=new JLabel("Current Employee Record and State(Total&Average Pays) : ");
	displayLabel.setForeground(Color.BLUE);

	//text fields
	employeeIdTxt=new JTextField(5);
	lastNameTxt=new JTextField(10);
	firstNameTxt=new JTextField(10);
	streetTxt=new JTextField(25);
	houseNumberTxt=new JTextField(5);
	cityTxt=new JTextField(10);
	zipCodeTxt=new JTextField(10);
	payPeriodIdTxt=new JTextField(5);
	startDateTxt=new JTextField(10);
	endDateTxt=new JTextField(10);
	payRecordIdTxt=new JTextField(5);
	monthlyIncomeTxt=new JTextField(10);
	numberOfMonthTxt=new JTextField(5);
	payHoursTxt=new JTextField(5);
	payRateTxt=new JTextField(5);
	
	// Radio Buttons
	fullTimeBtn=new JRadioButton("Full Time");
	hourlyBtn=new JRadioButton("Hourly");

	// Radio Buttons to ButtonGroup
	group=new ButtonGroup();
	group.add(fullTimeBtn);
	group.add(hourlyBtn);

	//define text area and add it to scroll pane
	textArea=new JTextArea("Program Output\n",10,48);
	textArea.setEditable(false);
	jp=new JScrollPane(textArea);
	
	// buttons
	addEmployBtn=new JButton(" Add Employee ");
	addPayRecordBtn=new JButton(" Add Pay Record ");
	displayBtn=new JButton(" Display Total & Average Net Pay");
	closeBtn=new JButton(" Close ");

	//Combo Box
	JComboBox<String> stateList = new JComboBox<>();

	stateList.addItem("Alabama");stateList.addItem("Alaska");stateList.addItem("Arizona");stateList.addItem("Arkansas");stateList.addItem("California");
	stateList.addItem("Colorado");stateList.addItem("Connecticut");stateList.addItem("Delaware");stateList.addItem("Florida");stateList.addItem("Georgia");
	stateList.addItem("Hawaii");stateList.addItem("Idaho");stateList.addItem("Illinois");stateList.addItem("Indiana");stateList.addItem("Iowa");
	stateList.addItem("Kansas");stateList.addItem("Kentucky");stateList.addItem("Louisiana");stateList.addItem("Maine");stateList.addItem("Maryland");
	stateList.addItem("Massachusetts");stateList.addItem("Michigan");stateList.addItem("Minnesota");stateList.addItem("Mississippi");stateList.addItem("Missouri");
	stateList.addItem("Montana");stateList.addItem("Nebraska");stateList.addItem("Nevada");stateList.addItem("New hampshire");stateList.addItem("New jersey");
	stateList.addItem("New mexico");stateList.addItem("NewYork");stateList.addItem("North Carolina");stateList.addItem("NorthDakota");stateList.addItem("Ohio");
	stateList.addItem("Oklahoma");stateList.addItem("Oregon");stateList.addItem("Pennsylvania");stateList.addItem("Rhode island");stateList.addItem("South carolina");
	stateList.addItem("South Dakota");stateList.addItem("Tennessee");stateList.addItem("Texas");stateList.addItem("Utah");stateList.addItem("Vermont");
	stateList.addItem("Virginia");stateList.addItem("Washington");stateList.addItem("West Virginia");stateList.addItem("Wisconsin");stateList.addItem("Wyoming");
}

private void doTheLayout(){
	// Organize the components into GUI window
	JPanel employeeLayer=new JPanel();
	JPanel employeeInforLayer=new JPanel();
	JPanel employeeStatusLayer=new JPanel();
	JPanel statusOptionLayer=new JPanel();
	JPanel employAddressLayer=new JPanel();
	JPanel addressInforLayer=new JPanel();
	JPanel addressInforSecondLayer=new JPanel();
	JPanel addEmployeeLayer=new JPanel();
	JPanel payPeriodLayer=new JPanel();
	JPanel payPeriodInforLayer=new JPanel();
	JPanel payRecordLayer=new JPanel();
	JPanel payRecordInforLayer=new JPanel();
	JPanel payRecordInforSecondLayer=new JPanel();
	JPanel displayLayer=new JPanel();
	JPanel closebtnLayer=new JPanel();
	JPanel addPayRecordBtnLayer=new JPanel();

	//to GridLayout
	this.setLayout(new GridLayout(5,1));
	this.add(employeeLayer);
	this.add(employeeInforLayer);
	this.add(employeeStatusLayer);
	this.add(statusOptionLayer);
	this.add(employAddressLayer);
	this.add(addressInforLayer);
	this.add(addressInforSecondLayer);
	this.add(addEmployeeLayer);
	this.add(payPeriodLayer);
	this.add(payPeriodInforLayer);
	this.add(payRecordLayer);
	this.add(payRecordInforLayer);
	this.add(payRecordInforSecondLayer);
	this.add(addPayRecordBtnLayer);
	this.add(displayLayer);
	this.add(closebtnLayer);
	
	// set Layout for the Employee Layer Panel
	employeeLayer.setLayout(new GridLayout(3,1));
	employeeLayer.add(employeeLabel);
	employeeLayer.add(employeeInforLayer);
	employeeLayer.add(employeeStatusLayer);
	
	// add components to the the employee Layer Panel
	employeeInforLayer.add(employeeIdLabel);employeeInforLayer.add(employeeIdTxt);
	employeeInforLayer.add(firstNameLabel);employeeInforLayer.add(firstNameTxt);
	employeeInforLayer.add(lastNameLabel);employeeInforLayer.add(lastNameTxt);
	employeeStatusLayer.setLayout(new GridLayout(2,1));
	employeeStatusLayer.add(statusLabel);
	employeeStatusLayer.add(statusOptionLayer);
	statusOptionLayer.setLayout(new
	FlowLayout(FlowLayout.CENTER));
	statusOptionLayer.add(fullTimeBtn);statusOptionLayer.add(hourlyBtn);
	
	// set Layout for the Employ Address Layer Panel
	employAddressLayer.setLayout(new GridLayout(4,1));
	employAddressLayer.add(addressLabel);
	employAddressLayer.add(addressInforLayer);
	employAddressLayer.add(addressInforSecondLayer);
	employAddressLayer.add(addEmployeeLayer);

	// add components to the Employ Address Layer Panel
	addressInforLayer.add(streetLabel);addressInforLayer.add(streetTxt);
	addressInforLayer.add(houseNumberLabel);addressInforLayer.add(houseNumberTxt);
	addressInforLayer.add(cityLabel);addressInforLayer.add(cityTxt);
	addressInforSecondLayer.add(stateLabel);addressInforSecondLayer.add(stateList);
	addressInforSecondLayer.add(zipCodeLabel);addressInforSecondLayer.add(zipCodeTxt);
	addEmployeeLayer.add(addEmployBtn);

	// set Layout for the Pay Period Layer Panel
	payPeriodLayer.setLayout(new GridLayout(2,1));
	payPeriodLayer.add(payPeriodLabel);
	payPeriodLayer.add(payPeriodInforLayer);
	
	// add components to the Pay Period Layer Panel
	payPeriodInforLayer.add(payPeriodIdLabel);payPeriodInforLayer.add(payPeriodIdTxt);
	payPeriodInforLayer.add(startDateLabel);payPeriodInforLayer.add(startDateTxt);
	payPeriodInforLayer.add(endDateLabel);payPeriodInforLayer.add(endDateTxt);
	
	// set Layout for the Pay Record Layer Panel
	payRecordLayer.setLayout(new GridLayout(4,1));
	payRecordLayer.add(payRecordLabel);
	payRecordLayer.add(payRecordInforLayer);
	payRecordLayer.add(payRecordInforSecondLayer);
	payRecordLayer.add(addPayRecordBtnLayer);

	// add components to the Pay Record Layer Panel
	payRecordInforLayer.add(payRecordIdLabel);payRecordInforLayer.add(payRecordIdTxt);
	payRecordInforLayer.add(monthlyIncomeLabel);payRecordInforLayer.add(monthlyIncomeTxt);
	payRecordInforLayer.add(numberOfMonthLabel);payRecordInforLayer.add(numberOfMonthTxt);
	payRecordInforSecondLayer.add(payHoursLabel);payRecordInforSecondLayer.add(payHoursTxt);
	payRecordInforSecondLayer.add(payRateLabel);payRecordInforSecondLayer.add(payRateTxt);
	addPayRecordBtnLayer.add(addPayRecordBtn);addPayRecordBtnLayer.add(displayBtn);

	//set Layout for and add components to the display Layer Panel
	displayLayer.setLayout(new BorderLayout());
	displayLayer.add(displayLabel,BorderLayout.NORTH);
	displayLayer.add(jp,BorderLayout.CENTER);
	displayLayer.add(closebtnLayer,BorderLayout.SOUTH);
	closebtnLayer.add(closeBtn);
}

public static void main(String[] args) throws IOException,
	ParseException {
	//Input number of employees
	while(!gotCorrect){
		try {
			numberOfPeople = Integer.parseInt(JOptionPane.showInputDialog(null," Enter number of employees"));
			if (numberOfPeople >= 3) {
				break;
			}else {
				JOptionPane.showMessageDialog(null," The number of employees must be greater than 2 ");
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null," Invalid input ");
			continue;
		}
	}
	
	//Create a new GUI
	UserGUI frame = new UserGUI(numberOfPeople);

	//Set GUI frame settings
	frame.setTitle("Pay Roll");
	frame.setPreferredSize(new Dimension(670,850));
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);

	// read from file
	payRoll = new PayRoll("PayRoll.txt",numberOfPeople);
	payRoll.readFromFile("PayRoll.txt");
	payRoll.displayPayRecord(textArea);
	}
	
@Override
	public void actionPerformed(ActionEvent e) {

	// Call the required button action method according to the action event
	if(e.getSource()==addEmployBtn)
		addEmployButtonClicked();
	else if(e.getSource()==addPayRecordBtn)
		addPayRecordButtonClicked();
	else if (e.getSource()==closeBtn)
		closeButtonClicked();
	else if (e.getSource()==displayBtn)
		try {
			displayButtonClicked();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	if (fullTimeBtn.isSelected()) {
		payHoursTxt.setEditable(false);
		payRateTxt.setEditable(false);
		numberOfMonthTxt.setEditable(true);
		monthlyIncomeTxt.setEditable(true);
	}
	else if(hourlyBtn.isSelected()) {
		numberOfMonthTxt.setEditable(false);
		monthlyIncomeTxt.setEditable(false);
		payHoursTxt.setEditable(true);
		payRateTxt.setEditable(true);
	}
}

private void addEmployButtonClicked() {
	String lastName="", firstName="", state="", street="", city="", houseNumber="";
	int zipCode=0,employeeId = 0;

	//input Employee Id
	if(employeeIdTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(employeeIdTxt,"Input Employee ID");
		employeeIdTxt.setText("");
		return;
	}else
		try {
			employeeId = Integer.parseInt(employeeIdTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid ID number");
			employeeIdTxt.setText("");
			return;
		}
	
	//input first name
	if(firstNameTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(firstNameTxt,"Input First Name");
		firstNameTxt.setText("");
		return;
	}else
		firstName=firstNameTxt.getText();

	//input last name
	if(lastNameTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(lastNameTxt,"Input Last Name");
		lastNameTxt.setText("");
		return;
	}else
		lastName = lastNameTxt.getText();

	//input address
	if(streetTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(streetTxt,"Input Address");
		streetTxt.setText("");
		return;
	}else
		street = streetTxt.getText();

	//input houseNumber
	if(houseNumberTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(houseNumberTxt,"Input House Number");
		houseNumberTxt.setText("");
		return;
	}else
		try {
			houseNumber = houseNumberTxt.getText();
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid house number");
			houseNumberTxt.setText("");
			return;
		}

	//input city
	if(cityTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(cityTxt,"Please input City");
		cityTxt.setText("");
		return;
	}else
		city = cityTxt.getText();

	//input Zip Code
	if(zipCodeTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(zipCodeTxt,"Input Zip Code");
		zipCodeTxt.setText("");
		return;
	}else
		try {
			zipCode = Integer.parseInt(zipCodeTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid Zip Code");
			zipCodeTxt.setText("");
			return;
		}

	//select state
	state = stateList.getSelectedItem().toString();

	// get employee status
	if(fullTimeBtn.isSelected()) {
		status = Status.FullTime;
	}
	else if(hourlyBtn.isSelected()) {
		status = Status.Hourly;
	}
	else {
		JOptionPane.showMessageDialog(null,"Select Employee status");
		return;
	}

	if( countEmployee+3<numberOfPeople ) {
		int houseNo = Integer.parseInt(houseNumber);
		Address address = new Address(street, houseNo, city, state, zipCode);
		employee = payRoll.createEmployee(employeeId, status, firstName,lastName, address);
		countEmployee++;
	}else{
		JOptionPane.showMessageDialog(null,"Maximum employees reached");
	}
}

private void addPayRecordButtonClicked() {
	int numberOfMonths = 0,payPeriodId,payRecordId;
	Date startDate = new Date(),endDate;
	double payHours = 0, payRate=0, monthlyIncome = 0;

	// input payPeriodId
	if(payPeriodIdTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(payPeriodIdTxt,"Input Employee ID");
		payPeriodIdTxt.setText("");
		return;
	}
	else
		try {
			payPeriodId=Integer.parseInt(payPeriodIdTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid ID number");
			payPeriodIdTxt.setText("");
			return;
		}
	
	//input start date
	if(startDateTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(startDateTxt,"Input Start Date");
		startDateTxt.setText("");
		return;
	}else
		try {
			SimpleDateFormat sdf = new
			SimpleDateFormat("MM/dd/yyyy");
			startDate=sdf.parse(startDateTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid Date Format\nValid format: MM/DD/YYYY");
			startDateTxt.setText("");
			return;
	}
	
	// input end date
	if(endDateTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(endDateTxt,"Input End Date");
		endDateTxt.setText("");
		return;
	}else
		try {
			SimpleDateFormat sdf=new
			SimpleDateFormat("MM/dd/yyyy");
			endDate = sdf.parse((endDateTxt.getText()));
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid Date Format\nValid format: MM/DD/YYYY");
			endDateTxt.setText("");
			return;
		}

	// Verify date
	if(endDate.before(startDate)) {
		JOptionPane.showMessageDialog(null,"Start Date cannot be later than the end date! ");
		endDateTxt.setText("");
		startDateTxt.setText("");
		return;
	}

	//input Pay Record ID
	if(payRecordIdTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(payRecordIdTxt,"Input Pay Record ID");
		payRecordIdTxt.setText("");
		return;
	}else
		try {
			payRecordId = Integer.parseInt(payRecordIdTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid ID");
			payRecordIdTxt.setText("");
			return;
		}

	if(fullTimeBtn.isSelected()) {
		
		//monthly income
		if(monthlyIncomeTxt.getText().isEmpty()){
			JOptionPane.showMessageDialog(monthlyIncomeTxt,"Input Monthly Income");
			payRecordIdTxt.setText("");
			return;
		}else
			try {
				monthlyIncome=Double.parseDouble(monthlyIncomeTxt.getText());
			}catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Invalid Monthly Income");
					payRecordIdTxt.setText("");
	return;
	}
			
	//input month
	if(numberOfMonthTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(numberOfMonthTxt,"Input Number Of Month");
		numberOfMonthTxt.setText("");
		return;
	}
	else
		try {
			numberOfMonths=Integer.parseInt(numberOfMonthTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid Month");
			numberOfMonthTxt.setText("");
			return;
			}
		payHours=0;
		payRate=0;
	}
	else if(hourlyBtn.isSelected()) {
	
	//input pay hours
	if(payHoursTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(payHoursTxt,"Input Pay Hours");
			payHoursTxt.setText("");
			return;
			}
	else {
		try {
			payHours=Double.parseDouble(payHoursTxt.getText());
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"Invalid Pay Hours");
				payHoursTxt.setText("");
			return;
			}
			}
			
	//input pay Rate
	if(payRateTxt.getText().isEmpty()){
		JOptionPane.showMessageDialog(payRateTxt,"Input Pay Rate");
			payRateTxt.setText("");
			return;
			}
	else {
		try {
			payRate=Double.parseDouble(payRateTxt.getText());
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null,"Invalid Pay Rate");
			payRateTxt.setText("");
			return;
			}
			}
			monthlyIncome=0;
			numberOfMonths=0;
			}
	if( countPayRecord+3<numberOfPeople ) {
		
		if(fullTimeBtn.isSelected()) {
			new PayRecord(payRecordId, employee, period, monthlyIncome, numberOfMonths);
		}else {
			new PayRecord(payRecordId, employee, period, payRate, payHours);
		}//end if/else
		
		countPayRecord++;
	}
	else {
		JOptionPane.showMessageDialog(null, "Maximum employees reached");
		return;
	}
			
	//clear text fields from data
	employeeIdTxt.setText("");
	lastNameTxt.setText("");
	firstNameTxt.setText("");
	streetTxt.setText("");
	houseNumberTxt.setText("");
	cityTxt.setText("");
	zipCodeTxt.setText("");
	payPeriodIdTxt.setText("");
	startDateTxt.setText("");
	endDateTxt.setText("");
	payRecordIdTxt.setText("");
	monthlyIncomeTxt.setText("");
	numberOfMonthTxt.setText("");
	payHoursTxt.setText("");
	payRateTxt.setText("");
}

private void displayButtonClicked() throws IOException{
	//if display button is clicked with incomplete fields
	if (countEmployee + 3 < numberOfPeople) {
			JOptionPane.showMessageDialog(null,"You need to add " + (numberOfPeople -(countEmployee + 3))+" more person");
			return;
	}
			payRoll.displayPayRecord(textArea);
			JOptionPane.showMessageDialog(null,"Total Net Pay:" + 
					String.format("%.2f",payRecord.netPay())+"\nAverage Net Pay:" + 
					String.format("%.2f",payRoll.avgNetPay()));
					payRoll.writeToFile();
			}
private void closeButtonClicked() {
	JOptionPane.showMessageDialog(null,"PROGRAM CLOSED");
	System.exit(0);	
}
}
