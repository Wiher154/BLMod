package BL.Relics;

import BL.BLCharacter;
import BL.Powers.Blood;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CoreEssence extends CustomRelic {
    public static final String ID = "BLMod:CoreEssence";
    private static final String IMG_PATH = "img/relics/BloodCore.png";
    private static final Texture IMG = new Texture(IMG_PATH);
    private static final String DES_P1 = "Defeat enemies and absorb Blood to awaken Core Essence. Current essence : ";
    private static final int ESSENCE_NEEDED_AMOUNT = 50;
    private static final int AWAKEN_ENERGY_AMOUNT = 1;
    private static final int AWAKEN_BLOOD_AMOUNT = 2;
    private static final int AWAKEN_BLOCK_AMOUNT = 8;
    private static final String DES_P2 = "The Blood Core is alive! At the start of battle gain: "+ AWAKEN_ENERGY_AMOUNT +" [E] "+ AWAKEN_BLOOD_AMOUNT +" Blood and "+ AWAKEN_BLOCK_AMOUNT +" Block";
    private boolean awaken = false;
    private int essenceAmount;

    public CoreEssence() {
        super(ID, IMG, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
        this.awaken = false;
        this.essenceAmount = 0;
    }
    public void atBattleStart() {
        if(!this.awaken)
            if(this.essenceAmount >= ESSENCE_NEEDED_AMOUNT)
                this.awaken = true;

        if(this.awaken) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(AWAKEN_ENERGY_AMOUNT));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Blood(AbstractDungeon.player,AWAKEN_BLOOD_AMOUNT),AWAKEN_BLOOD_AMOUNT));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AWAKEN_BLOCK_AMOUNT));
        }
        this.getUpdatedDescription();
    }

    public void onMonsterDeath(AbstractMonster m) {
        this.essenceAmount += 3;
        if(AbstractDungeon.getCurrRoom().monsters.areMonstersDead()) {
            AbstractPower pow = AbstractDungeon.player.getPower("BLMod:Blood");
            if(pow != null)
                this.essenceAmount += pow.amount;
        }
    }

    @Override
    public String getUpdatedDescription() {
        //return this.DESCRIPTIONS[0];
        if(this.awaken)
            return DES_P2;
        else
            return DES_P1 + this.essenceAmount + "/" + ESSENCE_NEEDED_AMOUNT;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CoreEssence();
    }

    private void calculateEssence(){
        int temp = 3 * AbstractDungeon.getCurrRoom().monsters.monsters.size();
        AbstractPower pow = AbstractDungeon.player.getPower("BLMod:Blood");
        if(pow != null)
            temp += pow.amount;
        if(temp < 0)
            temp = 0;
        this.essenceAmount += temp;

    }
}
