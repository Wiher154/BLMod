package BL.Cards.Skill.Rare;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import BL.Powers.SanguinePoolPow;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SanguinePool extends BLBloodcostCard {
    public static final String ID = "BLMod:SanguinePool";
    public static final String NAME = "Sanguine Pool";
    public static final String DESCRIPTION = "Bloodcost: 2 NL Lose 20% of current HP (min 1) NL Invulnerable for 1 turn NL Exhaust";
    public static final String IMG_PATH = "img/cards/Sanguine pool.png";

    private static final int COST = 3;
    private static final int BLOOD_COST = 2;
    private static final int MAGIC_NUMBER = 1;
    private static final double CURRENT_HP_LOSS_MULTI = 0.2;
    private static final int MINIMAL_HP_LOSS = 1;


    public SanguinePool() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        int hpLossAmount = (int)Math.ceil((double) p.currentHealth * CURRENT_HP_LOSS_MULTI);
        if(hpLossAmount < MINIMAL_HP_LOSS)
            hpLossAmount = MINIMAL_HP_LOSS;
        this.addToBot(new LoseHPAction(p,p,hpLossAmount));

        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SanguinePoolPow(AbstractDungeon.player, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SanguinePool();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.retain = true;
            this.rawDescription = "Bloodcost: 2 NL Lose 20% of current HP (min 1) NL Invulnerable for 1 turn NL Retain";
            this.initializeDescription();
        }
    }
}
