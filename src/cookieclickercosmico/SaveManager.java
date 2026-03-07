package cookieclickercosmico;

import java.io.*;

public class SaveManager {
    
    private static final String ARQUIVO_SAVE = "save_cosmico.dat";

    public static void salvar(GameState estado) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SAVE))) {
            oos.writeObject(estado);
            System.out.println("Jogo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo: " + e.getMessage());
        }
    }

    public static GameState carregar() {
        File arquivo = new File(ARQUIVO_SAVE);
        
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SAVE))) {
                GameState estadoCarregado = (GameState) ois.readObject();
                System.out.println("Jogo carregado com sucesso!");
                return estadoCarregado;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar o save. Criando novo jogo. Erro: " + e.getMessage());
            }
        }
        
        System.out.println("Nenhum save encontrado. Iniciando novo jogo.");
        return new GameState(); 
    }
}