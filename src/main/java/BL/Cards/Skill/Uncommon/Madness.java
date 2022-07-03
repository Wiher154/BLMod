package BL.Cards.Skill.Uncommon;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Madness extends CustomCard {
    public static final String ID = "BLMod:Madness";
    public static final String NAME = "Madness";
    public static final String DESCRIPTION = "When i'm drawn play me for free NL Discard !M! at random NL Draw !M!";
    public static final String IMG_PATH = "img/cards/Madness.png";

    private static final int COST = 2;
    private static final int UPGRADED_COST = 3;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public Madness() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p,p,this.magicNumber, true));
        this.addToBot(new DrawCardAction(p,this.magicNumber));
        if(this.upgraded) {
            AbstractCard c = new Madness();
            c.upgrade();
            this.addToBot(new MakeTempCardInDiscardAction(c, this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Madness();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = "When i'm drawn play me for free NL Discard !M! at random NL Draw !M! NL Create !M! Madness+ in discard";
            this.initializeDescription();

        }
    }

    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), this.energyOnUse, true, true), true);
    }
}
