/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cookieclickercosmico;

import cookieclickercosmico.Entidades.GameTimer;

/**
 *
 * @author sowbo
 */
public class CookieClickerCosmico {

    public static void main(String[] args) {
        GameState meuJogo = SaveManager.carregar();

        java.awt.EventQueue.invokeLater(() -> {
            MainFrame telaPrincipal = new MainFrame(meuJogo);
            telaPrincipal.setVisible(true);
            
            GameTimer relogio = new GameTimer(meuJogo, telaPrincipal);
            relogio.iniciar();
        });
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            SaveManager.salvar(meuJogo);
        }));
    }
    
    
}
