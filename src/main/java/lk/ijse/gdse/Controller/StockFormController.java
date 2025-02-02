package lk.ijse.gdse.Controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.gdse.BO.ItemBO;
import lk.ijse.gdse.BO.StockBO;
import lk.ijse.gdse.DAO.StockDAO;
import lk.ijse.gdse.DTO.StockDTO;
import lk.ijse.gdse.Util.Regex;
import lk.ijse.gdse.Entity.Stock;
import lk.ijse.gdse.DAO.Impl.StockDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StockFormController {

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
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Stock> tblStock;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;
    StockBO stockBO  = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.STOCK);

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();

        Stock stock = stockBO.searchById(id);

        if (stock != null) {
            txtId.setText(stock.getStockId());
            txtDescription.setText(stock.getDescription());
            txtCategory.setText(stock.getCategory());
        }else {
            new Alert(Alert.AlertType.INFORMATION,"StockDTO is not found!");
        }
    }

    public void initialize(){
        setCellValueFactory();
        loadAllStock();
        getCurrentStockId();

        txtId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        });

        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCategory.requestFocus();
            }
        });

        tblStock.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtId.setText(newSelection.getStockId());
                txtDescription.setText(newSelection.getDescription());
                txtCategory.setText(newSelection.getCategory());
            }
        });



    }

    private void getCurrentStockId() {
        try {
            String currentId = stockBO.getCurrentId();
            String nextOrderId = generateNextStockId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextStockId(String currentId) {
        if (currentId != null && currentId.startsWith("S")) {
            int idNum = Integer.parseInt(currentId.substring(3)) + 1;
            return "S" + String.format("%03d", idNum);
        }
        return "S001";
    }

    private void loadAllStock() {
        ObservableList<Stock> objList = FXCollections.observableArrayList();
        try {
            List<StockDTO> stockList = stockBO.getAllStock();
            for (StockDTO stock : stockList) {
                Stock stock1 = new Stock(
                        stock.getStockId(),
                        stock.getDescription(),
                        stock.getCategory()
                );
                objList.add(stock1);
            }
            tblStock.setItems(objList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        this.colId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
        this.colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
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

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = stockBO.deleteStock(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"StockDTO Deleted!");
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        loadAllStock();
        clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String description = txtDescription.getText();
        String category = txtCategory.getText();

        try {
            if (isValied()) {
                boolean isSaved = stockBO.saveStock(new StockDTO(id, description, category));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "StockDTO saved successfully!").show();
                    clearFields();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Details are not valid.").show();
            }
            getCurrentStockId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllStock();
    }

    private void clearFields() {
        txtDescription.clear();
        txtCategory.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String description = txtDescription.getText();
        String category = txtCategory.getText();

        StockDTO stockDTO = new StockDTO(id, description, category);

        try {
            boolean isUpdate = stockBO.updateStock(stockDTO);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "StockDTO is updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllStock();
        clearFields();
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtId)) return false;
        return true;
    }

    public void txtStockIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtId);
    }
}
