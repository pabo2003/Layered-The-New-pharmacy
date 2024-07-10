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
import lk.ijse.gdse.BO.ItemBO;
import lk.ijse.gdse.BO.StockBO;
import lk.ijse.gdse.DTO.ItemDTO;
import lk.ijse.gdse.Util.Regex;
import lk.ijse.gdse.Entity.Item;
import lk.ijse.gdse.Entity.Stock;
import lk.ijse.gdse.DAO.Impl.StockDAOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemFormController {

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
    private TableColumn<Item, String> colCode;

    @FXML
    private TableColumn<Item, String> colDescription;

    @FXML
    private TableColumn<Item, Integer> colQtyOnHand;

    @FXML
    private TableColumn<Item, Double> colUnitPrice;

    @FXML
    private TableColumn<Item, String> colStockId;

    @FXML
    private ComboBox<String> comStockId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;
    ItemBO itemBO  = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM);
    StockBO stockBO  = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.STOCK);

    @FXML
    private void initialize() throws SQLException {
        setCellValueFactory();
        loadAllItems();
        getStockIds();
        getCurrentItemId();
        tblItem.setItems(FXCollections.observableArrayList()); 
        loadAllItems();
        txtCode.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        });

        txtDescription.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtQtyOnHand.requestFocus();
            }
        });

        txtQtyOnHand.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtUnitPrice.requestFocus();
            }
        });

        tblItem.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtCode.setText(newSelection.getItemId());
                txtDescription.setText(newSelection.getDescription());
                txtQtyOnHand.setText(String.valueOf(newSelection.getQtyOnHand()));
                txtUnitPrice.setText(String.valueOf(newSelection.getUnitPrice()));
                comStockId.setValue(newSelection.getStockId());
            }
        });
    }

    private void getCurrentItemId() {
        try {
            String currentId = itemBO.getCurrentId();
            String nextOrderId = generateNextItemId(currentId);
            txtCode.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextItemId(String currentId) {
        if (currentId != null && currentId.startsWith("I")) {
            int idNum = Integer.parseInt(currentId.substring(3)) + 1;
            return "I" + String.format("%03d", idNum);
        }
        return "I001";
    }

    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colStockId.setCellValueFactory(new PropertyValueFactory<>("stockId"));
    }

    private void loadAllItems() throws SQLException {
        ObservableList<Item> obList = FXCollections.observableArrayList();
        /*try {
            List<ItemDTO> itemList = itemBO.getAllItem();
            obList.addAll((Item) itemList);
            tblItem.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    try {
        List<ItemDTO> itemList = itemBO.getAllItem();
        for (ItemDTO item : itemList) {
            Item item1 = new Item(item.getItemId(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getStockId());
            obList.add(item1);
        }
        tblItem.setItems(obList);
    }catch (SQLException e){
        throw new RuntimeException(e);
    }
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
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtCode.getText();

        try {
            boolean isDeleted = itemBO.deleteItem(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "ItemDTO Deleted!");
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllItems();
        clearFields();
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String itemId = txtCode.getText();
        String description = txtDescription.getText();
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        String stockId = comStockId.getValue();

        try {
            if (isValied()) {
                boolean isSaved = itemBO.saveItem(new ItemDTO(itemId, description, unitPrice, qtyOnHand, stockId));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ItemDTO saved successfully!").show();
                    clearFields();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Details are not valid.").show();
            }
            getCurrentItemId();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllItems();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String stockId = comStockId.getValue();
        String itemId = txtCode.getText();
        String description = txtDescription.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        ItemDTO itemDTO = new ItemDTO(itemId, description, unitPrice, qtyOnHand, stockId);
        try {
            boolean isUpdate = itemBO.updateItem(itemDTO);
            if (isUpdate) {
                showAlert(Alert.AlertType.CONFIRMATION, "ItemDTO is updated!");
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while updating ItemDTO: " + e.getMessage());
        }
        loadAllItems();
        clearFields();
    }

    @FXML
    void comStockIdOnAction(ActionEvent event) {
        String id = comStockId.getValue();
        try {
            Stock stock = stockBO.searchById(id);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while searching for stock: " + e.getMessage());
        }
    }

    private void getStockIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> idList = stockBO.getId();
            obList.addAll(idList);
            comStockId.setItems(obList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching stock IDs: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        comStockId.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtCode)) return false;
        if (!Regex.setTextColor(lk.ijse.gdse.Util.TextField.AMOUNT,txtUnitPrice)) return false;
        return true;
    }

    public void txtItemIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.ID,txtCode);
    }


    public void txtItemUnitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.gdse.Util.TextField.AMOUNT,txtUnitPrice);
    }
}
