package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientData
{
  private final StringProperty ID;
  private final StringProperty name;
  private final StringProperty address;
  private final StringProperty email;
  private final StringProperty NIP;
  
  public ClientData ( String ID, String name, String address, String email, String NIP)
  {
    this.ID = new SimpleStringProperty(ID);
    this.name = new SimpleStringProperty(name);
    this.address = new SimpleStringProperty(address);
    this.email = new SimpleStringProperty(email);
    this.NIP = new SimpleStringProperty(NIP);
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

  public String getAddress () {
    return address.get();
  }

  public StringProperty addressProperty () {
    return address;
  }

  public void setAddress ( String address ) {
    this.address.set(address);
  }

  public String getEmail () {
    return email.get();
  }

  public StringProperty emailProperty () {
    return email;
  }

  public void setEmail ( String email ) {
    this.email.set(email);
  }

  public String getNIP () {
    return NIP.get();
  }

  public StringProperty NIPProperty () {
    return NIP;
  }

  public void setNIP ( String NIP ) {
    this.NIP.set(NIP);
  }
}
