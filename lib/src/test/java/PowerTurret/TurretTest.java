package PowerTurret;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import Power_Turret.Math.Vector2;

class TurretTest {

    @Test
    public void TargetingCorectPositionOne() {
       WPI_TalonFX t = new WPI_TalonFX(1); 


        //turret.UpdateOdomitry(Vector2.position(4, 4));
        //turret.UpdateTargetSystem();
        //turret.UpdateOdomitry(Vector2.position(3, 3));

        assertEquals(Vector2.ZERO().x, Vector2.ZERO().x, "booooo!!!");
    }

}
