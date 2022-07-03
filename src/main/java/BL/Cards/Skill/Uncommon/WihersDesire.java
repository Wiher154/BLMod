package BL.Cards.Skill.Uncommon;

import BL.Actions.BetterAutoPlayCardAction;
import BL.Actions.PlayCardsFormDeckAction;
import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;


public class WihersDesire extends CustomCard {
    public static final String ID = "BLMod:WihersDesire";
    public static final String NAME = "Wiher's Desire";
    public static final String DESCRIPTION = "Cost 1 less for each card you played this turn NL Play !M! random attack cards with costs 0-1 from deck";
    public static final String IMG_PATH = "img/cards/Wiher's desire.png";

    private static final int COST = 7;
    private static final int MAGIC_NUMBER = 3;
    private static final int MAX_COST_OF_CARD = 1;


    public WihersDesire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded)
            this.addToBot(new PlayCardsFormDeckAction(this.magicNumber, CardType.ATTACK));
        else {
            ArrayList<AbstractCard> list = new ArrayList<>();
            for(AbstractCard c : p.drawPile.group)
                if(c.type == CardType.ATTACK && c.cost <= MAX_COST_OF_CARD)
                    list.add(c);
            if(list.size() > 0) {
                Collections.shuffle(list);
                for(int i=0; i<this.magicNumber && i< list.size();i++)
                    this.addToBot(new BetterAutoPlayCardAction(list.get(i), p.drawPile));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new WihersDesire();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Cost 1 less for each card you played this turn NL Play !M! attack cards from deck";
            this.initializeDescription();

        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) { calculateCostForTurn();
    }
    public void triggerWhenDrawn() { calculateCostForTurn();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p,m);
        this.calculateCostForTurn();
        return canUse;
    }


    private void calculateCostForTurn() {
        int temp_cost = this.cost - AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        if(temp_cost <= 0)
            temp_cost = 0;
        this.costForTurn = temp_cost;
    }
}
