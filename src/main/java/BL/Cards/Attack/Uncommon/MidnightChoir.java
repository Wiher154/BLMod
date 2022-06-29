package BL.Cards.Attack.Uncommon;

import BL.Abstract.BLBloodcostCard;
import BL.Actions.BloodBarrierBlock;
import BL.BLCardEnum;
import BL.Cards.Skill.Common.Tap;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MidnightChoir extends BLBloodcostCard {
    public static final String ID = "BLMod:MidnightChoir";
    public static final String NAME = "Midnight Choir";
    public static final String DESCRIPTION = "Bloodcost: 3 NL Deal !D! to all enemies NL Gain that much unblocked damage Block NL When i'm discarded create Tap in hand";
    public static final String IMG_PATH = "img/cards/Midnight choir.png";

    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int DISCARD_CREATE_AMOUNT = 1;
    private static final int UPGRADE_DAMAGE_AMOUNT = 10;
    private static final int BLOOD_COST = 3;


    public MidnightChoir() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.cardsToPreview = new Tap();
    }

    public void useEffect(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
            addToBot((AbstractGameAction)new BloodBarrierBlock((AbstractCreature)mo, new DamageInfo((AbstractCreature)p, this.damage), AbstractGameAction.AttackEffect.NONE,0));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new MidnightChoir();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            this.cardsToPreview.upgrade();
            this.rawDescription = "Deal !D! to all enemies NL Gain that much unblocked damage Block NL When i'm discarded create Tap+ in hand";
            this.initializeDescription();
        }
    }

    public void triggerOnManualDiscard() {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,DISCARD_CREATE_AMOUNT));
    }
}
