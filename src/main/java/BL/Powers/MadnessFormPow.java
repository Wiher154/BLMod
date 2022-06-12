package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MadnessFormPow extends AbstractPower{
    public static final String POWER_ID = "BLMod:MadnessFormPow";
    public static final String NAME = "Madness Form";
    public static final String DESCRIPTION = "Your madness progressing....and it's harmful.";

    public MadnessFormPow(AbstractCreature owner, int madnessAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = madnessAmount;
        this.type = AbstractPower.PowerType.BUFF;
        this.description = DESCRIPTION;
        this.img = new Texture("img/BalefulJourneyPow.png");


    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    public void onDrawOrDiscard(){
        AbstractMonster mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
        if(mon != null)
            if(!mon.isDying && mon.currentHealth > 0)
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mon, new DamageInfo(AbstractDungeon.player, this.amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));

    }





}
