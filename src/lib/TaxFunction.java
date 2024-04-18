package lib;

public class TaxFunction {
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int numberOfMonthsWorking;
    private int deductible;
    private boolean isMarried;
    private int numberOfChildren;

    public TaxFunction(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorking, int deductible, boolean isMarried, int numberOfChildren) {
        this.monthlySalary = monthlySalary;
        this.otherMonthlyIncome = otherMonthlyIncome;
        this.numberOfMonthsWorking = numberOfMonthsWorking;
        this.deductible = deductible;
        this.isMarried = isMarried;
        this.numberOfChildren = numberOfChildren;
    }

    // Getters and setters omitted for brevity

    /**
     * Method to calculate the annual income tax amount an employee should pay.
     * 
     * The tax is calculated as 5% of the annual net income (monthly salary and other monthly income multiplied by number of months worked minus deductible) minus the tax exempt income.
     * 
     * If the employee is unmarried and has no children, the tax exempt income is Rp 54,000,000.
     * If the employee is married, the tax exempt income is increased by Rp 4,500,000.
     * If the employee has children, the tax exempt income is increased by Rp 4,500,000 per child up to the third child.
     * 
     */
    
    public static int calculateTax(TaxFunction taxFunction) {
        int tax = 0;
        
        if (taxFunction.getNumberOfMonthsWorking() > 12) {
            System.err.println("More than 12 months working per year");
        }
        
        int numberOfChildren = taxFunction.getNumberOfChildren();
        if (numberOfChildren > 3) {
            numberOfChildren = 3;
        }
        
        int taxExemptAmount = 54000000;
        if (taxFunction.isMarried()) {
            taxExemptAmount += 4500000;
        }
        taxExemptAmount += numberOfChildren * 1500000;

        tax = (int) Math.round(0.05 * (((taxFunction.getMonthlySalary() + taxFunction.getOtherMonthlyIncome()) * taxFunction.getNumberOfMonthsWorking()) - taxFunction.getDeductible() - taxExemptAmount));
        
        return Math.max(tax, 0); // Ensure tax is not negative
    }
