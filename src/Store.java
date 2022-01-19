import java.util.*;

public class Store {
    private static final Calendar calendar = GregorianCalendar.getInstance();
    private static final Scanner scanner = new Scanner(System.in);
    private User[] users;
    private Product[] products;
    private Product[] cartProducts;
    private static final int MIN_PASSWORD_LENGTH = 6;

    public Store() {
        this.users = new User[0];
        this.products = new Product[0];
        this.cartProducts = new Product[0];
        Product basic = new Product( 0,4.5, "Milk", true, 0);
        Product bread = new Product(basic);
        bread.setName("Bread");
        bread.setPrice(1.9);
        bread.setAvailable(false);
        Product butter = new Product(basic);
        butter.setName("Butter");
        butter.setPrice(3.9);
        addProductIntoStore(basic);
        addProductIntoStore(bread);
        addProductIntoStore(butter);

        addCustomer("Person", "Family", "user", "123456", true);
        addEmployee("Admin", "System", "admin", "123456", 1);

    }

    void printAllProducts() {
        System.out.println("These are the available products in the store: ");
        System.out.println();
        for (int i = 0; i < products.length; i++) {
            if (products[i].isAvailable()) {
                System.out.println("[" + i + "]" + " " + products[i]);
            }
        }
        System.out.println();
        System.out.println("Unavailable products: ");
        for (int i = 0; i < products.length; i++) {
            if (!products[i].isAvailable()) {
                System.out.println("[" + i + "]" + " " + products[i]);
            }
        }
        purchaseAProduct();
    }

    void printCustomerWithHighestPurchase() {
        double max = 0;

        for (int i = 0; i < users.length; i++) {
            if (users[i] instanceof Customer) {
                if (((Customer) users[i]).getTotalPurchasePrice() > max) {
                    max = ((Customer) users[i]).getTotalPurchasePrice();
                }
            }
        }
        for (int j = 0; j < users.length; j++) {
            if (users[j] instanceof Customer) {
                if (((Customer) users[j]).getTotalPurchasePrice() == max) {
                    System.out.println(users[j].getName() + " " + users[j].getSurname() + " || Purchase price: " + ((Customer) users[j]).getTotalPurchasePrice() + "$");
                }
            }
        }
    }

    private void purchaseAProduct() {
        System.out.println();
        System.out.println("Enter the number of the product you want to purchase: ");
        System.out.println("To end the purchase , enter -1");
        int productChoice = scanner.nextInt();
        while (productChoice > products.length || productChoice < Cart.END_PURCHASE) {
            if (!products[productChoice].isAvailable()) {
                System.out.println("Product is unavailable right now");
            } else {
                System.out.println("Error, try other choice");
            }
            productChoice = scanner.nextInt();
        }
        if (productChoice == Cart.END_PURCHASE){
            printCart();
        } else {
            if (doesProductExists(productChoice)) {
                System.out.println("How much " + products[productChoice].getName() + " do you want: ");
                int amountOfProduct = scanner.nextInt();
                while (amountOfProduct < 0) {
                    System.out.println("Amount cannot be negative");
                    amountOfProduct = scanner.nextInt();
                }
                products[productChoice].setAmount(amountOfProduct);
                addProductIntoCart(products[productChoice]);
                printAllProducts();
            } else {
                System.out.println("Product doesn't exist or is not available");
                purchaseAProduct();
            }
        }
}

    void changeAvailabilityOfProduct(){
      justPrintProducts();
        System.out.println();
        System.out.println("Which product do you want to change availability for?");
        int employeeSelect = scanner.nextInt();
        while (employeeSelect > products.length || employeeSelect < 0){
            System.out.println("You can't choose nonexistent products");
            employeeSelect = scanner.nextInt();
        }
        System.out.println("is " + products[employeeSelect] + " should be available or unavailable?");
        System.out.println("1 - Available");
        System.out.println("2 - Unavailable");
        int availabilityChoice = scanner.nextInt();
        while (availabilityChoice < Main.FIRST_CHOICE || availabilityChoice > Main.SECOND_CHOICE){
            System.out.println("Error , try again");
            availabilityChoice = scanner.nextInt();
        }
        if (availabilityChoice == Main.FIRST_CHOICE) {
            products[employeeSelect].setAvailable(true);
        } else{
            products[employeeSelect].setAvailable(false);
        }
        System.out.println("Change of availability succeeded!");
        justPrintProducts();
    }

    private void justPrintProducts(){
        System.out.println("Available products: ");
        for (int i = 0; i < products.length; i++) {
            if (products[i].isAvailable()) {
                System.out.println("[" + i + "]" + " " + products[i]);
            }
        }
        System.out.println("Unavailable products: ");
        for (int i = 0; i < products.length; i++) {
            if (!products[i].isAvailable()) {
                System.out.println("[" + i + "]" + " " + products[i]);
            }
        }
    }
    private void printCart(){
        Date lastPurchase = Calendar.getInstance().getTime();
        Cart cartClass = new Cart();
        cartClass.setPrice(cartClass.calcPrice(cartProducts));
        System.out.println("Your cart has: ");
        for (int i =0; i < cartProducts.length; i++) {
            System.out.println(cartProducts[i].getName() + " || price: " + cartProducts[i].getPrice() + "$ || amount: " + cartProducts[i].getAmount());
            }
        System.out.println(cartClass.toString());
        for (int k = 0; k<users.length; k++){
            if (users[k]instanceof Customer){
                ((Customer) users[k]).setLastPurchaseDate(lastPurchase);
                ((Customer) users[k]).setHasPurchasedOnce(true);
                ((Customer) users[k]).setTotalPurchases(((Customer) users[k]).getTotalPurchases() + 1);
                ((Customer) users[k]).setTotalPurchasePrice(((Customer) users[k]).getTotalPurchasePrice()+cartClass.price);
        }
        }
    }

    void printAllCustomers() {
        for (int i = 0; i < users.length; i++) {
            if (users[i] instanceof Customer) {
                System.out.println(users[i].getName() + " " + users[i].getSurname());
            }
        }
    }

    void printAllMembershipCustomers(){
        for (int i = 0; i < users.length; i++) {
            if (users[i] instanceof Customer && ((Customer) users[i]).isHasMembership()) {
                System.out.println(users[i].getName() + " " + users[i].getSurname());
            }
        }
    }

    void printCustomersThatPurchasedOnce(){
        for (int i = 0; i < users.length; i++) {
            if(users[i] instanceof Customer && ((Customer) users[i]).isHasPurchasedOnce()){
                System.out.println(users[i].getName() + " " + users[i].getSurname());
            }
        }
    }
    void createProduct(){
        String productName;
        double productPrice;
        double priceDiscountForMembership;

        System.out.println("What will be the product's name?");
        productName = scanner.next();
        System.out.println("What is the price of the product?");
        productPrice = scanner.nextDouble();
        while (productPrice < 0){
            System.out.println("Price can't be negative");
            productPrice = scanner.nextDouble();
        }
        System.out.println("What is the discount for membership customers?");
        priceDiscountForMembership = scanner.nextDouble();
        while (priceDiscountForMembership < 0){
            System.out.println("Price can't be negative");
            priceDiscountForMembership = scanner.nextDouble();
        }
        Product newProduct = new Product(0,productPrice,productName,true,priceDiscountForMembership);
        addProductIntoStore(newProduct);
    }

    private void addProductIntoCart(Product prod){
        Product pd = new Product(prod);
        Product[] cartList = new Product[this.cartProducts.length + 1];
        for (int i = 0; i < this.cartProducts.length; i++) {
            cartList[i] = this.cartProducts[i];
        }
        cartList[this.cartProducts.length] = pd;
        this.cartProducts = cartList;
    }

    private boolean doesProductExists(int index){
        for (int i = 0; i<products.length; i++){
            if (products[i] == products[index] && products[index].isAvailable()){
                return true;
            }
        }
        return false;
    }

    private void addProductIntoStore(Product prod){
       Product pd = new Product(prod);
        Product[] productsArr = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            productsArr[i] = products[i];
        }
        productsArr[products.length] = pd;
        products = productsArr;
    }

    void createUser(){
        Store store = new Store();
        String name;
        String surname;
        String username;
        String password;
        int employeeRank = -1;
        boolean hasMembership = false;

        System.out.println("What type of account do you want?");
        System.out.println("1 - Customer account");
        System.out.println("2 - Employee account");
        int userType = scanner.nextInt();
        while(userType < Main.FIRST_CHOICE || userType > Main.SECOND_CHOICE) {
            System.out.println("Error, try again");
            userType = scanner.nextInt();
        }
        if (userType == Main.FIRST_CHOICE) {
            System.out.println("Do you have membership in the store?");
            System.out.println("Yes / No");
            String input = scanner.next();
            while (!input.equals("Yes")&&!input.equals("No")) {
                System.out.println("Invalid answer, try again");
                input = scanner.next();
            }
            if (input.equals("Yes")){
                hasMembership = true;
            } else{
                hasMembership = false;
            }

        } else {
            System.out.println("What is your employee rank?");
            System.out.println("1 - Normal employee");
            System.out.println("2 - Manager");
            System.out.println("3 - Management member");
            employeeRank = scanner.nextInt();
            while(employeeRank > Main.THIRD_CHOICE || employeeRank < Main.FIRST_CHOICE){
                System.out.println("Error, try again");
                employeeRank = scanner.nextInt();
            }
        }
        System.out.println("Enter your name: ");
        name = scanner.next();
    while (checkName(name)){
        System.out.println("The name can't have numbers in it, try again");
        name = scanner.next();
    }
        System.out.println("Enter your surname: ");
        surname = scanner.next();
        while (checkName(surname)){
            System.out.println("The surname can't have numbers in it, try again");
        }
        System.out.println("Enter a username: ");
        username = scanner.next();
        while(checkForExistingUsername(username)){
            System.out.println("Username exists already, enter a new one");
            username = scanner.next();
        }
        System.out.println("Enter a password: (must be at least 6 digits long)");
        password = scanner.next();
        while(!isValidPass(password)){
            System.out.println("Password is weak, try a stronger one");
            password = scanner.next();
        }
        if (userType == Main.FIRST_CHOICE){
        addCustomer(name,surname,username,password,hasMembership);
        } else {
            addEmployee(name,surname,username,password,employeeRank);
        }
        System.out.println("Account has been created successfully!");
    }
    Customer customerLogin(){
       String username;
       String password;
       boolean usernameExists = false;
       boolean correctPassword = false;

       System.out.println("Enter your username: ");
       username = scanner.next();
       System.out.println("Enter your password: ");
       password = scanner.next();
       if (checkForExistingUsername(username)) {
           usernameExists = true;
       }
       if (checkPassForValidation(password) && usernameExists) {
           correctPassword = true;
       }

       int index = findUser(username,password);



       if(usernameExists && correctPassword && index > -1 && users[index] instanceof Customer){
        return (Customer) users[index];
       }

       return null;
    }

        Employee employeeLogin(){
        String username;
        String password;
        boolean usernameExists = false;
        boolean correctPassword = false;

        System.out.println("Enter your username: ");
        username = scanner.next();
        System.out.println("Enter your password: ");
        password = scanner.next();
        if (checkForExistingUsername(username)) {
            usernameExists = true;
        }
        if (checkPassForValidation(password) && usernameExists) {
            correctPassword = true;
        }

        int index = findUser(username,password);



        if(usernameExists && correctPassword && index > -1 && users[index] instanceof Employee){
            return (Employee) users[index];
        }

        return null;
    }


    private int findUser(String user, String pass) {
        for (int i = 0; i < users.length; i++) {
            user = user.toLowerCase();
            User existingUser = this.users[i];
            if (existingUser.getUsername().equals(user) && existingUser.getPassword().equals(pass)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkPassForValidation(String password) {
        for (int i = 0; i < users.length; i++) {
            User account = users[i];
            if (account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void addCustomer(String name, String surname, String username, String password, boolean isAClubMember) {
        Customer addedCustomer = new Customer(name,surname,username, password,isAClubMember, false, 0, null, 0);
        User[] userArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            userArray[i] = this.users[i];
        }
        userArray[this.users.length] = addedCustomer;
        this.users = userArray;
    }
    private void addEmployee(String name, String surname, String username, String password,int employeeRank) {
        Employee addedEmployee = new Employee(name,surname,username, password,employeeRank);
        User[] userArray = new User[this.users.length + 1];
        for (int i = 0; i < this.users.length; i++) {
            userArray[i] = this.users[i];
        }
        userArray[this.users.length] = addedEmployee;
        this.users = userArray;
    }

    private boolean checkForExistingUsername(String username){
        for (int i = 0; i<users.length; i++){
            String existingUsername = users[i].getUsername();
            existingUsername = existingUsername.toLowerCase();
            username = username.toLowerCase();
            if (existingUsername.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidPass(String password) {
        boolean length = false;

        if (password.length() >= MIN_PASSWORD_LENGTH){
            length = true;
        }
        return length;
    }
        private boolean checkName(String name){
        boolean hasDigit = false;

        for (int i = 0; i<name.length(); i++){
            char ch = name.charAt(i);
            if (Character.isDigit(ch)){
                hasDigit = true;
                break;
            }
        }
            return hasDigit;
    }
}