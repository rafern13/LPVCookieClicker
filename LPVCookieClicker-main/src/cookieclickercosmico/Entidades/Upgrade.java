package cookieclickercosmico.Entidades;

import cookieclickercosmico.GameState;
import java.io.Serializable;

public abstract class Upgrade implements Serializable {
    private String nome;
    private String descricao;
    private double custo;
    protected double bonusMultiplicador; // Quanto ele vai somar no seu clique
    private boolean comprado;

    public Upgrade(String nome, String descricao, double custo, double bonusMultiplicador) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.bonusMultiplicador = bonusMultiplicador;
        this.comprado = false; // Todo upgrade começa não comprado
    }

    // A lógica de compra acontece aqui, interagindo diretamente com o GameState
    public boolean comprar(GameState estado) {
        // Verifica se ainda não foi comprado e se o jogador tem cookies suficientes
        if (!this.comprado && estado.getTotalCookies() >= this.custo) {
            estado.gastarCookies(this.custo);
            this.aplicarEfeito(estado); // Executa a habilidade unica de cada upgrade!
            this.comprado = true;
            return true; // Sucesso na compra
        }
        return false; // Falhou (sem cookies ou já comprado)
    }
    
    public abstract void aplicarEfeito(GameState estado);

    // Getters para a interface (TelaLoja) ler os dados
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getCusto() { return custo; }
    public boolean isComprado() { return comprado; }
}