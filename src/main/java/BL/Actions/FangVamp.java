package BL.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FangVamp extends AbstractGameAction {
    private DamageInfo info;
    private int healAmount;

    public FangVamp(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect, int vampValue){
        this.info = info;
        this.healAmount = vampValue;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }
    public void update(){
        this.tickDuration();
        if (this.isDone) {
            this.target.damage(this.info);
            this.addToTop(new HealAction(this.source, this.source, this.healAmount));
            this.addToTop(new WaitAction(0.05F));

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

    }
}
