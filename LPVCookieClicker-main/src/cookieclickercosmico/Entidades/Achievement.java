package cookieclickercosmico.Entidades;

import java.io.Serializable;

public class Achievement implements Serializable {
    private String nome;
    private String descricao;
    private double metaDeCookies;
    private boolean desbloqueada;

    public Achievement(String nome, String descricao, double metaDeCookies) {
        this.nome = nome;
        this.descricao = descricao;
        this.metaDeCookies = metaDeCookies;
        this.desbloqueada = false; // Começa bloqueada
    }

    // Método que checa se a meta foi batida
    public boolean verificarDesbloqueio(double totalHistorico) {
        
        if (!desbloqueada && totalHistorico >= metaDeCookies) {
            this.desbloqueada = true;
            return true; // Retorna true apenas no exato momento que desbloqueia
        }
        return false;
    }

    // Getters
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public boolean isDesbloqueada() { return desbloqueada; }

    public void setDesbloqueada(boolean desbloqueada) {
        this.desbloqueada = desbloqueada;
    }
    
}