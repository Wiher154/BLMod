package BL.Cards.Skill.Uncommon;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InTheShadows extends CustomCard {
    public static final String ID = "BLMod:InTheShadows";
    public static final String NAME = "In the Shadows";
    public static final String DESCRIPTION = "Gain Block equal to your exhausted skills !M! times";
    public static final String IMG_PATH = "img/cards/In the shadows.png";

    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 4;
    private static final int UPGRADE_MAGIC_NUMBER_AMOUNT = 4;


    public InTheShadows() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, BLCardEnum.BL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int exhastedSkillCount = 0;

        for (AbstractCard c : p.exhaustPile.group)
            if(c.type == CardType.SKILL)
                exhastedSkillCount++;

        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, this.magicNumber*exhastedSkillCount));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new InTheShadows();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER_AMOUNT);
        }
    }


}
