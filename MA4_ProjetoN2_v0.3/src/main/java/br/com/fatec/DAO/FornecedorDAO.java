/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.DAO;

import br.com.fatec.Model.Fornecedor;
import br.com.fatec.Persistencia.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Guilherme
 */
public class FornecedorDAO implements DAO<Fornecedor>{
    private Fornecedor fornecedor;
    private PreparedStatement pst;
    private ResultSet rs;
    
    @Override
    public boolean insere(Fornecedor dado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(Fornecedor dado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean altera(Fornecedor dado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Fornecedor buscaID(Fornecedor dado) throws SQLException {
        fornecedor = null;
        //Comando SELECT
        String sql = "SELECT * FROM Proprietario WHERE codProprietario = ?";
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setInt(1, dado.getIdFornecedor());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            //cria o objeto fornecedor
            fornecedor = new Fornecedor();
            //move os dados do resultSet para o objeto fornecedor
            fornecedor.setIdFornecedor(rs.getInt("codFornecedor"));
            fornecedor.setNomeFornecedor(rs.getString("nome"));
        }
        
        Banco.desconectar();
        
        return fornecedor;
    }

    @Override
    public Collection<Fornecedor> lista(String filtro) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
