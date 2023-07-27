/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec;

import br.com.fatec.Persistencia.Banco;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class ConsultaavancadaController implements Initializable {

    @FXML
    private Button btn_sair;
    @FXML
    private TextField txt_consulta;
    @FXML
    private Button btn_limpar;
    @FXML
    private Button btn_buscar;
    @FXML
    private ListView<String> listaAvancada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ObservableList<String> data = retrieveDataFromDatabase();
    // Populate the ListView with the retrieved data
    listaAvancada.setItems(data);

    // Set the cell factory to apply custom styling
    listaAvancada.setCellFactory(listView -> new ListCell<String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(item);
            if (!empty && isMatchedItem(item)) {
                // Apply custom highlighting style
                setStyle("-fx-background-color: yellow;");

                // Delay the scroll operation to ensure it happens after rendering
                Platform.runLater(() -> {
                    listView.scrollTo(getIndex());
                    listView.getSelectionModel().clearAndSelect(getIndex());
                });
            } else {
                // Reset the style for non-matched items
                setStyle("");
            }
        }
    });
}
 @FXML
 private void deletarPedidoListaAction() {
        String primaryKeyValue = txt_consulta.getText();
        try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        String sql = "DELETE FROM Pedido WHERE idPedido = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, primaryKeyValue);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
                // Deletion successful
                System.out.println("Registro Deletado com sucesso!");
            // Remove the deleted item from the list
            ObservableList<String> items = listaAvancada.getItems();
            for (String item : items) {
                String[] lines = item.split("\n");
                for (String line : lines) {
                    String[] columns = line.split(": ");
                    if (columns.length > 1 && columns[0].trim().equals("idPedido") && columns[1].trim().equals(primaryKeyValue)) {
                        items.remove(item);
                        break;
                    }
                }
            }
        } else {
                // No matching record found
                System.out.println("Não achamos registros com esse ID");
            }
        statement.close();
        connection.close();
        }
        catch (SQLException e) {
        e.printStackTrace();
        // Handle any potential errors
        System.err.println("Error ao conectar ao banco: " + e.getMessage());
    }
    }
private boolean isMatchedItem(String item) {
    String searchText = txt_consulta.getText();
    String[] lines = item.split("\n");
    for (String line : lines) {
        String[] columns = line.split(": ");
        if (columns.length > 1 && columns[0].trim().equals("idPedido") && columns[1].trim().equals(searchText)) {
            return true;
        }
    }
    return false;
}

    @FXML
    public void btn_fechar(ActionEvent event) {
         Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleSearchButtonAction() {
    String searchText = txt_consulta.getText();
    
    // Iterate over the items in the ListView and search for a match
    for (String item : listaAvancada.getItems()) {
        // Split the item into lines
        String[] lines = item.split("\n");

        // Check each line for a match with the search text
        for (String line : lines) {
            // Split the line into columns
            String[] columns = line.split(": ");

            // Check if the line contains the idPedido value
            if (columns.length > 1 && columns[0].trim().equals("idPedido") && columns[1].trim().equals(searchText)) {
                // Found a match, get the ID from the idPedido value
                String itemId = columns[1].trim();
                System.out.println("Item ID: " + itemId);

                // Perform any desired operations with the ID
                // ...

                return; // Exit the loop once a match is found
            }
        }
    }

    // If no match is found, handle accordingly
    System.out.println("Item not found.");
}
    
     private ObservableList<String> retrieveDataFromDatabase() {
    ObservableList<String> data = FXCollections.observableArrayList();
    
    try {
        // Connect to the database and execute a query
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Pedido");
        
        // Get the column names from the result set
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder columnNames = new StringBuilder();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            columnNames.append(columnName).append(" ");
        }
        data.add(columnNames.toString()); // Add column names to the list
        
        // Process the query result and add data to the list
        while (resultSet.next()) {
            StringBuilder rowData = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String value = resultSet.getString(i);
                rowData.append(columnName).append(": ").append(value).append("\n");
            }
            data.add(rowData.toString()); // Add row data to the list
        }
        
        // Close the resources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any potential errors
    }
    
    return data;
}

    
}
