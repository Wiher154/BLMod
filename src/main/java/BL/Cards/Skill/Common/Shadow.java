package BL.Cards.Skill.Common;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shadow extends CustomCard {
    public static final String ID = "BLMod:Shadow";
    public static final String NAME = "Shadow";
    public static final String DESCRIPTION = "Block !B! NL Create !M! Shade in Discard NL Exhaust";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 1;
    private static final int BLOCK = 10;
    private static final int UPGRADE_BLOCK_AMOUNT = 4;
    private static final int MAGIC_NUMBER = 1;


    public Shadow() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.cardsToPreview = new Shade();

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, this.block));
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(this.cardsToPreview,this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Shadow();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK_AMOUNT);
            this.cardsToPreview.upgrade();
            this.rawDescription = "Block !B! NL Create !M! Shade+ in Discard NL Exhaust";
            this.initializeDescription();

        }
    }
}
