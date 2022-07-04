package BL.Cards.Skill.Common;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class ConvertionRitual extends BLBloodcostCard {
    public static final String ID = "BLMod:ConvertionRitual";
    public static final String NAME = "Convertion Ritual";
    public static final String DESCRIPTION = "Bloodcost: up to !M! NL  Gain energy for each Blood spend NL Exhaust";
    public static final String IMG_PATH = "img/cards/Convertion ritual.png";

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int BLOOD_COST = 4;
    private static final int UPGRADE_BLOOD_COST_AMOUNT = 2;
    private static final int MAGIC_NUMBER = 4;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;



    public ConvertionRitual() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.SetNotFixedBloodCost();
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainEnergyAction(this.BloodSpend()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ConvertionRitual();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.upgradeBloodCost(UPGRADE_BLOOD_COST_AMOUNT);
        }
    }
}
