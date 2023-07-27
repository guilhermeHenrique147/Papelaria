/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.fatec.DAO;

import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Guilherme
 */
public interface DAO <T>{
    public boolean insere(T dado) throws SQLException;
    public boolean remove(T dado) throws SQLException;
    public boolean altera(T dado) throws SQLException;
    public T buscaID(T dado) throws SQLException;
    public Collection <T> lista(String filtro) throws SQLException;
}
