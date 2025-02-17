
package main.oophomework;

public class PhysicalProduct extends Item {
    private int quantitySold;
    
    @Override
    double calculateTotalSales(){
        return getProdPrice() * quantitySold;
    }
    
    @Override
    void displayBreakdown(){
        System.out.println("Item: " + getProdName());
        System.out.println("Price: $" + getProdPrice());
        System.out.println("Quantity: " + quantitySold);
        System.out.println("Total: $" + calculateTotalSales());
    }
    
    public PhysicalProduct(String prodName, double prodPrice, int quantitySold){
        super(prodName, prodPrice);
        this.quantitySold = quantitySold;
    }
    
    
    //Getter and Setter methods
    
    public int getQuantitySold(){
        return quantitySold;
    }
    
    public void setQuantitySold(int quantitySold){
        if (quantitySold >= 0){
            this.quantitySold = quantitySold;
        } else {
            System.out.println("Quantity cannot be negative.");
        }
    }
    
}
