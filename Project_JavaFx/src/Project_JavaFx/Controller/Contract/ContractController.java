/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Contract;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author lehie
 */
public class ContractController {
    
    @FXML
    private TableView<Contract> tvContract;

    @FXML
    private TableColumn<Contract, String> tcSku;

    @FXML
    private TableColumn<Contract, String> tcCarName;

    @FXML
    private TableColumn<Contract, String> tcReceiptDate;

    @FXML
    private TableColumn<Contract, Integer> tcPrice;

    @FXML
    private TableColumn<Contract, Integer> tcDeposits;

    @FXML
    private TableColumn<Contract, String> tcCustomerName;

    @FXML
    private TableColumn<Contract, String> tcDos;
    
    @FXML
    private TableColumn<Contract, String> tcStatus;
    
    @FXML
    private TextField txtSearch;

    @FXML
    void btnDetails(ActionEvent event) throws IOException, SQLException {
        Contract ShowContract = tvContract.getSelectionModel().getSelectedItem();
        if (ShowContract == null) {
            selectedContractWarning();
        } else {
            Navigator.getInstance().goToDetailsContract(ShowContract);
        }
    }

    @FXML
    void btnSearch(ActionEvent event) {
        String input = txtSearch.getText();
            if (!input.isEmpty()) {
                ObservableList<Contract> contractList = Contract.getContractByCarName(input);
                loadTable(contractList);
            }else{
                loadTable(Contract.selectAll());
            }
    }
    
    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException, SQLException {
        Navigator.getInstance().goToCreateContract(null);
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException, SQLException {
        Contract updateContract = tvContract.getSelectionModel().getSelectedItem();
        if (updateContract == null) {
            selectedContractWarning();
        } else {
            Navigator.getInstance().goToCreateContract(updateContract);
        }
    }

    @FXML
    void btnDelete(ActionEvent event) {
        Contract selectedContract = tvContract.getSelectionModel().getSelectedItem();

        if (selectedContract == null) {
            selectedContractWarning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa một chiếc xe");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa chiếc xe");
            Optional<ButtonType> confirmationResponse = alert.showAndWait();

            if (confirmationResponse.get() == ButtonType.OK) {
                boolean result = Contract.delete(selectedContract);

                if (result) {
                    tvContract.getItems().remove(selectedContract);
                    System.out.println("Hợp Đồng đã được xóa thành công");
                } else {
                    System.out.println("Hợp Đồng xóa không thành công");
                }
            }
        }
    }
    
    private void selectedContractWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một bản hơp đồng");
        alert.setHeaderText("Bạn phải chọn một bản hơp đồng ở trong danh sách");
        alert.showAndWait();
    }
    
    @FXML
    void btnStatus(ActionEvent event) {
        Contract updateStatus = tvContract.getSelectionModel().getSelectedItem();

        if (updateStatus == null) {
            selectedContractWarning();
        } else {
            if(updateStatus.getStatus().equals("Đã Giao")){
                
                updateStatus.setStatus("Đang Lấy Hàng");
            }else{
                updateStatus.setStatus("Đã Giao");
            }
            Contract.update(updateStatus);
        }
    }
    
    public void initialize() {

        tvContract.setItems(Contract.selectAll());

        tcSku.setCellValueFactory((Contract) -> {
            return Contract.getValue().getSkuProperty();
        });
        tcCarName.setCellValueFactory((Contract) -> {
            return Contract.getValue().getCarNameProperty();
        });
        tcReceiptDate.setCellValueFactory((Contract) -> {
            return Contract.getValue().getProductReceiptDateProperty();
        });
        tcPrice.setCellValueFactory((Contract) -> {
            return Contract.getValue().getPriceProperty();
        });
        tcDeposits.setCellValueFactory((Contract) -> {
            return Contract.getValue().getDepositsProperty();
        });
        tcCustomerName.setCellValueFactory((Contract) -> {
            return Contract.getValue().getCustomerNameProperty();
        });
        tcDos.setCellValueFactory((Contract) -> {
            return Contract.getValue().getDateOfSaleProperty();
        });
        tcStatus.setCellValueFactory((Contract) -> {
            return Contract.getValue().getStatusProperty();
        });
    }

    public void initialize(List<Contract> contracts) {
         tvContract.setItems((ObservableList<Contract>) contracts);

        tcSku.setCellValueFactory((Contract) -> {
            return Contract.getValue().getSkuProperty();
        });
        tcCarName.setCellValueFactory((Contract) -> {
            return Contract.getValue().getCarNameProperty();
        });
        tcReceiptDate.setCellValueFactory((Contract) -> {
            return Contract.getValue().getProductReceiptDateProperty();
        });
        tcPrice.setCellValueFactory((Contract) -> {
            return Contract.getValue().getPriceProperty();
        });
        tcDeposits.setCellValueFactory((Contract) -> {
            return Contract.getValue().getDepositsProperty();
        });
        tcCustomerName.setCellValueFactory((Contract) -> {
            return Contract.getValue().getCustomerNameProperty();
        });
        tcDos.setCellValueFactory((Contract) -> {
            return Contract.getValue().getDateOfSaleProperty();
        });
        tcStatus.setCellValueFactory((Contract) -> {
            return Contract.getValue().getStatusProperty();
        });
    }
    
    void loadTable(ObservableList<Contract> contracts) {
        tvContract.setItems(contracts);

        tcSku.setCellValueFactory((Contract) -> {
            return Contract.getValue().getSkuProperty();
        });
        tcCarName.setCellValueFactory((Contract) -> {
            return Contract.getValue().getCarNameProperty();
        });
        tcReceiptDate.setCellValueFactory((Contract) -> {
            return Contract.getValue().getProductReceiptDateProperty();
        });
        tcPrice.setCellValueFactory((Contract) -> {
            return Contract.getValue().getPriceProperty();
        });
        tcDeposits.setCellValueFactory((Contract) -> {
            return Contract.getValue().getDepositsProperty();
        });
        tcCustomerName.setCellValueFactory((Contract) -> {
            return Contract.getValue().getCustomerNameProperty();
        });
        tcDos.setCellValueFactory((Contract) -> {
            return Contract.getValue().getDateOfSaleProperty();
        });
        tcStatus.setCellValueFactory((Contract) -> {
            return Contract.getValue().getStatusProperty();
        });
    }
}
