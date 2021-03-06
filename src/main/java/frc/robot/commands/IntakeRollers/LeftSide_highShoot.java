/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeRollers;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeRollers;

public class LeftSide_highShoot extends CommandBase {
  private IntakeRollers intake;
  private double vertspeed = 0;
  private double horspeed = 0;
  private double shootoutS;
  /**
   * Creates a new RightSide_lowShoot.
   */
  public LeftSide_highShoot(IntakeRollers i, double verticleS, double horizontalS, double shootoutspeed) {
    intake = i;
    vertspeed = verticleS;
    horspeed = horizontalS;
    shootoutS = shootoutspeed;
    addRequirements(intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.soSetSpeed(shootoutS);
    if(intake.getSo_Speed() == shootoutS){
      intake.leftMec_highShoot(horspeed, vertspeed);
    }
    else
    {
      intake.leftMec_highShoot(horspeed -.1, vertspeed -.1);
    }
     
    //intake.leftMec_highShoot(horspeed, vertspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopMainRoller();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
