package doggroomer.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class Administrator
{
    
    public static List<Customer> customers;
    public static List<Dog> dogs;
    protected String name;
    protected String password;

    public Administrator(String name, String password) 
    {
        this.name = name;
        this.password = password;
        this.customers = new ArrayList<>();
        
        initializeListCustomer();
        initializeListDog();
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public static void initializeListCustomer()
    {   
        customers = new ArrayList<>();
        try
        {            
            File archivo = new File ("customers.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null)
            {
                customers.add(new Customer(line.split(":")[0],
                        Integer.parseInt(line.split(":")[1]),
                        line.split(":")[2]));
                System.out.println(customers.get(0).toString());
            }
            br.close();
            fr.close();
        }
        catch(Exception e)
        {
            System.out.println("The list customers could not be initialized");
        }    
    }
    
    public static void initializeListDog()
    {   
        dogs = new ArrayList<>();
        try
        {
            File archivo = new File ("dogs.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            
            String line;
                
            while((line=br.readLine())!=null)
            {
                System.out.println(line.split(":")[0]);
                addDog(line.split(":")[0], line.split(":")[1], 
                        Boolean.parseBoolean(line.split(":")[2]),
                        Boolean.parseBoolean(line.split(":")[3]),
                        Double.parseDouble(line.split(":")[4]),
                        Integer.parseInt(line.split(":")[5]), true);
                
            }
            br.close();
            fr.close();
        }
        catch(Exception e)
        {
            System.out.println("The list dog could not be initialized");
        }    
    }
    public void addCustomer(String name, int tel, String email)
    {
        customers.add(new Customer(name,tel,email));
        try
        {
            File archivo = new File ("customers.txt");
            FileWriter wr = new FileWriter (archivo, true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(name+":"+Integer.toString(tel)+":"+email +"\n");
            bw.close();
            wr.close();
            
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Customer add Success");
            dialog.setContentText("Customer " + name 
                    + " add success!");
            dialog.showAndWait();

        }
        catch(Exception e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("ERROR");
            dialog.setContentText("Customer don't save");
            dialog.showAndWait();
        }
    }

    public static void modifyCustomer(Customer customer)
    {
    
    }
    
    public static void deleteCustomer(Customer customer)
    {
    
    }
    
    public static void addDog(String name, String size, boolean isAgressive, 
            boolean longHair, double price, int tel, boolean isInitialize)
    {
        System.out.println("eeeeeeeeeeeeeeeeeeeeeee");

        int positionDog;
        String customer = "";
        boolean ownerFound = false;

        dogs.add(new Dog(name, size, isAgressive, longHair, price));
        
        positionDog = dogs.size() - 1;
        System.out.println(dogs.get(positionDog).toString());
            
        for(Customer c : customers)
        {
            if (c.getTelephone() == tel) 
            {
                c.setDogs(dogs.get(positionDog));
                System.out.println(c.getDogs());
                customer = c.getName();
                ownerFound = true;
                System.out.println(c.toString());
            }
        }
        if (isInitialize)
        {
            return;
        }
        
        if (!ownerFound) 
        {
            dogs.remove(positionDog);
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("Error");
            dialog.setContentText("the phone does not correspond with any"
                    + " customer, the dog will not be added");
            dialog.showAndWait();
        }
        else
        {
            try
            {
                File archivo = new File ("dogs.txt");
                FileWriter wr = new FileWriter (archivo, true);
                BufferedWriter bw = new BufferedWriter(wr);
                bw.write(name+":"+size+":"+isAgressive+":"+longHair+":"+price
                        +":"+ tel+"\n");
                bw.close();
                wr.close();

            }
            catch(Exception e)
            {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("ERROR");
                dialog.setContentText("Dog don't save");
                dialog.showAndWait();
            }


            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Dog add Success");
            dialog.setContentText("Dog " + name 
                    + " add success for the customer " + customer);
            dialog.showAndWait();
        }
    }
    public static void addAppointment(Appointment newAppointment)
    {
        
    }
    
    public static void modifyAppointment(Appointment appointment)
    {
    
    }
    
    public static void deleteAppointment(Appointment appointment)
    {
    
    }
    
    @Override
    public String toString() 
    {
        return "Administrator{" + "name=" + name + ", password=" + 
                password + '}';
    }
}
