// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI.Port;

/** Add your docs here. */
public class FakeNavx {
    Port m_port;
    double m_yaw;
    public static final double MAX_DEGREES_PER_SECOND = 180;

    public FakeNavx(Port NAVX_PORT) {
        m_port = NAVX_PORT;
    }

    public double getYaw() {
        return m_yaw;
    }

    // time is in seconds for update function
    public void update(double time, double motorSpeed) {
        double velocity = motorSpeed * MAX_DEGREES_PER_SECOND;
        if (motorSpeed >= -0.3 && motorSpeed <= 0.3 ) {
            velocity = 0;
        }
        m_yaw = velocity * time + m_yaw;
    }
}