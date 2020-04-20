package sample.Style;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainData  {
    private final StringProperty ID;
    private final StringProperty name;
    private final StringProperty date;
    private final StringProperty total;
    private final StringProperty taxes;

    public MainData ( String ID, String name, String date, String total, String taxes ) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleStringProperty(date);
        this.total = new SimpleStringProperty(total);
        this.taxes = new SimpleStringProperty(taxes);
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

    public String getDate () {
        return date.get();
    }

    public StringProperty dateProperty () {
        return date;
    }

    public void setDate ( String date ) {
        this.date.set(date);
    }

    public String getTotal () {
        return total.get();
    }

    public StringProperty totalProperty () {
        return total;
    }

    public void setTotal ( String total ) {
        this.total.set(total);
    }

    public String getTaxes () {
        return taxes.get();
    }

    public StringProperty taxesProperty () {
        return taxes;
    }

    public void setTaxes ( String taxes ) {
        this.taxes.set(taxes);
    }
}