package cookieclickercosmico.Entidades;

import cookieclickercosmico.Entidades.GameItem;
import cookieclickercosmico.Entidades.Upgrade;
import cookieclickercosmico.GameState;

public class UpgradeAlienTurbinado extends Upgrade {
    public UpgradeAlienTurbinado() {
        super("Café Espacial", "Dobra a produção dos Aliens Confeiteiros", 500.0, 2.0);
    }

    @Override
    public void aplicarEfeito(GameState estado) {
        for (GameItem item : estado.getListaUnidades()) {
            if (item.getNome().equals("Alien Confeiteiro")) {
                // Usa o bonusMultiplicador definido no super()
                item.setProducaoBase(item.getProducaoBase() * this.bonusMultiplicador);
                break;
            }
        }
    }
}