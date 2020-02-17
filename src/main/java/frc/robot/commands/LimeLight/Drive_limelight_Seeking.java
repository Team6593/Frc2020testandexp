/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.LimeLight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.limelightvision.ControlMode.LedMode;
import frc.robot.subsystems.DriveTrain;

public class Drive_limelight_Seeking extends CommandBase {
  /**
   * Creates a new Drive_limelight_Seeking.
   */

  private DriveTrain drive;
  private double kaim = 0.05;
  


  public Drive_limelight_Seeking(DriveTrain d) {
    drive = d;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.getlimelight().setLEDMode(LedMode.kforceOn);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean tv = drive.getlimelight().getIsTargetFound();
    double tx = drive.getlimelight().getdegRotationToTarget();

    double m_rotateValue = 0.0;
    double m_moveValue = 0.0;
    if(tv == false){
      m_rotateValue = 0.59;
      m_moveValue = 0.0;
    }else{
      m_rotateValue = kaim * tx;
      m_moveValue = 0.0;
    }
    drive.arcadedrive(m_moveValue, m_rotateValue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.arcadedrive(0, 0);
    drive.getlimelight().setLEDMode(LedMode.kforceOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
