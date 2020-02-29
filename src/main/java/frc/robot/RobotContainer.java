package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous.Autonomous;
import frc.robot.commands.Drive.ArcadeControl;
import frc.robot.commands.Drive.DriveStraightAuto;
import frc.robot.commands.Drive.HighGear;
import frc.robot.commands.Drive.LowGear;
import frc.robot.commands.LimeLight.Drive_limeLight_Aim;
import frc.robot.commands.LimeLight.Drive_limeLight_Aim_n_Range;
import frc.robot.commands.LimeLight.Drive_limeLight_Range;
import frc.robot.commands.LimeLight.Drive_limelight_Seeking;
import frc.robot.commands.LimeLight.LimeLED_ON;
import frc.robot.commands.ShootOut.ShootOutInverseRolling;
import frc.robot.commands.ShootOut.ShootOutStartRolling;
import frc.robot.commands.ShootOut.ShootOutStop;
import frc.robot.commands.TurnTable.RGB_BackM;
import frc.robot.commands.TurnTable.RGB_StartM;
import frc.robot.commands.TurnTable.ReachColorWheel;
import frc.robot.commands.TurnTable.UnreachColorWheel;
import frc.robot.subsystems.Cameras;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX_Gyro;
import frc.robot.subsystems.ShootOut;

public class RobotContainer {
  
  //SUBSYSTEMS
  private final DriveTrain m_drive = new DriveTrain();
  private final NavX_Gyro gyro = new NavX_Gyro();
  private final Cameras cam = new Cameras();
  private final ColorWheel cw = new ColorWheel();
  private final ShootOut so = new ShootOut();


  //LIMELIGHT
  private final Drive_limeLight_Aim aim = new Drive_limeLight_Aim(m_drive);
  private final Drive_limeLight_Range range = new Drive_limeLight_Range(m_drive);
  private final Drive_limeLight_Aim_n_Range aim_range = new Drive_limeLight_Aim_n_Range(m_drive);
  private final Drive_limelight_Seeking seeking = new Drive_limelight_Seeking(m_drive);
  private final LimeLED_ON ledON = new LimeLED_ON(m_drive);


  //AUTONOMUS
  private final DriveStraightAuto auto = new DriveStraightAuto(m_drive);
  private final CommandBase m_autonomousCommand = new Autonomous(m_drive);


  //TURN TABLE
  private final RGB_StartM rgb_StartM = new RGB_StartM(cw, .5);
  private final RGB_BackM rgb_BackM = new RGB_BackM(cw, -.5);
  private final ReachColorWheel reachColorWheel = new ReachColorWheel(cw);
  private final UnreachColorWheel unreachColorWheel = new UnreachColorWheel(cw);

  //SHOOTOUT
  private final ShootOutInverseRolling so_inverse = new ShootOutInverseRolling(so, -.5);
  private final ShootOutStartRolling so_start = new ShootOutStartRolling(so, .5);
  private final ShootOutStop so_stop = new ShootOutStop(so);
  
  //JOYSTICK
  private final XboxController x_stick = new XboxController(0);


  //SHIFTER
  private final HighGear highGear = new HighGear(m_drive);
  private final LowGear lowGear = new LowGear(m_drive);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drive.setDefaultCommand(new ArcadeControl(m_drive, () -> x_stick.getRawAxis(1), () -> x_stick.getRawAxis(4)));

    m_drive.log();
    gyro.log();
    cam.log();

    configureButtonBindings();
  }

  private void configureButtonBindings() {

    //BUTTON DECLARATION
    final JoystickButton b_Button = new JoystickButton(x_stick, Constants.B_BUTTON);
    final JoystickButton a_Button = new JoystickButton(x_stick, Constants.A_BUTTON);
    final JoystickButton x_Button = new JoystickButton(x_stick, Constants.X_BUTTON);
    final JoystickButton y_Button = new JoystickButton(x_stick, Constants.Y_BUTTON);
    final JoystickButton LEFT_TRIGGER_BUTTON = new JoystickButton(x_stick, Constants.LEFT_TRIGGER_BUTTON);
    final JoystickButton RIGHT_TIGGER_BUTTON = new JoystickButton(x_stick, Constants.RIGHT_TRIGGER_BUTTON);
    final JoystickButton right_small_Button = new JoystickButton(x_stick, Constants.RIGHT_SMALL_BUTTON);

    //BUTTON MAPPING
     a_Button.whileHeld(rgb_StartM);
     b_Button.whileHeld(rgb_BackM);
     LEFT_TRIGGER_BUTTON.whenPressed(highGear);
     RIGHT_TIGGER_BUTTON.whenPressed(lowGear);
     x_Button.whenPressed(reachColorWheel);
     y_Button.whenPressed(unreachColorWheel);
  
  }

  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}
