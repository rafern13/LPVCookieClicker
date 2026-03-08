/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookieclickercosmico.Entidades;

import java.io.Serializable;


public class GameItem implements Serializable {
    private String nome;
    private double custoBase;
    private double producaoBase;
    private int quantidade;
    
    public GameItem(String nome, double custoBase, double producaoBase) {
        this.nome = nome;
        this.custoBase = custoBase;
        this.producaoBase = producaoBase;
        this.quantidade = 0;
    }

    public double getCustoAtual() {
        return custoBase * Math.pow(1.15, quantidade);
    }

    public double getProducaoTotal() {
        return producaoBase * quantidade;
    }
    
    public void comprar() {
        this.quantidade++;
    }
    
}
