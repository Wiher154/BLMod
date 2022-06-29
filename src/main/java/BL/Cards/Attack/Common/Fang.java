package BL.Cards.Attack.Common;

import BL.Actions.FangVamp;
import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

import javax.swing.text.html.HTML;

public class Fang extends CustomCard {
    public static final String ID = "BLMod:Fang";
    public static final String NAME = "Fang";
    public static final String DESCRIPTION = "Deal !D! NL Heal !M!";
    public static final String IMG_PATH = "img/cards/Fang.png";

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int HEAL = 2;

    private static final int UPGRADE_DAMAGE_AMOUNT = 3;
    private static final int UPGRADE_HEAL_AMOUNT = 1;

    public Fang() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, BLCardEnum.BL, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;
        this.baseMagicNumber = this.magicNumber = HEAL;
        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.tags.add(AbstractCard.CardTags.STARTER_STRIKE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo var1 = new DamageInfo((AbstractCreature)p, this.damage);
        if (m != null)
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.1F));
        addToBot((AbstractGameAction)new FangVamp((AbstractCreature)m, var1, AbstractGameAction.AttackEffect.NONE,this.magicNumber));
        //AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        //addToBot((AbstractGameAction) new HealAction((AbstractCreature)p, (AbstractCreature)p, this.magicNumber ));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Fang();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
            upgradeMagicNumber(UPGRADE_HEAL_AMOUNT);
        }
    }


}
