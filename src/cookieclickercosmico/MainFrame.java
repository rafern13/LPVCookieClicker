/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cookieclickercosmico;

/**
 *
 * @author sowbo
 */
public class MainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainFrame.class.getName());

    private GameState estado;
    private javax.swing.JPanel painelMenuInferior; 
    private cookieclickercosmico.Telas.TelaInicio telaInicio;
    /**
     * Creates new form MainFrame
     */
    public MainFrame(GameState estado) {
        this.estado = estado;
        initComponents(); // O NetBeans faz a bagunça dele aqui...
        
        // 1. A MARRETA: Forçamos a janela principal a usar o BorderLayout, ignorando o NetBeans!
        this.getContentPane().setLayout(new java.awt.BorderLayout());

        // 2. Transforma o painel central em um baralho de cartas
        painelCartas.setLayout(new java.awt.CardLayout());
        
        // 3. Adiciona as cartas
        telaInicio = new cookieclickercosmico.Telas.TelaInicio(this.estado); 
        cookieclickercosmico.Telas.TelaLoja telaLoja = new cookieclickercosmico.Telas.TelaLoja(this.estado);
        cookieclickercosmico.Telas.TelaConquistas telaConquistas = new cookieclickercosmico.Telas.TelaConquistas(this.estado);
        cookieclickercosmico.Telas.TelaPerfil telaPerfil = new cookieclickercosmico.Telas.TelaPerfil(this.estado);
        cookieclickercosmico.Telas.TelaMenu telaMenu = new cookieclickercosmico.Telas.TelaMenu(this.estado, this);
        cookieclickercosmico.Telas.TelaReviver telaReviver = new cookieclickercosmico.Telas.TelaReviver(this.estado);

        painelCartas.add(telaInicio, "cardInicio");
        painelCartas.add(telaLoja, "cardLoja");
        painelCartas.add(telaConquistas, "cardConquistas");
        painelCartas.add(telaPerfil, "cardPerfil");
        painelCartas.add(telaMenu, "cardMenu");
        painelCartas.add(telaReviver, "cardReviver");
        
        // 4. Cria e configura o menu inferior com GridLayout (1 linha, 4 colunas)
        painelMenuInferior = new javax.swing.JPanel();
        painelMenuInferior.setLayout(new java.awt.GridLayout(1, 4)); 
        
        // Criando os botões
        javax.swing.JButton btnInicio = new javax.swing.JButton("Início");
        javax.swing.JButton btnConquistas = new javax.swing.JButton("Conquistas");
        javax.swing.JButton btnLoja = new javax.swing.JButton("Loja");
        javax.swing.JButton btnPerfil = new javax.swing.JButton("Perfil");

        // --- APLICANDO O VISUAL (Preto com borda e texto brancos) ---
        javax.swing.JButton[] botoesMenu = {btnInicio, btnConquistas, btnLoja, btnPerfil};
        for (javax.swing.JButton btn : botoesMenu) {
            btn.setBackground(java.awt.Color.BLACK); // Fundo preto
            btn.setForeground(java.awt.Color.WHITE); // Texto branco
            btn.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.WHITE, 1)); // Borda branca fina
            btn.setFocusPainted(false); // Tira aquele quadrado feio de seleção ao clicar
            btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14)); // Deixa a fonte mais bonita
        }

        // --- ADICIONANDO AS AÇÕES DE CLIQUE ---
        btnInicio.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardInicio"); 
        });

        btnConquistas.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardConquistas"); 
        });

        btnLoja.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardLoja"); 
        });

        btnPerfil.addActionListener(e -> {
            java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
            cl.show(painelCartas, "cardPerfil"); 
        });

        // Adiciona os botões formatados no painel
        painelMenuInferior.add(btnInicio);
        painelMenuInferior.add(btnConquistas);
        painelMenuInferior.add(btnLoja);
        painelMenuInferior.add(btnPerfil);        
        
        // Mantém invisível até o jogador apertar o PLAY (se você já configurou o iniciarJogo)
        painelMenuInferior.setVisible(false); // Pode deixar true pra testar
        
        // 5. O SEGUNDO GOLPE DA MARRETA: Encaixa as peças nos lugares certos do BorderLayout
        this.getContentPane().add(painelCartas, java.awt.BorderLayout.CENTER); // Painel das telas vai no MEIO
        this.getContentPane().add(painelMenuInferior, java.awt.BorderLayout.SOUTH); // O menu vai embaixo (SUL)
        
        // 6. Mostra o Menu primeiro (ou o Início, para testar a navegação)
        java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
        cl.show(painelCartas, "cardMenu");
        
        // 7. Garante que a tela recarregue o visual com as novas peças
        this.revalidate();
        this.repaint();
    }

    public void iniciarJogo() {
        painelMenuInferior.setVisible(true);
        
        java.awt.CardLayout cl = (java.awt.CardLayout)(painelCartas.getLayout());
        cl.show(painelCartas, "cardInicio");
    }
    
    public void atualizarTextos() {
        System.out.println("atualizando");
        // Se a tela de início já foi criada, manda ela se atualizar
        if (this.telaInicio != null) {
            System.out.println("atualizando 2");

            this.telaInicio.atualizarTela(); 
        }
        
        // Mais pra frente, você vai adicionar a Loja aqui também!
        // if (this.telaLoja != null) {
        //     this.telaLoja.atualizarBotoesDeCompra();
        // }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        painelCartas = new javax.swing.JPanel();

        jButton2.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout painelCartasLayout = new javax.swing.GroupLayout(painelCartas);
        painelCartas.setLayout(painelCartasLayout);
        painelCartasLayout.setHorizontalGroup(
            painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        painelCartasLayout.setVerticalGroup(
            painelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 627, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelCartas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel painelCartas;
    // End of variables declaration//GEN-END:variables
}
