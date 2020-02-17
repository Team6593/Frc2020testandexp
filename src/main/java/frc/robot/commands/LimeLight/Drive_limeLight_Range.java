/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.LimeLight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.limelightvision.ControlMode.LedMode;
import frc.robot.subsystems.DriveTrain;

public class Drive_limeLight_Range extends CommandBase {
  private DriveTrain drive;
  private double kpDistance = 0.1;
  private double m_moveValue;
  private double m_rotateValue;
  /**
   * Creates a new Drive_limeLight_Range.
   */
  public Drive_limeLight_Range(DriveTrain d) {
    drive = d;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.getlimelight().setLEDMode(LedMode.kforceOn);
    //Timer.delay(1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double ty = drive.getlimelight().getdegVerticalToTarget();
    boolean targetfound = drive.getlimelight().getIsTargetFound();

    if(targetfound){
      m_moveValue = ty * kpDistance;
      m_rotateValue = 0;
    }else{
      m_moveValue = 0;
      m_rotateValue = 0;
    }

    drive.arcadedrive(m_moveValue, m_rotateValue);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.arcadedrive(0, 0);
    //drive.getlimelight().setLEDMode(LedMode.kforceOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  /*
  h1 =  The height of your camera above the floor.
  h2 = the height of the target.
  a1 = its mounting angle.
  a2 = The limelight (or your vision system) can tell you the y angle to the target.
  */
  private double Estimated_Distance(double a2){
    double h1 = 6.0;
    double h2 = 36.0;
    double a1 = 0.0;
    return (h2-h1)/Math.tan(a1+a2);
  }
}
