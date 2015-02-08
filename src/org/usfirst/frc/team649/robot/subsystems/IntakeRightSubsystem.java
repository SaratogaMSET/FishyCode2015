
package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem.PIDConstants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 * 
 */
public class IntakeRightSubsystem extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Victor roller, arm;
	public Potentiometer pot;
	public PIDController pid;
	public DigitalInput touchSensor;
	public static final double INTAKE_ROLLER_SPEED = 0.4;

	public static final class PIDConstants{
		public static final double P = 0.0;
		public static final double I = 0.0;
		public static final double D = 0.0;
		public static final double ABS_TOLERANCE = 0;
		
		public static final double ARM_POS_RELEASE = 1000000.0;
		public static final double ARM_POS_GRABBING = 0.0;
		public static final double ARM_POS_STORING = 6000000.0;
		
		public static final int GRABBING_STATE = 0;
		public static final int RELEASING_STATE = 1;
		public static final int STORE_STATE = 2;
	}	
	
    public IntakeRightSubsystem(){
    	super("Grabber Right Subsystem", PIDConstants.P, PIDConstants.I, PIDConstants.D);
    	/*
    	 * see intakeLeftSubsystem
    	pid = FishyRobot2015.intakeRightSubsystem.getPIDController();
    	pid.setAbsoluteTolerance(PIDConstants.ABS_TOLERANCE);
    	*/
    	//potentiometer
    	pot = new AnalogPotentiometer(RobotMap.RIGHT_GRABBER.POT);
    	
    	//motors
    	roller = new Victor(RobotMap.RIGHT_GRABBER.ROLLER_MOTOR);
    	arm = new Victor(RobotMap.RIGHT_GRABBER.ARM_MOTOR);
    	
    	touchSensor = new DigitalInput(RobotMap.RIGHT_GRABBER.LIMIT_SWITCH);
    }
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public double getPot(){
		return pot.get();
	}
    

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return getPot();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		arm.set(output);
	}
}

