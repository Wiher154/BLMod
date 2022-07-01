package BL.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;

public class PlayCardsFormDeckAction extends AbstractGameAction {
   private int cardsAmount;
   private boolean isAutoselect;
   private int maxCost;
   private AbstractCard.CardType cardType;

    public PlayCardsFormDeckAction(int cardsAmount, boolean isAutoselect, int maxCost, AbstractCard.CardType cardType) {
        this.cardsAmount = cardsAmount;
        if(this.cardsAmount < 0)
            this.cardsAmount = 0;
        this.isAutoselect = isAutoselect;
        this.maxCost = maxCost;
        this.cardType = cardType;

    }

    public void update() {
        ArrayList<AbstractCard> list = new ArrayList<>();

        if (this.isAutoselect)
            autoSelectCards(list, this.cardType, this.maxCost);
        else
            manuallySelectCards(list);

        autoPlayCards(list);
    }
    private void autoPlayCards(ArrayList<AbstractCard> autoplayCardList){
        Collections.shuffle(autoplayCardList);
        for (int i = 0; i < this.cardsAmount && i < autoplayCardList.size(); i++)
            addToBot((AbstractGameAction) new BetterAutoPlayCardAction(autoplayCardList.get(i), AbstractDungeon.player.drawPile));
    }

    private void autoSelectCards(ArrayList<AbstractCard> outputCardsList, AbstractCard.CardType cardType, int maxCost){
        for (AbstractCard card : AbstractDungeon.player.drawPile.group)
            if (card.type == AbstractCard.CardType.ATTACK && card.cost <= maxCost)
                outputCardsList.add(card);
    }

    private void manuallySelectCards(ArrayList<AbstractCard> outputCardsList){
        CardGroup cards_tolook = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        for (AbstractCard card : AbstractDungeon.player.drawPile.group)
            if (card.type == this.cardType)
                cards_tolook.addToTop(card);

        if (cards_tolook.size() > 0) {
            AbstractDungeon.gridSelectScreen.open(cards_tolook, this.cardsAmount, true, "Select up to " + this.cardsAmount + " cards to play");
            this.tickDuration();

            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (AbstractCard abstractCard : AbstractDungeon.gridSelectScreen.selectedCards)
                    outputCardsList.add(abstractCard);
            }
        }

    }

}
