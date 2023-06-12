
package MotorPH;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class data {
    
    private String _employeeNo;
    private String _employeeFN;
    private String _employeeLN;
    private String _employeeAddress;
    private String _employeeDOB;
    private double _rice;
    private double _phone;
    private double _cloth;
    private double _hrRate;
    
    
    
    //Getter Methods
    public String EmployeeNo(){
        return _employeeNo;
    }
    
    public String EmployeeFN(){
        return _employeeFN;
    }
    
    public String EmployeeLN(){
        return _employeeLN;
    }
    
    public String EmployeeDOB(){
        return _employeeDOB;
    }
    
    public String EmployeeAddress(){
        return _employeeAddress;
    }
    
    public double RiceAllowance(){
        return _rice;
    }
    
    public double PhoneAllowance(){
        return _phone;
    }
    
    public double ClothAllowance(){
        return _cloth;
    }
    
    public double HourlyRate(){
        return _hrRate;
    }
    
    
   
   //Setter Methods
    public void SetEmployeetNo(String EmployeeNo){
        _employeeNo = EmployeeNo;
    }
    
    public void SetEmployeeFN(String EmployeeFN){
        _employeeFN = EmployeeFN;
    }
    
    public void SetEmployeeLN(String EmployeeLN){
        _employeeLN = EmployeeLN;
    }
    
    public void SetEmployeeAddress(String EmployeeAddress){
        _employeeAddress = EmployeeAddress;
    }
    
    
    public void ReadEmployee(String search)throws FileNotFoundException, IOException, CsvValidationException {
        String filename = "Employee Data.csv";
        CSVReader reader = new CSVReader(new FileReader(filename));
        String[] headers = reader.readNext();
        String[] employeeData;
        while((employeeData = reader.readNext()) != null){
            if(employeeData[0].equals(search)){
                _employeeNo = employeeData[0];
                _employeeLN = employeeData[1];
                _employeeFN = employeeData[2];
                _employeeDOB = employeeData[3];
                _employeeAddress = employeeData[4];
                _rice = Double.parseDouble(employeeData[14]);
                _phone = Double.parseDouble(employeeData[15]);
                _cloth = Double.parseDouble(employeeData[16]);
                _hrRate = Double.parseDouble(employeeData[18]);
                break;
            }
                
        }
        
    }
    
}
