package BL.Cards.Attack.Uncommon;

import BL.BLCardEnum;
import BL.Powers.Blood;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RedRain extends CustomCard {
    public static final String ID = "BLMod:RedRain";
    public static final String NAME = "Red Rain";
    public static final String DESCRIPTION = "Everyone Lose !M! HP NL Gain !M! Blood NL When i'm discarded random enemy lose 3 HP";
    public static final String IMG_PATH = "img/cards/Red rain.png";

    private static final int COST = 0;
    private static final int MAGIC_NUMBER = 9;
    private static final int DISCARD_HP_LOSS = 3;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 2;
    private static final int UPGRADE_DISCARD_HP_LOSS_AMOUNT = 2;

    private  int discard_hp_loss;


    public RedRain() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.discard_hp_loss = DISCARD_HP_LOSS;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot( new LoseHPAction(p,p,this.magicNumber));
        for(AbstractMonster mon: AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new LoseHPAction(mon,p,this.magicNumber));
        }
        this.addToBot(new ApplyPowerAction(p, p, new Blood(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RedRain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
            this.discard_hp_loss += UPGRADE_DISCARD_HP_LOSS_AMOUNT;
            this.rawDescription = "Everyone Lose !M! HP NL Gain !M! Blood NL When i'm discarded random enemy lose 5 HP";
            this.initializeDescription();
        }
    }

    public void triggerOnManualDiscard() {
        AbstractCreature mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
        if(mon != null)
            this.addToBot( new LoseHPAction(mon,AbstractDungeon.player,this.discard_hp_loss));
    }

}
