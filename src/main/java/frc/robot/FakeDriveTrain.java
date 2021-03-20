// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** Add your docs here. */
public class FakeDriveTrain {
    FakeMotor m_front_left, m_front_right, m_back_left, m_back_right;

    public FakeDriveTrain(FakeMotor f1, FakeMotor f2, FakeMotor b1, FakeMotor b2) {
        m_front_left = f1;
        m_front_right = f2;
        m_back_left = b1;
        m_back_right = b2;
    }
    public void tankDrive(double left_speed, double right_speed) {
        m_front_right.set(right_speed);
        m_back_right.set(right_speed);
        m_front_left.set(left_speed);
        m_back_left.set(left_speed);
    }
}
