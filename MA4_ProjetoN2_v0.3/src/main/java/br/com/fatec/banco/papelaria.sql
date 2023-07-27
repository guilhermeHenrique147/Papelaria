/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Guilherme
 * Created: 26 de mai. de 2023
 */
/*CREATE DATABASE Papelaria;*/

USE Papelaria;

-- Criar tabela Produtos
CREATE TABLE Produtos (
    idProduto INT PRIMARY KEY AUTO_INCREMENT,
    nomeProduto VARCHAR(50) NOT NULL,
    valor VARCHAR(50) NOT NULL,
    qtde VARCHAR(10) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    fornecedor VARCHAR(50) NOT NULL
);

-- Criar tabela Pedidos
CREATE TABLE Pedidos (
    idPedido INT PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(11) NOT NULL,
    valor VARCHAR(50) NOT NULL,
    produto VARCHAR(50) NOT NULL,
    qtde VARCHAR(10) NOT NULL
);

-- Criar tabela Clientes
CREATE TABLE Clientes (
    cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    celular VARCHAR(15) NOT NULL,
    datanasc VARCHAR(10) NOT NULL
);

-- Criar tabela Login -> admin e funcionário
CREATE TABLE Funcionarios (
    email VARCHAR(30) PRIMARY KEY,
    nome VARCHAR(30) NOT NULL,
    senha VARCHAR(30) NOT NULL,
    datanasc DATE NOT NULL
);

--Criar tabela Fornecedores
CREATE TABLE Fornecedores (
    idFornecedor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE Usuarios (
    idUsuarios INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL
);