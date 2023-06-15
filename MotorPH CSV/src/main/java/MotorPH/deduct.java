
package MotorPH;

import java.text.DecimalFormat;

public class deduct{
    
    private double _baseSalary;
    private double sssContri;
    private double PhHContri;
    private double PhIContri;
    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public deduct(double base){
        _baseSalary = base;
    }
    
    public void Contributions() {
        //SSS CONTRIBUTION COMPUTATION
        double sssContri = 0;
        if(_baseSalary == 0){
            sssContri = 0;
        }else if(_baseSalary < 22500){
            sssContri = 135;
        } else if (_baseSalary >= 22500 && _baseSalary <= 22750){
            sssContri = 1012.50;
        } else if (_baseSalary >= 22750 && _baseSalary <= 23250){
            sssContri = 1035.00;
        } else if (_baseSalary >= 23250 && _baseSalary <= 23750){
            sssContri = 1057.50;
        } else if (_baseSalary >= 23750 && _baseSalary <= 24250){
            sssContri = 1080.00;
        } else if (_baseSalary >= 24250 && _baseSalary <= 24750){
            sssContri = 1102.50;
        } else if (_baseSalary >= 24750){
            sssContri = 1125.00;
        }
        System.out.println("\n\tDeductions");
        System.out.println("SSS: " + df.format(sssContri));

        //PHILHEALTH CONTRIBUTION COMPUTATION
        double PhHContri;
        if (_baseSalary < 60000){
            PhHContri = (_baseSalary *.03)/2;
        } else {
            PhHContri = 1800 / 2;
        }
        System.out.println("PhilHealth: " + df.format(PhHContri));

        //PAGIBIG CONTRIBUTION COMPUTATION
        double PgIContri = 0;
        if (_baseSalary <= 1500){
            PgIContri = (_baseSalary *.03) - (_baseSalary *.02);
        } else if (_baseSalary > 1500){
            //DIVIDED INTO 2 AS HALF WILL BE PAID BY THE EMPLOYER
            PgIContri = Math.min((_baseSalary*0.04)/2, 200/2);
        }
        System.out.println("Pag Ibig: " + df.format(PgIContri));

        //WITHHOLDING TAX COMPUTATION
        double GovContri = sssContri + PhHContri + PgIContri;
        double taxableInc = _baseSalary - GovContri;
        double withHoldingTax = 0;
        if (taxableInc <= 20832){
            withHoldingTax = 0;
        } else if (taxableInc >= 20833 && taxableInc < 33333) {
            withHoldingTax = (taxableInc - 20833)*0.20;
        } else if (taxableInc >= 33333 && taxableInc < 66667) {
            withHoldingTax = ((taxableInc - 33333)*0.25) + 2500;
        } else if (taxableInc >= 66667 && taxableInc < 166667) {
            withHoldingTax = ((taxableInc - 66667)*0.30) + 10833;
        } else if (taxableInc >= 166667 && taxableInc < 666667) {
            withHoldingTax = ((taxableInc - 166667)*0.32) + 40833.33;
        } else if (taxableInc >= 666667) {
            withHoldingTax = ((taxableInc - 666667)*0.35) + 200833.33;
        }
        System.out.println("Tax: " + df.format(withHoldingTax));

        double _netPay = taxableInc - withHoldingTax;
        System.out.println("\n\tSalary");
        System.out.println("Net Salary: " + df.format(_netPay));
        System.out.println("------------------------------------------------------------------------");
    }
    
    
}
