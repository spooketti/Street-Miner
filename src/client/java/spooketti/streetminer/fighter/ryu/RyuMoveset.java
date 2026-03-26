package spooketti.streetminer.fighter.ryu;

import net.minecraft.world.phys.Vec3;
import spooketti.streetminer.fighter.MovesetBase;

public class RyuMoveset implements MovesetBase {

    public enum Shoryu
    {
        LIGHT(new Vec3(0,2,0),2),
        MEDIUM(new Vec3(0,4,0),4),
        HEAVY(new Vec3(0,6,0),6);

        public final Vec3 momentum;
        public final int damage;

        Shoryu(Vec3 momentum, int damage)
        {
            this.momentum = momentum;
            this.damage = damage;
        }
    }

    public void Shoryuken()
    {

    }
}
