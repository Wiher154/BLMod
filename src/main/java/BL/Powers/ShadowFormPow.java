package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

public class ShadowFormPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:ShadowFormPow";
    public static final String NAME = "Shadow Form";
    public static final String DESCRIPTION = "When you gain Block get that much Dexterity";
    public static final String IMG_PATH = "img/powers/Shadow form pow.png";

    public ShadowFormPow(AbstractCreature owner, int amount) {
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

    public void onGainedBlock(float blockAmount) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, this.amount), this.amount));
    }


}
