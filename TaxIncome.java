package PP03;

public class TaxIncome implements Taxable{

	
	public TaxIncome(){
		
	}//end default constructor
	
	@Override
	public double compStateTax(double grossPay) {
		return STATE_TAX * grossPay;
	}

	@Override
	public double compFederalTax(double grossPay) {
		return FEDERAL_TAX * grossPay;
	}

	@Override
	public double compIncomeTax(double grossPay) {
		double state = compStateTax(grossPay);
		double federal = compFederalTax(grossPay);
		return state + federal;
	}

	// 1- this class implements the Taxable interface
	// 2- implement all the unimplemented abstract methods in the Taxable Interface, income tax is computed based on state and federal taxes   
	
	
	

}
