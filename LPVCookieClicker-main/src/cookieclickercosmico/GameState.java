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
import cookieclickercosmico.Entidades.UpgradeOPoderDeSi;

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
    private List<GameItem> listaCliques = new ArrayList<>(); // Nova lista para itens de clique
    private double valorClique;
    private List<Upgrade> upgradesDisponiveis = new ArrayList<>();
    private double totalCookiesHistorico; // para conquistas
    private List<Achievement> conquistas = new ArrayList<>();
    private long segundosJogados;
    
    // Novas variáveis para o sistema de Prestígio (Reviver)
    private double multiplicadorPrestigio = 1.0;
    private boolean popupPrestigioMostrado = false;
    private long totalUnidadesCompradasNaRun = 0; // "franquias"
    private long totalUpgradesCompradosNaRun = 0; // "corpos celestes"

    
    public GameState() {
        this.multiplicadorGlobal = 1.0;
        this.multiplicadorPrestigio = 1.0;
        this.totalCookies = 0;
        this.totalCookiesHistorico = 0;
        
        inicializarItens();
    }
    
    private void inicializarItens() {
        // Limpa listas para o caso de estar reiniciando o jogo (Prestígio)
        unidades.clear();
        listaCliques.clear();
        upgradesDisponiveis.clear();
        conquistas.clear();
        
        // preencher unidades
        // Nome, Custo Inicial, Produção Inicial (Lvl 1), Produção Adicional (Lvl 2+)
        GameItem alien = new GameItem("Alien Confeiteiro", 15, 2.0, 1.5); // Produção x2 = 2.0, bônus nível x3 = 1.5
        alien.setQuantidade(1); // Já começa com 1 nível (Lvl 1 = +2.0)
        unidades.add(alien);
        
        unidades.add(new GameItem("Forno Asteroide", 100, 10.0, 3.0)); // Produção x2 = 10.0, bônus nível x3 = 3.0
        unidades.add(new GameItem("Misturador Quântico", 1100, 30.0, 9.0)); // Produção x2 = 30.0, bônus nível x3 = 9.0
        unidades.add(new GameItem("Compressor de Buraco Negro", 12000, 140.0, 45.0)); // Produção x2 = 140.0, bônus nível x3 = 45.0
        
        // preencher poderes de clique (base de produção funcionará como base de clique)
        listaCliques.add(new GameItem("Dedo Biônico", 50, 0.5, 0.5)); // +0.5 por clique sempre
        listaCliques.add(new GameItem("Mouse Quântico", 500, 4.0, 4.0));
        listaCliques.add(new GameItem("Mente Coletiva Alienígena", 3000, 15.0, 15.0));
        listaCliques.add(new GameItem("Clique de Antimatéria", 10000, 50.0, 50.0));
        
        upgradesDisponiveis.add(new UpgradeAlienTurbinado());
        upgradesDisponiveis.add(new UpgradeFornoNuclear());
        upgradesDisponiveis.add(new UpgradeEntropiaCosmica());        
        upgradesDisponiveis.add(new UpgradeOPoderDeSi());        
        
        conquistas.add(new Achievement("Confeiteiro de mão cheia", "Prepare 100.000 cookies!", 100000));
        conquistas.add(new Achievement("Magnata do Biscoito", "Tenha um montante total de 1.000.000 de cookies!", 1000000));
        conquistas.add(new Achievement("O Início", "Prepare seus primeiros 100 cookies!", 100));
        conquistas.add(new Achievement("Velocidade da Luz", "Alcance uma produção de 100 cookies por segundo.", 0));
        conquistas.add(new Achievement("O Renascimento", "Evolua suas indústrias e comece um Novo Jogo+.", 0));
    }
    
    // Sistema de Nível
    public long calcularNivelAtual() {
        long xp = (long) getTotalCookiesHistorico();
        
        // Metas exatas definidas pelo jogador
        long[] metas = {50, 100, 1000, 10000, 50000, 100000, 200000, 300000, 500000, 1000000};
        
        for (int i = 0; i < metas.length; i++) {
            if (xp < metas[i]) {
                return i + 1; // Se a exp for menor que o requisito do proximo nivel, este é o nivel atual (1 a 10)
            }
        }
        
        return metas.length + 1; // Se passar da ultima meta, ele é lvl 11+
    }
    
    // Reinicia tudo exceto as conquistas e o multiplicador de prestígio, que ganha +1.0
    public void reiniciarParaPrestigio() {
        this.multiplicadorPrestigio += 1.0;
        
        // Resetamos todos os contadores da rodada
        this.multiplicadorGlobal = 1.0;
        this.totalCookies = 0;
        this.totalCookiesHistorico = 0;
        this.segundosJogados = 0;
        this.totalUnidadesCompradasNaRun = 0;
        this.totalUpgradesCompradosNaRun = 0;
        
        // Salva as conquistas atuais antes de limpar para re-aplicar depois
        List<Achievement> conquistasSalvas = new ArrayList<>();
        for (Achievement c : this.conquistas) {
            if (c.isDesbloqueada()) {
                conquistasSalvas.add(c);
            }
        }
        
        // Reconstrói todo o motor do zero
        inicializarItens();
        
        // Restaura o status das conquistas que já foram ganhas antes de reviver
        for (Achievement ganha : conquistasSalvas) {
            for (Achievement atual : this.conquistas) {
                if (atual.getNome().equals(ganha.getNome())) {
                    atual.setDesbloqueada(true);
                }
            }
        }
        
        // Desbloqueia a conquista de Renascimento (afinal, acabou de reviver)
        for (Achievement c : this.conquistas) {
            if (c.getNome().equals("O Renascimento")) {
                c.setDesbloqueada(true);
            }
        }
    }
    
    // Getter para a tela de Pop-up e Reviver saber se já foi exibido
    public boolean isPopupPrestigioMostrado() {
        return popupPrestigioMostrado;
    }

    public void setPopupPrestigioMostrado(boolean popupPrestigioMostrado) {
        this.popupPrestigioMostrado = popupPrestigioMostrado;
    }
    
    public double getMultiplicadorPrestigio() {
        return multiplicadorPrestigio;
    }

    public long getTotalUnidadesCompradasNaRun() {
        return totalUnidadesCompradasNaRun;
    }

    public long getTotalUpgradesCompradosNaRun() {
        return totalUpgradesCompradosNaRun;
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
        
        // Multiplica a produção final base pelo Prestígio (New Game+)
        return producaoPorSegundo * multiplicadorPrestigio;
    }
    
    public void adicionarCookies(double producaoGeral) {
       this.totalCookies += producaoGeral; 
       this.totalCookiesHistorico += producaoGeral; // Conta para as conquistas
       this.segundosJogados++; // A cada segundo que adiciona cookie passivo, passa 1s
       verificarConquistas();
    }
    
    public void clicarNoCookie() {
        // Pega o valor real de clique que já processa os poderes comprados e os multiplicadores de prestígio/globais
        double valorDoClique = getValorPorClique();
        this.totalCookies += valorDoClique;
        this.totalCookiesHistorico += valorDoClique;
        verificarConquistas();
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

    public double getValorPorClique() {
        double baseClique = 1.0;
        
        for (GameItem cliqueItem : listaCliques) {
            baseClique += cliqueItem.getProducaoTotal(); // Usa a 'producaoTotal' como força do clique
        }
        
        // O multiplicador Global afeta o clique. E o Prestígio afeta o resultado absoluto de tudo.
        return (baseClique * multiplicadorGlobal) * multiplicadorPrestigio; 
    }

    public double getTotalCookiesHistorico() {
        return totalCookiesHistorico;
    }

    public long getSegundosJogados() {
        return segundosJogados;
    }

    public List<Achievement> getConquistas() {
        return conquistas;
    }

    public List<GameItem> getListaUnidades() {
        return unidades;
    }
    
    public List<GameItem> getListaCliques() {
        return listaCliques;
    }
    
 

    // Tenta efetuar a compra de uma unidade
    public boolean comprarItem(String nomeDoItem) {
        
        for (cookieclickercosmico.Entidades.GameItem item : unidades) {
            if (item.getNome().equals(nomeDoItem)) {
                double preco = item.getCustoAtual();
                if (this.totalCookies >= preco) {
                    this.totalCookies -= preco;
                    item.comprar();
                    totalUnidadesCompradasNaRun++; // Status estatistico da run
                    return true; 
                } else {
                    return false; 
                }
            }
        }
        return false; 
    }
    
    // Tenta efetuar a compra de um poder de clique
    public boolean comprarPoderDeClique(String nomeDoItem) {
        for (cookieclickercosmico.Entidades.GameItem item : listaCliques) {
            if (item.getNome().equals(nomeDoItem)) {
                double preco = item.getCustoAtual();
                if (this.totalCookies >= preco) {
                    this.totalCookies -= preco;
                    item.comprar();
                    totalUnidadesCompradasNaRun++; // Entra como compra de unidade
                    return true; 
                } else {
                    return false; 
                }
            }
        }
        return false;
    }
    
    public boolean comprarUpgrade(String nomeDoUpgrade) {
        for (cookieclickercosmico.Entidades.Upgrade upgrade : upgradesDisponiveis) {
            if (upgrade.getNome().equals(nomeDoUpgrade)) {
                // Passa o motor do jogo (this) para o upgrade fazer a mágica dele e processar o pagamento
                if (upgrade.comprar(this)) {
                    totalUpgradesCompradosNaRun++; // Adiciona para a estatistica da Run atual
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
                if (!c.isDesbloqueada() && getProducaoPorSegundo() >= 100.0) {
                    c.setDesbloqueada(true);
                    System.out.println("Nova Conquista Desbloqueada: " + c.getNome());
                }
            } else if (c.getNome().equals("O Renascimento")) {
                // Esta conquista só destrava lá dentro do reiniciarParaPrestigio(), então pulamos ela da checagem automática de Cookies
                continue; 
            } else {
                if (c.verificarDesbloqueio(this.totalCookiesHistorico)) {
                    System.out.println("Nova Conquista Desbloqueada: " + c.getNome());
                }
            }
        }
    }
    
    
}
