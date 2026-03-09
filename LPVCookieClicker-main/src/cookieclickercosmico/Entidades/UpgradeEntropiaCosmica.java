package cookieclickercosmico.Entidades;

import cookieclickercosmico.GameState;

public class UpgradeEntropiaCosmica extends Upgrade {

    public UpgradeEntropiaCosmica() {
        super(
            "Entropia Positiva", 
            "Aumenta a eficiência de todas as suas unidades em 100% através da manipulação térmica.", 
            10000.0, 
            2.00 // 2.0 representa um aumento de 100% (dobro)
        );
    }

    @Override
    public void aplicarEfeito(GameState estado) {
        // Este efeito é global: ele percorre todas as unidades que o jogador possui
        // e aplica o multiplicador na produção base de cada uma.
        for (GameItem item : estado.getListaUnidades()) {
            double novaProducao = item.getProducaoBase() * this.bonusMultiplicador;
            item.setProducaoBase(novaProducao);
            
            double novoGanhoPorNivel = item.getProducaoPorNivel() * this.bonusMultiplicador;
            item.setProducaoPorNivel(novoGanhoPorNivel);
        }
        
        System.out.println("Upgrade aplicado: Produção global aumentada em 100%!");
    }
}