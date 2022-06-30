package BL.Cards.Skill.Uncommon;

import BL.Actions.BetterAutoPlayCardAction;
import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class WihersDesire extends CustomCard {
    public static final String ID = "BLMod:WihersDesire";
    public static final String NAME = "Wiher's Desire";
    public static final String DESCRIPTION = "Cost 1 less for each card you played this turn NL Play 3 random attack cards with costs 0-1 from deck";
    public static final String IMG_PATH = "img/cards/Wiher's desire.png";

    private static final int COST = 7;
    private static final int MAGIC_NUMBER = 3;


    public WihersDesire() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> list = new ArrayList<>();

        if(!this.upgraded) {
            for (AbstractCard card : p.drawPile.group)
                if (card.type == AbstractCard.CardType.ATTACK && card.cost <= 1)
                    list.add(card);

            if (list.size() > 0) {
                Collections.shuffle(list);
                for (int i = 0; i < this.magicNumber && i < list.size(); i++)
                    addToBot((AbstractGameAction) new BetterAutoPlayCardAction(list.get(i), p.drawPile));
            }
        } else {
            CardGroup cards_tolook = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            for (AbstractCard card : p.drawPile.group)
                if (card.type == AbstractCard.CardType.ATTACK)
                    cards_tolook.addToTop(card);

            if(list.size() > 0) {
                AbstractDungeon.gridSelectScreen.open(cards_tolook, this.magicNumber, true, "Select up to 3 cards to play");
                //this.tickDuration();
                if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                    Iterator var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
                    while(var1.hasNext())
                        list.add((AbstractCard) var1.next());
                    for (int i = 0; i < this.magicNumber && i < list.size(); i++)
                        addToBot((AbstractGameAction) new BetterAutoPlayCardAction(list.get(i), p.drawPile));
                }

            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new WihersDesire();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "Cost 1 less for each card you played this turn NL Play 3 attack cards from deck";
            this.initializeDescription();

        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        int temp_cost = this.cost - AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        if(temp_cost <= 0)
            temp_cost = 0;
        this.costForTurn = temp_cost;
    }
}
