/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookieclickercosmico.Entidades;

import cookieclickercosmico.GameState;
import cookieclickercosmico.MainFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class GameTimer {
    private Timer timer;
    private GameState estadoDoJogo;
    private MainFrame telaPrincipal;

    public GameTimer(GameState estadoDoJogo, MainFrame telaPrincipal) {
        this.estadoDoJogo = estadoDoJogo;
        this.telaPrincipal = telaPrincipal;

        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processarUmSegundo();
            }
        });
    }

    // Métodos para controlar o tempo
    public void iniciar() {
        timer.start();
    }

    public void pausar() {
        timer.stop();
    }

    // A mágica acontece aqui a cada segundo
    private void processarUmSegundo() {
        double producaoGeral = estadoDoJogo.getProducaoPorSegundo();
       // System.out.println(producaoGeral);
        
        estadoDoJogo.adicionarCookies(producaoGeral);
        
        telaPrincipal.atualizarTextos();
    }
}