package Power_Turret.TargetSystem;

import java.awt.Robot;
import java.sql.Time;

import Power_Turret.Math.Vector2;

public class Targeting {
    private Vector2 m_prevPosition;
    private Vector2 m_currPosition;
    private Vector2 m_futurePosition;
    private double m_projectileVel;
    private double m_timeStamp;
    private boolean m_useProjectileVel = false;
    private Vector2 m_aimVel;
    private Vector2 m_targetPos;
    private Vector2 m_targetSize = null;

    public Targeting(Vector2 targetPos, Vector2 targetSize, double timeStamp) {
        m_targetPos = targetPos;
        m_targetSize = targetSize;
        m_timeStamp = timeStamp;
    }

    public Targeting(double ProjectileVel, Vector2 targetPos, Vector2 targetSize, double timeStamp) {
        m_projectileVel = ProjectileVel;
        m_useProjectileVel = true;
        m_targetPos = targetPos;
        m_targetSize = targetSize;
        m_timeStamp = timeStamp;
    }

    public Targeting(Vector2 targetPos, double timeStamp) {
        m_timeStamp = timeStamp;
        m_targetPos = targetPos;
        m_useProjectileVel = false;
    }

    public void Update(Vector2 currentPosition) {
        if (!m_useProjectileVel) {
            m_prevPosition = m_currPosition;
            m_currPosition = currentPosition;

            m_futurePosition = Vector2.Add(
                    Vector2.Mult(m_currPosition, Vector2.position(2, 2)),
                    Vector2.Mult(m_prevPosition, Vector2.position(-1, -1)));

            m_aimVel = Vector2.Add(
                    Vector2.Dev(Vector2.Sub(m_currPosition, m_prevPosition), 0.1),
                    m_targetPos);
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
        Vector2 q = Vector2.Sub(m_aimVel, m_targetPos);
        q.y = 0;
        m_aimVel.y = 0;

        double a = Vector2.Dot(m_aimVel, m_aimVel) - (m_projectileVel * m_projectileVel);
        double b = 2 * Vector2.Dot(m_aimVel, q);
        double c = Vector2.Dot(q, q);

        double d = Math.sqrt((b * b) - 4 * a * c);
        double t1 = (-b + d) / (2 * a);
        double t2 = (-b - d) / (2 * a);

        var time = Math.max(t1, t2);

        Vector2 prodictedPosition = Vector2.Add(m_currPosition, m_aimVel).Mult(time);
        return prodictedPosition;
    }
}
