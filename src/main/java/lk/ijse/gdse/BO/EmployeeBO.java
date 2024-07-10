package lk.ijse.gdse.BO;

import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.Entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO{
    public boolean saveEmployee(Employee employee) throws SQLException;

    public List<EmployeeDTO> getAllEmployee() throws SQLException;

    public boolean deleteEmployee(String id) throws SQLException;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException;

    public Employee searchByTel(String tel) throws SQLException;

    public List<String> getTel() throws SQLException;

    public String getCurrentId() throws SQLException;
}
