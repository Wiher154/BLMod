package BL.Cards.Attack.Common;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import BL.Powers.Blood;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedRivers extends BLBloodcostCard {
    public static final String ID = "BLMod:RedRivers";
    public static final String NAME = "Red Rivers";
    public static final String DESCRIPTION = "Bloodcost: 2 NL Everyone Lose 5 HP NL Heal the same amount for each enemy NL When i'm discarded gain !M! Blood";
    public static final String IMG_PATH = "img/cards/Red rivers.png";

    private static final int COST = 0;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE_AMOUNT = 2;
    private static final int MAGIC_NUMBER = 1;
    private static final int BLOOD_COST = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;
    private int real_hp_lose;

    public RedRivers() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.real_hp_lose = DAMAGE;

    }
    public void useEffect(AbstractPlayer p, AbstractMonster m){
        this.addToBot( new LoseHPAction(p,p,this.real_hp_lose));
        for(AbstractMonster mon: AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot( new LoseHPAction(mon,p,this.real_hp_lose));
            this.addToBot( new HealAction(p,p,this.real_hp_lose));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new RedRivers();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.real_hp_lose += UPGRADE_DAMAGE_AMOUNT;
            this.rawDescription = "Bloodcost: 2 NL Everyone Lose 7 HP NL Heal the same amount for each enemy NL When i'm discarded gain !M! Blood";
            this.initializeDescription();
        }
    }

    public void triggerOnManualDiscard() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Blood(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }
}
