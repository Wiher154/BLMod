package BL.Cards.Attack.Uncommon;


import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BloodVeil extends BLBloodcostCard {
    public static final String ID = "BLMod:BloodVeil";
    public static final String NAME = "Blood Veil";
    public static final String DESCRIPTION = "Bloodcost: 4 NL Block !B! NL Artefact !M! NL When i'm discarded play me for free";
    public static final String IMG_PATH = "img/fang.png";

    private static final int COST = 0;
    private static final int BLOCK = 8;
    private static final int MAGIC_NUMBER = 2;
    private static final int BLOOD_COST = 4;
    private static final int UPGRADE_BLOCK_AMOUNT = 8;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;


    public BloodVeil() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = this.block = BLOCK;
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }
    public void useEffect(AbstractPlayer p, AbstractMonster m){
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, this.block));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new ArtifactPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BloodVeil();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK_AMOUNT);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }

    public void triggerOnManualDiscard() {
        AbstractRoom room = AbstractDungeon.getCurrRoom();
        MonsterGroup monGr = room.monsters;

        this.useEffect(AbstractDungeon.player,monGr.getRandomMonster());
    }



}


