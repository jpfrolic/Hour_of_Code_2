/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Forward extends CommandBase {

  double distance;

  public Forward(double inches) {
    addRequirements(RobotContainer.chassis);
    distance = inches;
  }

  @Override
  public void initialize() {
    RobotContainer.chassis.reset();
  }

  @Override
  public void execute() {
    RobotContainer.chassis.drive(0.15, 0);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.chassis.stop();
  }

  @Override
  public boolean isFinished() {
    return RobotContainer.chassis.getLeftDistance() >= distance;
  }
}
