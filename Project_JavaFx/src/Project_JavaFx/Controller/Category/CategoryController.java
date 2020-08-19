/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Category;

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
public class CategoryController {
    @FXML
    private TableView<Category> tvCategory;

    @FXML
    private TableColumn<Category, String> tcCategory;
    
    @FXML
    private TableColumn<Category, String> tcStatus;
    
    @FXML
    void btnCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateCategory(null);
    }

     @FXML
    void btnStatus(ActionEvent event) {
        Category updateStatus = tvCategory.getSelectionModel().getSelectedItem();

        if (updateStatus == null) {
            selectedCategoryWarning();
        } else {
            if(updateStatus.getStatus().equals("Đang Kinh Doanh")){
                
                updateStatus.setStatus("Ngừng Kinh Doanh");
            }else{
                updateStatus.setStatus("Đang Kinh Doanh");
            }
            Category.update(updateStatus);
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
        Category updateCategory = tvCategory.getSelectionModel().getSelectedItem();

        if (updateCategory == null) {
            selectedCategoryWarning();
        } else {
            Navigator.getInstance().goToCreateCategory(updateCategory);
        }
    }
    
    @FXML
    void txtSearch(ActionEvent event) {

    }
    
    @FXML
    void btnSearch(ActionEvent event) {

    }
    
    private void selectedCategoryWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một hợp đồng");
        alert.setHeaderText("Bạn phải chọn một hợp đồng ở trong danh sách");
        alert.showAndWait();
    }
    
    public void initialize(){
        
        tvCategory.setItems(Category.selectAll());
        
        tcCategory.setCellValueFactory((Category)->{
            return Category.getValue().getCategoryProperty();
        });
        
        tcStatus.setCellValueFactory((Category)->{
            return Category.getValue().getStatusProperty();
        }); 
    }
}
