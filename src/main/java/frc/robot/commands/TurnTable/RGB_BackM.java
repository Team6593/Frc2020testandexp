/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.TurnTable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class RGB_BackM extends CommandBase {
  /**
   * Creates a new RGB_StartM.
   */
  private ColorWheel colorWheel;
  private double speed;

  public RGB_BackM(ColorWheel cw, double RGB_MOTOR_SPEED) {
    // Use addRequirements() here to declare subsystem dependencies.
    colorWheel = cw;
    speed = RGB_MOTOR_SPEED;
    addRequirements(cw);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    colorWheel.back_rgbMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    colorWheel.stop_rgbMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
