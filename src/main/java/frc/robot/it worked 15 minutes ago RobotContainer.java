/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.Forward;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.Labyrinth;
import frc.robot.commands.Turn;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import frc.robot.subsystems.OnBoardIO;
import frc.robot.subsystems.OnBoardIO.ChannelMode;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
// for Intake and IntakeBall
  private final Intake intake;
  private final IntakeBall intakeBall;

  private final OnBoardIO m_onboardIO = new OnBoardIO(ChannelMode.INPUT, ChannelMode.INPUT);

  public static final Chassis chassis = new Chassis();
  public static Joystick joystick = new Joystick(0);

  public static XboxController driverJoystick;

  public static XboxController controller = new XboxController(0);


  //private final XboxController m_Controller = new XboxController(0);// added from jasondaring chief delphi
   

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driverJoystick = new XboxController(Constants.JOYSTICKNUMBER);// Same as new XboxController(0);


    // Configure the button bindings      
    configureButtonBindings();

    intake = new Intake();
    intakeBall = new IntakeBall(intake);  // command intakeBall is of class IntakeBall and needs argument intake?
    intakeBall.addRequirements(intake);
    intake.setDefaultCommand(intakeBall);

    SmartDashboard.putData("Forward", new Forward(13));//Label Box Forward and put Command Forward(13) as link
    SmartDashboard.putData("Turn90", new Turn(90));
    SmartDashboard.putData("TurnN90", new Turn(-90));
    SmartDashboard.putData("Labyrinth", new Labyrinth());

    chassis.setDefaultCommand(new DefaultDrive());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    Button onboardButtonA = new Button(m_onboardIO::getButtonAPressed);
    onboardButtonA
        //.whenActive(new PrintCommand("Button A Pressed"))
        //.whenActive(new Forward(2))
        //.whenActive(setYellowLed(true)) //OnBoardIO.setYellowLed(true)
        //.whenInactive(new PrintCommand("Button A Released"));

        //onboardButtonA
        .whenActive(() -> m_onboardIO.setYellowLed(true))
        .whenInactive(() -> m_onboardIO.setYellowLed(false));

        Button onboardButtonB = new Button(m_onboardIO::getButtonBPressed);//Line 36 ChannelMode.INPUT 
        onboardButtonB
        .whenActive(new PrintCommand("Button B Pressed"))
        .whenInactive(new PrintCommand("Button B Released"));

    //m_controller.XButton.whenPressed(() -> m_onboardIO.setYellowLed(true));
    //m_controller.YButton.whenPressed(() -> m_onboardIO.setYellowLed(false));
    
    //JoystickButton YAxis = new JoystickButton(controller, XboxController.Axis.kLeftY.value);
    // new PrintCommand(YAxis); // Should print value of kLeftY

    JoystickButton bumperLeft = new JoystickButton(joystick, 5);
    bumperLeft.whenActive(new Forward(2));// System.out.println("I got here."));
    JoystickButton bumperRight = new JoystickButton(joystick, 6);
    bumperRight.whenPressed(new Forward(15));// System.out.println("I got here."));
    JoystickButton AButton = new JoystickButton(joystick, 1);
    AButton.whenReleased(new Forward(5));//
    JoystickButton XButton = new JoystickButton(joystick, 3);
        // Because of bug First push does not turn on LED
    XButton.whenActive(() -> m_onboardIO.setYellowLed(true));// X turns yellow LED ON
    //was  XButton.whenActive(new Forward(20));//
    JoystickButton BButton = new JoystickButton(joystick, 2);
    BButton.whenActive(new Turn(15));//
    JoystickButton YButton = new JoystickButton(joystick, 4);
    YButton.whenActive(() -> m_onboardIO.setYellowLed(false));// Y turns yellow LED OFF

    //was YButton.whenActive(new Forward(20));//
    JoystickButton backButton = new JoystickButton(joystick, 7);
    backButton.toggleWhenPressed(new Forward(5));//
    JoystickButton startButton = new JoystickButton(joystick, 8);
    startButton.whenActive(new Turn(-180));// 
    
    //driveWithJoystick(10, .3);

 // if (m_controller.getAButtonPressed()) () -> m_onboardIO.setRedLed(true);                                       //;
 //m_controller.kY.whenPressed(() -> m_onboardIO.setRedLed(false));
    
    //if (true) {new exampleController.getAButtonPressed();}
    //exampleController XboxController.getStickButtonPressed(Axis.kLeft);    
  }
 
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
