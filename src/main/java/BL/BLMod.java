package BL;

import BL.Cards.Attack.Common.*;
import BL.Cards.Attack.Rare.Feast;
import BL.Cards.Attack.Rare.RuinousRevelation;
import BL.Cards.Attack.Rare.SanguineMadEye;
import BL.Cards.Attack.Uncommon.BloodVeil;
import BL.Cards.Attack.Uncommon.Jaw;
import BL.Cards.Attack.Uncommon.MidnightChoir;
import BL.Cards.Attack.Uncommon.*;
import BL.Cards.Power.Common.BrokenConcentration;
import BL.Cards.Power.Common.Infusion;
import BL.Cards.Power.Common.MadEye;
import BL.Cards.Power.Common.Tabadash;
import BL.Cards.Power.Rare.BloodMist;
import BL.Cards.Power.Rare.MadnessForm;
import BL.Cards.Power.Rare.SanguineForm;
import BL.Cards.Power.Uncommon.*;
import BL.Cards.Skill.Common.*;
import BL.Cards.Skill.Rare.BloodRitual;
import BL.Cards.Skill.Rare.EvolveRitual;
import BL.Cards.Skill.Rare.SanguinePool;
import BL.Cards.Skill.Uncommon.*;
import BL.Cards.Special.ScratchTemp;
import BL.Cards.Special.TapTemp;
import BL.Relics.CoreEssence;
import basemod.*;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.RelicStrings;

import java.util.Properties;


@SpireInitializer
public class BLMod implements PostInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, EditStringsSubscriber{

    public static Properties BLDefaultSettings = new Properties();
    public static Boolean is_tny_enabled = false;
    public static final String TNY_CHAR_PROP = "tnny-bl";
    public static final String CHAR_IMAGE = "img/char_bl.png";
    public static final String CHAR_IMAGE_TNY = "img/char_bl_tny.png";
    public static String CHAR_IMAGE_FINAL = CHAR_IMAGE;

    public static final Color BL_BLACK_RED = CardHelper.getColor(117.0F, 10.0F, 10.0F);


    public BLMod(){
        BaseMod.subscribe((ISubscriber) this);
        BLDefaultSettings.setProperty(TNY_CHAR_PROP, "FALSE");
        try {
            SpireConfig config = new SpireConfig("BLMod", "BLConfig", BLDefaultSettings);
            config.load();
            is_tny_enabled = config.getBool(TNY_CHAR_PROP);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(is_tny_enabled)
            CHAR_IMAGE_FINAL = CHAR_IMAGE_TNY;
    }
    public static void initialize(){
        BaseMod.addColor(BLCardEnum.BL, BL_BLACK_RED, BL_BLACK_RED, BL_BLACK_RED, BL_BLACK_RED, BL_BLACK_RED, BL_BLACK_RED, BL_BLACK_RED, "img/attack_bl.png", "img/skill_bl.png", "img/power_bl.png", "img/blood_orb.png", "img/attack_bl_p.png", "img/skill_bl_p.png", "img/power_bl_p.png", "img/blood_orb_p.png", "img/orb_ui.png");
        BLMod main = new BLMod();
    }

    public void receiveEditCards() {
        BaseMod.addCard((AbstractCard)new Fang() );
        BaseMod.addCard((AbstractCard)new Transfusion() );
        BaseMod.addCard((AbstractCard)new Feast() );
        BaseMod.addCard((AbstractCard)new BloodVeil() );
        BaseMod.addCard((AbstractCard)new Shade());
        BaseMod.addCard((AbstractCard)new BloodSign());
        BaseMod.addCard((AbstractCard)new BatForm());
        BaseMod.addCard((AbstractCard)new BloodMist());
        BaseMod.addCard((AbstractCard)new BloodBarrier());
        BaseMod.addCard((AbstractCard)new BalefulJourney());
        BaseMod.addCard((AbstractCard)new BloodRitual());
        BaseMod.addCard((AbstractCard)new BloodWalk());
        BaseMod.addCard((AbstractCard)new VeinFiltering());
        BaseMod.addCard((AbstractCard)new BrokenConcentration());
        BaseMod.addCard((AbstractCard)new DarkTide());
        BaseMod.addCard((AbstractCard)new DrawnInBlood());
        BaseMod.addCard((AbstractCard)new EvolveRitual());
        BaseMod.addCard((AbstractCard)new Scratch());
        BaseMod.addCard((AbstractCard)new Flurry());
        BaseMod.addCard((AbstractCard)new ISeeYou());
        BaseMod.addCard((AbstractCard)new InTheShadows());
        BaseMod.addCard((AbstractCard)new Infusion());
        BaseMod.addCard((AbstractCard)new Jaw());
        BaseMod.addCard((AbstractCard)new MadBite());
        BaseMod.addCard((AbstractCard)new MadEye());
        BaseMod.addCard((AbstractCard)new MadnessForm());
        BaseMod.addCard((AbstractCard)new MidnightChoir());
        BaseMod.addCard((AbstractCard)new Tap());
        BaseMod.addCard((AbstractCard)new RedMadEye());
        BaseMod.addCard((AbstractCard)new RedRain());
        BaseMod.addCard((AbstractCard)new RedRivers());
        BaseMod.addCard((AbstractCard)new RedShadow());
        BaseMod.addCard((AbstractCard)new RiversOfBlood());
        BaseMod.addCard((AbstractCard)new RuinousRevelation());
        BaseMod.addCard((AbstractCard)new SanguineForm());
        BaseMod.addCard((AbstractCard)new SanguineMadEye());
        BaseMod.addCard((AbstractCard)new SanguinePool());
        BaseMod.addCard((AbstractCard)new Shadow());
        BaseMod.addCard((AbstractCard)new ShadowForm());
        BaseMod.addCard((AbstractCard)new Shadows());
        BaseMod.addCard((AbstractCard)new Shark());
        BaseMod.addCard((AbstractCard)new Tabadash());
        BaseMod.addCard((AbstractCard)new VampiricTutor());
        BaseMod.addCard((AbstractCard)new WihersDesire());
        BaseMod.addCard((AbstractCard)new Madness());
        BaseMod.addCard((AbstractCard)new BloodOath());
        BaseMod.addCard((AbstractCard)new ConvertionRitual());
        BaseMod.addCard((AbstractCard)new PainfulMemories());
        BaseMod.addCard((AbstractCard)new ScratchTemp());
        BaseMod.addCard((AbstractCard)new TapTemp());
        BaseMod.addCard((AbstractCard)new BloodAvatar());
        BaseMod.addCard((AbstractCard)new Stab());
        BaseMod.addCard((AbstractCard)new Consume());

    }
    public void receiveEditKeywords(){
        BaseMod.addKeyword("Blood",new String[]{"Blood", "blood"},"Use Blood to play Bloodcost cards");
        BaseMod.addKeyword("Bloodcost",new String[]{"Bloodcost", "bloodcost"},"The amount of Blood needed to play this card");
    }

    public void receiveEditCharacters(){
        BaseMod.addCharacter(new BLCharacter("Blood lord"), "img/Button.png", "img/portrait.png", BLClassEnum.BloodLord);
    }
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new CoreEssence(), BLCardEnum.BL);
    }
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "files/CoreEssence.json");
    }
    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture("img/bl-badge.png");
        ModPanel modPanel = new ModPanel();
        modPanel.addUIElement((IUIElement) new ModLabel("There is not a lot, but still:", 350.0F, 750.0F, Color.GOLD, modPanel, label ->{}));
        ModLabeledToggleButton mytnybtn =  new ModLabeledToggleButton("Make Blood Lord tnny! (restart game required)", 350.0F, 650.0F, Settings.CREAM_COLOR, FontHelper.charDescFont, is_tny_enabled, modPanel, (label)->{}, (button) ->{
            is_tny_enabled = button.enabled;

            try{
                SpireConfig config = new SpireConfig("BLMod", "BLConfig", BLDefaultSettings);
                config.setBool(TNY_CHAR_PROP, is_tny_enabled);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        modPanel.addUIElement(mytnybtn);
        BaseMod.registerModBadge(badgeTexture, "BLMod", "Wiher154", "Holy sht dude", modPanel);
        }

}
