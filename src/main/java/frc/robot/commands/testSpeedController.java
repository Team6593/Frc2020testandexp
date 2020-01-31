/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class testSpeedController extends CommandBase {
  private DriveTrain drive;
  private double lspeed;
  private double rspeed;
  private double to;
  /**
   * Creates a new testSpeedController.
   */
  public testSpeedController(DriveTrain d, double ls, double rs, double timeout) { 
     drive = d;
     lspeed = ls;
     rspeed = rs;
     to = timeout;
    addRequirements(drive);
    //withTimeout(timeout);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Timer.getFPGATimestamp() != to){
      drive.leftspeed(lspeed);
      drive.rightspeed(rspeed);
    }
    else{
      drive.leftspeed(0);
      drive.rightspeed(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stopmotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
