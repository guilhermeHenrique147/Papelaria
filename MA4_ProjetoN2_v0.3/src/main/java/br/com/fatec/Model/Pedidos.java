/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.Model;

import java.util.HashSet;

/**
 *
 * @author Guilherme
 */
public class Pedidos {
    private int idPedido;
    private Cliente cliente;
    private float valor;
    private Produto produto;
    
    //contrutores
    public Pedidos() {
    }

    public Pedidos(int idPedido, Cliente cliente, float valor) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.valor = valor;
    }
    
    //equals, hashcode e to string

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idPedido;
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
        final Pedidos other = (Pedidos) obj;
        return this.idPedido == other.idPedido;
    }

    @Override
    public String toString() {
        return "Pedidos{" + "idPedido=" + idPedido + '}';
    }
    
    //getters e setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
    
}
