/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.limelightvision.LimeLight;

public class DriveTrain extends SubsystemBase {
  private LimeLight _limelight = new LimeLight();
  private AHRS gyro;

  private final SpeedController m_leftMotor = new SpeedControllerGroup(new PWMSparkMax(Constants.m_MasterLeftID), new PWMSparkMax(Constants.m_SlaveLeftID));
  private final SpeedController m_rightMotor = new SpeedControllerGroup(new PWMSparkMax(Constants.m_MasterRightID ), new PWMSparkMax(Constants.m_SlaveRightID));

  private final DifferentialDrive drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final DoubleSolenoid shifter = Constants.SHIFTER_SOLENOID;
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    drive.setExpiration(.5);
    drive.setSafetyEnabled(false);
    gyro = Constants.navx_gyro;

  }

  public void highGear(){
    shifter.set(Value.kForward);
  }

  public void lowGear(){
    shifter.set(Value.kReverse);
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
