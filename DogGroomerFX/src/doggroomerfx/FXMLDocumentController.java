/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggroomerfx;

import doggroomer.data.Administrator;
import doggroomer.data.Customer;
import doggroomer.data.Dog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 *
 * @author Pablo
 */
public class FXMLDocumentController implements Initializable 
{
    @FXML
    private TextField loginName;
    @FXML
    private TextField loginPass;
    @FXML
    private Button buttonLogin;
    @FXML
    private  Pane loginPane;
    @FXML
    private  Pane paneAppointments, paneShop, paneCustomers, paneWhite;
    @FXML
    private Button buttonCustomer, buttonShop, buttonAppointment;
    @FXML
    private TextField addCustomerName, addCustomerEmail;
    @FXML
    private TextField addCustomerTel;
    @FXML
    private TextField addDogName, addDogTel, addDogAgressive, addDogLongHair,
            addDogPrice;
    @FXML
    private RadioButton  dogSizeBig, dogSizeMedium, dogSizeSmall, longHairYes,
            longHairNo, agressiveYes, agressiveNo;
    private  List<Administrator> administrators;
    
    private Administrator admin;
    
    @FXML
    private ToggleGroup sizeGroup, longHairGroup, agressiveGroup;
     
    private boolean loginDisable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        sizeGroup = new ToggleGroup();
        dogSizeBig.setToggleGroup(sizeGroup);
        dogSizeMedium.setToggleGroup(sizeGroup);
        dogSizeSmall.setToggleGroup(sizeGroup);
        
        longHairGroup = new ToggleGroup();
        longHairYes.setToggleGroup(longHairGroup);
        longHairNo.setToggleGroup(longHairGroup);
        
        agressiveGroup = new ToggleGroup();
        agressiveYes.setToggleGroup(agressiveGroup);
        agressiveNo.setToggleGroup(agressiveGroup);
        
        paneCustomers.toFront();
        loginDisable = false;
        
        administrators= new ArrayList<>();        
        try
        {
            File archivo = new File ("administrators.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null)
            {
                administrators.add(new Administrator(line.split(":")[0],
                        line.split(":")[1]));
            }
            
            br.close();
            fr.close();
            
        }
        catch (Exception e)
        {
           System.out.println("The list could not be initialized");
        }
        
            addCustomerTel.textProperty().addListener
                    (new ChangeListener<String>() 
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) 
            {
                if (!newValue.matches("\\d")) 
                {
                    addCustomerTel.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    } 
    
    public void login(ActionEvent event) 
    {
        for(Administrator a : administrators)
        {
            if (a.getName().equals(loginName.getText()) 
                    && a.getPassword().equals(loginPass.getText())) 
            {
                
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setHeaderText("Login Success");
                dialog.setContentText("Welcome " + a.getName() + "!");
                dialog.showAndWait();
                loginPane.setDisable(true);
                loginDisable = true;
                admin = new Administrator(a.getName(), a.getPassword());
            }
            else
            {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("Error");
                dialog.setContentText("username or password incorrect");
                dialog.showAndWait();
            }
        }
    }
    @FXML
    public void addCustomer(ActionEvent event)
    {
        String name = addCustomerName.getText();
        int tel = Integer.parseInt(addCustomerTel.getText());
        String email = addCustomerEmail.getText();
        
        if (name.equals("") || addCustomerTel.getText().equals("")
                || email.equals("")) 
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("Error");
            dialog.setContentText("you must fill in all fields in order to "
                    + "add a customer");
            dialog.showAndWait();
        }
        else
        {
            admin.addCustomer(name, tel, email);
        }
    }
    @FXML
    public void addDog(ActionEvent event)
    {
        String dogName = addDogName.getText();
        boolean isAgressive, haveLongHair;
        
        if (dogName.equals("") 
                || addDogTel.getText().equals("")
                || sizeGroup.getSelectedToggle() == null
                || agressiveGroup.getSelectedToggle() == null
                || longHairGroup.getSelectedToggle() == null
                || addDogPrice.getText().equals("")) 
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("Error");
            dialog.setContentText("you must fill in all fields in order to "
                    + "add a dog");
            dialog.showAndWait();
        }
        else
        {
            int tel = Integer.parseInt(addDogTel.getText()); 
            int price = Integer.parseInt(addDogPrice.getText());
            
            String longHair[] = longHairGroup.getSelectedToggle().
                    toString().split("'");
            String agressive[] = longHairGroup.getSelectedToggle().
                    toString().split("'");
            
            if (agressive[1].equals("yes"))
                isAgressive = true;
            else
                isAgressive = false;
            
            if (longHair[1].equals("yes"))
                haveLongHair = true;
            else
                haveLongHair = false;
            
            String size[] = sizeGroup.getSelectedToggle().toString().split("'");

            Administrator.addDog(dogName, size[1], isAgressive, 
                    haveLongHair, price, tel, false);
        }
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event)
    {
        if (loginDisable) 
        {
            if (event.getSource() == buttonCustomer) 
            {
                paneCustomers.toFront();
            }
            else if (event.getSource() == buttonShop) 
            {
                paneShop.toFront();
            }
            else if (event.getSource() == buttonAppointment) 
            {
                paneAppointments.toFront();
            }
        }
        else
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("Error login");
            dialog.setContentText("you must login to access the functionalities");
            dialog.showAndWait();
        }
    }
    
    
}
