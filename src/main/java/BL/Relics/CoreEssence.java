package BL.Relics;

import BL.BLCharacter;
import BL.BLClassEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CoreEssence extends CustomRelic {
    public static final String ID = "BLMod:CoreEssence";
    private static final String IMG_PATH = "img/relics/BloodCore.png";
    private static final Texture IMG = new Texture(IMG_PATH);
    private static final int ESSENCE_NEEDED_AMOUNT = 50;
    private static final int AWAKEN_ENERGY_AMOUNT = 1;
    private static final int AWAKEN_BLOOD_AMOUNT = 2;
    private static final int AWAKEN_BLOCK_AMOUNT = 8;
    private static final double ESSENCE_FOR_MONSTER_HP_MULTI = 0.1;
    private static final String DES_P1 = "Defeat enemies and absorb Blood to awaken Core Essence. (at least "+ ESSENCE_NEEDED_AMOUNT +")";
    private static final String DES_P2 = "The Core is alive! At the start of battle gain: "+ AWAKEN_ENERGY_AMOUNT +" [E] "+ AWAKEN_BLOOD_AMOUNT +" Blood and "+ AWAKEN_BLOCK_AMOUNT +" Block";
    private boolean awaken;
    private boolean isFirstRoom;

    public CoreEssence() {
        super(ID, IMG, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
        this.awaken = false;
        this.counter = 0;
        this.isFirstRoom = true;
    }
    public void atBattleStart() {
        if(isFirstRoom) {
            this.isFirstRoom = false;
            this.addToTop(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player, BLCharacter.MAX_HP - BLCharacter.STARTING_HP));
        }
        if(!this.awaken)
            if(this.counter >= ESSENCE_NEEDED_AMOUNT) {
                this.awaken = true;
                this.updateDescription(BLClassEnum.BloodLord);
            }
        if(this.awaken) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(AWAKEN_ENERGY_AMOUNT));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new Blood(AbstractDungeon.player,AWAKEN_BLOOD_AMOUNT),AWAKEN_BLOOD_AMOUNT));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AWAKEN_BLOCK_AMOUNT));
        }
    }

    public void onMonsterDeath(AbstractMonster m) {
        if(this.counter >= ESSENCE_NEEDED_AMOUNT)
            return;
        this.counter += Math.round(m.maxHealth * ESSENCE_FOR_MONSTER_HP_MULTI);
    }
    public void onVictory(){
        super.onVictory();
        if(this.counter < ESSENCE_NEEDED_AMOUNT)
            this.counter += ((BLCharacter)AbstractDungeon.player).getBloodGainedThisBattle();
    }

    public void updateDescription(AbstractPlayer.PlayerClass c) {
        this.description = this.getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public String getUpdatedDescription() {
        if(this.awaken)
            return DES_P2;
        else
            return DES_P1;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CoreEssence();
    }

}
