package doggroomer.data;

public class Dog 
{
    protected String name;
    protected String size; //small, medium or big
    protected boolean isAgressive;
    protected boolean longHair;
    protected double price;

    public Dog(String name, String size, boolean isAgressive,
            boolean longHair, double price) 
    {
        this.name = name;
        this.size = size;
        this.isAgressive = isAgressive;
        this.longHair = longHair;
        this.price = price;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSize() {return size;}

    public void setSize(String size) {this.size = size;}

    public boolean isIsAgressive(){return isAgressive;}

    public void setIsAgressive(boolean isAgressive){this.isAgressive = isAgressive;}

    public boolean isLongHair(){return longHair;}

    public void setLongHair(boolean longHair){this.longHair = longHair;}

    public double getPrice() 
    {return price;}

    public void setPrice(double price) 
    {this.price = price;}   

    @Override
    public String toString() 
    {
        return "Dog{" + "name=" + name + ", size=" + size + 
                ", isAgressive=" + isAgressive + ", longHair=" + longHair + 
                ", price=" + price + '}';
    } 
}
