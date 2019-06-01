package doggroomer.data;
import doggroomerfx.DogGroomer;
import java.util.Date;

public class Appointment extends DogGroomer
{
    protected Customer customer;
    protected String date;
    protected int hour;

    public Appointment(Customer customer, String date, int hour) 
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

    public int getHour() 
    {
        return hour;
    }

    public void setHour(int hour) 
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
