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
	
	public static final class GRABBER {
		//FOR RIGHT AND LEFT, so there are some not-IO things here so i didnt have to repeat per class
		
		//0 and 1 are RIGHT....2 and 3 are LEFT    (roller and then arm)
		public static final int[] MOTORS = new int[] {9, 10, 12, 13};
		public static final int[] POTS = new int[] {0,1};
		public static final int LIMIT_SWITCH_IN_RIGHT = 0;
		public static final int LIMIT_SWITCH_IN_LEFT = 1;
		
		//CONSTANTS THAT ARE SHARED
		
		//(these are the pot values and -- of course not accurate at all lolol)
		//for releasing
		public static final double ARM_POS_RELEASE = 1000000.0;
		//for pulling in totes
		public static final double ARM_POS_GRABBING = 0.0;
		//for both arms completely back
		public static final double ARM_POS_STORING = 6000000.0;
		
		public static final class PIDConstants{
			public static final double P = 0.0;
			public static final double I = 0.0;
			public static final double D = 0.0;
			public static final double ABS_TOLERANCE = 0;
		}
	}
}
