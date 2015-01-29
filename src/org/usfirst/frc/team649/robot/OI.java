package org.usfirst.frc.team649.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick operatorJoystick;
	private Joystick horizontal;
    private Joystick vertical;
    
    public OI(){
    	operatorJoystick = new Joystick(RobotMap.JOYSTICKS.JOYSTICK_OPERATOR);
    	//i think...i cant remember actually
    	vertical = new Joystick(RobotMap.JOYSTICKS.JOYSTICK_DRIVER_RIGHT);
    	horizontal = new Joystick(RobotMap.JOYSTICKS.JOYSTICK_DRIVER_LEFT);
    }
    
    public class Operator{
    	
		public boolean isPurgeButtonPressed(){
			return operatorJoystick.getRawButton(1);
		}
		
		public boolean isIntakeButtonPressed(){
			return operatorJoystick.getRawButton(2);
		}
		
    	public boolean isRaiseToteButtonPressed() {	
			return operatorJoystick.getRawButton(5);
		}
		
		public boolean isLowerToteButtonPressed() {
			return operatorJoystick.getRawButton(6);
		}
	
		public boolean isScoreAllButtonPressed() {
			return operatorJoystick.getRawButton(7);
		}
		
		public boolean isStepButtonPressed() {
			return operatorJoystick.getRawButton(8);
		}
		
		public boolean isStoreButtonPressed() {
			return operatorJoystick.getRawButton(9);
		}
		
    }
    
}

