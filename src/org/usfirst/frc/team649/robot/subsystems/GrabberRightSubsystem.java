
package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.commands.CommandBase;
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
public class GrabberRightSubsystem extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Victor roller, arm;
	public Potentiometer pot;
	public PIDController pid;
	public DigitalInput touchSensor;
	
	
	
    public GrabberRightSubsystem(){
    	super("Grabber Right Subsystem", RobotMap.GRABBER.PIDConstants.P, RobotMap.GRABBER.PIDConstants.I, RobotMap.GRABBER.PIDConstants.D);
    	
    	pid = FishyRobot2015.commandBase.grabberRightSubsystem.getPIDController();
    	pid.setAbsoluteTolerance(RobotMap.GRABBER.PIDConstants.ABS_TOLERANCE);
    	
    	//potentiometer
    	pot = new AnalogPotentiometer(RobotMap.GRABBER.POTS[0]);
    	
    	//motors
    	roller = new Victor(RobotMap.GRABBER.MOTORS[0]);
    	arm = new Victor(RobotMap.GRABBER.MOTORS[2]);
    	
    	touchSensor = new DigitalInput(RobotMap.GRABBER.LIMIT_SWITCH_IN_RIGHT);
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

