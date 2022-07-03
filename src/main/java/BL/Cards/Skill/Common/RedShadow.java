package BL.Cards.Skill.Common;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedShadow extends BLBloodcostCard {
    public static final String ID = "BLMod:RedShadow";
    public static final String NAME = "Red Shadow";
    public static final String DESCRIPTION = "Bloodcost: 1 NL Block !B! NL When i'm discarded play me for free";
    public static final String IMG_PATH = "img/cards/Red shadow.png";

    private static final int COST = 1;
    private static final int BLOCK = 10;
    private static final int BLOOD_COST = 1;
    private static final int UPGRADE_BLOCK_AMOUNT = 4;


    public RedShadow() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = BLOCK;
        this.exhaust = true;

    }
    public void useEffect(AbstractPlayer p, AbstractMonster m){
        this.addToBot(new GainBlockAction(p,p,this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RedShadow();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(UPGRADE_BLOCK_AMOUNT);
        }
    }

    public void triggerOnManualDiscard() {
        this.useEffect(AbstractDungeon.player, AbstractDungeon.getCurrRoom().monsters.getRandomMonster());
    }
}
