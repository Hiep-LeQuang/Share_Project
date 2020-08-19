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
    void btnCUAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateAccount();
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
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Thông Báo");
                    alert1.setHeaderText("Tài khoản đã được xóa thành công ?");
                    alert1.show();
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Thông Báo");
                    alert2.setHeaderText("Xóa tài khoản không thành công ?");
                    alert2.show();
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
