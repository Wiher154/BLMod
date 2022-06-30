package BL.Cards.Skill.Common;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VeinFiltering extends CustomCard {
    public static final String ID = "BLMod:VeinFiltering";
    public static final String NAME = "Vein Filtering";
    public static final String DESCRIPTION = "Draw !M! NL Discard !M!";
    public static final String IMG_PATH = "img/cards/Vein filtering.png";

    private static final int COST = 0;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public VeinFiltering() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)p,(AbstractCreature)p,this.magicNumber,false ));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new VeinFiltering();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }

}
