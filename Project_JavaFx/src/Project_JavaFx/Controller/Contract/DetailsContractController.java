/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Contract;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author lehie
 */
public class DetailsContractController {

    @FXML
    private Text txtSku;

    @FXML
    private Text txtCarName;

    @FXML
    private Text txtDos;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtRD;

    @FXML
    private Text txtPrice;

    @FXML
    private Text txtdeposits;

    @FXML
    private Text txtAccountant;

    @FXML
    private Text txtCustomer;

    @FXML
    private Text txtPhone;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtStatus;

    @FXML
    private TextArea txtNote;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }
    
    public void initialize(Contract contract) {
        txtAccountant.setText(contract.getAccountant());
        txtAddress.setText(contract.getAddress());
        txtCarName.setText(contract.getCarName());
        txtCustomer.setText(contract.getCustomerName());
        txtDos.setText(contract.getDateOfSale());
        txtEmail.setText(contract.getEmail());
        txtNote.setText(contract.getNote());
        txtPhone.setText(contract.getPhone());
        txtPrice.setText(contract.getPrice().toString());
        txtRD.setText(contract.getProductReceiptDate());
        txtSku.setText(contract.getSku());
        txtStatus.setText(contract.getStatus());
        txtdeposits.setText(contract.getDeposits().toString());
    }    

    public void initialize(List<String> errorList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
