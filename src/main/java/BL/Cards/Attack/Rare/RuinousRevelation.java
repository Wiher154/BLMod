package BL.Cards.Attack.Rare;

import BL.BLCardEnum;
import BL.BLCharacter;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RuinousRevelation extends CustomCard {
    public static final String ID = "BLMod:RuinousRevelation";
    public static final String NAME = "Ruinous Revelation";
    public static final String DESCRIPTION = "To play Discard !M! NL If Discarded !M! times Deal !D! x 10 NL When i'm discarded Deal !D! to random enemy";
    public static final String IMG_PATH = "img/cards/Ruinous revelation.png";

    private static final int COST = 0;
    private static final int DISCARD_COST = 3;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE_AMOUNT = 7;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 0;
    private static final int DAMAGE_MULTI_ON_MANUAL_CAST = 10;
    private static final int REVELATION_COUNT_INCREASE_ON_DISCARD = 1;
    private static int reveletionDiscardCount;



    public RuinousRevelation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        reveletionDiscardCount = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if(!this.isInAutoplay)
            this.addToBot(new DiscardAction(p, p, this.magicNumber, false));

        //if(AbstractDungeon.player instanceof BLCharacter)
            //if(((BLCharacter) AbstractDungeon.player).getRuinousReveletionDiscardCount() >= this.magicNumber)
        if(reveletionDiscardCount >= this.magicNumber)
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage*DAMAGE_MULTI_ON_MANUAL_CAST, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RuinousRevelation();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }

    public void triggerOnManualDiscard(){
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        reveletionDiscardCount++;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if(this.isInAutoplay)
            return true;
        if (!canUse)
            return false;
        return p.hand.size() > DISCARD_COST;
    }

    public void triggerOnGlowCheck() {
        boolean glow = false;
        if(reveletionDiscardCount >= this.magicNumber)
            glow = true;

        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
