package doggroomer.data;
import doggroomerfx.DogGroomer;
import java.util.Date;

public class Appointment extends DogGroomer
{
    protected Customer customer;
    protected Date date;

    public Appointment(Customer customer, Date date) 
    {
        this.customer = customer;
        this.date = date;
    }

    public Customer getCustomer() 
    {
        return customer;
    }

    public void setCustomer(Customer customer) 
    {
        this.customer = customer;
    }

    public Date getDate() 
    {
        return date;
    }

    public void setDate(Date date) 
    {
        this.date = date;
    }

    
    
    @Override
    public String toString() 
    {
        return "Appointment{" + "customer=" + customer + ", date=" + date + '}';
    }
}
