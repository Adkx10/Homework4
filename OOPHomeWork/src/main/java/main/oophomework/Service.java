
package main.oophomework;

public class Service extends Item {
    private double pricePerHour;
    private double numberOfHour;

    @Override
    double calculateTotalSales() {
        return pricePerHour * numberOfHour;
    }
    
    @Override
    public void displayBreakdown(){
        System.out.println("Item: " + getProdName());
        System.out.println("Labor Rate: $" + pricePerHour);
        System.out.println("Labor Hour: " + numberOfHour);
        System.out.println("Total: $" + calculateTotalSales());
    }
    
    
    public Service(String prodName, double pricePerHour, double numberOfHour){
        super(prodName,pricePerHour);
        this.pricePerHour = pricePerHour;
        this.numberOfHour = numberOfHour;
    }
    
    
    //Getter and Setter methods
    
    public double getPricePerHour(){
        return pricePerHour;
    }
    
    public double getNumberOfHour(){
        return numberOfHour;
    }    
    
    public void setPricePerHour(double pricePerHour){
        if(pricePerHour >= 0){
            this.pricePerHour = pricePerHour;
        } else {
            System.out.println("Hourly rate cannot be negative.");
        }
    }
    
    public void setNumberOfHours(double numberOfHour){
        if(numberOfHour >= 0){
            this.numberOfHour = numberOfHour;
        } else {
            System.out.println("Number of hours cannot be negative.");
        }
    }
    
}
