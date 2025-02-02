package lk.ijse.gdse.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.BO.BOFactory;
import lk.ijse.gdse.BO.EmployeeBO;
import lk.ijse.gdse.DTO.EmployeeDTO;
import lk.ijse.gdse.Entity.Item;
import lk.ijse.gdse.Util.Regex;
import lk.ijse.gdse.Entity.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFormController {

    public TableView tblCustomer;
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colNICNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNICNo;

    @FXML
    private TextField txtName;

    @FXML

    private TextField txtTel;

    @FXML
    private TextField txtSalary;

    EmployeeBO employeeBO  = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE);

    public void initialize() {
       setCellValueFactory();
       loadAllEmployee();
       getCurrentEmployeeId();

        txtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtNICNo.requestFocus();
            }
        });

        txtNICNo.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });
        txtAddress.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtTel.requestFocus();
            }
        });
        txtTel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtSalary.requestFocus();
            }
        });

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getEmployeeId());
                txtName.setText(newSelection.getName());
                txtNICNo.setText(newSelection.getNICNo());
                txtAddress.setText(newSelection.getAddress());
                txtTel.setText(newSelection.getTel());
                txtSalary.setText(String.valueOf(newSelection.getSalary()));
            }
        });
    }

    private void getCurrentEmployeeId() {
        try {
            String currentId = employeeBO.getCurrentId();
            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null && currentId.startsWith("E")) {
            int idNum = Integer.parseInt(currentId.substring(3)) + 1;
            return "E" + String.format("%03d", idNum);
        }
        return "E001";
    }

    private void loadAllEmployee() {
        ObservableList<Employee> objList = FXCollections.observableArrayList();
        try {
            List<EmployeeDTO> employeeList = employeeBO.getAllEmployee();
            for (EmployeeDTO employee : employeeList) {
                Employee employee1 = new Employee(
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getNICNo(),
                        employee.getAddress(),
                        employee.getTel(),
                        employee.getSalary()
                );
                objList.add(employee1);
            }
            tblEmployee.setItems(objList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colNICNo.setCellValueFactory(new PropertyValueFactory<>("NICNo"));
        this.colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        this.colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }


    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String tel = txtTel.getText();

        Employee employee = employeeBO.searchByTel(tel);

        if (employee != null) {
            txtId.setText(employee.getEmployeeId());
            txtName.setText(employee.getName());
            txtNICNo.setText(employee.getNICNo());
            txtAddress.setText(employee.getAddress());
            txtTel.setText(employee.getTel());
            txtSalary.setText(String.valueOf(employee.getSalary()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "EmployeeDTO is not found!");
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        /*ObservableList<Employee> selectedRows = tblEmployee.getSelectionModel().getSelectedItems();
        List<Employee> employeeToDelete = new ArrayList<>(selectedRows);
        try {
            for (Employee employee : employeeToDelete) {
                boolean isDeleted = employeeBO.deleteEmployee(employee.getEmployeeId());
                if (isDeleted) {
                    tblEmployee.getItems().remove(employee);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failed to delete employee: " + employee.getEmployeeId());
                }
            }
            showAlert(Alert.AlertType.CONFIRMATION, "Employees deleted successfully!");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while deleting items: " + e.getMessage());
        }
        loadAllEmployee();*/
        String id = txtId.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO Deleted!");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllEmployee();
        clearFields();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/resources/view/dashboard_form.fxml"));
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(rootNode));
            stage.setTitle("Dashboard Form");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nicNo = txtNICNo.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        try {
            if (isValied()) {
                boolean isSaved = employeeBO.saveEmployee(new Employee(id, name, nicNo, address, tel, salary));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO saved successfully!").show();
                    clearFields();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Details are not valid.").show();
            }
            getCurrentEmployeeId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllEmployee();
    }


    private void clearFields() {
        txtName.clear();
        txtNICNo.clear();
        txtAddress.clear();
        txtTel.clear();
        txtSalary.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nicNo = txtNICNo.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, nicNo, address, tel, salary);

        try {
            boolean isUpdate = employeeBO.updateEmployee(employeeDTO);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "EmployeeDTO is updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllEmployee();
        clearFields();
    }

    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.NIC,txtNICNo)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.TEL,txtTel)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.SALARY,txtSalary)) return false;
        return true;
    }
    public void txtEmployeeIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtId);
    }

    public void txtEmployeeNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.NAME,txtName);
    }

    public void txtEmployeeNICOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.NIC,txtNICNo);
    }

    public void txtEmployeeTelOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.TEL,txtTel);
    }

    public void txtEmployeeSalaryOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.SALARY,txtSalary);
    }

}
