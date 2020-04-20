package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tableReturnsController implements Initializable {
    @FXML
    public AnchorPane tableRurnsPane;
    @FXML
    public JFXTextField taxesTF;
    @FXML
    public JFXTextField totalTF;
    @FXML
    public JFXTextField dateTF;
    @FXML
    public JFXTextField searchTF;
    @FXML
    public AnchorPane managmenPane;
    @FXML
    public JFXTextField IDTF1;
    @FXML
    public Label dateLB;
    @FXML
    public Label totalLB;
    @FXML
    public Label taxesLB;
    @FXML
    public TableColumn<MainData, String> taxesCol;
    @FXML
    public DatePicker datePicker;
    @FXML
    private TableView<MainData> returnsTable;
    @FXML
    private TableColumn<MainData, String> IDcol;
    @FXML
    private TableColumn<MainData, String> nameCol;
    @FXML
    private TableColumn<MainData, String> dateCol;
    @FXML
    private TableColumn<MainData, String> totalCol;
    @FXML
    private JFXTextField IDTF;
    @FXML
    private JFXTextField nameTF;
    @FXML
    private Label nameLB;
    @FXML
    private Label IDLB;
    @FXML
    private dbConnection dc;
    @FXML
    private ObservableList<MainData> data;


   @Override
    public void initialize ( URL location, ResourceBundle rb ) {

        dc = new dbConnection();
        Refresh();
        populateTextfields();
    }


    @FXML
    public void clearFields () {
        IDTF.setText("");
        IDLB.setText("");
        nameTF.setText("");
        nameLB.setText("");
        dateTF.setText("");
        dateLB.setText("");
        totalTF.setText("");
        totalLB.setText("");
        taxesTF.setText("");
        taxesLB.setText("");

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
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM returns");
            while (rs.next()) {
                data.add(new MainData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        IDcol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("datta"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        taxesCol.setCellValueFactory(new PropertyValueFactory<>("taxes"));
        returnsTable.setItems(null);
        returnsTable.setItems(this.data);
    }



    @FXML
    public void deleteReturn () {
        String sql = "DELETE FROM returns WHERE ID="+IDTF.getText()+"";
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
        alert.setContentText("return Deleted!");
        alert.showAndWait();
    }

    @FXML
    private void addReturn () {
        String sql = "INSERT INTO `returns`(`ID`, `name`, `datta`, `total`, `taxes`) VALUES (? , ?, ?, ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, IDTF.getText());
            stmt.setString(2, nameTF.getText());
            stmt.setString(3, dateTF.getText());
            stmt.setString(4, taxesTF.getText());
            stmt.setString(5, taxesTF.getText());
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
        alert.setContentText("return Added!");
        alert.showAndWait();
    }

    @FXML
    private void updateReturn ( ActionEvent event ) {
        String sql = "UPDATE returns  SET name='"+nameTF.getText()+"', datta='"+dateTF.getText()+"', total='"+totalTF.getText()+"', taxes='"+taxesTF.getText()+"' WHERE ID="+IDTF.getText()+"";
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
        alert.setContentText("return Updated!");
        alert.showAndWait();
    }

    private void populateTextfields () {
        returnsTable.setOnMouseClicked(event -> {
//        IDTF.setText(returnsTable.getSelectionModel().getSelectedItem().getID());                           //1
        MainData cd = returnsTable.getSelectionModel().getSelectedItem();                                //2
//            sample.ClientData cd = clientttable.getItems().get(clientttable.getSelectionModel().getSelectedIndex());   //3
//            IDTF.setText(cd1.getID());    //kiedy jedynka aktywna to musi być nieaktywne
          IDTF.setText(cd.getID());
            IDLB.setText(cd.getID());
        nameTF.setText(cd.getName());
        nameLB.setText(cd.getName());
        dateTF.setText(cd.getDatta());
        dateLB.setText(cd.getDatta());
        totalTF.setText(cd.getTotal());
        totalLB.setText(cd.getTotal());
        taxesTF.setText(cd.getTaxes());
        taxesLB.setText(cd.getTaxes());
        });
    }



}



