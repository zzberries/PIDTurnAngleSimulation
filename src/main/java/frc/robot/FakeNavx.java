// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.SPI.Port;

/** Add your docs here. */
public class FakeNavx {
    Port m_port;
    double m_yaw;
    public FakeNavx(Port NAVX_PORT) {
        m_port = NAVX_PORT;
    }
    public double getYaw() {
        return m_yaw;
    }
    
}