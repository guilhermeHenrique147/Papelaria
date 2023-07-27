/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author Guilherme
 */
public class MenuController implements Initializable {

    @FXML
    private Button btn_fechar;
    @FXML
    private Button btn_cadastroClientes;
    @FXML
    private Button btn_cadastroProdutos;
    @FXML
    private Button btn_cadastroPedidos;
    @FXML
    private Button btn_consultaAvancada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_cadastroClientes_Click(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastracliente.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void btn_cadastroProdutos_Click(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastraproduto.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void btn_cadastroPedidos_Click(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastrapedidos.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void btn_consultaAvancada_Click(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("consultaavancada.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void btn_fechar(ActionEvent event) {
         Stage stage = (Stage) btn_fechar.getScene().getWindow();
        stage.close();
    }
}
 