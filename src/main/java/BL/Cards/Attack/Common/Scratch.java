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

public class Scratch extends CustomCard {
    public static final String ID = "BLMod:Scratch";
    public static final String NAME = "Scratch";
    public static final String DESCRIPTION = "Deal !D! NL Gain !M! Blood";
    public static final String IMG_PATH = "img/temp.png";

    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE_AMOUNT = 3;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public Scratch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Blood((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Scratch();
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
