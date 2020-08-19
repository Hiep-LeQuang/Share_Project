/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Color;

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
public class ColorController {
    @FXML
    private TableView<Color> tvColor;

    @FXML
    private TableColumn<Color, String> tcColor;

    @FXML
    void btnCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateColor(null);
    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
         Color updateColor = tvColor.getSelectionModel().getSelectedItem();

        if (updateColor == null) {
            selectedColorWarning();
        } else {
            Navigator.getInstance().goToCreateColor(updateColor);
        }
    }
    
    private void selectedColorWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một thương hiệu");
        alert.setHeaderText("Bạn phải chọn một thuiwng hiệu ở trong danh sách");
        alert.showAndWait();
    }

    @FXML
    void txtSearch(ActionEvent event) {

    }
    
    public void initialize(){
        
        tvColor.setItems(Color.selectAll());
        
        tcColor.setCellValueFactory((Color)->{
            return Color.getValue().getColorProperty();
        });   
    }
}
