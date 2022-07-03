package BL.Cards.Skill.Rare;

import BL.Abstract.BLBloodcostCard;
import BL.BLCardEnum;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.Iterator;

public class EvolveRitual extends BLBloodcostCard {
    public static final String ID = "BLMod:EvolveRitual";
    public static final String NAME = "Evolve Ritual";
    public static final String DESCRIPTION = "Bloodcost: 3 NL Lose !M! HP NL Upgrade random card in deck NL Exhaust";
    public static final String IMG_PATH = "img/cards/Evolve ritual.png";

    private static final int COST = 1;
    private static final int BLOOD_COST = 3;
    private static final int MAGIC_NUMBER = 2;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;

    public EvolveRitual() {
        super(ID, NAME, IMG_PATH, COST, BLOOD_COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;
    }

    private static void evolveCardEffect() {
        AbstractCard theCard = null;
        ArrayList<AbstractCard> possibleCards = new ArrayList();
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if (c.canUpgrade()) {
                possibleCards.add(c);
            }
        }
        if (!possibleCards.isEmpty()) {
            theCard = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
            theCard.upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(theCard);
        }
        if(theCard != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(theCard.makeStatEquivalentCopy()));
        }
    }

    @Override
    public void useEffect(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        evolveCardEffect();
        if(this.upgraded)
            evolveCardEffect();
    }

    @Override
    public AbstractCard makeCopy() {
        return new EvolveRitual();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.rawDescription = "Bloodcost: 3 NL Lose !M! HP NL Upgrade 2 random cards in deck NL Exhaust";
            this.initializeDescription();
        }
    }

}
