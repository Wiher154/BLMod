package BL.Cards.Skill.Common;

import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Tap extends CustomCard {
    public static final String ID = "BLMod:Tap";
    public static final String NAME = "Tap";
    public static final String DESCRIPTION = "Lose !M! HP NL Gain !M! Blood";
    public static final String IMG_PATH = "img/cards/Tap.png";

    private static final int COST = 0;

    private static final int MAGIC_NUMBER = 2;

    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;


    public Tap() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new Blood(p, this.magicNumber), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Tap();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }
}
