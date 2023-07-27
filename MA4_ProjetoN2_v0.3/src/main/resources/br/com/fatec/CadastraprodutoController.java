/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec;

import br.com.fatec.Model.Fornecedor;
import br.com.fatec.Model.Produto;
import br.com.fatec.Persistencia.Banco;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class CadastraprodutoController implements Initializable {

    @FXML
    private Button btn_sair;
    @FXML
    private GridPane gridDados;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_valor;
    @FXML
    private TextField txt_nome;
    @FXML
    private GridPane gridDados1;
    @FXML
    private TextField txt_fornecedor;
    @FXML
    private TextField txt_descricao;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_editar;
    @FXML
    private Button btn_salvar;
    
    Produto produto = new Produto();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txt_id.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {     
                handleTextFieldFocusLost();
            }
        });
    }
      
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    @FXML
    private void deletarProdutoAction() {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Cliente?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO){
            return;
        }
                
        String primaryKeyValue = txt_id.getText();
        try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        String sql = "DELETE FROM Produto WHERE idProduto = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, primaryKeyValue);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
                mensagem("Dados Excluídos com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
            } else {
                mensagem("Não for possivel achar o id do pedido", 
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
    public void btn_fechar(ActionEvent event) {
         Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
    }
 @FXML
 private void handleEditarProdutoAction(){    
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        String id = txt_id.getText();
        String descricao = txt_descricao.getText();
        String nome = txt_nome.getText();
        String fornecedor = txt_fornecedor.getText();
        String valor = txt_valor.getText();
        String primaryKeyValue = txt_id.getText();
        try{
        Banco.conectar();
        Connection connection = Banco.obterConexao();
        String sql = "UPDATE Produto SET idProduto = ?, nomeProduto = ?, valor = ?, descricao = ?, fornecedor = ? WHERE idProduto = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2, nome);
        statement.setString(3, valor);
        statement.setString(4, descricao);
        statement.setString(5, fornecedor);
        statement.setString(6, primaryKeyValue);
        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
                mensagem("Dados Alterados com Sucesso", 
                        Alert.AlertType.INFORMATION);
                limparCampos();
            } else {
                mensagem("Não for possivel achar o id do pedido", 
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
        fetchRecord();
}
 
        public void fetchRecord() {
        String primaryKey = txt_id.getText();
        
        try{
            Banco.conectar();
            Connection connection = Banco.obterConexao();

            String sql = "SELECT idProduto, nomeProduto, valor, descricao, fornecedor FROM Produto WHERE idProduto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, primaryKey);
            
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {   
                String id = resultSet.getString("idProduto");
                String nome = resultSet.getString("nomeProduto");
                String valor = resultSet.getString("valor");
                String descricao = resultSet.getString("descricao");
                String fornecedor = resultSet.getString("fornecedor");
                
                txt_id.setText(id);
                txt_nome.setText(nome);
                txt_valor.setText(valor);
                txt_descricao.setText(descricao);
                txt_fornecedor.setText(fornecedor);
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error ao conectar ao banco: " + e.getMessage());
    }
    }
 
    public void handleSalvarProdutosButtonAction() {
        if(!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }
        
        int id = Integer.parseInt(txt_id.getText());
        String nome = txt_nome.getText();
        double valor = Double.parseDouble(txt_valor.getText());
        String descricao = txt_descricao.getText();
        String fornecedor = txt_fornecedor.getText();
        try {
        Banco.conectar();

        Connection connection = Banco.obterConexao();
        String sql = "INSERT INTO Produto (idProduto, nomeProduto, valor, descricao, fornecedor) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setInt(1, id);
        statement.setString(2, nome);
        statement.setDouble(3, valor);
        statement.setString(4, descricao);
        statement.setString(5, fornecedor);

        statement.executeUpdate();

        statement.close();
        connection.close();

        mensagem("Produto salvo com Sucesso", 
            Alert.AlertType.INFORMATION);
        limparCampos();
    }
        catch (SQLException e) {
        e.printStackTrace();
        mensagem("Erro ao salvar o produto", 
                        Alert.AlertType.INFORMATION);
        limparCampos();
    }
        
}
    private boolean validarCampos() {
        if(txt_id.getText().length() == 0 ||
           txt_nome.getText().length() == 0 ||
           txt_valor.getText().length() == 0 ||
           txt_descricao.getText().length() == 0 ||
           txt_fornecedor.getText().length()==0) {
            return false;
        } else {
            return true;
        }
    }
    private void limparCampos() {
        txt_id.setText("");
        txt_nome.setText("");
        txt_valor.setText("");
        txt_descricao.setText("");
        txt_fornecedor.setText("");
    }
    
}
