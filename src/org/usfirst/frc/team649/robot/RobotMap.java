package org.usfirst.frc.team649.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static final class DRIVE_TRAIN {
		public static final int[] MOTORS = new int[] {1,2,3,4};
        public static final int[] ENCODERS = new int[]{3, 4, 6, 7};

	}
	
	public static final class CHAIN_LIFT {
		public static final int[] MOTORS = new int[] {5,6,7,8};
        public static final int[] ENCODERS = new int[]{8, 9, 10, 11};
		public static final int RESET_LIM_SWITCH = 1;
		public static final int MAX_LIM_SWITCH = 0;


	}
}
