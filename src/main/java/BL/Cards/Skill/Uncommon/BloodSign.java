package BL.Cards.Skill.Uncommon;


import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodSign extends BLBloodcostCard {
    public static final String ID = "BLMod:BloodSign";
    public static final String NAME = "Blood Sign";
    public static final String DESCRIPTION = "Bloodcost: 2 NL Draw !M! NL If i'm discarded Draw 1";
    public static final String IMG_PATH = "img/cards/Blood sign.png";

    private static final int COST = 0;
    private static final int BLOOD_COST = 2;
    private static final int UPGRADE_BLOOD_COST_AMOUNT = -1;
    private static final int MAGIC_NUMBER = 2;
    private static final int AFTERDISCARD_DRAW_AMOUNT = 1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public BloodSign() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }
    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BloodSign();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.upgradeBloodCost(UPGRADE_BLOOD_COST_AMOUNT);
            this.rawDescription = "Bloodcost: 1 NL Draw !M! NL If i'm discarded Draw 1";
            this.initializeDescription();

        }
    }

    public void triggerOnManualDiscard() {
        addToBot((AbstractGameAction)new DrawCardAction(AFTERDISCARD_DRAW_AMOUNT));
    }


}

