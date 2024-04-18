package lib;

public class TaxFunction {

    private static final int MAX_MONTHS_WORKING = 12;
    private static final int MAX_CHILDREN_FOR_EXEMPTION = 3;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * 
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
     * 
     * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
     * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
     * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
     * 
     * @throws IllegalArgumentException Jika jumlah bulan kerja melebihi 12 bulan.
     */
    
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > MAX_MONTHS_WORKING) {
            throw new IllegalArgumentException("More than 12 month working per year");
        }
        
        if (numberOfChildren > MAX_CHILDREN_FOR_EXEMPTION) {
            numberOfChildren = MAX_CHILDREN_FOR_EXEMPTION;
        }

        int taxExemptAmount = calculateTaxExemptAmount(isMarried, numberOfChildren);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxableIncome = totalIncome - deductible - taxExemptAmount;

        int tax = (int) Math.round(0.05 * taxableIncome);
        
        return Math.max(tax, 0); // Ensure tax is not negative
    }

    private static int calculateTaxExemptAmount(boolean isMarried, int numberOfChildren) {
        int taxExemptAmount = 54000000;
        if (isMarried) {
            taxExemptAmount += 4500000;
        }
        taxExemptAmount += numberOfChildren * 1500000;
        return taxExemptAmount;
    }
}
