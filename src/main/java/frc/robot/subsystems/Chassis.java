package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  PWMVictorSPX leftMotor;
  PWMVictorSPX rightMotor;
  DifferentialDrive diffDrive;
  Encoder leftEncoder;
  Encoder rightEncoder;
  public static XboxController controller = new XboxController(0);


  public Chassis() {
    leftMotor = new PWMVictorSPX(Constants.leftMotorPort);
    rightMotor = new PWMVictorSPX(Constants.rightMotorPort);

    diffDrive = new DifferentialDrive(leftMotor, rightMotor);
    addChild("diffDrive", diffDrive);
    diffDrive.setRightSideInverted(false);
    diffDrive.setSafetyEnabled(false);

    leftEncoder = new Encoder(Constants.leftEncoderA, Constants.leftEncoderB, true);
    addChild("leftEncoder", leftEncoder);
    leftEncoder.setDistancePerPulse(Constants.inchesPerPulse);
    rightEncoder = new Encoder(Constants.rightEncoderA, Constants.rightEncoderB, true);
    addChild("rightEncoder", rightEncoder);
    rightEncoder.setDistancePerPulse(Constants.inchesPerPulse);
  }
// drive with joystick (XboxController) reference source is YouTube FRC command based System
  public void driveWithJoystick(XboxController controller, double speed)
  {
    
    diffDrive.arcadeDrive(controller.getRawAxis(Constants.XBOX_Left_Y_Axis) * speed,
        controller.getRawAxis(Constants.XBOX_Left_X_Axis) * speed);
  }


  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightDistance() {
    return rightEncoder.getDistance();
  }

  public void drive(double speed, double direction) {
    diffDrive.arcadeDrive(speed, direction, false);
  }

  public void drive(double speed, double direction, boolean squaredInputs) {
    diffDrive.arcadeDrive(speed, direction, squaredInputs);
  }

  public void reset() {
    rightEncoder.reset();
    leftEncoder.reset();
  }

  public void stop() {
    diffDrive.arcadeDrive(0, 0);
  }
}
