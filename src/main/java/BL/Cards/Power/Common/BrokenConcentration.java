package BL.Cards.Power.Common;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BrokenConcentration extends CustomCard {
    public static final String ID = "BLMod:BrokenConcentration";
    public static final String NAME = "Broken Concentration";
    public static final String DESCRIPTION = "Weak !M! all enemies NL When i'm discarded play me for free";
    public static final String IMG_PATH = "img/cards/Broken concentration.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;
    private static final int AFTER_DISCARD_DRAW_AMOUNT = 1;


    public BrokenConcentration() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    private void useEffect(AbstractPlayer p) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        useEffect(p);
    }

    @Override
    public AbstractCard makeCopy() {
        return new BrokenConcentration();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.rawDescription = DESCRIPTION + "THEN NL Draw 1";
            this.initializeDescription();
        }
    }

    public void triggerOnManualDiscard() {
        this.useEffect(AbstractDungeon.player);
        if(this.upgraded)
            this.addToBot(new DrawCardAction(AFTER_DISCARD_DRAW_AMOUNT));
    }
}
