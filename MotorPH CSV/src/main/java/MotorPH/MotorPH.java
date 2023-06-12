package MotorPH;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MotorPH {

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        
        Scanner scan = new Scanner(System.in);
        
        String choice = "Y";
        
        while(choice.equals("Y") || choice.equals("y")) {
        attendance hW = new attendance();
        System.out.print("Enter Employee No: ");
        String search = scan.nextLine();
        System.out.print("Enter Month: ");
        hW.SetMonth(scan.nextLine());
        DecimalFormat df = new DecimalFormat("#,##0.00");
        data employee = new data();
        try {
            employee.ReadEmployee(search);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }

        //EMPLOYEE DETAILS
        System.out.println();
        System.out.println("Employee No.: " + employee.EmployeeNo());
        System.out.println("Employee Name: " + employee.EmployeeFN() +" "+ employee.EmployeeLN());
        System.out.println("Birthday: " + employee.EmployeeDOB());
        System.out.println("Address: " + employee.EmployeeAddress());
        
        hW.SetEmployeeNo(search);
        hW.SetMonth(hW.Month());
        try {
            hW.ComputeHourWorked();
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //SALARY COMPUTATIONS
        //System.out.println("Hourly Rate: " + employee.HourlyRate()); //This line is for testing if the hourly rate is being read.
        //System.out.println("Total Hours: " + hW.FinalHW()); //This line is for testing if the total hours are being computed.
        double allowances = employee.RiceAllowance() + employee.PhoneAllowance() + employee.ClothAllowance();
        double _baseSalary = (employee.HourlyRate() * hW.FinalHW()) + allowances;
        System.out.println("\n\tAllowance");
        System.out.println("Rice Subsidy: " + df.format(employee.RiceAllowance()));
        System.out.println("Phone: " + df.format(employee.PhoneAllowance()));
        System.out.println("Cloth: " + df.format(employee.ClothAllowance()));
        System.out.println("Base Salary (inc. allowance): " + df.format(_baseSalary));
        
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
        
        System.out.println("\nNet Pay: " + df.format(_netPay));
        
        //PROMPT THE USER TO CONTINUE OR NOT
        System.out.print("\nDo you want to continue? (Y or N): ");
        choice = scan.nextLine();
        System.out.println();
                
        //CATCH INVALID INPUT
        while(!choice.equals("Y") && !choice.equals("y") && !choice.equals("N") && !choice.equals("n") && !choice.equals(" ")){
            System.out.println("Invalid Input..");
            System.out.print("\nDo you want to continue? (Y or N): ");
            choice = scan.nextLine();  
            System.out.println();
        }
      }
    
    
  }
   
}
