package lk.ijse.gdse.DAO.Impl;

import lk.ijse.gdse.DAO.EmployeeDAO;
import lk.ijse.gdse.DAO.SQLUtil;
import lk.ijse.gdse.Entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException {
        /*String sql = "INSERT INTO Employee VALUES(?,?,?,?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getNICNo());
        pstm.setObject(4, employee.getAddress());
        pstm.setObject(5, employee.getTel());
        pstm.setObject(6, employee.getSalary());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?)",employee.getEmployeeId(),employee.getName(),employee.getNICNo(),employee.getAddress(),employee.getTel(),employee.getSalary());
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        /*String sql = "SELECT * FROM Employee";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<Employee> employeesList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nicNo = resultSet.getString(3);
            String address = resultSet.getString(4);
            String tel = resultSet.getString(5);
            double salary = resultSet.getDouble(6);

            Employee employee = new Employee(id, name, nicNo, address, tel, salary);
            employeesList.add(employee);
        }
        return employeesList;*/
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee");
        while (rst.next()){
            Employee employee = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)
            );
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        /*String sql = "DELETE FROM Employee WHERE employeeId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM Employee WHERE employeeId = ?",id);
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        /*String sql = "UPDATE Employee SET name = ? , nicNo = ? , address = ? , tel = ? , salary = ? WHERE employeeId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

                                                                                                                                            pstm.setObject(1,employee.getName());
                                                                                                                                            pstm.setObject(2,employee.getNICNo());
                                                                                                                                            pstm.setObject(3,employee.getAddress());
                                                                                                                                            pstm.setObject(4,employee.getTel());
                                                                                                                                            pstm.setObject(5,employee.getSalary());
                                                                                                                                            pstm.setObject(6,employee.getEmployeeId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE Employee SET name = ? , nicNo = ? , address = ? , tel = ? , salary = ? WHERE employeeId = ?",employee.getName(),employee.getNICNo(),employee.getAddress(),employee.getTel(),employee.getSalary(),employee.getEmployeeId());
    }

    @Override
    public Employee searchByTel(String tel) throws SQLException {
        /*String sql = "SELECT * FROM Employee WHERE tel = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, tel);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String tel1 = resultSet.getString("tel");
            String employeeId = resultSet.getString("employeeId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String nicNo = resultSet.getString("NICNo");
            double salary = resultSet.getDouble("salary");

            Employee employee = new Employee(employeeId, name, nicNo, address, tel1, salary);
            return employee;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Employee WHERE tel = ?", tel + "");
        if (rst.next()) {
            return new Employee(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDouble(6));
        }
        return null;
    }

    @Override
    public List<String> getTel() throws SQLException {
        /*String sql = "SELECT tel from Employee";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> employeeList = new ArrayList<>();

        while (resultSet.next()){
            String tel = resultSet.getString(1);
            employeeList.add(tel);
        }
        return employeeList;*/
        List<String> telList = new ArrayList<>();

        try (ResultSet rst = SQLUtil.execute("SELECT tel FROM Employee")) {
            while (rst.next()) {
                String tel = rst.getString("tel");
                telList.add(tel);
            }
        }

        return telList;
    }

    @Override
    public String getCurrentId() throws SQLException {
        /*String sql = "SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String employeeId = resultSet.getString(1);
            return employeeId;
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT employeeId FROM Employee ORDER BY employeeId DESC LIMIT 1");
        if(rst.next()) {
            String employeeId = rst.getString(1);
            return employeeId;
        }
        return null;
    }
}
