package BL.Cards.Skill.Uncommon;

import BL.Actions.DiscardToHandAction;
import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PainfulMemories extends CustomCard {
    public static final String ID = "BLMod:PainfulMemories";
    public static final String NAME = "Painful Memories";
    public static final String DESCRIPTION = "Draw 3 from Discard then Discard 2 at random NL Lose !M! HP at the end of turn NL Exhaust";
    public static final String IMG_PATH = "img/cards/Painful memories.png";

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int MAGIC_NUMBER = 1;
    private static final int DRAW_FROM_DISCARD_AMOUNT = 3;
    private static final int RANDOM_DISCARD_AMOUNT = 2;


    public PainfulMemories() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot((AbstractGameAction)new DiscardToHandAction(DRAW_FROM_DISCARD_AMOUNT));
        this.addToBot((AbstractGameAction)new DiscardAction(p,p,RANDOM_DISCARD_AMOUNT,true));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new PainfulMemories();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADED_COST);
            this.exhaust = false;
            this.rawDescription = "Draw 3 from Discard then Discard 2 at random NL Lose !M! HP at the end of turn";
            this.initializeDescription();
        }
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m){
        boolean canUse = super.canUse(p, m);
        if(!canUse)
            return false;
        if(AbstractDungeon.player.discardPile.size() >= DRAW_FROM_DISCARD_AMOUNT)
            return true;
        return false;
    }
}
