/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookieclickercosmico.componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

// A nossa classe herda tudo de um JButton normal, mas muda a aparência
public class BotaoRedondo extends JButton {

    public BotaoRedondo(String texto) {
        super(texto);
        // Desliga o fundo e a borda quadrada padrão do Swing
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    // 1. Pinta o fundo do botão em formato de círculo
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            // Cor quando o botão está sendo clicado (pressionado)
            g.setColor(Color.DARK_GRAY); 
        } else {
            // Cor normal do botão
            g.setColor(getBackground());
        }
        
        // Desenha a elipse (círculo) preenchida
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        
        // Chama o método original para desenhar o texto/ícone por cima
        super.paintComponent(g);
    }

    // 2. Desenha a linha da borda (opcional)
    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.WHITE); // Cor da borda
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // 3. O Pulo do Gato: Garante que o jogador só consiga clicar DENTRO do círculo
    private Shape formatoCirculo;
    @Override
    public boolean contains(int x, int y) {
        if (formatoCirculo == null || !formatoCirculo.getBounds().equals(getBounds())) {
            formatoCirculo = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return formatoCirculo.contains(x, y);
    }
}
