package BL.Cards.Skill.Uncommon;

import BL.BLCardEnum;
import BL.Cards.Skill.Common.Shadow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shadows extends CustomCard {
    public static final String ID = "BLMod:Shadows";
    public static final String NAME = "Shadows";
    public static final String DESCRIPTION = "Block !B! NL Block !B! NL Block !B! NL Create !M! Shadow in Discard NL Exhaust";
    public static final String IMG_PATH = "img/cards/Shadows.png";

    private static final int COST = 2;
    private static final int BLOCK = 10;
    private static final int UPGRADE_BLOCK_AMOUNT = 4;
    private static final int MAGIC_NUMBER = 3;


    public Shadows() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.cardsToPreview = new Shadow();

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview,this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shadows();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_AMOUNT);
            this.cardsToPreview.upgrade();
            this.rawDescription = "Block !B! NL Block !B! NL Block !B! NL Create !M! Shadow+ in Discard NL Exhaust";
            this.initializeDescription();
        }
    }
}
