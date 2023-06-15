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
    
    public static double _baseSalary;

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        
        Scanner scan = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        
        String choice = "Y";
        
        while(choice.equals("Y") || choice.equals("y")) {
        attendance hW = new attendance();
        System.out.print("\nEnter Employee No: ");
        String search = scan.nextLine();
        System.out.print("Enter Month: ");
        hW.SetMonth(scan.nextLine());
        data employee = new data();
        try {
            employee.ReadEmployee(search);
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }

        hW.SetEmployeeNo(search);
        hW.SetMonth(hW.Month());
        try {
            hW.ComputeHourWorked();
        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //SALARY COMPUTATIONS
        double allowances = employee.RiceAllowance() + employee.PhoneAllowance() + employee.ClothAllowance();
        _baseSalary = (employee.HourlyRate() * hW.FinalHW()) + allowances;
        System.out.println("Gross Salary (inc. allowance): " + df.format(_baseSalary));
        
        deduct base = new deduct(_baseSalary);
        base.Contributions();

        //PROMPT THE USER TO CONTINUE OR NOT
        System.out.print("Do you want to continue? (Y or N): ");
        choice = scan.nextLine();
                
        //CATCH INVALID INPUT
        while(!choice.equals("Y") && !choice.equals("y") && !choice.equals("N") && !choice.equals("n") && !choice.equals(" ")){
            System.out.println("Invalid Input..");
            System.out.print("Do you want to continue? (Y or N): ");
            choice = scan.nextLine();  
      }
    }
  } 
}
