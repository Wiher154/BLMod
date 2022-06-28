package BL.Cards.Attack.Common;


import BL.Actions.BloodBarrierBlock;
import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;



public class BloodBarrier extends CustomCard {
    public static final String ID = "BLMod:BloodBarrier";
    public static final String NAME = "Blood Barrier";
    public static final String DESCRIPTION = "Bloodcost: up to 2 NL Deal !D! NL Block amount of damage + !M! times Blood spend";
    public static final String IMG_PATH = "img/cards/Blood barrier.png";

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE_AMOUNT = 3;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;





    public BloodBarrier() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower pow = p.getPower("BLMod:Blood");
        int addblock = 0;
        if(pow != null) {
            if(pow.amount > 0)
                addblock += 1;
            if(pow.amount > 1)
                addblock += 1;
            if(pow.amount > 2 && this.magicNumber == 3)
                addblock += 1;
        }
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Blood((AbstractCreature) p, -addblock), -addblock));
        DamageInfo var1 = new DamageInfo((AbstractCreature)p, this.damage);
        addToBot((AbstractGameAction)new BloodBarrierBlock((AbstractCreature)m, var1, AbstractGameAction.AttackEffect.NONE,addblock*this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BloodBarrier();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.rawDescription = "Bloodcost: up to 3 NL Deal !D! NL Block amount of damage + !M! times Blood spend";
            this.initializeDescription();
        }
    }


}


