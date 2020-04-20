package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainData {
    private final StringProperty ID;
    private final StringProperty name;
    private final StringProperty datta;
    private final StringProperty total;
    private final StringProperty taxes;

    public MainData ( String ID, String name, String datta, String total, String taxes ) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.datta = new SimpleStringProperty(datta);
        this.total = new SimpleStringProperty(total);
        this.taxes = new SimpleStringProperty(taxes);
    }

    public String getID () {
        return ID.get();
    }

    public void setID ( String ID ) {
        this.ID.set(ID);
    }

    public String getDatta () {
        return datta.get();
    }

    public String getName () {
        return name.get();
    }

    public String getTotal () {
        return total.get();
    }

    public String getTaxes () {
        return taxes.get();
    }

    public StringProperty dattaProperty () {
        return datta;
    }

    private void setDatta ( String datta ) {
        this.datta.set(datta);
    }

    public StringProperty IDProperty () {
        return ID;
    }

    public StringProperty nameProperty () {
        return name;
    }

    public void setName ( String name ) {
        this.name.set(name);
    }


    public StringProperty totalProperty () {
        return total;
    }

    public void setTotal ( String total ) {
        this.total.set(total);
    }

    public StringProperty taxesProperty () {
        return taxes;
    }

    public void setTaxes ( String taxes ) {
        this.taxes.set(taxes);
    }
}