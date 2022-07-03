package BL.Cards.Power.Uncommon;

import BL.BLCardEnum;
import BL.Powers.BloodWalkPow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodWalk extends CustomCard {
    public static final String ID = "BLMod:BloodWalk";
    public static final String NAME = "Blood Walk";
    public static final String DESCRIPTION = "At the end of your turn gain !M! Blood THEN gain Block equal to your Blood";
    public static final String IMG_PATH = "img/cards/Blood walk.png";

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;

    public BloodWalk() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot( new ApplyPowerAction(p, p, new BloodWalkPow(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodWalk();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.upgradeBaseCost(UPGRADED_COST);
        }
    }

}
