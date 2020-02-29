/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ShootOut;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShootOut;

public class ShootOutInverseRolling extends CommandBase {
  private ShootOut so = new ShootOut();
  private double s = 0;
  /**
   * Creates a new StartRolling.
   */
  public ShootOutInverseRolling(ShootOut shoot, double speed) {
    so = shoot;
    s = speed;
    addRequirements(so);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    so.start_rolling(s);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    so.stop_rolling();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
