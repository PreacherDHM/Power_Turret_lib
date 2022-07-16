package Power_Turret.TargetSystem;


import Power_Turret.Math.Vector2;

public class Targeting {
    private Vector2 m_prevPosition;
    private Vector2 m_currPosition;
    private Vector2 m_currVel;
    private Vector2 m_futurePosition;
    private double m_projectileVel;
    private double m_timeStamp;
    private boolean m_useProjectileVel = false;
    private Vector2 m_aimVel;
    private Vector2 m_targetPos;

    public Targeting(Vector2 targetPos, Vector2 targetSize, double timeStamp) {
        m_targetPos = targetPos;
        m_timeStamp = timeStamp;
    }

    public Targeting(double ProjectileVel, Vector2 targetPos, double timeStamp) {
        m_projectileVel = ProjectileVel;
        m_useProjectileVel = true;
        m_targetPos = targetPos;
        m_timeStamp = timeStamp;
    }

    public Targeting(Vector2 targetPos, double timeStamp) {
        m_timeStamp = timeStamp;
        m_targetPos = targetPos;
        m_useProjectileVel = false;
    }

    public void Update(Vector2 currentPosition) {
        if (!m_useProjectileVel) {
            if (m_prevPosition == null) {
                m_prevPosition = currentPosition;
            } else {
                m_prevPosition = m_currPosition;
            }
            m_currPosition = currentPosition;

            m_futurePosition = Vector2.Add(
                    Vector2.Mult(m_currPosition, Vector2.position(2, 2)),
                    Vector2.Mult(m_prevPosition, Vector2.position(-1, -1)));

            m_aimVel = Vector2.Add(
                    Vector2.Dev(Vector2.Sub(m_currPosition, m_prevPosition), m_timeStamp),
                    m_targetPos);

            m_currVel = Vector2.Dev(Vector2.Sub(m_prevPosition, m_currPosition), m_timeStamp);

            return;
        }
        return;
    }

    public Vector2 Get() {
        return m_aimVel;
    }

    public Vector2 GetFuturePosition() {
        return m_futurePosition;
    }

    public Vector2 ProdictedPosition() {
        Vector2 q = Vector2.Sub(m_currPosition, m_targetPos);

        double a = Vector2.Dot(m_currPosition, m_currPosition) - Math.pow(m_projectileVel,2);
        double b = 2 * Vector2.Dot(m_currPosition, q);
        double c = Vector2.Dot(q, q);

        double disc = Math.pow(b,2) - 4 * a * c;
        double d = Math.sqrt(disc);

        double t1 = (-b + d) / (2 * a);
        double t2 = (-b - d) / (2 * a);

        var time = Math.max(t1, t2);

        Vector2 prodictedPosition = Vector2.Add(m_currPosition, m_currVel.Mult(time));
        return prodictedPosition;
    }
}
