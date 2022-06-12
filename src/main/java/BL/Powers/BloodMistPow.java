package BL.Powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodMistPow extends AbstractPower {
    public static final String POWER_ID = "BLMod:BloodMistPow";
    public static final String NAME = "Blood Mist Power";
    public static final String DESCRIPTION = "BLOOD MIST!";
    private static boolean ready;

    public BloodMistPow(AbstractCreature owner, int mistAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = mistAmount;
        this.type = PowerType.BUFF;
        this.description = DESCRIPTION;
        ready = true;
        this.img = new Texture("img/BalefulJourneyPow.png");

    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && this.amount > 0 && ready) {
            for (int i = 0; i < this.amount; ++i) {
                this.flash();
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }

                AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = (float) Settings.HEIGHT / 2.0F;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }

                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            }
            ready = false;
        }
        if (this.amount == 0) {
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "BLMod:BloodMistPow"));
        }

    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            ready = true;
    }

}
