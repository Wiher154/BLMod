package BL.Cards.Power.Common;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class MadEye extends CustomCard {
    public static final String ID = "BLMod:MadEye";
    public static final String NAME = "Mad Eye";
    public static final String DESCRIPTION = "Weak !M! NL Frail !M! NL When i'm discarded remove !M! buff(s) from random enemy";
    public static final String IMG_PATH = "img/cards/Mad eye.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_AMOUNT = 1;


    public MadEye() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new MadEye();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_AMOUNT);
        }
    }

    public void triggerOnManualDiscard() {
        int removedAmount = 0;
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if(m.currentHealth>0 && !m.isDying && !m.isEscaping)
                for (AbstractPower pow : m.powers) {
                    if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting")
                    {
                        this.addToBot(new RemoveSpecificPowerAction(pow.owner,  AbstractDungeon.player, pow.ID));
                        removedAmount++;
                        if (removedAmount >= this.magicNumber)
                            break;
                    }
            }
            if (removedAmount >= this.magicNumber)
                break;
        }
    }
}
