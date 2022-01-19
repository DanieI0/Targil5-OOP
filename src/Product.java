public class Product {
    private double price;
    private int amount;
    private String name;
    private boolean available;
    private double membershipDiscount;


    public Product(int amount,double price,String name,boolean available, double membershipDiscount){
        this.available = available;
        this.price = price;
        this.name = name;
        this.membershipDiscount = membershipDiscount;
        this.amount = amount;
    }

    public Product (Product product){
        this.available = product.available;
        this.price = product.price;
        this.name = product.name;
        this.membershipDiscount = product.membershipDiscount;
        this.amount = product.amount;
    }

    public boolean isAvailable() {
        return available;
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return name + ", price: " + price + "$";
    }
}
