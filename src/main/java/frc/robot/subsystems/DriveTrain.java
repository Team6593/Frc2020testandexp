/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.limelightvision.LimeLight;

public class DriveTrain extends SubsystemBase {
  private LimeLight _limelight = new LimeLight();

  private final SpeedController m_leftMotor =
  new SpeedControllerGroup(new Spark(2), new Spark(3));
private final SpeedController m_rightMotor =
  new SpeedControllerGroup(new Spark(0), new Spark(1));

  private final DifferentialDrive drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    drive.setExpiration(.5);
    drive.setSafetyEnabled(false);

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
    SmartDashboard.getBoolean("TargetFound[tv]", getlimelight().getIsTargetFound());
    SmartDashboard.getNumber("GetDegVertical[ty]", getlimelight().getdegVerticalToTarget());
    SmartDashboard.getNumber("GetDegHorizontal[tx]", getlimelight().getdegRotationToTarget());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    log();
  }
}
