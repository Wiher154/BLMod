package BL.Cards.Power.Rare;


import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import BL.Powers.BloodMistPow;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodMist extends BLBloodcostCard {
    public static final String ID = "BLMod:BloodMist";
    public static final String NAME = "Blood Mist";
    public static final String DESCRIPTION = "Bloodcost: 2 NL Play the first attack you play again each turn";
    public static final String IMG_PATH = "img/cards/Blood mist.png";

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_AMOUNT = -1;
    private static final int BLOOD_COST = 2;


    public BloodMist() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.POWER, BLCardEnum.BL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new BloodMistPow(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodMist();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBloodCost(UPGRADE_AMOUNT);
            this.upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = "Bloodcost: 1 NL Play the first attack you play again each turn";
            this.initializeDescription();
        }
    }


}

