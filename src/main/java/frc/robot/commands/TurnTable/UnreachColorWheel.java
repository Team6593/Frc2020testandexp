package frc.robot.commands.TurnTable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class UnreachColorWheel extends CommandBase {
  ColorWheel colorWheel;
  /**
   * Creates a new UnreachColorWheel.
   */
  public UnreachColorWheel(ColorWheel cw) {
    colorWheel = cw;
    addRequirements(colorWheel);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    colorWheel.unreachColorWheel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
