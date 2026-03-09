/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cookieclickercosmico.Telas;

import cookieclickercosmico.GameState;

/**
 *
 * @author sowbo
 */
public class TelaReviver extends javax.swing.JPanel {

    private GameState estado; // Variável para guardar o motor do jogo

    public TelaReviver(GameState estado) {
        this.estado = estado;
        initComponents();
        initComponentsPersonalizado();
    }

    private void initComponentsPersonalizado() {
        this.setLayout(new java.awt.BorderLayout());
        
        // Fundo principal
        javax.swing.JPanel painelFundo = new javax.swing.JPanel();
        painelFundo.setBackground(new java.awt.Color(20, 10, 40)); 
        painelFundo.setLayout(new javax.swing.BoxLayout(painelFundo, javax.swing.BoxLayout.Y_AXIS));
        painelFundo.setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Título
        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("O FIM DE UMA ERA...");
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 28));
        lblTitulo.setForeground(new java.awt.Color(255, 215, 0)); // Dourado
        lblTitulo.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        // Descrição Encurtada
        String multAtual = String.format("%.0fx", estado.getMultiplicadorPrestigio());
        String multNovo = String.format("%.0fx", estado.getMultiplicadorPrestigio() + 1.0);
        
        javax.swing.JLabel lblDescr = new javax.swing.JLabel(
            "<html><div style='text-align: center; color: white; font-size: 16px;'>" +
            "Deseja Evoluir e recomeçar seu império?<br><br>" +
            "<b style='color: #00FFCC;'>Multiplicador Universal:<br><br>" +
            "<span style='font-size: 24px;'>" + multAtual + " &nbsp; &#10145; &nbsp; " + multNovo + "</span></b>" +
            "</div></html>"
        );
        lblDescr.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        // Painel dos Botões
        javax.swing.JPanel painelBotoes = new javax.swing.JPanel();
        painelBotoes.setBackground(new java.awt.Color(20, 10, 40));
        painelBotoes.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));

        // Botão Evoluir
        javax.swing.JButton btnEvoluir = new javax.swing.JButton("EVOLUIR (Renascer)");
        btnEvoluir.setBackground(new java.awt.Color(138, 43, 226)); // Roxo cósmico
        btnEvoluir.setForeground(java.awt.Color.WHITE);
        btnEvoluir.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        btnEvoluir.setFocusPainted(false);
        btnEvoluir.addActionListener(e -> {
            int certeza = javax.swing.JOptionPane.showConfirmDialog(
                this, 
                "Você tem CERTEZA ABSOLUTA?\nIsso vai apagar todas as unidades, dinheiro e a força do seu clique, mas deixará o seu multiplicador permanente mais forte!",
                "Decisão Irreversível", 
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
            
            if (certeza == javax.swing.JOptionPane.YES_OPTION) {
                estado.reiniciarParaPrestigio();
                
                // Força o Game a voltar para o Inicio
                java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
                if (win instanceof cookieclickercosmico.MainFrame) {
                    ((cookieclickercosmico.MainFrame) win).iniciarJogo();
                    ((cookieclickercosmico.MainFrame) win).atualizarTextos();
                }
            }
        });

        // Botão Cancelar
        javax.swing.JButton btnCancelar = new javax.swing.JButton("Ainda Não");
        btnCancelar.setBackground(java.awt.Color.GRAY);
        btnCancelar.setForeground(java.awt.Color.WHITE);
        btnCancelar.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> {
            // Volta pra aba Perfil
            java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(this);
            if (win instanceof cookieclickercosmico.MainFrame) {
                // Não tem um método direto pra voltar, mas podemos chamar a atualização pra limpar
                // E usar o cardLayout. Como eu não tenho a ref do painelCartas aqui, peço pro Frame voltar pro inicio
                ((cookieclickercosmico.MainFrame) win).iniciarJogo();
            }
        });

        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnEvoluir);

        // Adiciona tudo no fundo
        painelFundo.add(lblTitulo);
        painelFundo.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 30)));
        painelFundo.add(lblDescr);
        painelFundo.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 50)));
        painelFundo.add(painelBotoes);

        // Adiciona o fundo na própria tela
        this.add(painelFundo, java.awt.BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
