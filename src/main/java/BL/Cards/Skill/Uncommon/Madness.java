package BL.Cards.Skill.Uncommon;

import BL.BLCardEnum;
import BL.BLCharacter;
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
    public static final String DESCRIPTION = "Endless upgrades NL When i'm drawn play me for free NL Discard !M! at random NL Draw !M!";
    public static final String IMG_PATH = "img/cards/Madness.png";

    private static final int COST = 2;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;
    private static final int MAX_MADNESS_PER_TURN_AMOUNT = 30;
    private static final int MAX_CARDS_SEEN_ON_SCREEN = 5;
    private int createCardsAmountAtUpgrade;


    public Madness() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.createCardsAmountAtUpgrade = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DiscardAction(p,p,this.magicNumber, true));
        this.addToBot(new DrawCardAction(p,this.magicNumber));
        if(this.upgraded) {
            int autoCardCreateAmount = this.createCardsAmountAtUpgrade;
            AbstractCard c = new Madness();
            c.upgrade();
            if(this.createCardsAmountAtUpgrade > MAX_CARDS_SEEN_ON_SCREEN)
                while(autoCardCreateAmount > 0)
                {
                    if(autoCardCreateAmount > MAX_CARDS_SEEN_ON_SCREEN)
                        this.addToBot(new MakeTempCardInDiscardAction(c, MAX_CARDS_SEEN_ON_SCREEN));
                    else
                        this.addToBot(new MakeTempCardInDiscardAction(c, autoCardCreateAmount));

                    autoCardCreateAmount -= MAX_CARDS_SEEN_ON_SCREEN;
                }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Madness();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
        this.upgradeName();
        this.createCardsAmountAtUpgrade++;
        this.rawDescription = "Endless upgrades NL When i'm drawn play me for free NL Discard !M! at random NL Draw !M! NL Create "+ this.createCardsAmountAtUpgrade +" Madness+ in discard";
        this.initializeDescription();
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }

    public void triggerWhenDrawn() {
        if(((BLCharacter) AbstractDungeon.player).getMadnessTriggerperTurnCount() < MAX_MADNESS_PER_TURN_AMOUNT) {
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(this, AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true), this.energyOnUse, true, true), true);
            ((BLCharacter) AbstractDungeon.player).addMadnessTriggerperTurnCount(1);
        }
    }
}
