import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    // Main menu
    public static final int CREATE_ACCOUNT = 1;
    public static final int LOGIN = 2;
    public static final int EXIT = 3;
    // A menu for employee
    public static final int PRINT_ALL_CUSTOMERS = 1;
    public static final int PRINT_ALL_MEMBERSHIP_CUSTOMERS = 2;
    public static final int PRINT_ALL_CUSTOMERS_THAT_HAS_PURCHASED_ONCE= 3;
    public static final int PRINT_CUSTOMER_THAT_HAS_HIGHEST_PURCHASE = 4;
    public static final int ADD_NEW_PRODUCT = 5;
    public static final int CHANGE_AVAILABILITY_OF_PRODUCT = 6;
    public static final int MAKE_A_PURCHASE = 7;
    public static final int LOGOUT = 8;
    //Additional settings
    public static final int FIRST_CHOICE = 1;
    public static final int SECOND_CHOICE = 2;
    public static final int THIRD_CHOICE = 3;


    public static void main(String[] args) {
        Store store = new Store();
        mainMenu(store);
    }

    public static void mainMenu(Store store) {
        Customer cs;
        Employee employ;
        System.out.println("1 - Create an account");
        System.out.println("2 - Login");
        System.out.println("3 - Exit");
        int choice = scanner.nextInt();
        switch (choice) {
            case CREATE_ACCOUNT:
                store.createUser();
                break;
            case LOGIN:
                System.out.println("What type of account do you want to log into?");
                System.out.println("1 - Customer account");
                System.out.println("2 - Employee account");
                int userInp = scanner.nextInt();
                while (userInp < FIRST_CHOICE || userInp > SECOND_CHOICE) {
                    System.out.println("Invalid answer, try again");
                    userInp = scanner.nextInt();
                }
                if (userInp == FIRST_CHOICE) {
                    cs = store.customerLogin();
                    if (cs == null) {
                        System.out.println("Error,try again");
                        System.out.println();
                        mainMenu(store);
                    } else {
                        System.out.println(cs.toString());
                        store.printAllProducts();

                    }
                } else {
                    employ = store.employeeLogin();
                    if (employ == null) {
                        System.out.println("Error,try again");
                        System.out.println();
                        mainMenu(store);
                    } else {
                        System.out.println(employ.toString());
                        printEmployeeMenu(employ,store);
                    }

                }
                break;
            case EXIT:
                break;
        }
    }
    public static void printEmployeeMenu(Employee employ,Store store){
        System.out.println("1 - Print all customers");
        System.out.println("2 - Print all customers with membership");
        System.out.println("3 - Print all customers that purchased once");
        System.out.println("4 - Print the customer with the highest purchase");
        System.out.println("5 - Add a new product to the store");
        System.out.println("6 - Change availability for a product");
        System.out.println("7 - Make a purchase as an employee");
        System.out.println("8 - Logout");
        int employeeSelection = scanner.nextInt();
        switch (employeeSelection) {
            case PRINT_ALL_CUSTOMERS:
                store.printAllCustomers();
                break;
                case PRINT_ALL_MEMBERSHIP_CUSTOMERS:
                    store.printAllMembershipCustomers();
                    break;
            case PRINT_ALL_CUSTOMERS_THAT_HAS_PURCHASED_ONCE:
                store.printCustomersThatPurchasedOnce();
                break;
            case PRINT_CUSTOMER_THAT_HAS_HIGHEST_PURCHASE:
                store.printCustomerWithHighestPurchase();
                break;
            case ADD_NEW_PRODUCT:
                store.createProduct();
                break;
            case CHANGE_AVAILABILITY_OF_PRODUCT:
                store.changeAvailabilityOfProduct();
                break;
            case MAKE_A_PURCHASE:
                store.printAllProducts();
                break;
            case LOGOUT:
                mainMenu(store);
                break;
        }

    }
}