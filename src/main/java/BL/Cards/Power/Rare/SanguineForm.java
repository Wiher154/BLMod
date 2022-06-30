package BL.Cards.Power.Rare;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import BL.Powers.SanguineFormPow;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SanguineForm extends BLBloodcostCard {
    public static final String ID = "BLMod:SanguineForm";
    public static final String NAME = "Sanguine Form";
    public static final String DESCRIPTION = "Bloodcost: 5 NL When you get Blood gain that much Strength";
    public static final String IMG_PATH = "img/cards/Sanguine form.png";

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int MAGIC_NUMBER = 1;

    private static final int BLOOD_COST = 5;
    private static final int UPGRADE_BLOOD_COST_AMOUNT = -2;


    public SanguineForm() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.POWER, BLCardEnum.BL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new SanguineFormPow((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new SanguineForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBloodCost(UPGRADE_BLOOD_COST_AMOUNT);
            this.upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = "Bloodcost: 3 NL When you get Blood gain that much Strength";
            this.initializeDescription();
        }
    }
}
