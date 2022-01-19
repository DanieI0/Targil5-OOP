
public class Cart  {
    protected double price;
    protected Product[] prod;
    public static final int END_PURCHASE = -1;

    public Cart(Product[] prod ,double price){
        this.price = price;
        this.prod = prod;
    }
    public Cart(){
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double calcPrice(Product[] cartList) {
        double totalPriceToPay = 0;
        for (int i = 0; i < cartList.length; i++) {
            double price = cartList[i].getPrice() * cartList[i].getAmount();
            totalPriceToPay = price + totalPriceToPay;
        }
        return totalPriceToPay;
    }


    @Override
    public String toString() {
        return "Cart: " + "price: "  + price + "$";
    }
}
