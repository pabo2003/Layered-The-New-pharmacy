package lk.ijse.gdse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.BO.BOFactory;
import lk.ijse.gdse.BO.CustomerBO;
import lk.ijse.gdse.DTO.CustomerDTO;
import lk.ijse.gdse.Util.Regex;
import lk.ijse.gdse.Entity.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {
    @FXML
    private TableView<Customer> tblCustomer;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colNICNo;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtNICNo;
    @FXML
    private TextField txtTel;
    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER);

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String tel = txtId.getText();

        Customer customer = customerBO.searchByTel(tel);

        if (customer != null) {
            txtId.setText(customer.getCuId());
            txtName.setText(customer.getName());
            txtNICNo.setText(customer.getNicNo());
            txtAddress.setText(customer.getAddress());
            txtTel.setText(customer.getTel());
        }else {
            new Alert(Alert.AlertType.INFORMATION,"CustomerDTO is not found!");
        }
    }

    public void initialize(){
        setCellValueFactory();
        loadAllCustomers();
        getCurrentCustomerId();

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

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getCuId());
                txtName.setText(newSelection.getName());
                txtNICNo.setText(newSelection.getNicNo());
                txtAddress.setText(newSelection.getAddress());
                txtTel.setText(newSelection.getTel());
            }
        });
    }

    private void getCurrentCustomerId() {
        try {
            String currentId = customerBO.getCurrentId();
            String nextOrderId = generateNextOrderId(currentId);
            System.out.println(nextOrderId);
            txtId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null && currentId.startsWith("C")) {
            int idNum = Integer.parseInt(currentId.substring(3)) + 1;
            return "C" + String.format("%03d", idNum);
        }
        return "C001";
    }

    private void loadAllCustomers() {
        ObservableList<Customer> objList = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomer();
            for (CustomerDTO customer : customerList) {
                Customer customer1 = new Customer(
                        customer.getCuId(),
                        customer.getName(),
                        customer.getNicNo(),
                        customer.getAddress(),
                        customer.getTel()
                );
                objList.add(customer1);
            }
            tblCustomer.setItems(objList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("cuId"));
        this.colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.colNICNo.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        this.colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        this.colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nicNo = txtNICNo.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        try {
            if (isValied()) {
                boolean isSaved = customerBO.saveCustomer(new CustomerDTO(id, name, nicNo, address, tel));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "CustomerDTO saved successfully!").show();
                    clearFields();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Details are not valid.").show();
            }
            getCurrentCustomerId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllCustomers();
        clearFields();
    }
  
    private void clearFields() {
        txtName.clear();
        txtNICNo.clear();
        txtAddress.clear();
        txtTel.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nicNo = txtNICNo.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        CustomerDTO customerDTO = new CustomerDTO(id,name,nicNo,address,tel);

        try {
            boolean isUpdate = customerBO.updateCustomer(customerDTO);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION,"CustomerDTO is updated!").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadAllCustomers();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = customerBO.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"CustomerDTO Deleted!");
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadAllCustomers();
        clearFields();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDashBoardOnAction(ActionEvent event) {
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

    private boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.NIC,txtNICNo)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.TEL,txtTel)) return false;
        return true;
    }

    public void txtCustomerIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtId);
    }

    public void txtCustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.NAME,txtName);
    }

    public void txtCustomerNICOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.NIC,txtNICNo);
    }

    public void txtCustomerTelOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.TEL,txtTel);
    }
}
