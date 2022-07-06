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
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class BLMod implements PostInitializeSubscriber, EditCardsSubscriber, EditRelicsSubscriber, EditCharactersSubscriber, EditKeywordsSubscriber, EditStringsSubscriber{
    public static final Color BL_BLACK_RED = CardHelper.getColor(117.0F, 10.0F, 10.0F);
    public static final Logger logger = LogManager.getLogger(BLMod.class.getName());
    public BLMod(){
        BaseMod.subscribe((ISubscriber) this);

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
    public void receivePostInitialize() {

        }





}
