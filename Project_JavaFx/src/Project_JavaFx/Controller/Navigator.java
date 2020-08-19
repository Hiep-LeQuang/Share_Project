/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller;

import Project_JavaFx.Controller.Customer.CUCustomerController;
import Project_JavaFx.Controller.Customer.Customer;
import Project_JavaFx.Controller.Contract.CUContractController;
import Project_JavaFx.Controller.Contract.Contract;
import Project_JavaFx.Controller.Color.CUColorController;
import Project_JavaFx.Controller.Color.Color;
import Project_JavaFx.Controller.Car.Car;
import Project_JavaFx.Controller.Car.CUCarController;
import Project_JavaFx.Controller.Category.CUCategoryController;
import Project_JavaFx.Controller.Category.Category;
import Project_JavaFx.Controller.Brand.CUBrandController;
import Project_JavaFx.Controller.Brand.Brand;
import Project_JavaFx.Controller.Car.DetailsCarController;
import Project_JavaFx.Controller.Contract.DetailsContractController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lehie
 */
public class Navigator {
    public static final String LOGIN_FXML = "/Project_JavaFx/FXML/Login.fxml";
    public static final String MAIN_FXML = "/Project_JavaFx/FXML/FormMain.fxml";
    public static final String CREATE_CAR_FXML = "/Project_JavaFx/FXML/Car/FormCUCar.fxml";
    public static final String CREATE_CONTRACT_FXML = "/Project_JavaFx/FXML/Contract/FormCUContract.fxml";
    public static final String CREATE_CATEGORY_FXML = "/Project_JavaFx/FXML/Category/FormCUCategory.fxml";
    public static final String CREATE_BRAND_FXML = "/Project_JavaFx/FXML/Brand/FormCUBrand.fxml";
    public static final String CREATE_COLOR_FXML = "/Project_JavaFx/FXML/Color/FormCUColor.fxml";
    public static final String CREATE_CUSTOMER_FXML = "/Project_JavaFx/FXML/Customer/FormCUCustomer.fxml";
    public static final String CREATE_ACCOUNT_FXML = "/Project_JavaFx/FXML/Account/FormCUAccount.fxml";
    public static final String CREATE_DETAILSCAR_FXML = "/Project_JavaFx/FXML/Car/FormDetailsCar.fxml";
    public static final String CREATE_DETAILSCONTRACT_FXML = "/Project_JavaFx/FXML/Contract/FormDetailsContract.fxml";
    
    private FXMLLoader loader;
    private Stage stage = null;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private static Navigator nav = null;
    
    private Navigator(){
        
    }
    
    public static Navigator getInstance(){
        if(nav == null){
            nav = new Navigator();
        }
        return nav;
    }
    
    private void goTo(String fxml) throws IOException {
        this.loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
    }
    
    public void goToLogin() throws IOException{
        this.goTo(LOGIN_FXML);
    }
    
    public void goToMain() throws IOException{
        this.goTo(MAIN_FXML);
    }
    
    public void goToCreateCar(Car editCar) throws IOException, SQLException{
        this.goTo(CREATE_CAR_FXML);
        CUCarController ctrl = loader.getController();
        ctrl.initialize(editCar);
    }
    
    public void goToCreateContract(Contract editContract) throws IOException, SQLException{
        this.goTo(CREATE_CONTRACT_FXML);
        CUContractController ctrl = loader.getController();
        ctrl.initialize(editContract);
    }
    
    public void goToCreateCategory(Category editCategory) throws IOException{
        this.goTo(CREATE_CATEGORY_FXML);
        CUCategoryController ctrl = loader.getController();
        ctrl.initialize(editCategory);
    }
    
    public void goToCreateBrand(Brand editBrand) throws IOException{
        this.goTo(CREATE_BRAND_FXML);
        CUBrandController ctrl = loader.getController();
        ctrl.initialize(editBrand);
    }
    
    public void goToCreateColor(Color editColor) throws IOException{
        this.goTo(CREATE_COLOR_FXML);
        CUColorController ctrl = loader.getController();
        ctrl.initialize(editColor);
    }
    
    public void goToCreateCustomer(Customer editCustomer) throws IOException{
        this.goTo(CREATE_CUSTOMER_FXML);
        CUCustomerController ctrl = loader.getController();
        ctrl.initialize(editCustomer);
    }
    
    public void goToCreateAccount() throws IOException{
        this.goTo(CREATE_ACCOUNT_FXML);
    }

    public void goToDetailsCar(Car car) throws IOException, SQLException{
        this.goTo(CREATE_DETAILSCAR_FXML);
        DetailsCarController ctr = loader.getController();
        ctr.initialize(car);
    }
    
    public void goToDetailsContract(Contract contract) throws IOException, SQLException{
        this.goTo(CREATE_DETAILSCONTRACT_FXML);
        DetailsContractController ctr = loader.getController();
        ctr.initialize(contract);
    }
    
}
