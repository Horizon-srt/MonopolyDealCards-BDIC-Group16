package Modules.Cards.impl;

import Core.GameController;
import Core.TerminalView;
import Modules.Player.impl.Player;

public class ForceDealCard extends ActionCard{
    public ForceDealCard(String name, int value) {
        super(name, value);
    }

    @Override
    public void use(GameController g, Player p) {
        TerminalView tv = g.tv;
        Player q = tv.getTargetplayer();
        PropertyCard targetProperty = tv.slyForceDealTargetProperty();
        PropertyCard ownProperty = tv.forceDealOwnProperty();
        q.getPp().removeCard(targetProperty);
        p.getPp().removeCard(ownProperty);
        q.getPp().setCard(ownProperty);
        p.getPp().setCard(targetProperty);
    }
}
