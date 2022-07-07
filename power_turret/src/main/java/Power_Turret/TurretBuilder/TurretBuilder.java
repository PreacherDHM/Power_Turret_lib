package Power_Turret.TurretBuilder;

import Power_Turret.Turrets.Turret;
import Power_Turret.Utils.Configurations;
import Power_Turret.Utils.ControlerInterface;
import Power_Turret.Utils.ControlerType;
import Power_Turret.Utils.CtreControler;
import Power_Turret.Utils.RevControler;
import Power_Turret.Math.Vector2;
import Power_Turret.TargetSystem.Targeting;
import edu.wpi.first.wpilibj.DigitalInput;

public class TurretBuilder {
    private boolean m_targeting = false;
    private boolean m_odomitry = false;
    private DigitalInput m_hallEffectSenser = null;
    private ControlerInterface m_controler = null;

    private Targeting m_targetSystem = null;

    public TurretBuilder(ControlerType controlerType, int CANId) {
        switch (controlerType) {
            case SparkMax:
                m_controler = new RevControler();
                break;
            case Falcon:
                m_controler = new CtreControler();
                break;
        }
        m_controler.setControler(CANId);
    }

    public TurretBuilder Targeting(Vector2 targetPos) {
        this.m_targeting = true;
        this.m_targetSystem = new Targeting(targetPos);
        return this;
    }

    public TurretBuilder TurretVersion(Configurations configuration) {
        m_controler.setConfiguration(configuration);
        return this;
    }

    public Turret Build() {
        return new Turret(m_targeting, m_targetSystem, m_odomitry, m_hallEffectSenser, m_controler);
    }

}
