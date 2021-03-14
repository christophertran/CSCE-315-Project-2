package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * Returns employee by ID if they exist in the database
     * @param id ID of employee wanted
     * @return Employee object is employee exists, null if they don't exist
     * @throws SQLException Throws SQL Exception
     */
    public static Employee getEmployeeByID(Integer id) throws SQLException {
        HashMap<String, String> constraints = new HashMap<>();
        constraints.put(Employee.id_column, id.toString());
        ArrayList<HashMap<String, String>> employeeResult = QueryBuilder.executeQuery(QueryBuilder.buildSelectionQuery(Employee.tableName, constraints, null, null));

        if (employeeResult.size() == 0)
        {
            return null;
        }

        return new Employee(Integer.parseInt(employeeResult.get(0).get(Employee.id_column)), employeeResult.get(0).get(Employee.name_column), employeeResult.get(0).get(Employee.address_column), employeeResult.get(0).get(Employee.email_column));
    }
}
