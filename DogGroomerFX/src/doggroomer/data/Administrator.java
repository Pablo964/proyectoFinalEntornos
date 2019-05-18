package doggroomer.data;

public class Administrator 
{
    protected String name;
    protected String password;

    public Administrator(String name, String password) 
    {
        this.name = name;
        this.password = password;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

     public static void addCustomer(Customer newCustomer)
    {
    
    }

    public static void modifyCustomer(Customer customer)
    {
    
    }
    
    public static void deleteCustomer(Customer customer)
    {
    
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
