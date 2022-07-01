package BL.Abstract;

import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class BLBloodcostCard extends CustomCard {
    private int blood_cost = 0;
    private boolean isCostAllBlood;
    private boolean isCostFixed;
    private int bloodSpend;

    public BLBloodcostCard(String id, String name, String img, int cost, int bloodcost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target){
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.blood_cost = bloodcost;
        this.isCostAllBlood = false;
        if(this.blood_cost < 0)
            this.blood_cost = 0;
        this.bloodSpend = 0;
        this.isCostFixed = true;
    }

    public void SetBloodcostToAll() {
        this.isCostAllBlood = true;
    }
    public void DontSpendAllBlood() {
        this.isCostAllBlood = false;
    }
    public void SetNotFixedBloodCost() {this.isCostFixed = false;}
    public int BloodSpend(){return this.bloodSpend;}

    public void upgradeBloodCost(int upgradeAmount) {
        this.blood_cost += upgradeAmount;
        if(this.blood_cost < 0)
            this.blood_cost = 0;
    }
    public abstract void useEffect(AbstractPlayer p, AbstractMonster m);

    private void spendBlood(AbstractPlayer p){
        if(!this.isInAutoplay){
            AbstractPower pow = p.getPower("BLMod:Blood");
            if(pow != null) {
                if (pow.amount >= this.blood_cost && !isCostAllBlood) {
                    this.bloodSpend = this.blood_cost;
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Blood((AbstractCreature) p, -this.blood_cost), -this.blood_cost));
                } else {
                    this.bloodSpend = pow.amount;
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new Blood((AbstractCreature) p, -pow.amount), -pow.amount));
                }
            }


        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        spendBlood(p);
        useEffect(p,m);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if(this.isInAutoplay)
            return true;
        if (!canUse)
            return false;
        if(!this.isCostFixed)
            return true;
        AbstractPower pow = p.getPower("BLMod:Blood");
        if(pow != null)
            if (pow.amount >= this.blood_cost)
                return true;
        return false;
    }

    public void triggerOnGlowCheck() {
        boolean glow = true;
        AbstractPower pow = AbstractDungeon.player.getPower("BLMod:Blood");
        if(pow != null)
            if (pow.amount < this.blood_cost || isCostAllBlood)
                glow = false;
        if (glow) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }
}
