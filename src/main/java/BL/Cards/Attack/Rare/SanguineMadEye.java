package BL.Cards.Attack.Rare;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import BL.Powers.Blood;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class SanguineMadEye extends BLBloodcostCard {

    public static final String ID = "BLMod:SanguineMadEye";
    public static final String NAME = "Sanguine Mad Eye";
    public static final String DESCRIPTION = "Bloodcost: All NL Deal !D! * Blood spend NL When i'm discarded 1 random enemy Vulnerable and Frail !M! NL Gain !M! Blood";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 2;

    private static final int DAMAGE = 8;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;
    private static final int UPGRADE_DAMAGE_AMOUNT = 2;
    private static final int BLOOD_COST = 0;


    public SanguineMadEye() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.baseDamage = this.damage = DAMAGE;
        this.SetBloodcostToAll();
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        AbstractPower pow = AbstractDungeon.player.getPower("BLMod:Blood");
        int bloodSpend = 0;
        if(pow != null)
            bloodSpend = pow.amount;

        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage * bloodSpend, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new SanguineMadEye();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }

    public void triggerOnManualDiscard(){
        AbstractCreature mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        addToBot((AbstractGameAction)new ApplyPowerAction(mon, AbstractDungeon.player, (AbstractPower)new FrailPower(AbstractDungeon.player, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction(mon, AbstractDungeon.player, (AbstractPower)new VulnerablePower(AbstractDungeon.player, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new Blood(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }
}
