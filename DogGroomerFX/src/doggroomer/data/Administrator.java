package doggroomer.data;

import doggroomerfx.FXMLDocumentController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.Files;
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
    public static List<Appointment> appointments;

    protected String name;
    protected String password;

    public Administrator(String name, String password) 
    {
        this.name = name;
        this.password = password;
        
        initializeListCustomer();
        initializeListDog();
        initializeListAppointments();
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public static void initializeListAppointments()
    {   
        appointments = new ArrayList<>();
        
        try
        {      
            Customer customerAppointment = customers.get(0);
                    
            File archivo = new File ("appointments.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null)
            {
                for (Customer c : customers) 
                {
                    if (c.getTelephone() == Integer.parseInt(line.split(":")[0]))
                    {
                        customerAppointment = c;
                    }
                }

                appointments.add(new Appointment(customerAppointment,
                        line.split(":")[1], line.split(":")[2]));
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("The list appointments could not be initialized");
        }    
    }
    
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
            }
            br.close();
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
            File file = new File ("customers.txt");
            FileWriter wr = new FileWriter (file, true);
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
            dialog.setContentText("Unsaved customer");
            dialog.showAndWait();
        }
    }

    public static Customer searchCustomer(int tel)
    {
        boolean encountered = false;
        for(Customer c : customers)
        {
            if (c.getTelephone() == tel) 
            {
                encountered = true;
                return c;
            }
        }
        if (!encountered) 
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("ERROR");
            dialog.setContentText("The customer does not exist");
            dialog.showAndWait();
        }
        return null;
    }
    public static void modifyCustomer(String tel, String oldTel,
            String email, String name)
    {
        try
        {
            File inputFile = new File("customers.txt");
            File tempFile = new File("tmp" + inputFile.getName());
            BufferedReader br = new BufferedReader(new FileReader(inputFile.getName()));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            
            String line = null;
            while ((line = br.readLine()) != null) 
            {
                if (!line.split(":")[1].equals(oldTel)) 
                {
                    pw.println(line);
                    pw.flush();
                }
                else
                {
                    pw.println(name+":"+tel+":"+email);
                    pw.flush();
                }
            }
            br.close();
            pw.close();
            Files.delete(inputFile.toPath());
            tempFile.renameTo(inputFile);
            
            for(Customer c : customers)
            {
                if (c.getTelephone() == Integer.parseInt(oldTel)) 
                {
                    c.setEmail(email);
                    c.setName(name);
                    c.setTelephone(Integer.parseInt(tel));
                }
            }
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Customer modify success");
            dialog.showAndWait();
        }
    
        catch (Exception e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("ERROR");
            dialog.setContentText("undeleted customer");
            dialog.showAndWait();
        }
    }
    
    public static void deleteCustomer(int telephone)
    {
        Customer deleteCustomer = searchCustomer(telephone);          
        customers.remove(deleteCustomer);
        try
        {
            File inputFile = new File("customers.txt");
            File tempFile = new File("tmp" + inputFile.getName());
            BufferedReader br = new BufferedReader(new FileReader(inputFile.getName()));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            
            String line = null;
            while ((line = br.readLine()) != null) 
            {
                if (!line.split(":")[1].equals(Integer.toString(telephone))) 
                {
                    pw.println(line);
                    pw.flush();
                }
            }
            br.close();
            pw.close();
            Files.delete(inputFile.toPath());
            tempFile.renameTo(inputFile);
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Customer delete success");
            dialog.setContentText("Customer whit telephone: " 
                    + telephone +" deleted");
            dialog.showAndWait();
        }
    
        catch (Exception e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("ERROR");
            dialog.setContentText("undeleted customer");
            dialog.showAndWait();
        }
    }
    
    public static void addDog(String name, String size, boolean isAgressive, 
            boolean longHair, double price, int tel, boolean isInitialize)
    {
        int positionDog;
        String customer = "";
        boolean ownerFound = false;

        dogs.add(new Dog(name, size, isAgressive, longHair, price));
        
        positionDog = dogs.size() - 1;
            
        for(Customer c : customers)
        {
            if (c.getTelephone() == tel) 
            {
                c.setDogs(dogs.get(positionDog));
                customer = c.getName();
                ownerFound = true;
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
    public void addAppointment(int tel, String date, String hour)
    {
        boolean telExist = false;
        boolean appointmentExist = false;
        Customer appointmentCustomer = customers.get(0);
        
        for(Customer c: customers)
        {
            if(c.getTelephone() == tel)
            {
                telExist = true;
                appointmentCustomer = c;
            }
        }
        for(Appointment a: appointments)
        {
            if(a.getDate().equals(date) && a.getHour().equals(hour))
                appointmentExist = true;
        }
        
        if (!telExist) 
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("ERROR");
                dialog.setContentText("The telephone not exist");
                dialog.showAndWait();
                
                return;
        }
        if (appointmentExist) 
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("ERROR");
                dialog.setContentText("You already have an appointment "
                        + "at that time.");
                dialog.showAndWait();
                
                return;
        }
        
        try
        {
            appointments.add(new Appointment(appointmentCustomer, date, hour));
            File archivo = new File ("appointments.txt");
            FileWriter wr = new FileWriter (archivo, true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(tel+":"+date+":"+hour+"\n");
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
            dialog.setHeaderText("Appointment add Success");
            dialog.showAndWait();
    }
    
    public static void modifyAppointment(Appointment appointment)
    {
    
    }
    
    public static void deleteAppointment(String date, String hour)
    {
        boolean exist = false;
        for(Appointment a : appointments)
        {
            if (a.getDate().equals(date) && a.getHour().equals(hour))
            {
                appointments.remove(a);
                exist = true;
            }
        }
        try
        {
            File inputFile = new File("appointments.txt");
            File tempFile = new File("tmp" + inputFile.getName());
            BufferedReader br = new BufferedReader(new FileReader(inputFile.getName()));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            
            String line = null;
            while ((line = br.readLine()) != null) 
            {
                if (!line.split(":")[1].equals(date) 
                        || !line.split(":")[2].equals(hour)) 
                {
                    pw.println(line);
                    pw.flush();
                }
            }
            br.close();
            pw.close();
            Files.delete(inputFile.toPath());
            tempFile.renameTo(inputFile);
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Customer delete success");
            dialog.setContentText("Appointment whit date "+ date +
                    "and hour"+hour+ "deleted");
            dialog.showAndWait();
        }
    
        catch (Exception e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("ERROR");
            dialog.setContentText("undeleted customer");
            dialog.showAndWait();
        }
    }
    
    @Override
    public String toString() 
    {
        return "Administrator{" + "name=" + name + ", password=" + 
                password + '}';
    }
}
