package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodWalkPow extends AbstractPower{
    public static final String POWER_ID = "BLMod:BloodWalkPow";
    public static final String NAME = "Blood Walk";
    public static final String DESCRIPTION = "At end of your turn gain Blood and Block";
    public static final String IMG_PATH = "img/powers/Blood walk pow.png";

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
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPower pow = this.owner.getPower("BLMod:Blood");
            int blockAmount = 0;
            if(pow != null)
                blockAmount = pow.amount;
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Blood(AbstractDungeon.player, this.amount), this.amount));
            this.addToBot(new GainBlockAction( AbstractDungeon.player, blockAmount+this.amount));
        }
    }
}
