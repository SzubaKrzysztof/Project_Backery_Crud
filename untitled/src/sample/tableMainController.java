package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tableMainController implements Initializable {

    public TableColumn dateCol;
    @FXML
    private TextField name, address, email, NIP, ID;

    @FXML
    public JFXTextField searchTF;
    @FXML
    public JFXTextField IDTF;
    @FXML
    public JFXTextField nameTF;
    @FXML
    public JFXTextField addressTF;
    @FXML
    public JFXTextField emailTF;
    @FXML
    public JFXTextField NIPTF1;
    @FXML
    public Label nameLB;
    @FXML
    public Label addressLB;
    @FXML
    public Label emailLB;
    @FXML
    public Label NIPLB;
    @FXML
    public Label IDLB;
    @FXML
    public TableView<ClientData> clientttable;
    @FXML
    public TableColumn<ClientData, String> nameCol;
    @FXML
    public TableColumn<ClientData, String> IDcol;
    @FXML
    public TableColumn<ClientData, String> adressCol;
    @FXML
    public TableColumn<ClientData, String> emailcol;
    @FXML
    public TableColumn<ClientData, String> NIPcol;
    @FXML
    private dbConnection dc;
    @FXML
    private ObservableList<ClientData> data;


    @Override
    public void initialize ( URL location, ResourceBundle rb ) {
        dc = new dbConnection();
        Refresh();
        populateTextfields();
    }

private void populateTextfields() {
        clientttable.setOnMouseClicked(event -> {
            //poniżej trzy sposoby na przeniesienie informacji z tabeli do textfield
            IDTF.setText(clientttable.getSelectionModel().getSelectedItem().getID());                           //1
            ClientData cd1 = clientttable.getSelectionModel().getSelectedItem();                                //2
            ClientData cd = clientttable.getItems().get(clientttable.getSelectionModel().getSelectedIndex());   //3
//            IDTF.setText(cd1.getID());    //kiedy jedynka aktywna to musi być nieaktywne
            IDLB.setText(cd1.getID());
            nameTF.setText(cd.getName());
            nameLB.setText(cd.getName());
            addressTF.setText(cd.getAddress());
            addressLB.setText(cd.getAddress());
            emailTF.setText(cd.getEmail());
            emailLB.setText(cd.getEmail());
            NIPTF1.setText(cd.getNIP());
            NIPLB.setText(cd.getNIP());
        });

}

    public void clearFields () {
        IDTF.setText("");
        IDLB.setText("");
        nameTF.setText("");
        nameLB.setText("");
        addressTF.setText("");
        addressLB.setText("");
        emailTF.setText("");
        emailLB.setText("");
        NIPLB.setText("");
        NIPTF1.setText("");
    }

    @FXML
    void clearAction () {
        clearFields();
    }

    @FXML
    public void Refresh () {
        try {
            Connection conn = dbConnection.getConnection();
            data = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM clients");
            while (rs.next()) {
                data.add(new ClientData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        IDcol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        adressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        NIPcol.setCellValueFactory(new PropertyValueFactory<>("NIP"));
        clientttable.setItems(null);
        clientttable.setItems(this.data);
    }


    @FXML
    public void RemoveClient () {
        String sql = "DELETE FROM clients WHERE ID="+IDTF.getText()+"";
        try {
            Connection conn = dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
               Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
               alert1.setTitle("Got an exception!");
               alert1.setContentText(e.getMessage());
               alert1.showAndWait();

            
        }
        Refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Client Deleted!");
        alert.showAndWait();
    }

    @FXML
    private void addClient () {
        String sql = "INSERT INTO `clients`(`ID`, `name`, `address`, `email`, `NIP`) VALUES (? , ?, ?, ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, IDTF.getText());
            stmt.setString(2, nameTF.getText());
            stmt.setString(3, addressTF.getText());
            stmt.setString(4, emailTF.getText());
            stmt.setString(5, NIPTF1.getText());
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        Refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Client Added!");
        alert.showAndWait();
    }

    @FXML
    private void updateClient ( ActionEvent event ) {
        String sql = "UPDATE clients  SET name='"+nameTF.getText()+"', address='"+addressTF.getText()+"', email='"+emailTF.getText()+"', NIP='"+NIPTF1.getText()+"' WHERE ID="+IDTF.getText()+"";
        try {
            Connection conn = dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        Refresh();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Client Updated!");
        alert.showAndWait();
    }

}

