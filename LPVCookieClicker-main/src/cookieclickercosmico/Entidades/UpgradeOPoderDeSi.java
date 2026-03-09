package cookieclickercosmico.Entidades;

import cookieclickercosmico.GameState;

public class UpgradeOPoderDeSi extends Upgrade {

    public UpgradeOPoderDeSi() {
        super(
            "O poder de SI", 
            "O ápice da existência. Multiplica a eficiência de todas as suas unidades em 1000%.", 
            100000.0, 
            11.0 // 11.0 representa um aumento de 1000% (+10 vezes a base, resultando em 11x)
        );
    }

    @Override
    public void aplicarEfeito(GameState estado) {
        // Este efeito é global e extremo: ele percorre todas as unidades que o jogador possui
        // e aplica o multiplicador massivo em ambas as escalas
        for (GameItem item : estado.getListaUnidades()) {
            double novaProducao = item.getProducaoBase() * this.bonusMultiplicador;
            item.setProducaoBase(novaProducao);
            
            double novoGanhoPorNivel = item.getProducaoPorNivel() * this.bonusMultiplicador;
            item.setProducaoPorNivel(novoGanhoPorNivel);
        }
        
        System.out.println("Upgrade aplicado: Produção global aumentada em 1000% graças a 'O poder de SI'!");
    }
}
