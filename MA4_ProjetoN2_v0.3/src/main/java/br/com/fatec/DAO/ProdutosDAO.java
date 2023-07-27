/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Fornecedor;
import br.com.fatec.Model.Produto;
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
public class ProdutosDAO implements DAO<Produto> {
    private Produto produto;
    private PreparedStatement pst;
    private ResultSet rs;
    
    @Override
    public boolean insere(Produto dado) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
        
        
        String sql = "INSERT INTO Produto (idProduto, nomeProduto, "
                   + "valor, descricao, fornecedor) values (?, ?, ?, ?, ?, ?)";
        
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
 
        pst.setInt(1, dado.getIdProduto()); //1º interrogação
        pst.setString(2, dado.getNome()); 
        pst.setFloat(3, dado.getValor()); 
        pst.setString(4, dado.getDescricao());
        pst.setString(5, dado.getFornecedor()); 
        
    
        if(pst.executeUpdate() > 0)
            inseriu = true; 
        else
            inseriu = false; 
        

        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Produto dado) throws SQLException {
        boolean removeu;
        
        Banco.conectar();
        
        String sql = "DELETE FROM Produto WHERE idProduto = ?";
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setInt(1, dado.getIdProduto()); 
        
        if(pst.executeUpdate() > 0)
            removeu = true; 
        else
            removeu = false; 
        
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Produto dado) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
        
        
        String sql = "UPDATE Produto SET nome = ?, valor = ?, "
                   + "descricao = ?, idFornecedor = ? WHERE idProduto = ?";
        
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, dado.getNome()); 
        pst.setFloat(2, dado.getValor());
        pst.setString(3, dado.getDescricao());
        pst.setString(4, dado.getFornecedor()); 
        
        if(pst.executeUpdate() > 0)
            inseriu = true; 
        else
            inseriu = false; 
      
        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public Produto buscaID(Produto dado) throws SQLException {
        FornecedorDAO dao = new FornecedorDAO();
        produto = null;
        //Comando SELECT
        String sql = "SELECT * FROM Produto WHERE idProduto = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdProduto());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto produto
            produto = new Produto();
            //move os dados do resultSet para o objeto produto
            produto.setIdProduto(rs.getInt("idProduto"));
            produto.setNome(rs.getString("nomeProduto"));
            produto.setValor(rs.getFloat("valor"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setFornecedor(rs.getString("fornecedor"));            
        }
        
        Banco.desconectar();
        
        return produto;
    }

    @Override
    public Collection<Produto> lista(String filtro) throws SQLException {
        //para acessar o fornecedor
        FornecedorDAO dao = new FornecedorDAO();

        //criar uma coleção
        Collection<Produto> listagem = new ArrayList<>();
        
        produto = null;
        //Comando SELECT
        String sql = "SELECT * FROM Produto ";
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
            produto = new Produto();
            //move os dados do resultSet para o objeto produto
            produto.setIdProduto(rs.getInt("idProduto"));
            produto.setNome(rs.getString("nomeProduto"));
            produto.setValor(rs.getFloat("valor"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setFornecedor(rs.getString("fornecedor"));
            
            //adicionar na coleção
            listagem.add(produto);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}
