/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec;

import br.com.fatec.Model.Cliente;
import java.net.URL;
import java.util.HashSet;
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

public class CadastraclienteController implements Initializable {

    @FXML
    private Button btn_sair;
    @FXML
    private GridPane gridDados;
    @FXML
    private TextField txt_cpf;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_nome;
    @FXML
    private GridPane gridDados1;
    @FXML
    private TextField txt_telefone;
    @FXML
    private TextField txt_data;
    @FXML
    private TextField txt_celular;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_editar;
    @FXML
    private Button btn_salvar;

    private HashSet<Cliente> clientes;
    
    private Cliente cliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientes = new HashSet<>();
        Cliente cliente = new Cliente("46563988881", "Guilherme", "gui@gui.com", "46563988", "946563988", "31/10/1999");
        clientes.add(cliente);
        txt_cpf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                handleTextFieldFocusLost();
            }
        });
    }
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    private Cliente moveDadosTelaModel() {
        cliente = new Cliente();
        cliente.setCpf(txt_cpf.getText());
        cliente.setNome(txt_nome.getText());
        cliente.setEmail(txt_email.getText());
        cliente.setTelefone(txt_telefone.getText());
        cliente.setCelular(txt_celular.getText());
        cliente.setDataNasc(txt_data.getText());
        
        return cliente;
    }
    
    private void moveDadosModelTela(Cliente c) {
        txt_cpf.setText(c.getCpf());
        txt_nome.setText(c.getNome());
        txt_email.setText(c.getEmail());
        txt_telefone.setText(c.getTelefone());
        txt_celular.setText(c.getCelular());
        txt_data.setText(c.getDataNasc());
        
    }
    @FXML
    public void btn_sair_onclick(ActionEvent event) {
        Stage stage = (Stage) btn_sair.getScene().getWindow();
        stage.close();
    }

    private void handleTextFieldFocusLost() {
        // Codigo a ser executado quando o textField perder o foco
        fetchRecord();
    }

    public void fetchRecord() {
        String primaryKey = txt_cpf.getText();

        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(primaryKey)) {
                txt_nome.setText(cliente.getNome());
                txt_email.setText(cliente.getEmail());
                txt_telefone.setText(cliente.getTelefone());
                txt_celular.setText(cliente.getCelular());
                txt_data.setText(cliente.getDataNasc());
                return;
            }
        } 
    }

    @FXML
    private void handleEditButtonAction() {
        if(!validarCampos()){
            mensagem("Preencher todos os campos", Alert.AlertType.NONE);
            return;
        }

        Cliente clienteEditado = moveDadosTelaModel();
        String primaryKeyValue = txt_cpf.getText();

        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(primaryKeyValue)) {
                clientes.remove(cliente);
                clientes.add(clienteEditado);
                mensagem("Dados Alterados com Sucesso", AlertType.NONE);
                limparCampos();
                return;
        }
    }

        mensagem("Falha ao alterar os dados", AlertType.NONE);
        limparCampos();
    }

    @FXML
    private void handleDeleteButtonAction() {
        if(!validarCampos()){
            mensagem("Preencher todos os campos", Alert.AlertType.NONE);
            return;
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
        
        String primaryKeyValue = txt_cpf.getText();
        cliente = moveDadosTelaModel();

        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(primaryKeyValue)) {
                clientes.remove(cliente);
                mensagem("Dados Excluidos com Sucesso", AlertType.NONE);
                limparCampos();
                return;
            }
        }

        mensagem("Erro ao Excluir os dados", AlertType.NONE);
        limparCampos();
    }

    @FXML
    public void handleSalvarButtonAction() {
        if(!validarCampos()){
            mensagem("Preencher todos os campos", Alert.AlertType.NONE);
            return;
        }

        cliente = moveDadosTelaModel();
        
        try{
            if(clientes.add(cliente)){
                mensagem("Dados Incluidos com Sucesso", Alert.AlertType.NONE);
                limparCampos();
                txt_cpf.requestFocus();
            }
        } catch(Exception ex){
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }
    
    private boolean validarCampos() {
        if(txt_cpf.getText().length() == 0 ||
           txt_nome.getText().length() == 0 ||
           txt_email.getText().length() == 0 ||
           txt_telefone.getText().length() == 0 ||
           txt_celular.getText().length() == 0 ||
           txt_data.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }
    private void limparCampos() {
        txt_cpf.setText("");
        txt_nome.setText("");
        txt_email.setText("");
        txt_telefone.setText("");
        txt_celular.setText("");
        txt_data.setText("");
    }

}

