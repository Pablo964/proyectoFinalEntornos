package doggroomer.data;
import doggroomerfx.DogGroomer;
import java.util.Date;

public class Appointment extends DogGroomer
{
    protected Customer customer;
    protected String date;
    protected String hour;

    public Appointment(Customer customer, String date, String hour) 
    {
        this.customer = customer;
        this.date = date;
        this.hour = hour;
    }

    public Customer getCustomer() 
    {
        return customer;
    }

    public void setCustomer(Customer customer) 
    {
        this.customer = customer;
    }

    public String getDate() 
    {
        return date;
    }

    public String getHour() 
    {
        return hour;
    }

    public void setHour(String hour) 
    {
        this.hour = hour;
    }

    public void setDate(String date) 
    {
        this.date = date;
    }
    @Override
    public String toString() 
    {
        return "Appointment{" + "customer=" + customer + ", date=" + date + '}';
    }
}
