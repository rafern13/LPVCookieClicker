/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookieclickercosmico.Entidades;


public class GameItem {
    private String nome;
    private double custoBase;
    private double producaoBase;
    private int quantidade; // Substitui o contador static

    public GameItem(String nome, double custoBase, double producaoBase) {
        this.nome = nome;
        this.custoBase = custoBase;
        this.producaoBase = producaoBase;
        this.quantidade = 0;
    }

    public double getCustoAtual() {
        // Fórmula de progressão exponencial sugerida para o gênero [cite: 61, 62]
        return custoBase * Math.pow(1.15, quantidade);
    }

    public double getProducaoTotal() {
        return producaoBase * quantidade;
    }
    
    public void comprar() {
        this.quantidade++;
    }
    
}
