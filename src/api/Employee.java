package api;

public class Employee {
    static final String tableName = "employees";

    Integer id;
    String name;
    String address;
    String email;


    public Employee(Integer id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
