/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Brand;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author lehie
 */
public class BrandController {

    @FXML
    private TableView<Brand> tvBrand;

    @FXML
    private TableColumn<Brand, String> tcBrand;

    @FXML
    private TableColumn<Brand, String> tcStatus;

    @FXML
    void btnCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateBrand(null);
    }

    @FXML
    void btnStatus(ActionEvent event) {
        Brand updateStatus = tvBrand.getSelectionModel().getSelectedItem();

        if (updateStatus == null) {
            selectedBrandWarning();
        } else {
            if (updateStatus.getStatus().equals("Đang Kinh Doanh")) {

                updateStatus.setStatus("Ngừng Kinh Doanh");
            } else {
                updateStatus.setStatus("Đang Kinh Doanh");
            }
            Brand.update(updateStatus);
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
        Brand updateBrand = tvBrand.getSelectionModel().getSelectedItem();

        if (updateBrand == null) {
            selectedBrandWarning();
        } else {
            Navigator.getInstance().goToCreateBrand(updateBrand);
        }
    }

    @FXML
    void txtSearch(ActionEvent event) {

    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

    private void selectedBrandWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một thương hiệu");
        alert.setHeaderText("Bạn phải chọn một thương hiệu ở trong danh sách");
        alert.showAndWait();
    }

    public void initialize() {

        tvBrand.setItems(Brand.selectAll());

        tcBrand.setCellValueFactory((Brand) -> {
            return Brand.getValue().getBrandProperty();
        });

        tcStatus.setCellValueFactory((Brand) -> {
            return Brand.getValue().getStatusProperty();
        });
    }
    
}
