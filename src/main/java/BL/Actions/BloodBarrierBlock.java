package BL.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BloodBarrierBlock extends AbstractGameAction {
    private DamageInfo info;
    private int blockAmount;

    public BloodBarrierBlock(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect, int blockValue){
        this.info = info;
        this.blockAmount = blockValue;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
    }


    public void update(){
        this.tickDuration();
        if (this.isDone) {
            this.target.damage(this.info);
            if (this.target.lastDamageTaken > 0) {
                this.addToTop(new GainBlockAction(this.source,this.target.lastDamageTaken + blockAmount));
                this.addToTop(new WaitAction(0.05F));
            } else if(blockAmount > 0){
                this.addToTop(new GainBlockAction(this.source, blockAmount));
                this.addToTop(new WaitAction(0.05F));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

    }
}
