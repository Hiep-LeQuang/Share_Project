/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Main;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 *
 * @author lehie
 */
public class MainController implements Initializable {

    public static int flag = 0;

    @FXML
    void btnLogo(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnProduct(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Car/FormCar.fxml"));
        secPane.getChildren().add(newLoadedPane);
        flag = 1;
    }

    @FXML
    void btnBrand(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Brand/FormBrand.fxml"));
        secPane.getChildren().add(newLoadedPane);
        flag = 3;
    }

    @FXML
    void btnCategory(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Category/FormCategory.fxml"));
        secPane.getChildren().add(newLoadedPane);
        flag = 4;
    }

    @FXML
    void btnColor(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Color/FormColor.fxml"));
        secPane.getChildren().add(newLoadedPane);
        flag = 5;
    }

    @FXML
    void btnCustomer(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Customer/FormCustomer.fxml"));
        secPane.getChildren().add(newLoadedPane);
        flag = 6;
    }

    @FXML
    void btnContract(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Contract/FormContract.fxml"));
        secPane.getChildren().add(newLoadedPane);
        flag = 2;
    }

    @FXML
    void btnAccount(ActionEvent event) throws IOException {
        secPane.getChildren().clear();
        Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Account/FormAccount.fxml"));
        secPane.getChildren().add(newLoadedPane);
    }

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();
    }

    @FXML
    private Pane secPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (flag == 1) {

        }
        switch (flag) {
            case 1:
                secPane.getChildren().clear();
                Pane newLoadedPane;
                try {
                    newLoadedPane = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Car/FormCar.fxml"));
                    secPane.getChildren().add(newLoadedPane);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    secPane.getChildren().clear();
                    Pane newLoadedPane1 = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Contract/FormContract.fxml"));
                    secPane.getChildren().add(newLoadedPane1);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                break;
            case 3:
                try {
                    secPane.getChildren().clear();
                    Pane newLoadedPane3 = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Brand/FormBrand.fxml"));
                    secPane.getChildren().add(newLoadedPane3);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                break;
            case 4:
                try {
                    secPane.getChildren().clear();
                    Pane newLoadedPane4 = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Category/FormCategory.fxml"));
                    secPane.getChildren().add(newLoadedPane4);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                break;
            case 5:
                try {
                    secPane.getChildren().clear();
                    Pane newLoadedPane5 = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Color/FormColor.fxml"));
                    secPane.getChildren().add(newLoadedPane5);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                break;
            case 6:
                try {
                    secPane.getChildren().clear();
                    Pane newLoadedPane6 = FXMLLoader.load(getClass().getResource("/Project_JavaFx/FXML/Customer/FormCustomer.fxml"));
                    secPane.getChildren().add(newLoadedPane6);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                break;

        }
    }

}
