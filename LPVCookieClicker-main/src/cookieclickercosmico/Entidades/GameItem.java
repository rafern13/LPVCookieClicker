package cookieclickercosmico.Entidades;

import java.io.Serializable;

public class GameItem implements Serializable {
    
    // 1. ID de versão para proteger os saves dos jogadores nas próximas atualizações
    private static final long serialVersionUID = 1L;

    private String nome;
    private double custoBase;
    private double producaoBase;
    private double producaoPorNivel; // O quanto ganha por nível APÓS o primeiro
    private int quantidade;
    private double bonusMultiplicador;

    
    public GameItem(String nome, double custoBase, double producaoBase, double producaoPorNivel) {
        this.nome = nome;
        this.custoBase = custoBase;
        this.producaoBase = producaoBase;
        this.producaoPorNivel = producaoPorNivel;
        this.quantidade = 0;
    }

    // 2. Refatorado para arredondar o preço para cima (ex: 17.2 vira 18.0)
    public double getCustoAtual() {
        return Math.ceil(custoBase * Math.pow(1.15, quantidade));
    }

    public double getProducaoTotal() {
        if (quantidade == 0) return 0;
        // O primeiro nível dá a 'producaoBase' cheia. Os níveis extras dão a 'producaoPorNivel'
        return producaoBase + ((quantidade - 1) * producaoPorNivel);
    }
    
    // Método auxiliar para a loja saber quanto vai dar exatamente na próxima compra
    public double getProximoGanho() {
        if (quantidade == 0) return producaoBase;
        return producaoPorNivel;
    }
    
    public void comprar() {
        this.quantidade++;
    }

    // --- GETTERS E SETTERS ---
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCustoBase() {
        return custoBase;
    }

    public void setCustoBase(double custoBase) {
        this.custoBase = custoBase;
    }

    public double getProducaoBase() {
        return producaoBase;
    }

    public void setProducaoBase(double producaoBase) {
        this.producaoBase = producaoBase;
    }

    public double getProducaoPorNivel() {
        return producaoPorNivel;
    }

    public void setProducaoPorNivel(double producaoPorNivel) {
        this.producaoPorNivel = producaoPorNivel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}