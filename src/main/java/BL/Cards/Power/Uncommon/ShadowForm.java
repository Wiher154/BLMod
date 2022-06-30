package BL.Cards.Power.Uncommon;

import BL.BLCardEnum;
import BL.Powers.ShadowFormPow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;

public class ShadowForm extends CustomCard {
    public static final String ID = "BLMod:ShadowForm";
    public static final String NAME = "Shadow Form";
    public static final String DESCRIPTION = "When you gain Block gain 1 Dexterity";
    public static final String IMG_PATH = "img/cards/Shadow form.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;


    public ShadowForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new ShadowFormPow((AbstractCreature) p, this.magicNumber), this.magicNumber));
        if(this.upgraded) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new BlurPower(p,this.magicNumber)));
        }


    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new ShadowForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = "When you gain Block gain 1 Dexterity NL Permanent Blur";
            this.initializeDescription();
        }
    }

}
