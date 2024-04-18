package lib;

public class TaxFunction {

    private static final int MAX_MONTHS_WORKING = 12;
    private static final int MAX_CHILDREN_FOR_EXEMPTION = 3;

    /**
     * Menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     *  monthlySalary Gaji bulanan pegawai.
     * motherMonthlyIncome Penghasilan bulanan lainnya.
     * numberOfMonthWorking Jumlah bulan kerja dalam setahun.
     *  deductible Jumlah pemotongan pajak lainnya.
     *  isMarried Status perkawinan pegawai.
     *  numberOfChildren Jumlah anak pegawai.
     *  Jumlah pajak yang harus dibayarkan setahun.
     *  IllegalArgumentException Jika jumlah bulan kerja melebihi 12 bulan.
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        validateNumberOfMonths(numberOfMonthWorking);
        
        if (numberOfChildren > MAX_CHILDREN_FOR_EXEMPTION) {
            numberOfChildren = MAX_CHILDREN_FOR_EXEMPTION;
        }

        int taxExemptAmount = calculateTaxExemptAmount(isMarried, numberOfChildren);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxableIncome = totalIncome - deductible - taxExemptAmount;

        int tax = (int) Math.round(0.05 * taxableIncome);
        
        return Math.max(tax, 0); // Ensure tax is not negative
    }

    /**
     * Memvalidasi jumlah bulan kerja.
     * 
     */
    private static void validateNumberOfMonths(int numberOfMonthWorking) {
        if (numberOfMonthWorking > MAX_MONTHS_WORKING) {
            throw new IllegalArgumentException("More than 12 month working per year");
        }
    }

    /**
     * Menghitung jumlah penghasilan tidak kena pajak.
     * 
     */
    private static int calculateTaxExemptAmount(boolean isMarried, int numberOfChildren) {
        int taxExemptAmount = 54000000;
        if (isMarried) {
            taxExemptAmount += 4500000;
        }
        taxExemptAmount += numberOfChildren * 1500000;
        return taxExemptAmount;
    }
}
