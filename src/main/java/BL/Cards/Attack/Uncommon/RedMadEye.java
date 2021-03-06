package BL.Cards.Attack.Uncommon;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RedMadEye extends BLBloodcostCard {
    public static final String ID = "BLMod:RedMadEye";
    public static final String NAME = "Red Mad Eye";
    public static final String DESCRIPTION = "Bloodcost: 1 NL Deal !D! NL Weak !M! Frail !M! NL When i'm discarded random enemy gets Weak !M! Frail !M!";
    public static final String IMG_PATH = "img/cards/Red mad eye.png";

    private static final int COST = 1;
    private static final int DAMAGE = 12;
    private static final int MAGIC_NUMBER = 1;
    private static final int BLOOD_COST = 1;
    private static final int UPGRADE_DAMAGE_AMOUNT = 4;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public RedMadEye() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }
    public void useEffect(AbstractPlayer p, AbstractMonster m){
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        this.addToBot(new ApplyPowerAction(m, p, new FrailPower(m, this.magicNumber, false), this.magicNumber));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

    }

    @Override
    public AbstractCard makeCopy() {
        return new RedMadEye();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }

    public void triggerOnManualDiscard() {
        AbstractCreature mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        if(mon != null) {
            this.addToBot(new ApplyPowerAction( mon, AbstractDungeon.player, new WeakPower(mon, this.magicNumber, false), this.magicNumber));
            this.addToBot(new ApplyPowerAction( mon, AbstractDungeon.player, new FrailPower(mon, this.magicNumber, false), this.magicNumber));
        }

    }
}
