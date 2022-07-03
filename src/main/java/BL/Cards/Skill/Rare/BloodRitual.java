package BL.Cards.Skill.Rare;


import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodRitual extends CustomCard {
    public static final String ID = "BLMod:BloodRitual";
    public static final String NAME = "Blood Ritual";
    public static final String DESCRIPTION = "Draw !M! NL Gain !M! Blood NL Gain !M! [E] NL Lose !M! HP.";
    public static final String IMG_PATH = "img/cards/Blood ritual.png";

    private static final int COST = 1;

    private static final int MAGIC_NUMBER = 2;

    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public BloodRitual() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new Blood(p, this.magicNumber), this.magicNumber));
        this.addToBot(new GainEnergyAction(this.magicNumber));
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodRitual();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }


}


