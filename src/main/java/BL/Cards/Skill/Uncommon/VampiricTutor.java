package BL.Cards.Skill.Uncommon;

import BL.Actions.DeckToTopDeckAction;
import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VampiricTutor extends CustomCard {
    public static final String ID = "BLMod:VampiricTutor";
    public static final String NAME = "Vampiric Tutor";
    public static final String DESCRIPTION = "Put card form deck on top of it NL Lose !M! HP";
    public static final String IMG_PATH = "img/cards/Vampiric tutor.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 2;
    private static final int CARDS_TO_TUTOR_AMOUNT = 1;


    public VampiricTutor() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DeckToTopDeckAction(CARDS_TO_TUTOR_AMOUNT));
        addToBot((AbstractGameAction)new LoseHPAction(p,p,this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new VampiricTutor();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = "Put card form deck on top of it NL Lose !M! HP NL Innate";
            this.initializeDescription();

        }
    }
}
