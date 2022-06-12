package BL.Cards.Power.Uncommon;

import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class DrawnInBlood extends CustomCard {
    public static final String ID = "BLMod:DrawnInBlood";
    public static final String NAME = "Drawn in Blood";
    public static final String DESCRIPTION = "Bloodcost: All NL Gain Strength equal Blood spend NL Every enemy gain Frail !M!";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_COST_AMOUNT = -1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;

    public DrawnInBlood() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower pow = p.getPower("BLMod:Blood");
        if(pow != null) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Blood((AbstractCreature) p, -pow.amount), -pow.amount));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new StrengthPower((AbstractCreature) p, pow.amount), pow.amount));
        }
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new FrailPower((AbstractCreature)mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new DrawnInBlood();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.cost += UPGRADE_COST_AMOUNT;
        }
    }


}
