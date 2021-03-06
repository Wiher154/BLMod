package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class SanguineFormPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:SanguineFormPow";
    public static final String NAME = "Sanguine Form";
    public static final String DESCRIPTION = "When you gain Blood get that much Strength";
    public static final String IMG_PATH = "img/powers/Sanguine form pow.png";

    public SanguineFormPow(AbstractCreature owner, int amount) {
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
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power.ID.equals("BLMod:Blood") && power.amount > 0)
            this.addToBot(new ApplyPowerAction(source, source, new StrengthPower(source, power.amount), power.amount));
    }
}
