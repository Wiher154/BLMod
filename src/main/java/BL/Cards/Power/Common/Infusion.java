package BL.Cards.Power.Common;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class Infusion extends BLBloodcostCard {
    public static final String ID = "BLMod:Infusion";
    public static final String NAME = "Infusion";
    public static final String DESCRIPTION = "Bloodcost: !M! NL Gain !M! Strength and Dexterity";
    public static final String IMG_PATH = "img/cards/Infusion.png";

    private static final int COST = 0;
    private static final int BLOOD_COST = 2;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_AMOUNT = 1;


    public Infusion() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.POWER, BLCardEnum.BL, CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Infusion();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_AMOUNT);
            this.upgradeBloodCost(UPGRADE_AMOUNT);

        }
    }

}
