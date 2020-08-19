/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Account;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Project_JavaFx.Controller.Navigator;
import java.io.IOException;

/**
 *
 * @author lehie
 */
public class AccountController {
     @FXML
    private TableView<Account> tvAccount;

    @FXML
    private TableColumn<Account, String> tcAccount;

    @FXML
    private TableColumn<Account, String> tcPass;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnDelete(ActionEvent event) {
        Account selectedAccount = tvAccount.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            selectedAccountWarning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa tài khoản");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa tài khoản này ?");
            Optional<ButtonType> confirmationResponse = alert.showAndWait();

            if (confirmationResponse.get() == ButtonType.OK) {
                boolean result = Account.delete(selectedAccount);

                if (result) {
                    tvAccount.getItems().remove(selectedAccount);
                    System.out.println("Tài khoản đã được xóa thành công");
                } else {
                    System.out.println("Tài khoản xóa không thành công");
                }
            }
        }
    }
    
    private void selectedAccountWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một tài khoản");
        alert.setHeaderText("Bạn phải chọn một tài khoản ở trong danh sách");
        alert.showAndWait();
    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

    @FXML
    void txtSearch(ActionEvent event) {

    }
    
    public void initialize() {

        tvAccount.setItems(Account.selectAll());

        tcAccount.setCellValueFactory((Account) -> {
            return Account.getValue().getUserNameProperty();
        });

        tcPass.setCellValueFactory((Account) -> {
            return Account.getValue().getPasswordProperty();
        });
    }
}
