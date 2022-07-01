package BL.Cards.Skill.Uncommon;

import BL.Actions.PlayCardsFormDeckAction;
import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class WihersDesire extends CustomCard {
    public static final String ID = "BLMod:WihersDesire";
    public static final String NAME = "Wiher's Desire";
    public static final String DESCRIPTION = "Cost 1 less for each card you played this turn NL Play !M! random attack cards with costs 0-1 from deck";
    public static final String IMG_PATH = "img/cards/Wiher's desire.png";

    private static final int COST = 7;
    private static final int MAGIC_NUMBER = 3;
    private static final int MAX_COST_OF_CARD = 1;
    private boolean autoplay;


    public WihersDesire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.autoplay = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot((AbstractGameAction)new PlayCardsFormDeckAction(this.magicNumber,this.autoplay,MAX_COST_OF_CARD, CardType.ATTACK));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new WihersDesire();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.autoplay = false;
            this.rawDescription = "Cost 1 less for each card you played this turn NL Play !M! attack cards from deck";
            this.initializeDescription();

        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) { calculateCostForTurn();
    }
    public void triggerWhenDrawn() { calculateCostForTurn();
    }


    private void calculateCostForTurn() {
        int temp_cost = this.cost - AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        if(temp_cost <= 0)
            temp_cost = 0;
        this.costForTurn = temp_cost;
    }
}
