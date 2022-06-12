package BL.Cards.Power.Common;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class MadEye extends CustomCard {
    public static final String ID = "BLMod:MadEye";
    public static final String NAME = "Mad Eye";
    public static final String DESCRIPTION = "Bloodcost: !M! NL Gain !M! Strength and Dexterity";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_AMOUNT = 1;


    public MadEye() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new WeakPower((AbstractCreature) p, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) m, (AbstractCreature) p, (AbstractPower) new FrailPower((AbstractCreature) p, this.magicNumber, false), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard) new MadEye();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_AMOUNT);
        }
    }

    public void triggerOnManualDiscard() {
        int removedAmount = 0;
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            for (AbstractPower pow : m.powers) {
                if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower" && !(pow instanceof com.megacrit.cardcrawl.powers.StrengthPower) && !(pow instanceof com.megacrit.cardcrawl.powers.DexterityPower)) {
                    addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner, (AbstractCreature) AbstractDungeon.player, pow.ID));
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
