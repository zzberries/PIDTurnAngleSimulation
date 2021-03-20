/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.FakeDriveTrain;
import frc.robot.FakeMotor;
import frc.robot.FakeNavx;
import frc.robot.RobotMap;
import frc.robot.TestingDashboard;

public class Drive extends SubsystemBase {
  private FakeMotor frontLeft;
  private FakeMotor frontRight;
  private FakeMotor backLeft;
  private FakeMotor backRight;

  final double PULSE_PER_FOOT = 1300;
  final double PULSE_PER_METER = 4265.1;
  public final double WHEEL_SIZE = 6;  //measured in inches
  public final double PULSES_PER_ROTATION = 2048;
  // ( inches / pulse ) = (WHEEL_SIZE * PI ) * ( 1 / PULSES_PER_ROTATION)
  public final double INCHES_PER_PULSE = (WHEEL_SIZE * Math.PI)/PULSES_PER_ROTATION;
  public final static double INITIAL_SPEED = 0.3;

  private FakeDriveTrain drivetrain;

  private FakeNavx ahrs;

  private static Drive drive;

  private double m_leftSpeed;
  private double m_rightSpeed;


  // private Pose2d pose;

  /**
   * Creates a new Drive.
   */
  private Drive() {
    frontLeft = new FakeMotor(RobotMap.D_FRONT_LEFT);
    frontRight = new FakeMotor(RobotMap.D_FRONT_RIGHT);
    backLeft = new FakeMotor(RobotMap.D_BACK_LEFT);
    backRight = new FakeMotor(RobotMap.D_BACK_RIGHT);

    ahrs = new FakeNavx(RobotMap.D_NAVX);

    drivetrain = new FakeDriveTrain(backLeft, backRight, frontLeft, frontRight);
    
    m_leftSpeed = 0;
    m_rightSpeed = 0;
  }

  /**
   * Used outside of the Drive subsystem to return an instance of Drive subsystem.
   * 
   * @return Returns instance of Drive subsystem formed from constructor.
   */
  public static Drive getInstance() {
    if (drive == null) {
      drive = new Drive();
      TestingDashboard.getInstance().registerSubsystem(drive, "Drive");
      TestingDashboard.getInstance().registerString(drive, "AHRS", "Heading", "");
      TestingDashboard.getInstance().registerNumber(drive, "Encoder", "LeftEncoderDistance", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Encoder", "RightEncoderDistance", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Travel", "DistanceToTravelInInches", 12);
      TestingDashboard.getInstance().registerNumber(drive, "Travel", "SpeedOfTravel", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Travel", "SpeedToTravel", INITIAL_SPEED);
      TestingDashboard.getInstance().registerNumber(drive, "Turn", "AngleToTurnInDegrees", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Turn", "SpeedWhenTurning", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Turn", "CurrentYawAngle", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Turn", "InitialAngle", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Turn", "m_leftSpeed", 0);
      TestingDashboard.getInstance().registerNumber(drive, "Turn", "m_rightSpeed", 0);




    }
    return drive;
  }

  // Drive Methods
  public void tankDrive(double leftSpeed, double rightSpeed) {
    drivetrain.tankDrive(leftSpeed, rightSpeed);
    m_leftSpeed = leftSpeed;
    m_rightSpeed = rightSpeed;
  }


  //Sensor Methods

  //AHRS Methods
  public double getYaw() {
    return ahrs.getYaw();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    TestingDashboard.getInstance().updateNumber(drive, "CurrentYawAngle", ahrs.getYaw());

    TestingDashboard.getInstance().updateNumber(drive, "m_leftSpeed", m_leftSpeed);
    TestingDashboard.getInstance().updateNumber(drive, "m_rightSpeed", m_rightSpeed);
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    // TODO Auto-generated method stub
    super.setDefaultCommand(defaultCommand);
  }
}
