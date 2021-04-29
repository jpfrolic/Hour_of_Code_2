/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int leftMotorPort = 0;
    public static final int rightMotorPort = 1;
    public static final int leftEncoderA = 4;
    public static final int leftEncoderB = 5;
    public static final int rightEncoderA = 6;
    public static final int rightEncoderB = 7;
    public static final double wheelDiameter = 2.83;
    public static final double pulsesPerRev = 1440;
    public static final double inchesPerPulse = Math.PI * wheelDiameter / pulsesPerRev;
    public static final double wheelTrack = 5.25; // 5.86;
    public static final double inchesPerDegree = Math.PI * wheelDiameter / 360;
	public static final int XBOX_Left_Y_Axis = 1;
	public static final int XBOX_Left_X_Axis = 0;
	public static final int JOYSTICKNUMBER = 0;
	public static double DRIVETRAINSPEED = 0.7;
}
