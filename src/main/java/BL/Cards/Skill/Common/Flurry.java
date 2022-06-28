package BL.Cards.Skill.Common;

import BL.BLCardEnum;
import BL.Cards.Attack.Common.Scratch;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flurry extends CustomCard {
    public static final String ID = "BLMod:Flurry";
    public static final String NAME = "Flurry";
    public static final String DESCRIPTION = "Create !M! Scratch in hand NL Exhaust";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public Flurry (){
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.cardsToPreview = new Scratch();
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Flurry();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.cardsToPreview.upgrade();
            this.rawDescription = "Create !M! Scratch+ in hand NL Exhaust";
            this.initializeDescription();
        }
    }

}