/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookieclickercosmico;

import cookieclickercosmico.Entidades.Achievement;
import java.util.ArrayList;
import java.util.List;
import cookieclickercosmico.Entidades.GameItem;
import cookieclickercosmico.Entidades.Upgrade;
import java.io.Serializable;


/**
 *
 * @author sowbo
 */
public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private double multiplicadorGlobal;
    private double totalCookies;
    private List<GameItem> unidades = new ArrayList<>();
    private double valorClique;
    private List<Upgrade> upgradesDisponiveis = new ArrayList<>();
    private double totalCookiesHistorico; // Não diminui quando você compra algo
    private List<Achievement> conquistas = new ArrayList<>();

    
    public GameState() {
        this.multiplicadorGlobal = 1.0;
        this.totalCookies = 0;
        this.totalCookiesHistorico = 0;
        
        // preencher unidades
        unidades.add(new GameItem("Alien Confeiteiro", 15, 0.1));
        unidades.add(new GameItem("Forno Asteroide", 100, 1.0));
        unidades.add(new GameItem("Misturador Quântico", 1100, 8.0));
        unidades.add(new GameItem("Compressor de Buraco Negro", 12000, 47.0));
        
        // add os upgrames disponiveis
        upgradesDisponiveis.add(new Upgrade("Dedo de Ouro", "Aumenta o clique em +1", 500, 1.0));
        upgradesDisponiveis.add(new Upgrade("Mouse Laser", "Aumenta o clique em +5", 5000, 5.0));
        
        conquistas.add(new Achievement("Confeiteiro de mão cheia", "Prepare 100.000 cookies!", 100000));
        conquistas.add(new Achievement("O Início", "Prepare seus primeiros 100 cookies!", 100));
    }
   
    public double getProducaoPorSegundo() {
        double producaoPorSegundo = 0.0;
        
        for (GameItem u : this.unidades ) {
            producaoPorSegundo += u.getProducaoTotal();
        }
        
        return producaoPorSegundo;
    }
    
    public void adicionarCookies(double producaoGeral) {
       this.totalCookies += producaoGeral; 
       this.totalCookiesHistorico += producaoGeral; // Conta para as conquistas
       verificarConquistas();
    }
    
    public void clicarNoCookie() {
        // Inicialmente, cada clique gera 1 cookie, afetado pelo multiplicador
        double valorDoClique = 1.0 * multiplicadorGlobal;
        this.totalCookies += valorDoClique;
    }
    
    // Método para descontar o valor da compra
    public void gastarCookies(double valor) {
        if (this.totalCookies >= valor) {
            this.totalCookies -= valor;
        }
    }

    // Método para melhorar a força do seu clique
    public void adicionarMultiplicadorDeClique(double bonus) {
        this.multiplicadorGlobal += bonus;
    }

    // Um getter para acessar a lista (útil para a TelaLoja)
    public List<Upgrade> getUpgradesDisponiveis() {
        return upgradesDisponiveis;
    }

    // Getters
    public double getTotalCookies() {
        return totalCookies;
    }
    
    private void verificarConquistas() {
        for (Achievement c : conquistas) {
            // Se o método retornar true, significa que acabou de desbloquear
            if (c.verificarDesbloqueio(this.totalCookiesHistorico)) {
                System.out.println("Nova Conquista Desbloqueada: " + c.getNome());
                // Aqui você pode disparar um popup na tela do jogador!
            }
        }
    }
    
    
}
