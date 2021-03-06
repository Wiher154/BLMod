package BL.Cards.Power.Uncommon;


import BL.BLCardEnum;
import BL.Powers.BalefulJourneyPow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BalefulJourney extends CustomCard {
    public static final String ID = "BLMod:BalefulJourney";
    public static final String NAME = "Baleful Journey";
    public static final String DESCRIPTION = "Lose 3 HP. If you play it !M! times - win battle";
    public static final String IMG_PATH = "img/cards/Baleful journey.png";

    private static final int COST = 2;
    private static final int MAGIC_NUMBER = 4;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = -1;
    private static final int HP_LOSE = 3;


    public BalefulJourney() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new LoseHPAction(p, p, HP_LOSE));
        int j = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        AbstractPower pow = p.getPower("BLMod:BalefulJourneyPow");
        if(pow != null) {
            if (pow.amount >= this.magicNumber - 1)
                for (int i = 0; i < j; ++i)
                    if (!(AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDying && ( AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).currentHealth > 0)
                        (AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).die();
        }
        this.addToTop(new ApplyPowerAction(p, p, new BalefulJourneyPow(p, 1), 1));
        this.addToBot(new MakeTempCardInDiscardAction(makeStatEquivalentCopy(), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BalefulJourney();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }


}


