package BL.Cards.Skill.Uncommon;

import BL.BLCardEnum;
import BL.Cards.Skill.Common.Shadow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Shark extends CustomCard {
    public static final String ID = "BLMod:Shark";
    public static final String NAME = "Shark";
    public static final String DESCRIPTION = "Deal equal to your Block to all enemies NL When i'm discarded create Shadow in discard NL Exhaust";
    public static final String IMG_PATH = "img/cards/Shark.png";

    private static final int COST = 2;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;
    private static final int CARDS_CREATED_ON_DISCARD = 1;


    public Shark() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
        this.cardsToPreview = new Shadow();
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = p.currentBlock * this.magicNumber;
        this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shark();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.cardsToPreview.upgrade();
            this.rawDescription = "Deal equal to your Block !M! times to all enemies NL When i'm discarded create Shadow+ in discard NL Exhaust";
            this.initializeDescription();

        }
    }

    public void triggerOnManualDiscard() {
        this.addToBot(new MakeTempCardInDiscardAction(this.cardsToPreview,CARDS_CREATED_ON_DISCARD));
    }
}
