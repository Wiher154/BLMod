package BL;


import BL.Cards.Attack.Common.Fang;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;

public class BLCharacter extends CustomPlayer {

    public static final int ENERGY_PER_TURN = 3;
    public static final String SHOULDER_1 = "img/shoulder.png";
    public static final String SHOULDER_2 = "img/shoulder2.png";
    public static final String CORPSE = "img/corpse.png";
    public static final String CHAR_IMAGE = "img/char_bl.png";
    public static final String NAME = "Test Blood Lord";
    public static final String DESCRIPTION = "Here comes description";
    public static final int STARTING_HP = 43;
    public static final int MAX_HP = 43;
    public static final int ORB_SLOTS = 0;
    public static final int STARTING_GOLD = 154;
    public static final int HAND_SIZE = 5;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    private int ruinousReveletionDiscardCount;

    public BLCharacter(String name) {
        super(name, BLClassEnum.BloodLord, null, null, null, (String)null);
        initializeClass(CHAR_IMAGE, SHOULDER_2, SHOULDER_1, CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
        this.ruinousReveletionDiscardCount = 0;
    }

    public Color getSlashAttackColor() {
        return Color.BLACK;
    }
    public Color getCardRenderColor() { return Color.BLACK; }
    public Color getCardTrailColor() { return Color.BLACK;}

    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
    }
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new Fang();
    }

    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    public AbstractCard.CardColor getCardColor() {
        return BLCardEnum.BL;
    }

    public AbstractPlayer newInstance() {
        return (AbstractPlayer) new BLCharacter(this.name);
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAME, DESCRIPTION, STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE, (AbstractPlayer)this,
                getStartingRelics(), getStartingDeck(), false);
    }

    public ArrayList<String> getStartingRelics() {       //CHANGE
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Burning Blood");
        UnlockTracker.markRelicAsSeen("Burning Blood");
        return retVal;
    }

    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        /*retVal.add("BLMod:Fang");
        retVal.add("BLMod:Fang");
        retVal.add("BLMod:Fang");
        retVal.add("BLMod:Fang");
        retVal.add("BLMod:Fang");
        retVal.add("BLMod:Shade");
        retVal.add("BLMod:Shade");
        retVal.add("BLMod:Shade");
        retVal.add("BLMod:Shade");
        retVal.add("BLMod:Transfusion");
        retVal.add("BLMod:Feast");*/

        retVal.add("BLMod:ShadowForm");
        retVal.add("BLMod:ShadowForm");
        retVal.add("BLMod:ShadowForm");

        retVal.add("BLMod:Shadows");
        retVal.add("BLMod:Shadows");
        retVal.add("BLMod:Shadows");

        retVal.add("BLMod:Shark");
        retVal.add("BLMod:Shark");
        retVal.add("BLMod:Shark");

        retVal.add("BLMod:Tabadash");
        retVal.add("BLMod:Tabadash");
        retVal.add("BLMod:Tabadash");

        retVal.add("BLMod:VampiricTutor");
        retVal.add("BLMod:VampiricTutor");
        retVal.add("BLMod:VampiricTutor");

        retVal.add("BLMod:VeinFiltering");
        retVal.add("BLMod:VeinFiltering");
        retVal.add("BLMod:VeinFiltering");
        retVal.add("BLMod:Transfusion");
        retVal.add("BLMod:Transfusion");
        retVal.add("BLMod:BloodRitual");
        retVal.add("BLMod:EvolveRitual");
        retVal.add("BLMod:Fang");
        retVal.add("BLMod:Fang");

        return retVal;
    }

    public String getPortraitImageName() {
        return null;
    }

    public String getVampireText(){
        return  DESCRIPTION;
    }
    public String getSpireHeartText(){
        return  DESCRIPTION;
    }
    public String getLocalizedCharacterName(){
        return NAME;
    }
    public String getCustomModeCharacterButtonSoundKey(){
        return "AUTOMATON_ORB_SPAWN";
    }

    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playV("AUTOMATON_ORB_SPAWN", 1.75F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
    }

    public int getAscensionMaxHPLoss(){
        return ASCENSION_MAX_HP_LOSS;
    }

    public String getTitle(AbstractPlayer.PlayerClass playerClass){
        return NAME;
    }

    public void onVictory() {
        super.onVictory();
        this.ruinousReveletionDiscardCount = 0;
    }

    public int getRuinousReveletionDiscardCount(){
        return this.ruinousReveletionDiscardCount;
    }
    public void changeRuinousReveletionDiscardCount(int changeValue){
        this.ruinousReveletionDiscardCount += changeValue;
    }



}

