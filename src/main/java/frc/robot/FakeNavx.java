// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI.Port;

/** Add your docs here. */
public class FakeNavx {
    Port m_port;
    double m_yaw;
    public static final double MAX_DEGREES_PER_SECOND = 360;

    public FakeNavx(Port NAVX_PORT) {
        m_port = NAVX_PORT;
    }

    public double getYaw() {
        return m_yaw;
    }

    // time is in seconds for update function
    public void update(double time, double motorSpeed) {
        // calculate the angular velocity from the motor speed
        double velocity = motorSpeed * MAX_DEGREES_PER_SECOND;
        // set up a "dead band" from -0.3 to 0.3 where the
        // motors are unable to turn the robot because they
        // cannot overcome friction.
        if (motorSpeed >= -0.3 && motorSpeed <= 0.3 ) {
            velocity = 0;
        }
        // calculate new angle based on angular velocity
        m_yaw = velocity * time + m_yaw;

        // wrap the angle around if it exceeds 180 degrees or
        // goes below -180
        if (m_yaw > 180) {
            double extra = m_yaw % 180;
            m_yaw = -180 + extra;
        }
        if (m_yaw < -180) {
            double extra = m_yaw % 180;
            m_yaw = 180 + extra;
        }
    }
}