package BL.Cards.Skill.Uncommon;


import BL.BLCardEnum;
import BL.Powers.Blood;
import BL.Powers.EvadeAttackPow;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BatForm extends CustomCard {
    public static final String ID = "BLMod:BatForm";
    public static final String NAME = "Bat Form";
    public static final String DESCRIPTION = "Evade next !M! attack(s). NL Exhaust";
    public static final String IMG_PATH = "img/cards/Bat form.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 1;


    public BatForm() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, BLCardEnum.BL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new EvadeAttackPow((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new BatForm();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }


}

