/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.limelightvision.LimeLight;

public class DriveTrain extends SubsystemBase {
  private LimeLight _limelight = new LimeLight();
  private AHRS gyro;

  private final SpeedController m_leftMotor = new SpeedControllerGroup(new Spark(Constants.LEFT_MOTOR_1_ID), new Spark(Constants.LEFT_MOTOR_2_ID));
  private final SpeedController m_rightMotor = new SpeedControllerGroup(new Spark(Constants.RIGHT_MOTOR_1_ID), new Spark(Constants.RIGHT_MOTOR_2_ID));

  private final DifferentialDrive drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    //drive.setExpiration(.5);
   // drive.setSafetyEnabled(false);

  }

  public void leftspeed(double s){
    m_leftMotor.set(s);
  }

  public void rightspeed(double s){
    m_rightMotor.set(s);
  }

  public void arcadedrive(double movevalue, double rotatevalue){
    drive.arcadeDrive(movevalue, rotatevalue);
  }

  public void tankdrive(double left, double right){
    drive.tankDrive(left, right);
  }

  public void stopmotor(){
    drive.stopMotor();
  }

  public LimeLight getlimelight(){
    return _limelight;
  }

  public void log(){
    SmartDashboard.putBoolean("TargetFound[tv]", getlimelight().getIsTargetFound());
    SmartDashboard.putNumber("GetDegVertical[ty]", getlimelight().getdegVerticalToTarget());
    SmartDashboard.putNumber("GetDegHorizontal[tx]", getlimelight().getdegRotationToTarget());
    SmartDashboard.putNumber("LeftMotorSpeed", m_leftMotor.get());
    SmartDashboard.putNumber("RightMotorSpeed", m_rightMotor.get());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    log();
  }
}
