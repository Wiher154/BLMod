package BL.Cards.Attack.Uncommon;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RiversOfBlood extends BLBloodcostCard {
    public static final String ID = "BLMod:RiversOfBlood";
    public static final String NAME = "Rivers Of Blood";
    public static final String DESCRIPTION = "Spend all Blood NL Deal !D! + 2*blood spend to all enemies NL Heal !M! for each enemy killed";
    public static final String IMG_PATH = "img/cards/Rivers of blood.png";

    private static final int COST = 2;
    private static final int BLOOD_COST = 0;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE_AMOUNT = 3;
    private static final int MAGIC_NUMBER = 3;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 5;
    private static final int BLOOD_SPEND_DAMAGE_MULTI = 2;
    private static final int UPGRADE_BLOOD_SPEND_DAMAGE_MULTI_AMOUNT = 1;
    private int bloodSpendDamageMulti;

    public RiversOfBlood() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.SetBloodcostToAll();
        this.bloodSpendDamageMulti = BLOOD_SPEND_DAMAGE_MULTI;

    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        int totalDamage = this.damage + bloodSpendDamageMulti*this.BloodSpend();

        this.addToBot(new DamageAllEnemiesAction(p, totalDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SMASH));

        for(AbstractMonster mon: AbstractDungeon.getCurrRoom().monsters.monsters)
            if(mon.currentBlock+mon.currentHealth <= totalDamage && !mon.isEscaping && !mon.isDying)
                this.addToBot(new HealAction(p,p,this.magicNumber));


    }

    @Override
    public AbstractCard makeCopy() {
        return new RiversOfBlood();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.bloodSpendDamageMulti += UPGRADE_BLOOD_SPEND_DAMAGE_MULTI_AMOUNT;
            this.rawDescription = "Spend all Blood NL Deal !D! + 3*blood spend to all enemies NL Heal !M! for each enemy killed";
            this.initializeDescription();
        }
    }
}
