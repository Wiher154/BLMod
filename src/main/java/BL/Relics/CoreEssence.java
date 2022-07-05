package BL.Relics;

import BL.BLCharacter;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CoreEssence extends CustomRelic {
    public static final String ID = "BLMod:CoreEssence";
    private static final String DES_P1 = "Defeat enemies and absorb Blood to awaken Core Essence. Current essence : ";
    private static final int ESSENCE_NEEDED_AMOUNT = 50;
    private static final int AWAKEN_ENERGY_AMOUNT = 1;
    private static final int AWAKEN_BLOOD_AMOUNT = 2;
    private static final int AWAKEN_BLOCK_AMOUNT = 8;
    private static final String DES_P2 = "The Blood Core is alive! At the start of battle gain: "+ AWAKEN_ENERGY_AMOUNT +" [E] "+ AWAKEN_BLOOD_AMOUNT +" Blood and "+ AWAKEN_BLOCK_AMOUNT +" Block";
    private boolean awaken;

    public CoreEssence() {
        super(ID, "rar",
                RelicTier.STARTER, LandingSound.MAGICAL);
        this.awaken = false;
    }
    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        if(!awaken) {
            if(AbstractDungeon.player instanceof BLCharacter)
                if(((BLCharacter) AbstractDungeon.player).getEssenceAmount() >= ESSENCE_NEEDED_AMOUNT)
                    this.awaken = true;
        }
        if(awaken) {

        }

    }

    @Override
    public String getUpdatedDescription() {
        if(awaken)
            return DES_P2;
        else
            return DES_P1 + ESSENCE_NEEDED_AMOUNT+ "/" + ESSENCE_NEEDED_AMOUNT;
    }

    @Override
    public void onEquip() {
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CoreEssence();
    }
}
