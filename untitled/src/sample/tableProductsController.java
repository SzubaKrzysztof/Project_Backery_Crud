package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class tableProductsController implements Initializable {


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
    public TableColumn <MainData, String> dateCol;
    @FXML
    public AnchorPane tableDeliveryPane;
    @FXML
    public AnchorPane managmenPane1;
    @FXML
    public AnchorPane tableInvoicePane;
    @FXML
    public TableColumn <ProductsData, String> priceCol;
    @FXML
    public AnchorPane tableProductsPane;
    @FXML
    public TableView<ProductsData> tableProducts;
    @FXML
    public Label priceLB;
    @FXML
    public JFXTextField priceTF;
    @FXML
    private TableColumn<ProductsData, String> IDcol;
    @FXML
    private TableColumn<ProductsData, String> nameCol;

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
    private ObservableList<ProductsData> data;


    @Override
    public void initialize ( URL location, ResourceBundle rb ) {

        dc = new dbConnection();
        Refresh();
        populateTextfields();
    }


    @FXML
    void addProduct ( ActionEvent event ) {
        String sql = "INSERT INTO `products`(`ID`, `name`, `price`) VALUES (? , ?, ?)";
        try {
            Connection conn = dbConnection.getConnection();
            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, IDTF.getText());
            stmt.setString(2, nameTF.getText());
            stmt.setString(3, priceTF.getText());
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
        alert.setContentText("product Added!");
        alert.showAndWait();
    }


    @FXML
    void clearAction () {
        clearFields();
    }

    @FXML
    private void clearFields () {
        IDTF.setText("");
        IDLB.setText("");
        nameTF.setText("");
        nameLB.setText("");
        priceTF.setText("");
        priceLB.setText("");

    }


    @FXML
    void deleteProduct ( ActionEvent event ) {
        String sql = "DELETE FROM products WHERE ID=" + IDTF.getText() + "";
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
        alert.setContentText("product Deleted!");
        alert.showAndWait();
    }

    @FXML
    void updateProduct ( ActionEvent event ) {
        String sql = "UPDATE products  SET name='" + nameTF.getText() + "', price='" + priceTF.getText() + "' WHERE ID=" + IDTF.getText() + "";
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
        alert.setContentText("product Updated!");
        alert.showAndWait();
    }

    @FXML
    public void Refresh () {
        try {
            Connection conn = dbConnection.getConnection();
            data = FXCollections.observableArrayList();

            assert conn != null;
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM products");
            while (rs.next()) {
                data.add(new ProductsData(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        IDcol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableProducts.setItems(null);
        tableProducts.setItems(this.data);
    }

    private void populateTextfields () {
        tableProducts.setOnMouseClicked(event -> {
        IDTF.setText(tableProducts.getSelectionModel().getSelectedItem().getID());                           //1
            ProductsData cd1 = tableProducts.getSelectionModel().getSelectedItem();                                //2
//            sample.ClientData cd = clientttable.getItems().get(clientttable.getSelectionModel().getSelectedIndex());   //3
//            IDTF.setText(cd1.getID());    //kiedy jedynka aktywna to musi być nieaktywne
            IDLB.setText(cd1.getID());
            nameTF.setText(cd1.getName());
            nameLB.setText(cd1.getName());
            priceTF.setText(cd1.getPrice());
            priceLB.setText(cd1.getPrice());

        });
    }



}
