package BL.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayCardsFormDeckAction extends AbstractGameAction {
   private int cardsAmount;
   private AbstractCard.CardType cardType;


    public PlayCardsFormDeckAction(int cardsAmount, AbstractCard.CardType cardType) {
        this.cardsAmount = cardsAmount;
        if(this.cardsAmount < 0)
            this.cardsAmount = 0;
        this.cardType = cardType;

        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;

    }

    public void update() {
        CardGroup cards_tolook = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        for (AbstractCard card : AbstractDungeon.player.drawPile.group)
            if (card.type == this.cardType)
                cards_tolook.addToTop(card);
        if(this.duration == Settings.ACTION_DUR_MED) {
            if (cards_tolook.size() > 0) {
                AbstractDungeon.gridSelectScreen.open(cards_tolook, this.cardsAmount, true, "Select up to " + this.cardsAmount + " cards to play");
                this.tickDuration();
            }

        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                for (AbstractCard abstractCard : AbstractDungeon.gridSelectScreen.selectedCards)
                    this.addToBot(new BetterAutoPlayCardAction(abstractCard, AbstractDungeon.player.drawPile));
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        this.tickDuration();
    }

}
