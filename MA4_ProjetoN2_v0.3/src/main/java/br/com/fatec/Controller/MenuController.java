/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
    
}
