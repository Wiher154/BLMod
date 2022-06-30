package BL.Cards.Skill.Common;


import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shade extends CustomCard {
    public static final String ID = "BLMod:Shade";
    public static final String NAME = "Shade";
    public static final String DESCRIPTION = "Block !B! NL Exhaust";
    public static final String IMG_PATH = "img/cards/Shade.png";

    private static final int COST = 1;

    private static final int BLOCK = 7;

    private static final int UPGRADE_BLOCK_AMOUNT = 5;


    public Shade() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = BLOCK;
        this.exhaust = true;
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Shade();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_AMOUNT);
        }
    }


}

