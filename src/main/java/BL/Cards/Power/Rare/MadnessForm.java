package BL.Cards.Power.Rare;

import BL.BLCardEnum;
import BL.Powers.MadnessFormPow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class MadnessForm extends CustomCard {
    public static final String ID = "BLMod:MadnessForm";
    public static final String NAME = "Madness Form";
    public static final String DESCRIPTION = "To play Discard !M! NL When you Draw or Discard deal 1 to random enemy NL Exhaust";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = -1;
    private int madness_amount;


    public MadnessForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.POWER, BLCardEnum.BL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.madness_amount = 1;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!this.isInAutoplay)
            addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)p, (AbstractCreature)p, this.magicNumber, false));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new MadnessFormPow((AbstractCreature)p, this.madness_amount), this.madness_amount));


    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new MadnessForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.madness_amount++;
            this.exhaust = false;
            this.rawDescription = "To play Discard !M! NL When you Draw or Discard deal 2 to random enemy";
            this.initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if(this.isInAutoplay)
            return true;
        if (!canUse)
            return false;
        if(p.hand.size() > this.magicNumber)
            return true;
        return false;
    }

}
