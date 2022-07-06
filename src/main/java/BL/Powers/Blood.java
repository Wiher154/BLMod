package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Blood extends AbstractPower {
    public static final String POWER_ID = "BLMod:Blood";
    public static final String NAME = "Blood";
    public static final String DESCRIPTION = "Use it for powerful effects!";
    public static final String IMG_PATH = "img/powers/Blood.png";

    public Blood(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTION;
        this.img = new Texture(IMG_PATH);
    }

    public void stackPower(int stackAmount){
        this.amount += stackAmount;
        if (this.amount <= 0)
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

}
