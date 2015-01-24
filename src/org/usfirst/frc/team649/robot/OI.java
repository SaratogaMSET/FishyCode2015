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
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.PURGE);
		}
		
		public boolean isIntakeButtonPressed(){
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.INTAKE);
		}
		
    	public boolean isRaiseToteButtonPressed() {	
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.RAISE_TOTE);
		}
		
		public boolean isLowerToteButtonPressed() {
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.LOWER_TOTE);
		}
	
		public boolean isScoreAllButtonPressed() {
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.SCORE_ALL);
		}
		
		public boolean isStepButtonPressed() {
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.STEP_OFFSET);
		}
		
		public boolean isStoreButtonPressed() {
			return operatorJoystick.getRawButton(RobotMap.JOYSTICKS.STORE);
		}
		
    }
    
}

