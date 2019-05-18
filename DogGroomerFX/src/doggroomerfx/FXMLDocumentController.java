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

    private  List<Administrator> administrators;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
                buttonLogin.setDisable(true);
                loginName.setDisable(true);
                loginPass.setDisable(true);
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
}
