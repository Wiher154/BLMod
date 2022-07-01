package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodOathPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:BloodOathPow";
    public static final String NAME = "Blood Oath";
    public static final String DESCRIPTION = "U MADE AN OATH";
    public static final String IMG_PATH = "img/BalefulJourneyPow.png";

    public BloodOathPow(AbstractCreature owner, int amount) {
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

    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer) {
            AbstractPlayer p = AbstractDungeon.player;
            addToBot((AbstractGameAction)new LoseHPAction(p,p,this.amount));
            addToBot((AbstractGameAction)new DrawCardAction(p,this.amount));
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new Blood(p,this.amount),this.amount ));
        }

    }
}
