package BL.Cards.Attack.Common;


import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Transfusion extends CustomCard {
    public static final String ID = "BLMod:Transfusion";
    public static final String NAME = "Transfusion";
    public static final String DESCRIPTION = "Deal !D! NL Gain !M! Blood";
    public static final String IMG_PATH = "img/cards/Transfusion.png";

    private static final int COST = 2;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_DAMAGE_AMOUNT = 5;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public Transfusion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Blood((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Transfusion();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }


}

