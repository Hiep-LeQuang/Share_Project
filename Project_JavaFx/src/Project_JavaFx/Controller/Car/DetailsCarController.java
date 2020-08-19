/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Car;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

/**
 *
 * @author lehie
 */
public class DetailsCarController {
    @FXML
    private Text txtFuel;

    @FXML
    private Text txtCarName;

    @FXML
    private Text txtBrand;

    @FXML
    private Text txtCategory;

    @FXML
    private Text txtYear;

    @FXML
    private Text txtGear;

    @FXML
    private Text txtSku;

    @FXML
    private Text txtSeat;

    @FXML
    private Text txtColor;

    @FXML
    private Text txtPrice;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }
    
    public void initialize(Car car) throws SQLException {

            txtSku.setText(car.getSku());
            txtCarName.setText(car.getCarName());
            txtYear.setText(car.getYearOfManufacture().toString());
            txtPrice.setText(car.getPrice().toString());
            txtBrand.setText(car.getBrand());
            txtBrand.setText(car.getCategory());
            txtColor.setText(car.getColor());
            txtFuel.setText(car.getFuelUsed());
            txtGear.setText(car.getGear());
            txtSeat.setText(car.getSeat());
            txtCategory.setText(car.getCategory());
    }
}
