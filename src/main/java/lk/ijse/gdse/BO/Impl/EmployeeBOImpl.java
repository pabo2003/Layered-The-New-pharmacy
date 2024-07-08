package lk.ijse.gdse.BO.Impl;

import lk.ijse.gdse.BO.EmployeeBO;
import lk.ijse.gdse.DAO.DAOFactory;
import lk.ijse.gdse.DAO.EmployeeDAO;
import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.Entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public boolean saveEmployee(Employee employee) throws SQLException {
        return employeeDAO.save(new Employee(employee.getEmployeeId(), employee.getName(), employee.getNICNo(),employee.getAddress(), employee.getTel(),employee.getSalary()));
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException {
        ArrayList<EmployeeDTO> allEmployee= new ArrayList<>();
        ArrayList<Employee> all = employeeDAO.getAll();
        for (Employee e : all) {
            allEmployee.add(new EmployeeDTO(e.getEmployeeId(),e.getName(),e.getNICNo(),e.getAddress(),e.getTel(),e.getSalary()));
        }
        return allEmployee;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) throws SQLException {
        return employeeDAO.update(new Employee(employee.getEmployeeId(), employee.getName(), employee.getNICNo(), employee.getAddress(), employee.getTel(), employee.getSalary()));
    }

    @Override
    public Employee searchByTel(String tel) throws SQLException {
        return employeeDAO.searchByTel(tel);
    }

    @Override
    public List<String> getTel() throws SQLException {
        return employeeDAO.getTel();
    }

    @Override
    public String getCurrentId() throws SQLException {
        return employeeDAO.getCurrentId();
    }
}
