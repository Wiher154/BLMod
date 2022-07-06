package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
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
    public static final String DESCRIPTION = "Draw a card and lose life at start of your turn";
    public static final String IMG_PATH = "img/powers/Blood oath pow.png";

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
            this.addToTop( new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    public void atStartOfTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new LoseHPAction(p,p,this.amount));
        this.addToBot(new DrawCardAction(p,this.amount));
        this.addToBot(new ApplyPowerAction(p,p,new Blood(p,this.amount),this.amount ));
    }
}
