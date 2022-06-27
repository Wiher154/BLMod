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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RedMadEye extends BLBloodcostCard {
    public static final String ID = "BLMod:RedMadEye";
    public static final String NAME = "Red Mad Eye";
    public static final String DESCRIPTION = "Bloodcost: 1 NL Deal !D! NL Weak !M! Frail !M! NL When i'm discarded random enemy gets Weak !M! Frail !M!";
    public static final String IMG_PATH = "img/temp.png";

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
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new FrailPower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new RedMadEye();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }

    public void triggerOnManualDiscard() {
        AbstractCreature mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
        if(mon != null) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mon, AbstractDungeon.player, (AbstractPower) new WeakPower(AbstractDungeon.player, this.magicNumber, false), this.magicNumber));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mon, AbstractDungeon.player, (AbstractPower) new FrailPower(AbstractDungeon.player, this.magicNumber, false), this.magicNumber));
        }

    }
}
