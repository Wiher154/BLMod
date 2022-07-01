package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class PainfulMemoriesPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:PainfulMemoriesPow";
    public static final String NAME = "Painful Memories";
    public static final String DESCRIPTION = "Try not remember too much...";
    public static final String IMG_PATH = "img/BalefulJourneyPow.png";

    public PainfulMemoriesPow(AbstractCreature owner, int amount) {
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
            this.addToBot((AbstractGameAction)new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,this.amount));
            this.stackPower(-this.amount);
        }
    }

}
