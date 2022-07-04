package BL.Cards.Attack.Common;


import BL.Abstract.BLBloodcostCard;
import BL.Actions.BloodBarrierBlock;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public class BloodBarrier extends BLBloodcostCard {
    public static final String ID = "BLMod:BloodBarrier";
    public static final String NAME = "Blood Barrier";
    public static final String DESCRIPTION = "Bloodcost: up to !M! NL Deal !D! NL Block amount of damage + !M! times Blood spend";
    public static final String IMG_PATH = "img/cards/Blood barrier.png";

    private static final int COST = 1;
    private static final int BLOOD_COST = 2;
    private static final int UPGRADE_BLOOD_COST_AMOUNT = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE_AMOUNT = 3;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;



    public BloodBarrier() {
        super(ID, NAME, IMG_PATH, COST,BLOOD_COST , DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.SetNotFixedBloodCost();
    }

    @Override
    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        DamageInfo var1 = new DamageInfo(p, this.damage);
        this.addToBot(new BloodBarrierBlock(m, var1, AbstractGameAction.AttackEffect.NONE,this.BloodSpend()*this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodBarrier();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.upgradeBloodCost(UPGRADE_BLOOD_COST_AMOUNT);
        }
    }


}


