/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.Model;

/**
 *
 * @author Guilherme
 */
public class Produto {
    private int idProduto;
    private String nome;
    private int qtde;
    private String descricao;
    private String fornecedor;
    private float valor;
    
    //construtores
    public Produto() {
    }

    public Produto(int idProduto, String nome, int qtde, String descricao, String fornecedor, float valor) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.qtde = qtde;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.valor = valor;
    }
    
    //equals, hashcode e tostring
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idProduto;
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
        final Produto other = (Produto) obj;
        return this.idProduto == other.idProduto;
    }
    
    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + '}';
    }
    
    //getters e setters

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
