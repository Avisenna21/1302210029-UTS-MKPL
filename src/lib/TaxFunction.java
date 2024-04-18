package lib;

public class TaxFunction {

    private static final int MAX_MONTHS_WORKING = 12;
    private static final int MAX_CHILDREN_FOR_EXEMPTION = 3;
    private static final int BASE_EXEMPTION_AMOUNT = 54000000;
    private static final int MARRIED_EXEMPTION_AMOUNT = 4500000;
    private static final int PER_CHILD_EXEMPTION_AMOUNT = 1500000;
    private static final double TAX_RATE = 0.05;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     * 
     */
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int tax = 0;
        
        if (numberOfMonthWorking > MAX_MONTHS_WORKING) {
            System.err.println("More than 12 month working per year");
        }
        
        if (numberOfChildren > MAX_CHILDREN_FOR_EXEMPTION) {
            numberOfChildren = MAX_CHILDREN_FOR_EXEMPTION;
        }
        
        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxExemptAmount = calculateTaxExemptAmount(isMarried, numberOfChildren);
        int taxableIncome = totalIncome - deductible - taxExemptAmount;

        tax = (int) Math.round(TAX_RATE * taxableIncome);
        
        return Math.max(tax, 0); // Ensure tax is not negative
    }

    private static int calculateTaxExemptAmount(boolean isMarried, int numberOfChildren) {
        int taxExemptAmount = BASE_EXEMPTION_AMOUNT;
        if (isMarried) {
            taxExemptAmount += MARRIED_EXEMPTION_AMOUNT;
        }
        taxExemptAmount += numberOfChildren * PER_CHILD_EXEMPTION_AMOUNT;
        return taxExemptAmount;
    }
}