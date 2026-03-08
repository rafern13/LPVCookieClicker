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
import cookieclickercosmico.Entidades.UpgradeAlienTurbinado;
import cookieclickercosmico.Entidades.UpgradeEntropiaCosmica;
import cookieclickercosmico.Entidades.UpgradeFornoNuclear;

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
    private double totalCookiesHistorico; // para conquistas
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
        
        upgradesDisponiveis.add(new UpgradeAlienTurbinado());
        upgradesDisponiveis.add(new UpgradeFornoNuclear());
        upgradesDisponiveis.add(new UpgradeEntropiaCosmica());        
        
        conquistas.add(new Achievement("Confeiteiro de mão cheia", "Prepare 100.000 cookies!", 100000));
        conquistas.add(new Achievement("O Início", "Prepare seus primeiros 100 cookies!", 100));
        conquistas.add(new Achievement("Velocidade da Luz", "Alcance uma produção de 100 cookies por segundo.", 0));
    }
    
    // Método auxiliar para contar unidades
    public int getQuantidadeDeItem(String nomeItem) {
        for (GameItem item : unidades) {
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                return item.getQuantidade();
            }
        }
        return 0;
    }
   
    public double getProducaoPorSegundo() {
        double producaoPorSegundo = 1.0;
        
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

    public List<Achievement> getConquistas() {
        return conquistas;
    }

    public List<GameItem> getListaUnidades() {
        return unidades;
    }
    
 

    // Tenta efetuar a compra de uma unidade
    public boolean comprarItem(String nomeDoItem) {
        
        for (cookieclickercosmico.Entidades.GameItem item : unidades) {
            
            if (item.getNome().equals(nomeDoItem)) {
                
                double preco = item.getCustoAtual();
                
                if (this.totalCookies >= preco) {
                    
                    this.totalCookies -= preco;
                    
                    item.comprar();
                    
                    return true; 
                    
                } else {
                    return false; 
                }
            }
        }
        
        // Se chegou aqui, é porque o item com esse nome não existe na lista
        return false; 
    }
    
    public boolean comprarUpgrade(String nomeDoUpgrade) {
        for (cookieclickercosmico.Entidades.Upgrade upgrade : upgradesDisponiveis) {
            
            if (upgrade.getNome().equals(nomeDoUpgrade)) {
                
                double preco = upgrade.getCusto();
                
                // Verifica se tem saldo E se o upgrade já não foi comprado
                if (this.totalCookies >= preco && !upgrade.isComprado()) {
                    
                    this.totalCookies -= preco;
                    
                    // Passa o motor do jogo (this) para o upgrade fazer a mágica dele
                    upgrade.comprar(this); 
                    
                    return true; 
                    
                } else {
                    return false; 
                }
            }
        }
        
        return false; 
    }    
    private void verificarConquistas() {
        for (Achievement c : conquistas) {
            // Lógica para Velocidade da Luz
            if (c.getNome().equals("Velocidade da Luz")) {
                if (getProducaoPorSegundo() >= 100.0) {
                    c.setDesbloqueada(true);
                    System.out.println("CONQUISTA DESBLOQUEADA: " + c.getNome());
                }
            }
            
            if (c.verificarDesbloqueio(this.totalCookiesHistorico)) {
                System.out.println("Nova Conquista Desbloqueada: " + c.getNome());
            }
        }
    }
    
    
}
