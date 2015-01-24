package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class GrabberLeftSubsystem extends PIDSubsystem {

	public Victor roller, arm;
	public Potentiometer pot;
	public PIDController pid;
	public DigitalInput touchSensor;
	
	public GrabberLeftSubsystem(){
		super("Grabber Left Subsystem", RobotMap.GRABBER.PIDConstants.P, RobotMap.GRABBER.PIDConstants.I, RobotMap.GRABBER.PIDConstants.D);
    	
    	pid =  FishyRobot2015.commandBase.grabberLeftSubsystem.getPIDController();
    	pid.setAbsoluteTolerance(RobotMap.GRABBER.PIDConstants.ABS_TOLERANCE);
    	
    	//potentiometer
    	pot = new AnalogPotentiometer(RobotMap.GRABBER.POTS[1]);
    	
    	//motors
    	roller = new Victor(RobotMap.GRABBER.MOTORS[1]);
    	arm = new Victor(RobotMap.GRABBER.MOTORS[3]);
    	
    	touchSensor = new DigitalInput(RobotMap.GRABBER.LIMIT_SWITCH_IN_LEFT);
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
		arm.set(output);;

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
