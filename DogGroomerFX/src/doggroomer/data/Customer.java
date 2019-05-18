package doggroomer.data;

import java.util.ArrayList;
import java.util.List;

public class Customer 
{
    protected String name;
    protected int telephone;
    protected String email;
    protected List<Dog> dogs;

    public Customer(String name, int telephone, String email) 
    {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.dogs = new ArrayList<>();
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getTelephone() {return telephone;}

    public void setTelephone(int telephone) {this.telephone = telephone;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public List<Dog> getDogs() {return dogs;}

    public void setDogs(List<Dog> dogs) {this.dogs = dogs;}
    
   
    
    @Override
    public String toString() 
    {
        return "Customer{" + "name=" + name + ", telephone=" + telephone +
                ", email=" + email + ", dogs=" + dogs + '}';
    }
}
