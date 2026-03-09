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
public class TelaPerfil extends javax.swing.JPanel {

    private GameState estado;
    
    // Labels que precisam ser atualizadas
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblMultiplicador;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblTempoJogado;
    private javax.swing.JLabel lblTotalCookies;
    private javax.swing.JLabel lblConquistas;
    private javax.swing.JButton btnReviver;

    public TelaPerfil(GameState estado) {
        this.estado = estado;
        initComponentsPersonalizado();
    }
    
    private void initComponentsPersonalizado() {
        this.setBackground(new java.awt.Color(0, 0, 0)); // Fundo preto do espaço
        this.setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        
        // 1. Painel Superior (Cor de fundo roxa escura como no design)
        javax.swing.JPanel painelSuperior = new javax.swing.JPanel();
        painelSuperior.setBackground(new java.awt.Color(20, 10, 60)); // Roxo escuro
        painelSuperior.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        painelSuperior.setMaximumSize(new java.awt.Dimension(800, 120));
        
        // Ícone fake de avatar
        lblAvatar = new javax.swing.JLabel("👤");
        lblAvatar.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 60));
        lblAvatar.setForeground(java.awt.Color.WHITE);
        painelSuperior.add(lblAvatar);
        
        // Textos: Nome e Multiplicador
        javax.swing.JPanel painelTextosTopo = new javax.swing.JPanel();
        painelTextosTopo.setBackground(painelSuperior.getBackground());
        painelTextosTopo.setLayout(new javax.swing.BoxLayout(painelTextosTopo, javax.swing.BoxLayout.Y_AXIS));
        
        lblNome = new javax.swing.JLabel("Einstein o Gamer");
        lblNome.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 22));
        lblNome.setForeground(java.awt.Color.WHITE);
        
        lblMultiplicador = new javax.swing.JLabel("Multiplicador      1,00x");
        lblMultiplicador.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        lblMultiplicador.setForeground(new java.awt.Color(180, 180, 220));
        
        painelTextosTopo.add(lblNome);
        painelTextosTopo.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
        painelTextosTopo.add(lblMultiplicador);
        
        painelSuperior.add(painelTextosTopo);
        
        // 2. Painel Inferior (Lista de estatísticas)
        javax.swing.JPanel painelStats = new javax.swing.JPanel();
        painelStats.setBackground(new java.awt.Color(0, 0, 0));
        painelStats.setLayout(new javax.swing.BoxLayout(painelStats, javax.swing.BoxLayout.Y_AXIS));
        painelStats.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        lblNivel = criarLinhaEstatistica(painelStats, "Nível", "Nível 1 (0/100)");
        lblTempoJogado = criarLinhaEstatistica(painelStats, "Tempo jogado", "0m0s");
        lblTotalCookies = criarLinhaEstatistica(painelStats, "Biscoitos produzidos", "0");
        lblConquistas = criarLinhaEstatistica(painelStats, "Conquistas", "0/0");
        
        this.add(painelSuperior);
        this.add(painelStats);
        
        // Botão de Reviver escondido no final
        btnReviver = new javax.swing.JButton("REVIVER (New Game+)");
        btnReviver.setBackground(new java.awt.Color(200, 40, 40));
        btnReviver.setForeground(java.awt.Color.WHITE);
        btnReviver.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        btnReviver.setFocusPainted(false);
        btnReviver.setMaximumSize(new java.awt.Dimension(800, 50));
        btnReviver.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnReviver.setVisible(false); // Fica escondido até o nível 10
        
        btnReviver.addActionListener(e -> {
            java.awt.Component parent = this.getParent(); // O CardLayout
            if (parent instanceof java.awt.Container) {
                java.awt.Container container = (java.awt.Container) parent;
                java.awt.CardLayout cl = (java.awt.CardLayout) container.getLayout();
                cl.show(container, "cardReviver");
            }
        });
        
        this.add(btnReviver);
        
        atualizarPerfil(); // Atualiza na primeira vez
    }
    
    private javax.swing.JLabel criarLinhaEstatistica(javax.swing.JPanel container, String titulo, String valorInicial) {
        javax.swing.JPanel linha = new javax.swing.JPanel(new java.awt.BorderLayout());
        linha.setBackground(new java.awt.Color(120, 120, 150)); // Cinza roxeado do botão no design
        linha.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 20, 15, 20));
        linha.setMaximumSize(new java.awt.Dimension(800, 50));
        
        // Borda arredondada fake
        linha.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 150, 180), 2, true),
            javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        javax.swing.JLabel lblTitulo = new javax.swing.JLabel(titulo);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        lblTitulo.setForeground(java.awt.Color.WHITE);
        
        javax.swing.JLabel lblValor = new javax.swing.JLabel(valorInicial);
        lblValor.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        lblValor.setForeground(java.awt.Color.WHITE);
        
        linha.add(lblTitulo, java.awt.BorderLayout.WEST);
        linha.add(lblValor, java.awt.BorderLayout.EAST);
        
        container.add(linha);
        container.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 15))); // Espaço entre os botões
        
        return lblValor;
    }

    public void atualizarPerfil() {
        if (estado == null) return;
        
        long xp = (long) estado.getTotalCookiesHistorico();
        long nivel = estado.calcularNivelAtual();
        long[] metas = {50, 100, 1000, 10000, 50000, 100000, 200000, 300000, 500000, 1000000};
        
        String metaStr = "Max";
        if (nivel <= metas.length) {
            metaStr = String.valueOf(metas[(int)nivel - 1]);
        }
        
        lblNivel.setText("Nível " + nivel + " (" + xp + "/" + metaStr + ")");
        
        // Tempo Jogado
        long segundosInfo = estado.getSegundosJogados();
        long horas = segundosInfo / 3600;
        long minutos = (segundosInfo % 3600) / 60;
        long segundos = segundosInfo % 60;
        
        String tempoStr = "";
        if (horas > 0) tempoStr += horas + "h";
        tempoStr += minutos + "m" + segundos + "s";
        lblTempoJogado.setText(tempoStr);
        
        // Cookies Produzidos
        lblTotalCookies.setText(String.format("%.0f", estado.getTotalCookiesHistorico()));
        
        // Conquistas
        long concluidas = estado.getConquistas().stream().filter(cookieclickercosmico.Entidades.Achievement::isDesbloqueada).count();
        long total = estado.getConquistas().size();
        lblConquistas.setText(concluidas + "/" + total);
        
        lblMultiplicador.setText(String.format("Multiplicador      %.2fx", estado.getMultiplicadorPrestigio()));
        
        // Exibe o botão de reviver apenas se já for nível 10 ou maior
        if (btnReviver != null) {
            btnReviver.setVisible(nivel >= 10);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}
