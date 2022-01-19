import java.util.Date;

public class Customer extends User {
    private boolean hasMembership;
    private boolean hasPurchasedOnce;
    private double totalPurchasePrice;
    private int totalPurchases;
    private Date lastPurchaseDate;

    public Customer(String name, String surname, String username, String password,boolean hasMembership,boolean hasPurchasedOnce, double totalPurchasePrice, Date lastPurchaseDate, int totalPurchases) {
        super(surname,name,username,password);
        this.surname = surname;
        this.name = name;
        this.username = username;
        this.password = password;
        this.hasMembership = hasMembership;
        this.hasPurchasedOnce = hasPurchasedOnce;
        this.totalPurchasePrice = totalPurchasePrice;
        this.lastPurchaseDate = lastPurchaseDate;
        this.totalPurchases = totalPurchases;
    }

    public Customer(Customer cs){
        super(cs.surname,cs.name,cs.username,cs.password);
        this.surname = cs.surname;
        this.name = cs.name;
        this.username = cs.username;
        this.password = cs.password;
        this.hasMembership = cs.hasMembership;
        this.hasPurchasedOnce = cs.hasPurchasedOnce;
        this.totalPurchasePrice = cs.totalPurchasePrice;
        this.lastPurchaseDate = cs.lastPurchaseDate;
        this.totalPurchases = cs.totalPurchases;
    }

    public String getUsername(){
        return username;
    }

    public boolean isHasPurchasedOnce() {
        return hasPurchasedOnce;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public double getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    public boolean isHasMembership() {
        return hasMembership;
    }

    public void setTotalPurchases(int totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    public void setHasPurchasedOnce(boolean hasPurchasedOnce) {
        this.hasPurchasedOnce = hasPurchasedOnce;
    }

    public void setHasMembership(boolean hasMembership) {
        this.hasMembership = hasMembership;
    }

    public void setTotalPurchasePrice(double totalPurchasePrice) {
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public void setLastPurchaseDate(Date lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    @Override
    public String toString(){
        String result = "";
        if(hasMembership){
            result =  name + " " + surname + " (VIP)" + " || Total purchases: " + totalPurchases + " || Total purchases price: " + totalPurchasePrice + " || Last purchase date: " + lastPurchaseDate;
        } else {
            result =  name + " " + surname +  " || Total purchases: " + totalPurchases + " || Total purchases price: " + totalPurchasePrice + " || Last purchase date: " + lastPurchaseDate;
        }
        return result;
    }
}
