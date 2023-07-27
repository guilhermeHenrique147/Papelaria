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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class CadastrapedidosController implements Initializable {

    @FXML
    private GridPane gridDados;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_valor;
    @FXML
    private TextField txt_cpf;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_sair;
    @FXML
    private TextField txt_qtde;
    @FXML
    private ComboBox<String> cb_produto;
    @FXML
    private Button btn_editar;
    @FXML
    private Button btn_salvar;
    @FXML
    private ListView<?> lista1;
    @FXML
    private Button btn_inserir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txt_id.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                // Perform your action when the TextField loses focus
                handleTextFieldFocusLost();
            }
        });
        populateComboBox();
        
    }
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    private boolean validarCampos() {
        if(txt_id.getText().length() == 0 ||
           txt_cpf.getText().length() == 0 ||
           txt_qtde.getText().length() == 0 ||
           txt_valor.getText().length() == 0 ||
           cb_produto.getSelectionModel().getSelectedIndex() == -1) {
            return false;
        } else {
            return true;
        }
    }
    private void limparCampos() {
        txt_id.setText("");
        txt_cpf.setText("");
        txt_qtde.setText("");
        cb_produto.getSelectionModel().clearSelection();
        txt_valor.setText("");
    }
    
    @FXML
    private void handleEditarPedidosButtonAction() {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        String produto = cb_produto.getValue();
        String quantidade = txt_qtde.getText();
        String cpf = txt_cpf.getText();
        String valor = txt_id.getText();
        String primaryKeyValue = txt_id.getText();
        try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        String sql = "UPDATE Pedido SET cpf = ?, valor = ?, produto = ?, qtde = ? WHERE idPedido = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, valor);
        statement.setString(3, produto);
        statement.setString(4, quantidade);
        statement.setString(5, primaryKeyValue);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
                mensagem("Dados Alterados com Sucesso", 
                     Alert.AlertType.INFORMATION);
                limparCampos();
            } else {
                mensagem("Não for possivel achar este pedido", 
                     Alert.AlertType.INFORMATION);
                limparCampos();
            }
        statement.close();
        connection.close();
        }
        catch (SQLException e) {
        e.printStackTrace();
        mensagem("Erro ao conectar com o banco", 
                        Alert.AlertType.INFORMATION);
        limparCampos();
    }
        
    }
    @FXML
    private void deletarPedidoAction() {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Veículo?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
        
        String primaryKeyValue = txt_id.getText();
        try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        String sql = "DELETE FROM Pedido WHERE idPedido = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, primaryKeyValue);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
                mensagem("Dados Deletados com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
            } else {
                mensagem("Não for possivel achar o este pedido", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
            }
        statement.close();
        connection.close();
        }
        catch (SQLException e) {
        e.printStackTrace();
        mensagem("Erro ao conectar com o banco", 
                        Alert.AlertType.INFORMATION);
        limparCampos();
    }
    }
    private void handleTextFieldFocusLost() {
    // Code to be executed when the TextField loses focus
        fetchRecord();
}
    public void fetchRecord() {
        String primaryKey = txt_id.getText();
        
        // Create a database connection
        try{
            Banco.conectar();
            Connection connection = Banco.obterConexao();
            // Prepare the SQL statement with a parameterized query
            String sql = "SELECT cpf, valor, produto, qtde FROM Pedido WHERE idPedido = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, primaryKey);
            
            // Execute the query and retrieve the result set
            ResultSet resultSet = statement.executeQuery();
            
            // Check if the result set has any rows
            if (resultSet.next()) {
                // Retrieve the values from the result set and populate the text fields
                String cpf = resultSet.getString("cpf");
                double valor = resultSet.getDouble("valor");
                String produto = resultSet.getString("produto");
                String qtde = resultSet.getString("qtde");
                
                txt_cpf.setText(cpf);
                txt_valor.setText(Double.toString(valor));
                cb_produto.setValue(produto);
                txt_qtde.setText(qtde);
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
        e.printStackTrace();
        // Handle any potential errors
        System.err.println("Error ao conectar ao banco: " + e.getMessage());
    }
    }
    @FXML
    public void btn_sair_onclick(ActionEvent event) {
         Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
    }
     private void populateComboBox() {
        List<String> produtos = retrieveProdutosFromDatabase();
        ObservableList<String> observableProdutos = FXCollections.observableArrayList(produtos);
        // Clear the ComboBox before adding new items
        cb_produto.getItems().clear();

        // Add the retrieved products to the ComboBox
        cb_produto.getItems().addAll(observableProdutos);
    }
    
      private List<String> retrieveProdutosFromDatabase(){
          List<String> produtos = new ArrayList<>();
          try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT nomeProduto FROM Produto");
         while (resultSet.next()) {
                String nomeProduto = resultSet.getString("nomeProduto");
                produtos.add(nomeProduto);
            }
        connection.close();
        System.out.println("Conexão com o banco feita com sucesso");

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle any potential errors
        System.err.println("Erro de conexão com o banco: " + e.getMessage());
    }
          return produtos;
      }
    public void handleInserirPedidoButtonAction() {       
        String selectedProduto = cb_produto.getValue();
        String quantidadeText = txt_qtde.getText();
        int quantidade = Integer.parseInt(quantidadeText);
        double valor = retrieveValorFromDatabase(selectedProduto);
        double totalValor = valor * quantidade;
        txt_valor.setText(String.valueOf(totalValor));
    }
    
    private double retrieveValorFromDatabase(String produto) {
        double valor = 0.0;
    try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        String sql = "SELECT valor FROM Produto WHERE nomeProduto = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, produto);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            valor = resultSet.getDouble("valor");
        }
        statement.close();
        connection.close();
}
    catch (SQLException e) {
        e.printStackTrace();
        mensagem("Erro ao conectar com o banco", 
                        Alert.AlertType.INFORMATION);

    }
    return valor;
    }
    public void handleSalvarPedidosButtonAction() {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        String cpf = txt_cpf.getText();
        String valor = txt_valor.getText();
        String produto = cb_produto.getValue();
        String qtde = txt_qtde.getText();
         try {
        Banco.conectar();
        // Get the database connection
        Connection connection = Banco.obterConexao();
        String sql = "INSERT INTO Pedido (cpf, valor, produto, qtde) VALUES (?, ?, ?, ?)";
        // Create a PreparedStatement with the SQL statement
        PreparedStatement statement = connection.prepareStatement(sql);

        // Set the values for the prepared statement
        statement.setString(1, cpf);
        statement.setString(2, valor);
        statement.setString(3, produto);
        statement.setString(4, qtde);

        // Execute the statement to insert the data
        statement.executeUpdate();

        // Close the statement and connection
        statement.close();
        connection.close();

        mensagem("Dados Salvos com Sucesso!!!", 
                        Alert.AlertType.INFORMATION);
        limparCampos();


    } catch (SQLException e) {
        e.printStackTrace();
        mensagem("Erro ao salvar os dados!!!", 
                        Alert.AlertType.INFORMATION);
        limparCampos();

    }
    }
}
