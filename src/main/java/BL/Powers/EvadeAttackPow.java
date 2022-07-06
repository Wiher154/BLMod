package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EvadeAttackPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:EvadeAttackPow";
    public static final String NAME = "Bat form";
    public static final String DESCRIPTION = "Evade next attack";
    public static final String IMG_PATH = "img/powers/Bat form pow.png";

    public EvadeAttackPow(AbstractCreature owner, int evadeAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = evadeAmount;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTION;
        this.img = new Texture(IMG_PATH);

    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if(info.owner != this.owner) {
            if (damageAmount > 0)
                this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
            return 0;
        }
        return damageAmount;
    }

}
