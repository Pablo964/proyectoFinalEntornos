package doggromer.data;

import java.util.Date;

public class Product 
{
    protected String name;
    protected double price;
    protected String date;

    public Product(String name, double price, String date) 
    {
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}
     
    @Override
    public String toString() 
    {
        return "Product{" + "name=" + name + ", price=" + price + ", date=" + 
                date + '}';
    }
}

