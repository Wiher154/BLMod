package BL.Cards.Attack.Uncommon;

import BL.BLCardEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

public class Jaw extends CustomCard {
    public static final String ID = "BLMod:Jaw";
    public static final String NAME = "Jaw";
    public static final String DESCRIPTION = "Deal !D! NL Heal unblocked amount";
    public static final String IMG_PATH = "img/fang.png";

    private static final int COST = 1;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_DAMAGE_AMOUNT = 7;

    public Jaw() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, BLCardEnum.BL, CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = this.damage = DAMAGE;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.1F));
        addToBot((AbstractGameAction)new VampireDamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Jaw();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE_AMOUNT);
        }
    }
}
