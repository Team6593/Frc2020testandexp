/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.limelightvision.ControlMode.LedMode;
import frc.robot.subsystems.DriveTrain;

public class Drive_limeLight_Aim extends CommandBase {
  private DriveTrain drive;
  private double kpAim = 0.05;
  //private float min_command = 0.01f;
  private double m_moveValue = 0;
  private double m_rotateValue = 0;
  
  /**
   * Creates a new Drive_limeLight_Aim.
   */
  public Drive_limeLight_Aim(DriveTrain d) {
    drive = d;
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
    double tx = drive.getlimelight().getdegRotationToTarget();
    boolean targetfound = drive.getlimelight().getIsTargetFound();
    //double steering_adjust = 0.0;
    //double headerror = -tx;

    if(targetfound){
      m_moveValue = 0;
      m_rotateValue = tx * kpAim;
    }else{
      m_moveValue = 0;
      m_rotateValue = 0;
    }

    drive.arcadedrive(m_moveValue, m_rotateValue);

    
      // if(tx > 1){
      //   steering_adjust = kpAim*headerror - min_command;
      // }
      // else if (tx < 1){
      //   steering_adjust = kpAim*headerror + min_command;
      // }
      // m_moveValue += steering_adjust;
      // m_rotateValue -= steering_adjust;

      // drive.tankdrive(m_moveValue, m_rotateValue);
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.getlimelight().setLEDMode(LedMode.kforceOff);
    drive.arcadedrive(0, 0);
    //drive.tankdrive(0, 0);
    //drive.stopmotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
}
