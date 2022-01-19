public class Employee extends User {
    private int rank;
    public static final double EMPLOYEE_DISCOUNT = 0.1;
    public static final double MANAGER_DISCOUNT = 0.2;
    public static final double MANAGEMENT_MEMBER_DISCOUNT = 0.3;

    public Employee(String name, String surname, String username, String password, int rank){
        super(surname,name,username,password);
        this.surname = surname;
        this.name = name;
        this.username = username;
        this.password = password;
        this.rank = rank;
    }
    public String getUsername(){
        return username;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        String result = "";
        if (rank == Main.FIRST_CHOICE){
            result = "Welcome, " + name + " " + surname + " (Regular Employee)";
        } else if (rank == Main.SECOND_CHOICE){
            result = "Welcome, " + name + " " + surname + " (Manager)";
        } else if (rank == Main.THIRD_CHOICE){
            result = "Welcome, " + name + " " + surname + " (Management Member)";
        }
        return result;
    }
}
