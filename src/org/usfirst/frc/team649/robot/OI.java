package org.usfirst.frc.team649.robot;

import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.subsystems.IntakeRightSubsystem;
import org.usfirst.frc.team649.robot.triggers.GrabArmTrigger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick operatorJoystick;
	public Joystick driveJoystickHorizontal;
	public Joystick driveJoystickVertical;
	public Operator operator;
	public Driver driver;

	
	public OI() {
		operatorJoystick = new Joystick(RobotMap.JOYSTICKS.JOYSTICK_OPERATOR);
		// i think...i cant remember actually
		driveJoystickVertical = new Joystick(RobotMap.JOYSTICKS.JOYSTICK_DRIVER_RIGHT);
		driveJoystickHorizontal = new Joystick(RobotMap.JOYSTICKS.JOYSTICK_DRIVER_LEFT);
		driver = new Driver();
		operator = new Operator();
		
	}

	public class Operator {
		public Button purgeButton = new JoystickButton(operatorJoystick, 1);
		public Button intakeButton = new JoystickButton(operatorJoystick, 2);
		public Button raiseToteButton = new JoystickButton(operatorJoystick, 5);
		public Button lowerToteButton = new JoystickButton(operatorJoystick, 6);
		public Button scoreAllButton = new JoystickButton(operatorJoystick, 7);
		public Button stepButton = new JoystickButton(operatorJoystick, 8);
		public Button storeButton = new JoystickButton(operatorJoystick, 9);
		 
		public Operator() {
//			purgeButton.whileHeld(new RunRoller(IntakeRightSubsystem.INTAKE_ROLLER_SPEED));
//			intakeButton.whenPressed(new IntakeTote());
//			raiseToteButton.whenPressed(new R);
		}
		
		public boolean isGrabArmState() {
	        return (operatorJoystick.getThrottle() > 0.9);
		}
		
		public boolean isReleaseArmState() {
	        return (operatorJoystick.getThrottle() >= 0.1 && operatorJoystick.getThrottle() <= 0.9);
		}
		
		public boolean isStoreArmState() {
	        return (operatorJoystick.getThrottle() <= 0.1);
		}
	}

	public class Driver {

		public static final double MIN_DRIVE_POWER = 0.05;
		public static final double ROTATION_POWER = 1.5;

		public double getDriveRotation() {
			final double turnVal = joystickDeadzone(driveJoystickHorizontal.getX(),
					MIN_DRIVE_POWER);
			final double sign = turnVal < 0 ? -1 : 1;
			return Math.pow(Math.abs(turnVal), ROTATION_POWER) * sign;
		}

		public double getDriveForward() {
			double value = -driveJoystickVertical.getY();
			value = joystickDeadzone(value, MIN_DRIVE_POWER);
			return value;
		}

		private double joystickDeadzone(double value, double deadzone) {
			if (Math.abs(value) < deadzone) {
				value = 0;
			}
			return value;
		}

	}
}
