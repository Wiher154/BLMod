package BL.Cards.Attack.Common;

import BL.Abstract.BLBloodcostCard;
import BL.Actions.RedRiversDamageHeal;
import BL.BLCardEnum;
import BL.Powers.Blood;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RedRivers extends BLBloodcostCard {
    public static final String ID = "BLMod:RedRivers";
    public static final String NAME = "Red Rivers";
    public static final String DESCRIPTION = "Bloodcost: 2 NL Everyone Lose !D! HP NL Heal amount lost by enemies NL When i'm discarded gain !M! Blood";
    public static final String IMG_PATH = "img/cards/Red rivers.png";

    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int MAGIC_NUMBER = 1;
    private static final int BLOOD_COST = 2;
    private static final int UPGRADE_DAMAGE_AMOUNT = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;


    public RedRivers() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }
    public void useEffect(AbstractPlayer p, AbstractMonster m){
        addToBot((AbstractGameAction) new LoseHPAction(p,p,this.damage));
        addToBot((AbstractGameAction)new RedRiversDamageHeal(this.damage));
        /*for(AbstractMonster mon: AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot((AbstractGameAction) new LoseHPAction(mon,p,this.damage));
            addToBot((AbstractGameAction) new HealAction(p,p,this.damage));
        }*/

    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new RedRivers();
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
        addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower)new Blood(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }
}
