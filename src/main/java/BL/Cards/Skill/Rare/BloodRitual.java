package BL.Cards.Skill.Rare;


import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodRitual extends CustomCard {
    public static final String ID = "BLMod:BloodRitual";
    public static final String NAME = "Blood Ritual";
    public static final String DESCRIPTION = "Draw !M! NL Gain !M! Blood NL Gain !M! Energy NL Lose !M! HP.";
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
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Blood((AbstractCreature)p, this.magicNumber), this.magicNumber));
        addToBot((AbstractGameAction)new GainEnergyAction(this.magicNumber));
        addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BloodRitual();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }


}


