/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.LimeLight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.limelightvision.ControlMode.LedMode;
import frc.robot.subsystems.DriveTrain;

public class LimeLED_ON extends CommandBase {
  private DriveTrain drive;
  /**
   * Creates a new LimeLED_ON.
   */
  public LimeLED_ON(DriveTrain d) {
    drive = d;
    addRequirements(drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.getlimelight().setLEDMode(LedMode.kforceOn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.getlimelight().setLEDMode(LedMode.kforceOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
