
package MotorPH;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class attendance {

    private String _employeeNo;
    private String _employeeName;
    private String _month;
    private String _date;
    private String _timeIn;
    private String _timeOut;
    private String pattern = "HH:mm";
    private Date time1;
    private Date time2;
    private double timeOne;
    private double timeTwo;
    private double hours;
    private double totalHours;
    private double gracePer;

    
    String[] hoursWorked;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);   
    
    //GETTER METHODS
    public String EmployeeNo(){
        return _employeeNo;
    }
    
    public String EmployeeName(){
        return _employeeName;
    }
    
    public String Month(){
        return _month;
    }
    
    public String Date(){
        return _date;
    }
    
    public double TimeIn() throws ParseException{
        _timeIn = hoursWorked[5];
        time1 = dateFormat.parse(_timeIn);
        timeOne = time1.getTime();
        return (timeOne / (60*60*1000))-5;
    }
    
    public double TimeOut() throws ParseException{
        _timeOut = hoursWorked[6];
        time2 = dateFormat.parse(_timeOut);
        timeTwo = time2.getTime();
        return (timeTwo / (60*60*1000))-5;
    }
    
    public double HoursWorked() throws ParseException{
            hours =  TimeOut()-TimeIn();
                if(hours >=8 || hours > 4){
                    hours -= 1;
                }
                //GRACE PERIOD OF 10 MINUTES
                if(hours >= 7.83 && hours < 8){
                    gracePer = (TimeIn() - 8) + hours;
                } else {
                    gracePer = hours;
                } 
        return gracePer;
    }
    
    public double FinalHW(){
        return totalHours;
    }
    
    
    
    
    //SETTER METHODS
    public void SetEmployeeNo(String EmployeeNo){
        _employeeNo = EmployeeNo;
    }
    
    public void SetMonth(String Month){
        _month = Month;
    }
    
    public void ComputeHourWorked()throws FileNotFoundException, IOException, CsvValidationException, ParseException{
        String filename = "Attendance.csv";
        CSVReader reader = new CSVReader(new FileReader(filename));
        String[] headers = reader.readNext();
        while((hoursWorked = reader.readNext()) != null){
            if(hoursWorked[0].equals(_employeeNo) && hoursWorked[3].equals(_month)){
                _month = hoursWorked[3];
                _date = hoursWorked[4];
                _timeIn = hoursWorked[5];
                _timeOut = hoursWorked[6];

//                System.out.println();
//                System.out.println("Date: " + _date);
//                System.out.println("Time In: " + _timeIn);
//                System.out.println("Time Out: " + _timeOut);
                HoursWorked();
                totalHours += gracePer;
                
            } 
        }
        //System.out.println("\nTotal Hours Worked: " + FinalHW());
        
        
    }
    
    
    
}
