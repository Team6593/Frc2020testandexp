package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NavX_Gyro extends SubsystemBase {
  private AHRS gyro;

	public boolean isCalibrating() {
    
		return gyro.isCalibrating();
	}
	
	public void zeroGyro() {
		gyro.zeroYaw();
	}
	
	public double getGyro() {
		return gyro.getYaw();
	}
	
	public double pidGet() {
		return this.getGyro();
  }
  
  public void log(){
    SmartDashboard.putNumber("Yaw", gyro.getYaw());
    SmartDashboard.putBoolean("imu-moving", gyro.isMoving());
		SmartDashboard.putBoolean("imu-connected", gyro.isConnected());
		SmartDashboard.putBoolean("imu-calibrating", gyro.isCalibrating());
		SmartDashboard.putData("imu", gyro);
  }
  /**
   * Creates a new NavX_Gyro.
   */
  public NavX_Gyro() {
    try {
      gyro = Constants.navx_gyro;
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
