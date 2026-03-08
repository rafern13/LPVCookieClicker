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
    private javax.swing.JPanel painelAbasLoja;
    private javax.swing.JPanel painelContainerUnidades;
    private javax.swing.JPanel painelContainerUpgrades;

    public TelaLoja(GameState estado) {
        this.estado = estado;
        initComponents();
        
        // 1. Define o layout da tela principal da Loja
        this.setLayout(new java.awt.BorderLayout());
        
        // 2. Cria o menu superior (Os botões de aba)
        javax.swing.JPanel painelMenuLoja = new javax.swing.JPanel();
        painelMenuLoja.setLayout(new java.awt.GridLayout(1, 2)); // Metade para cada botão
        
        javax.swing.JButton btnAbaUnidades = new javax.swing.JButton("Unidades");
        javax.swing.JButton btnAbaUpgrades = new javax.swing.JButton("Upgrades");
        
        // Estilizando os botões da aba para combinar com o jogo
        btnAbaUnidades.setBackground(java.awt.Color.DARK_GRAY);
        btnAbaUnidades.setForeground(java.awt.Color.WHITE);
        btnAbaUpgrades.setBackground(java.awt.Color.DARK_GRAY);
        btnAbaUpgrades.setForeground(java.awt.Color.WHITE);
        
        painelMenuLoja.add(btnAbaUnidades);
        painelMenuLoja.add(btnAbaUpgrades);
        
        // 3. Cria o painel central que vai funcionar como um baralho de cartas
        painelAbasLoja = new javax.swing.JPanel();
        painelAbasLoja.setLayout(new java.awt.CardLayout());
        
        // 4. Cria os dois painéis que vão receber as listas
        painelContainerUnidades = new javax.swing.JPanel();
        painelContainerUpgrades = new javax.swing.JPanel();
        
        // 5. Adiciona os painéis no baralho de cartas
        painelAbasLoja.add(painelContainerUnidades, "cardUnidades");
        painelAbasLoja.add(painelContainerUpgrades, "cardUpgrades");
        
        // 6. A lógica de trocar de aba quando clica nos botões!
        btnAbaUnidades.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelAbasLoja.getLayout());
            cl.show(painelAbasLoja, "cardUnidades");
            carregarLojaUnidades(); // Recarrega a lista
        });
        
        btnAbaUpgrades.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelAbasLoja.getLayout());
            cl.show(painelAbasLoja, "cardUpgrades");
            carregarLojaUpgrades(); // Recarrega a lista
        });
        
        // 7. Monta tudo na tela da Loja
        this.add(painelMenuLoja, java.awt.BorderLayout.NORTH);
        this.add(painelAbasLoja, java.awt.BorderLayout.CENTER);
        
        // Carrega a aba de unidades por padrão ao abrir
        carregarLojaUnidades();
    }   
    
    public void carregarLojaUnidades() {
        // 1. Limpa a aba de unidades
        painelContainerUnidades.removeAll();
        painelContainerUnidades.setLayout(new javax.swing.BoxLayout(painelContainerUnidades, javax.swing.BoxLayout.Y_AXIS));

        // 2. Varre a lista de unidades do motor
        for (cookieclickercosmico.Entidades.GameItem item : estado.getListaUnidades()) {
            
            javax.swing.JPanel painelItem = new javax.swing.JPanel();
            painelItem.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
            
            // Texto com os dados da unidade
            String texto = String.format("%s (Preço: %.0f) [+%.1f CpS]", 
                                         item.getNome(), item.getCustoAtual(), item.getProducaoBase());
            javax.swing.JLabel lblInfo = new javax.swing.JLabel(texto);
            
            javax.swing.JButton btnComprar = new javax.swing.JButton("Comprar");
            
            btnComprar.addActionListener(e -> {
                boolean sucesso = estado.comprarItem(item.getNome());
                if (sucesso) {
                    carregarLojaUnidades(); // Recarrega para atualizar os preços!
                } else {
                    System.out.println("Cookies insuficientes para comprar " + item.getNome());
                }
            });
            
            painelItem.add(lblInfo);
            painelItem.add(btnComprar);
            painelContainerUnidades.add(painelItem);
        }
        
        painelContainerUnidades.revalidate();
        painelContainerUnidades.repaint();
    }
    
    // Método 2: Carrega os Upgrades (Melhorias únicas)
    public void carregarLojaUpgrades() {
        // 1. Limpa o painel e arruma o layout de cima para baixo
        painelContainerUpgrades.removeAll();
        painelContainerUpgrades.setLayout(new javax.swing.BoxLayout(painelContainerUpgrades, javax.swing.BoxLayout.Y_AXIS));

        // 2. Varre a lista de upgrades do motor do jogo
        for (cookieclickercosmico.Entidades.Upgrade upg : estado.getUpgradesDisponiveis()) {
            
            // 3. A REGRA DE OURO: Só mostra na loja se ainda NÃO foi comprado!
            if (!upg.isComprado()) {
                
                javax.swing.JPanel painelItem = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
                
                // Mostra o nome e o preço do upgrade
                String texto = String.format("%s | Preço: %.0f", upg.getNome(), upg.getCusto());
                
                javax.swing.JLabel lblInfo = new javax.swing.JLabel(texto);
                javax.swing.JButton btnComprar = new javax.swing.JButton("Comprar");
                
                // 4. Ação de clique exclusiva para upgrades
                btnComprar.addActionListener(e -> {
                    // Chama um método novo no GameState feito só para Upgrades
                    boolean sucesso = estado.comprarUpgrade(upg.getNome());
                    
                    if (sucesso) {
                        // Recarrega a aba. Como isComprado() agora é true, ele não vai ser desenhado de novo!
                        carregarLojaUpgrades(); 
                    } else {
                        System.out.println("Cookies insuficientes para o upgrade: " + upg.getNome());
                    }
                });
                
                painelItem.add(lblInfo);
                painelItem.add(btnComprar);
                painelContainerUpgrades.add(painelItem);
            }
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
