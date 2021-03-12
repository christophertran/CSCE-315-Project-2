package api;

public class Employee {
    static final String tableName = "employees";
    static final String id_column = "id";
    static final String name_column = "name";
    static final String address_column = "address";
    static final String email_column = "email";

    private Integer id;
    private String name;
    private String address;
    private String email;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Employee(Integer id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }
}
