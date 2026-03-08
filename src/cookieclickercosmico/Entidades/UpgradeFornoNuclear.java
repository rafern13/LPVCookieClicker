package cookieclickercosmico.Entidades;


import cookieclickercosmico.Entidades.GameItem;
import cookieclickercosmico.Entidades.Upgrade;

import cookieclickercosmico.GameState;

public class UpgradeFornoNuclear extends Upgrade {
    public UpgradeFornoNuclear() {
        super("Núcleo de Supernova", "Aumenta a produção dos Fornos em 50%", 2500.0, 1.5);
    }

    @Override
    public void aplicarEfeito(GameState estado) {
        for (GameItem item : estado.getListaUnidades()) {
            if (item.getNome().equals("Forno Asteroide")) {
                item.setProducaoBase(item.getProducaoBase() * this.bonusMultiplicador);
                break;
            }
        }
    }
}