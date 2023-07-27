/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Cliente;
import br.com.fatec.Persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Guilherme
 */
public class ClientesDAO implements DAO <Cliente> {
    private Cliente cliente;
    private PreparedStatement pst;
    private ResultSet rs;

    @Override
    public boolean insere(Cliente dado) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
        
        
        String sql = "INSERT INTO Clientes (cpf, nome, "
                   + "email, telefone, celular, datanasc) values (?, ?, ?, ?, ?, ?, ?)";
        
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
 
        pst.setString(1, dado.getCpf()); //1º interrogação
        pst.setString(2, dado.getNome()); 
        pst.setString(3, dado.getEmail());
        pst.setString(4, dado.getTelefone()); 
        pst.setString(5, dado.getCelular());
        pst.setString(6, dado.getDataNasc()); 
        
    
        if(pst.executeUpdate() > 0)
            inseriu = true; 
        else
            inseriu = false; 
        

        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Cliente dado) throws SQLException {
        boolean removeu;
        
        Banco.conectar();
        
        String sql = "DELETE FROM Clientes WHERE cpf = ?";
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, dado.getCpf()); 
        
        if(pst.executeUpdate() > 0)
            removeu = true; 
        else
            removeu = false; 
        
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Cliente dado) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
          
        String sql = "UPDATE Clientes SET nome = ?, email = ?, "
                   + "telefone = ?, celular = ?, datanasc = ? WHERE cpf = ?";
         
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(2, dado.getNome()); 
        pst.setString(3, dado.getEmail());
        pst.setString(4, dado.getTelefone()); 
        pst.setString(5, dado.getCelular());
        pst.setString(6, dado.getDataNasc()); 
        
    
        if(pst.executeUpdate() > 0)
            inseriu = true; 
        else
            inseriu = false; 
        

        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public Cliente buscaID(Cliente dado) throws SQLException {
        cliente = null;
        //Comando SELECT
        String sql = "SELECT * FROM Clientes WHERE cpf = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, dado.getCpf());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto cliente
            cliente = new Cliente();
            //move os dados do resultSet para o objeto cliente
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome("nome");
            cliente.setEmail("email");
            cliente.setTelefone("telefone");
            cliente.setCelular("celular");
            cliente.setDataNasc("datanasc");
        }
        
        Banco.desconectar();
        
        return cliente;
    }

    @Override
    public Collection<Cliente> lista(String filtro) throws SQLException {
        Collection<Cliente> listagem = new ArrayList<>();
        
        cliente = null;
        //Comando SELECT
        String sql = "SELECT * FROM Cliente ";
        //colocar filtro ou nao
        if(filtro.length() != 0) {
            sql += "WHERE " + filtro;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        while(rs.next()) { //achou 1 registro
            //cria o objeto produto
            cliente = new Cliente();
            //move os dados do resultSet para o objeto cliente
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome("nome");
            cliente.setEmail("email");
            cliente.setTelefone("telefone");
            cliente.setCelular("celular");
            cliente.setDataNasc("datanasc");
           
            //adicionar na coleção
            listagem.add(cliente);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}
