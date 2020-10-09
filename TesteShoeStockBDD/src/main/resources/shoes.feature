#Author: Marcos
Feature: Teste cucumber ShoeStock

Eu quero pesquisar um sapato

  Scenario: Entra no site e pesquisar sapatos
    Given acessar site "https://www.shoestock.com.br/"
    When pesquiso "sapato"
    Then compro o sapato