/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cookieclickercosmico.Telas;

import cookieclickercosmico.Entidades.GameItem;
import cookieclickercosmico.GameState;

/**
 *
 * @author sowbo
 */
public class TelaLoja extends javax.swing.JPanel {

    private GameState estado; // Variável para guardar o motor do jogo
    private cookieclickercosmico.MainFrame parentFrame; // Referência para a janela principal
    private javax.swing.JPanel painelAbasLoja;
    private javax.swing.JPanel painelContainerUnidades;
    private javax.swing.JPanel painelContainerCliques;
    private javax.swing.JPanel painelContainerUpgrades;

    public TelaLoja(GameState estado, cookieclickercosmico.MainFrame parentFrame) {
        this.estado = estado;
        this.parentFrame = parentFrame;
        initComponents();
        
        // 1. Define o layout da tela principal da Loja
        this.setLayout(new java.awt.BorderLayout());
        
        // 2. Cria o menu superior (Os botões de aba)
        javax.swing.JPanel painelMenuLoja = new javax.swing.JPanel();
        painelMenuLoja.setLayout(new java.awt.GridLayout(1, 3)); // 3 botões
        painelMenuLoja.setBackground(new java.awt.Color(0, 0, 0)); // Preto
        
        javax.swing.JButton btnAbaUnidades = new javax.swing.JButton("Unidades");
        javax.swing.JButton btnAbaCliques = new javax.swing.JButton("Poder de Clique");
        javax.swing.JButton btnAbaUpgrades = new javax.swing.JButton("Upgrades");
        
        // Função auxiliar para mudar as cores dos botões ao clicar
        java.util.function.Consumer<javax.swing.JButton> destacarBotao = (botaoAtivo) -> {
            btnAbaUnidades.setBackground(java.awt.Color.DARK_GRAY);
            btnAbaCliques.setBackground(java.awt.Color.DARK_GRAY);
            btnAbaUpgrades.setBackground(java.awt.Color.DARK_GRAY);
            
            // O botão ativo fica com um fundo roxo/azul indicando seleção
            botaoAtivo.setBackground(new java.awt.Color(40, 40, 100));
        };

        // Estilizando os botões da aba para combinar com o jogo
        btnAbaUnidades.setForeground(java.awt.Color.WHITE);
        btnAbaUnidades.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        btnAbaUnidades.setFocusPainted(false);
        
        btnAbaCliques.setForeground(java.awt.Color.WHITE);
        btnAbaCliques.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        btnAbaCliques.setFocusPainted(false);
        
        btnAbaUpgrades.setForeground(java.awt.Color.WHITE);
        btnAbaUpgrades.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        btnAbaUpgrades.setFocusPainted(false);
        
        painelMenuLoja.add(btnAbaUnidades);
        painelMenuLoja.add(btnAbaCliques);
        painelMenuLoja.add(btnAbaUpgrades);
        
        // 3. Cria o painel central que vai funcionar como um baralho de cartas
        painelAbasLoja = new javax.swing.JPanel();
        painelAbasLoja.setLayout(new java.awt.CardLayout());
        
        // 4. Cria os painéis que vão receber as listas
        painelContainerUnidades = new javax.swing.JPanel();
        painelContainerUnidades.setBackground(new java.awt.Color(0, 0, 0));
        
        painelContainerCliques = new javax.swing.JPanel();
        painelContainerCliques.setBackground(new java.awt.Color(0, 0, 0));
        
        painelContainerUpgrades = new javax.swing.JPanel();
        painelContainerUpgrades.setBackground(new java.awt.Color(0, 0, 0));
        
        // 4.1 Adiciona barras de rolagem (Scroll)
        javax.swing.JScrollPane scrollUnidades = new javax.swing.JScrollPane(painelContainerUnidades);
        scrollUnidades.setBorder(null);
        scrollUnidades.getVerticalScrollBar().setUnitIncrement(16); 
        
        javax.swing.JScrollPane scrollCliques = new javax.swing.JScrollPane(painelContainerCliques);
        scrollCliques.setBorder(null);
        scrollCliques.getVerticalScrollBar().setUnitIncrement(16); 
        
        javax.swing.JScrollPane scrollUpgrades = new javax.swing.JScrollPane(painelContainerUpgrades);
        scrollUpgrades.setBorder(null);
        scrollUpgrades.getVerticalScrollBar().setUnitIncrement(16); 
        
        // 5. Adiciona os Scrolls no baralho de cartas
        painelAbasLoja.add(scrollUnidades, "cardUnidades");
        painelAbasLoja.add(scrollCliques, "cardCliques");
        painelAbasLoja.add(scrollUpgrades, "cardUpgrades");
        
        // 6. A lógica de trocar de aba quando clica nos botões!
        btnAbaUnidades.addActionListener(e -> {
            destacarBotao.accept(btnAbaUnidades);
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelAbasLoja.getLayout());
            cl.show(painelAbasLoja, "cardUnidades");
            carregarLojaUnidades(); // Recarrega a lista
        });
        
        btnAbaCliques.addActionListener(e -> {
            destacarBotao.accept(btnAbaCliques);
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelAbasLoja.getLayout());
            cl.show(painelAbasLoja, "cardCliques");
            carregarLojaCliques(); 
        });
        
        btnAbaUpgrades.addActionListener(e -> {
            destacarBotao.accept(btnAbaUpgrades);
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelAbasLoja.getLayout());
            cl.show(painelAbasLoja, "cardUpgrades");
            carregarLojaUpgrades(); // Recarrega a lista
        });
        
        // 7. Monta tudo na tela da Loja
        this.add(painelMenuLoja, java.awt.BorderLayout.NORTH);
        this.add(painelAbasLoja, java.awt.BorderLayout.CENTER);
        
        // Carrega a aba de unidades por padrão ao abrir
        destacarBotao.accept(btnAbaUnidades);
        carregarLojaUnidades();
    }   
    
    public void carregarLojaUnidades() {
        // 1. Limpa a aba de unidades
        painelContainerUnidades.removeAll();
        painelContainerUnidades.setLayout(new javax.swing.BoxLayout(painelContainerUnidades, javax.swing.BoxLayout.Y_AXIS));

        // 2. Varre a lista de unidades do motor
        for (cookieclickercosmico.Entidades.GameItem item : estado.getListaUnidades()) {
            
            javax.swing.JPanel painelItem = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));
            painelItem.setBackground(new java.awt.Color(120, 120, 150)); // Cinza roxeado do Perfil
            painelItem.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 180), 2, true),
                javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            painelItem.setMaximumSize(new java.awt.Dimension(800, 120)); // Aumentei o espaço para caber o texto
            painelItem.setPreferredSize(new java.awt.Dimension(350, 120)); 
            
            // Lógica para exibir o texto de bloqueado / Nível
            String prefixoNivel = item.getQuantidade() > 0 ? "Lvl " + item.getQuantidade() + " - " : "🔒 Lvl 0 - ";
            String producaoAtual = item.getQuantidade() > 0 ? "<br>Total Desta Unidade: <b style='color:#98FB98;'>" + String.format("%.1f", item.getProducaoTotal()).replace(",", ".") + " C/s</b>" : "";
            String textoGanho = item.getQuantidade() > 0 ? "Aumento por level" : "Gera por compra";
            
            // Texto com os dados da unidade usando HTML para formatar melhor
            String texto = "<html><body style='width: 220px;'>" 
                         + "<b style='font-size:15px; color:white;'>" + prefixoNivel + item.getNome() + "</b><br/>"
                         + "<span style='color:#E0E0E0;'>Custo Próximo: " + String.format("%.0f", item.getCustoAtual()) + "<br>" + textoGanho + ": +" + item.getProximoGanho() + " C/s"
                         + producaoAtual + "</span>"
                         + "</body></html>";
                         
            javax.swing.JLabel lblInfo = new javax.swing.JLabel(texto);
            
            javax.swing.JButton btnComprar = new javax.swing.JButton("Comprar");
            btnComprar.setBackground(java.awt.Color.BLACK);
            btnComprar.setForeground(java.awt.Color.WHITE);
            btnComprar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnComprar.setFocusPainted(false);
            
            btnComprar.addActionListener(e -> {
                boolean sucesso = estado.comprarItem(item.getNome());
                if (sucesso) {
                    carregarLojaUnidades(); // Recarrega para atualizar os preços!
                    if (parentFrame != null) parentFrame.atualizarTextos(); // Atualiza contador de cookies global
                } else {
                    System.out.println("Cookies insuficientes para comprar " + item.getNome());
                }
            });
            
            painelItem.add(lblInfo, java.awt.BorderLayout.CENTER);
            painelItem.add(btnComprar, java.awt.BorderLayout.EAST);
            
            painelContainerUnidades.add(painelItem);
            painelContainerUnidades.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
        }
        
        painelContainerUnidades.revalidate();
        painelContainerUnidades.repaint();
    }
    
    public void carregarLojaCliques() {
        painelContainerCliques.removeAll();
        painelContainerCliques.setLayout(new javax.swing.BoxLayout(painelContainerCliques, javax.swing.BoxLayout.Y_AXIS));

        for (cookieclickercosmico.Entidades.GameItem item : estado.getListaCliques()) {
            
            javax.swing.JPanel painelItem = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));
            painelItem.setBackground(new java.awt.Color(90, 70, 110)); // Uma cor um pouquinho mais roxa pra diferenciar
            painelItem.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(180, 150, 200), 2, true),
                javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            painelItem.setMaximumSize(new java.awt.Dimension(800, 120));
            painelItem.setPreferredSize(new java.awt.Dimension(350, 120)); 
            
            String prefixoNivel = item.getQuantidade() > 0 ? "Lvl " + item.getQuantidade() + " - " : "🔒 Lvl 0 - ";
            String producaoAtual = item.getQuantidade() > 0 ? "<br>Poder Deste Item: <b style='color:#98FB98;'>+" + String.format("%.1f", item.getProducaoTotal()).replace(",", ".") + " Cliques</b>" : "";
            String textoGanho = item.getQuantidade() > 0 ? "Aumento por level" : "Força adicionada";
            
            String texto = "<html><body style='width: 220px;'>" 
                         + "<b style='font-size:15px; color:white;'>" + prefixoNivel + item.getNome() + "</b><br/>"
                         + "<span style='color:#E0E0E0;'>Custo Próximo: " + String.format("%.0f", item.getCustoAtual()) + "<br>" + textoGanho + ": +" + item.getProximoGanho() + " por clique"
                         + producaoAtual + "</span>"
                         + "</body></html>";
                         
            javax.swing.JLabel lblInfo = new javax.swing.JLabel(texto);
            
            javax.swing.JButton btnComprar = new javax.swing.JButton("Comprar");
            btnComprar.setBackground(java.awt.Color.BLACK);
            btnComprar.setForeground(java.awt.Color.WHITE);
            btnComprar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnComprar.setFocusPainted(false);
            
            btnComprar.addActionListener(e -> {
                boolean sucesso = estado.comprarPoderDeClique(item.getNome());
                if (sucesso) {
                    carregarLojaCliques(); 
                    if (parentFrame != null) parentFrame.atualizarTextos(); // Atualiza contador de cookies global
                } else {
                    System.out.println("Cookies insuficientes para comprar " + item.getNome());
                }
            });
            
            painelItem.add(lblInfo, java.awt.BorderLayout.CENTER);
            painelItem.add(btnComprar, java.awt.BorderLayout.EAST);
            
            painelContainerCliques.add(painelItem);
            painelContainerCliques.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
        }
        
        painelContainerCliques.revalidate();
        painelContainerCliques.repaint();
    }
    
    // Método 2: Carrega os Upgrades (Melhorias únicas)
    public void carregarLojaUpgrades() {
        // 1. Limpa o painel e arruma o layout de cima para baixo
        painelContainerUpgrades.removeAll();
        painelContainerUpgrades.setLayout(new javax.swing.BoxLayout(painelContainerUpgrades, javax.swing.BoxLayout.Y_AXIS));

        // 2. Varre a lista de upgrades do motor do jogo
        for (cookieclickercosmico.Entidades.Upgrade upg : estado.getUpgradesDisponiveis()) {
            
            javax.swing.JPanel painelItem = new javax.swing.JPanel(new java.awt.BorderLayout(10, 10));
            painelItem.setBackground(new java.awt.Color(120, 120, 150)); // Tema do perfil
            
            // Define a cor da borda dependendo se já comprou (Verde) ou não (Dourado)
            java.awt.Color corBorda = upg.isComprado() ? new java.awt.Color(50, 205, 50) : new java.awt.Color(255, 215, 0);
            
            painelItem.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(corBorda, 2, true),
                javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            painelItem.setMaximumSize(new java.awt.Dimension(800, 140)); // Aumentei o espaço para Upgrades
            painelItem.setPreferredSize(new java.awt.Dimension(350, 140));
            
            // Mostra o nome, a descrição real e o preço do upgrade
            String textoCusto = upg.isComprado() ? "<span style='color:#32CD32;'><b>Adquirido</b></span>" : "<span style='color:#FFD700;'><b>Custo: " + String.format("%.0f", upg.getCusto()) + "</b></span>";
            String texto = "<html><body style='width: 190px; margin: 0; padding: 0;'>" // Largura menor para forçar a quebra de linha antes de encostar no botão
                         + "<b style='font-size:15px; color:white;'>⭐ " + upg.getNome() + "</b><br/>"
                         + "<i style='color:#E0E0E0; font-size:11px;'>" + upg.getDescricao() + "</i><br/>"
                         + textoCusto
                         + "</body></html>";
            
            javax.swing.JLabel lblInfo = new javax.swing.JLabel(texto);
            javax.swing.JButton btnComprar = new javax.swing.JButton(upg.isComprado() ? "Ativo" : "Comprar");
            btnComprar.setBackground(upg.isComprado() ? new java.awt.Color(50, 150, 50) : java.awt.Color.BLACK);
            btnComprar.setForeground(java.awt.Color.WHITE);
            btnComprar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 12));
            btnComprar.setFocusPainted(false);
            btnComprar.setPreferredSize(new java.awt.Dimension(90, 60)); // Força botão mais quadradinho
            btnComprar.setEnabled(!upg.isComprado()); // Desabilita o botão se já comprido
            
            // 4. Ação de clique exclusiva para upgrades
            btnComprar.addActionListener(e -> {
                // Chama um método novo no GameState feito só para Upgrades
                boolean sucesso = estado.comprarUpgrade(upg.getNome());
                
                if (sucesso) {
                    // Recarrega a aba para atualizar o botão e a borda!
                    carregarLojaUpgrades(); 
                    if (parentFrame != null) parentFrame.atualizarTextos(); // Atualiza contador de cookies global
                } else {
                    System.out.println("Cookies insuficientes para o upgrade: " + upg.getNome());
                }
            });
            
            painelItem.add(lblInfo, java.awt.BorderLayout.CENTER);
            painelItem.add(btnComprar, java.awt.BorderLayout.EAST);
            
            painelContainerUpgrades.add(painelItem);
            painelContainerUpgrades.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
        }
        
        // 5. Atualiza o visual da tela
        painelContainerUpgrades.revalidate();
        painelContainerUpgrades.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelContainerLoja = new javax.swing.JPanel();

        painelContainerLoja.setBackground(new java.awt.Color(0, 0, 0));
        painelContainerLoja.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout painelContainerLojaLayout = new javax.swing.GroupLayout(painelContainerLoja);
        painelContainerLoja.setLayout(painelContainerLojaLayout);
        painelContainerLojaLayout.setHorizontalGroup(
            painelContainerLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        painelContainerLojaLayout.setVerticalGroup(
            painelContainerLojaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelContainerLoja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelContainerLoja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel painelContainerLoja;
    // End of variables declaration//GEN-END:variables
}
