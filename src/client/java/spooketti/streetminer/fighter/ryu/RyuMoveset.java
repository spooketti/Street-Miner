package spooketti.streetminer.fighter.ryu;

import net.minecraft.world.phys.Vec3;
import spooketti.streetminer.fighter.MovesetBase;

public class RyuMoveset implements MovesetBase {

    public enum Shoryu
    {
        LIGHT(new Vec3(0,0.5,0),2),
        MEDIUM(new Vec3(0,0.5,0),4),
        HEAVY(new Vec3(0,1,0),6);

        public final Vec3 momentum;
        public final int damage;

        Shoryu(Vec3 momentum, int damage)
        {
            this.momentum = momentum;
            this.damage = damage;
        }
    }

    @Override
    public void QuarterForward() {

    }

    @Override
    public void QuarterBackward() {

    }

    @Override
    public void Shoryuken()
    {

    }

    @Override
    public void ForwardToDownQuarter() {

    }

    @Override
    public void HalfCircle() {

    }

    @Override
    public void ForwardSuper() {

    }

    @Override
    public void BackwardSuper() {

    }
}
