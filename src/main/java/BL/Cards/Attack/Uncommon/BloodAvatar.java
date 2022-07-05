package BL.Cards.Attack.Uncommon;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodAvatar extends BLBloodcostCard {
    public static final String ID = "BLMod:BloodAvatar";
    public static final String NAME = "Blood Avatar";
    public static final String DESCRIPTION = "Spend all Blood NL Deal !D! + !M! * blood spend to all enemies";
    public static final String IMG_PATH = "img/cards/Blood avatar.png";

    private static final int COST = 3;
    private static final int BLOOD_COST = 0;
    private static final int DAMAGE = 24;
    private static final int UPGRADE_DAMAGE_AMOUNT = 12;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;

    public BloodAvatar() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.SetBloodcostToAll();
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        int totalDamage = this.damage + this.magicNumber*this.BloodSpend();

        this.addToBot(new DamageAllEnemiesAction(p, totalDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SMASH));
    }

    @Override
    public AbstractCard makeCopy() {
        return new BloodAvatar();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }
}
