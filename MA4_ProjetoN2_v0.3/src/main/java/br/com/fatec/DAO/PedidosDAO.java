/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Cliente;
import br.com.fatec.Model.Pedidos;
import br.com.fatec.Model.Produto;
import br.com.fatec.Persistencia.Banco;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Guilherme
 */
public class PedidosDAO implements DAO<Pedidos> {
    private Pedidos pedidos;
    private PreparedStatement pst;
    private ResultSet rs;
  
    @Override
    public boolean insere(Pedidos dado) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
        
        
        String sql = "INSERT INTO Pedido (idPedido, cpf, "
                   + "valor, produto) values (?, ?, ?, ?)";
        
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
 
        pst.setInt(1, dado.getIdPedido()); //1º interrogação
        pst.setString(2, dado.getCliente().getCpf()); 
        pst.setFloat(3, dado.getValor());
        pst.setArray(4, (Array) dado.getProduto()); 
        
    
        if(pst.executeUpdate() > 0)
            inseriu = true; 
        else
            inseriu = false; 
        

        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public boolean remove(Pedidos dado) throws SQLException {
        boolean removeu;
        
        Banco.conectar();
        
        String sql = "DELETE FROM Pedido WHERE idPedido = ?";
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setInt(1, dado.getIdPedido()); 
        
        if(pst.executeUpdate() > 0)
            removeu = true; 
        else
            removeu = false; 
        
        Banco.desconectar();
        
        return removeu;
    }

    @Override
    public boolean altera(Pedidos dado) throws SQLException {
        boolean inseriu;
        
        Banco.conectar();
          
        String sql = "UPDATE Pedido SET cpf = ?, valor = ?, "
                   + "produto = ?, qtde = ? WHERE idPedido = ?";
         
        pst = Banco.obterConexao().prepareStatement(sql);
        
        pst.setString(1, dado.getCliente().getCpf()); 
        pst.setFloat(2, dado.getValor());
        pst.setArray(3, (Array) dado.getProduto()); 
        
    
        if(pst.executeUpdate() > 0)
            inseriu = true; 
        else
            inseriu = false; 
        

        Banco.desconectar();
        
        return inseriu;
    }

    @Override
    public Pedidos buscaID(Pedidos dado) throws SQLException {
        ClientesDAO dao = new ClientesDAO();
        ProdutosDAO daos = new ProdutosDAO();
        
        pedidos = null;
        //Comando SELECT
        String sql = "SELECT * FROM Pedido WHERE idPedido = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdPedido());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto pedido
            pedidos = new Pedidos();
            //move os dados do resultSet para o objeto pedido
            pedidos.setIdPedido(rs.getInt("idPedido"));
            pedidos.setValor(rs.getFloat("valor"));
            
            //precisa buscar os dados do cliente em clienteDAO
            Cliente cliente = new Cliente();
            //informa quem deve ser pego de cliente
            cliente.setCpf(rs.getString("cpf"));
            //faz a busca
            cliente = dao.buscaID(cliente);
            pedidos.setCliente(cliente);
            
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("produto"));
            produto = daos.buscaID(produto);
            pedidos.setProduto(produto);
          
        }
        
        Banco.desconectar();
        
        return pedidos;
    }

    @Override
    public Collection<Pedidos> lista(String filtro) throws SQLException {
        ClientesDAO dao = new ClientesDAO();
        ProdutosDAO daos = new ProdutosDAO();

        //criar uma coleção
        Collection<Pedidos> listagem = new ArrayList<>();
        
        pedidos = null;
        //Comando SELECT
        String sql = "SELECT * FROM Pedido ";
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
            //cria o objeto pedido
            pedidos = new Pedidos();
            //move os dados do resultSet para o objeto pedido
            pedidos.setIdPedido(rs.getInt("idPedido"));
            pedidos.setValor(rs.getFloat("valor"));
            
            //precisa buscar os dados do cliente em clienteDAO
            Cliente cliente = new Cliente();
            //informa quem deve ser pego de forncedor
            cliente.setCpf(rs.getString("cpf"));
            //faz a busca
            cliente = dao.buscaID(cliente);
            pedidos.setCliente(cliente);
            
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("produto"));
            produto = daos.buscaID(produto);
            pedidos.setProduto(produto);
            
            listagem.add(pedidos);   
        }
        
        Banco.desconectar();
        
        return listagem;
    }
    
}
