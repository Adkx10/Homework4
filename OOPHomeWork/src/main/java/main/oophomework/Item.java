
package main.oophomework;

abstract class Item {
    private String prodName;
    private double prodPrice;
    
    abstract double calculateTotalSales();
    
    abstract void displayBreakdown();
    
    public Item(String prodName, double prodPrice){
        this.prodName = prodName;
        this.prodPrice = prodPrice;
    }
    
    
    //Getter and Setter Methods
    
    public String getProdName(){
        return prodName;
    }
    
    public double getProdPrice(){
        return prodPrice;
    }
    
    public void setProdPrice(double prodPrice){
        if (prodPrice >= 0){
            this.prodPrice = prodPrice;
        } else {
            System.out.println("Price cannot be negative.");
        }
    }
    
}
