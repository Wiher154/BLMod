package BL.Powers;

import BL.BLCharacter;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodWalkPow extends AbstractPower{
    public static final String POWER_ID = "BLMod:BloodWalkPow";
    public static final String NAME = "Blood Walk Power";
    public static final String DESCRIPTION = "You basically walk in blood!";
    public static final String IMG_PATH = "img/BalefulJourneyPow.png";

    public BloodWalkPow(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.BUFF;
        this.description = DESCRIPTION;
        this.img = new Texture(IMG_PATH);
    }

    public void stackPower(int stackAmount){
        this.amount += stackAmount;
        if (this.amount <= 0)
            addToTop((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPower pow = this.owner.getPower("BLMod:Blood");
            int blockAmount = 0;
            if(pow != null)
                blockAmount = pow.amount;
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new Blood((AbstractCreature)AbstractDungeon.player, this.amount), this.amount));
            addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature) AbstractDungeon.player, blockAmount+this.amount));
        }
    }
}
