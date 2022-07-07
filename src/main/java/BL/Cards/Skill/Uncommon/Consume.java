package BL.Cards.Skill.Uncommon;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Consume extends BLBloodcostCard {
    public static final String ID = "BLMod:Consume";
    public static final String NAME = "Consume";
    public static final String IMG_PATH = "img/cards/Consume.png";

    private static final int COST = 2;
    private static final int BLOOD_COST = 3;
    private static final int UPGRADE_BLOOD_COST_AMOUNT = 2;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;
    private static final int BLOOD_SPEND_HEAL_MULTI = 2;
    public static final String DESCRIPTION = "Bloodcost: up to !M! NL  Heal "+ BLOOD_SPEND_HEAL_MULTI +" for each Blood spend";

    public Consume() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.SetNotFixedBloodCost();
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p,p, this.BloodSpend() * BLOOD_SPEND_HEAL_MULTI));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Consume();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.upgradeBloodCost(UPGRADE_BLOOD_COST_AMOUNT);
        }
    }
}
