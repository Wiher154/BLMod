package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SanguinePoolPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:SanguinePoolPow";
    public static final String NAME = "Sanguine Pool";
    public static final String DESCRIPTION = "U r fcking pool man!";
    public static final String IMG_PATH = "img/BalefulJourneyPow.png";

    public SanguinePoolPow(AbstractCreature owner, int amount) {
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
            addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void atStartOfTurn() {
        this.amount = 0;
        addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        return 0;
    }

}
