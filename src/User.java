public abstract class User {
    protected String surname;
    protected String name;
    protected String username;
    protected String password;

    public User(String name, String surname,String username,String password){
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
