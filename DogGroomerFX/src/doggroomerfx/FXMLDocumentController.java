/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggroomerfx;

import doggroomer.data.Administrator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    
    private  List<Administrator> administrators;
    private boolean loginDisable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loginDisable = false;
        try
        {
            administrators= new ArrayList<>();
            File archivo = new File ("administrators.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null)
            {
                administrators.add(new Administrator(line.split(":")[0],
                        line.split(":")[1]));
            }
        }
        catch (Exception e)
        {
           List<Administrator> administrators = new ArrayList<>();
        }
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
