package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductsData {
    private final StringProperty ID;
    private final StringProperty name;
    private final StringProperty price;


    public ProductsData ( String ID, String name, String price) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleStringProperty(price);
        }

    public String getID () {
        return ID.get();
    }

    public StringProperty IDProperty () {
        return ID;
    }

    public void setID ( String ID ) {
        this.ID.set(ID);
    }

    public String getName () {
        return name.get();
    }

    public StringProperty nameProperty () {
        return name;
    }

    public void setName ( String name ) {
        this.name.set(name);
    }

    public String getPrice () {
        return price.get();
    }

    public StringProperty priceProperty () {
        return price;
    }

    public void setPrice ( String price ) {
        this.price.set(price);
    }

    public void getDataa () {
    }
}