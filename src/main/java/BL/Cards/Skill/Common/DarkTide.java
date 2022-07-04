package BL.Cards.Skill.Common;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DarkTide extends CustomCard {
    public static final String ID = "BLMod:DarkTide";
    public static final String NAME = "Dark Tide";
    public static final String DESCRIPTION = "Block !B! NL Deal your Block to random enemy";
    public static final String IMG_PATH = "img/cards/Dark tide.png";

    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_BLOCK_AMOUNT = 2;


    public DarkTide() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new GainBlockAction(p, this.block));
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(p, p.currentBlock+this.block, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if(this.upgraded)
            this.addToBot(new DrawCardAction(p, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DarkTide();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_AMOUNT);
            this.rawDescription = "Block !B! NL Deal your Block to random enemy NL Draw !M!";
            this.initializeDescription();
        }
    }
}
