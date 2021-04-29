/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Turn extends CommandBase {

  double inches;
  double turnSpeed;

  public Turn(double angle) {
    addRequirements(RobotContainer.chassis);
    inches = Math.abs(angle) * Math.PI * Constants.wheelTrack / 360.0;
    turnSpeed = 0.10 * Math.signum(angle);
  }

  @Override
  public void initialize() {
    RobotContainer.chassis.reset();
  }

  @Override
  public void execute() {
    RobotContainer.chassis.drive(0, turnSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.chassis.stop();
  }

  @Override
  public boolean isFinished() {
    return Math.abs(RobotContainer.chassis.getLeftDistance()) >= inches;
  }
}
