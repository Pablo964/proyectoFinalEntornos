package doggromer.data;

import static doggroomer.data.Administrator.appointments;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

public class Shop 
{
    protected static List<Product> products;
    
    public Shop() 
    {
        initializeListProducts();
    } 
    
    public  static void initializeListProducts()
    {   
        products = new ArrayList<>();
        try
        {            
            File archivo = new File ("products.txt");
            FileReader fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line=br.readLine())!=null)
            {
                products.add(new Product(line.split(":")[0],
                        Double.parseDouble(line.split(":")[1]),
                        line.split(":")[2]));
            }
            System.out.println(products.get(0).toString());
            br.close();
        }
        catch(Exception e)
        {
            System.out.println("The list products could not be initialized");
        }    
    }
    public  void addProduct(Product newProduct)
    {
        products.add(newProduct);
        
        try
        {
            File file = new File ("products.txt");
            FileWriter wr = new FileWriter (file, true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(newProduct.getName()+":"+newProduct.getPrice()+
                    ":"+newProduct.getDate() +"\n");
            bw.close();
            wr.close();
            
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Product add Success");
            dialog.showAndWait();

        }
        catch(Exception e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setHeaderText("ERROR");
            dialog.setContentText("Unsaved Product");
            dialog.showAndWait();
        }
    }
    
    public static void modifyProduct(Product product)
    {
    
    }
    
    public  void deleteProduct(String name, String date)
    {
        boolean exist = false;
        int index = 0;
        for(int i=0; i<products.size();i++)
        {
            if (products.get(i).getDate().equals(date) 
                    && products.get(i).getName().equals(name))
            {
                exist = true;
                index = i;
            }
        }
        if (exist) 
        {
            products.remove(products.get(index));
            try
            {
                File inputFile = new File("products.txt");
                File tempFile = new File("tmp" + inputFile.getName());
                BufferedReader br = new BufferedReader(new FileReader(inputFile.getName()));
                PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

                String line = null;
                while ((line = br.readLine()) != null) 
                {
                    if (!line.split(":")[0].equals(name) 
                            || !line.split(":")[2].equals(date)) 
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
                dialog.setHeaderText("Product delete success");
                dialog.showAndWait();
            }

            catch (Exception e)
            {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("ERROR");
                dialog.setContentText("undeleted product");
                dialog.showAndWait();
            }
        }
        else
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setHeaderText("ERROR");
                dialog.setContentText("product not exist");
                dialog.showAndWait();
        }
    }
}