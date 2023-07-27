/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.Model;

/**
 *
 * @author Guilherme
 */
public class Fornecedor {
    private int idFornecedor;
    private String nomeFornecedor;

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nomeFornecedor) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
    }
    
    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.idFornecedor;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fornecedor other = (Fornecedor) obj;
        return this.idFornecedor == other.idFornecedor;
    }

    @Override
    public String toString() {
        return getNomeFornecedor();
    }
       
}
