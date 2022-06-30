package BL.Actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.HealAction;

public class RedRiversDamageHeal extends AbstractGameAction {
    private int lifeLossAmount;


    public RedRiversDamageHeal(int lifeLossAmount){
        this.lifeLossAmount = lifeLossAmount;
    }


    public void update(){
        int mon_initial_life = 0;
        int player_heal_amount = 0;

        this.tickDuration();
        if (this.isDone) {
            for(AbstractCreature mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
                mon_initial_life = 0;
                player_heal_amount = 0;

                if(!mon.isDying && !mon.isEscaping && !mon.isDead && mon.currentHealth > 0)
                    mon_initial_life = mon.currentHealth;

                this.addToTop((AbstractGameAction) new LoseHPAction(mon,AbstractDungeon.player,this.lifeLossAmount));
                this.addToTop(new WaitAction(0.05F));

                if(mon_initial_life > mon.currentHealth) {
                    if(mon.currentHealth <= 0)
                        player_heal_amount = mon_initial_life;
                    else player_heal_amount = mon_initial_life - mon.currentHealth;

                    this.addToTop((AbstractGameAction) new HealAction(AbstractDungeon.player,AbstractDungeon.player,player_heal_amount));
                    this.addToTop(new WaitAction(0.05F));
                }
            }
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }


}
