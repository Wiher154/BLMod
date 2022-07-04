package BL.Relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class CoreEssence extends CustomRelic {
    public static final String ID = "Blueberries";
    private static final int HP_PER_CARD = 1;

    public CoreEssence() {
        super(ID, "rar",
                RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HP_PER_CARD + DESCRIPTIONS[1];
    }

    @Override
    public void onEquip() {
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CoreEssence();
    }
}
