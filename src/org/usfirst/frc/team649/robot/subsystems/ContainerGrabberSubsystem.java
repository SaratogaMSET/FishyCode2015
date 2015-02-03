package org.usfirst.frc.team649.robot.subsystems;


import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ContainerGrabberSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	DoubleSolenoid piston1;
	DoubleSolenoid piston2;
	Value grabberState;
	public static final Value GRABBER_DEFAULT_STATE = DoubleSolenoid.Value.kReverse;
	public static final Value GRABBER_CLOSED_STATE = DoubleSolenoid.Value.kForward;
	public static final Value GRABBER_OPENED_STATE = DoubleSolenoid.Value.kReverse;
	
	public ContainerGrabberSubsystem() {
		piston1 = new DoubleSolenoid(RobotMap.CONTAINER_GRABBER.PISTON_1_FORWARD_CHANNEL, RobotMap.CONTAINER_GRABBER.PISTON_1_REVERSE_CHANNEL);
		piston2 = new DoubleSolenoid(RobotMap.CONTAINER_GRABBER.PISTON_2_FORWARD_CHANNEL, RobotMap.CONTAINER_GRABBER.PISTON_2_REVERSE_CHANNEL);
		grabberState = GRABBER_DEFAULT_STATE;
		SetGrabberState(GRABBER_DEFAULT_STATE);
	}
	
	public void SetGrabberState(Value state) {
		if(!state.equals(grabberState)) {
			grabberState = state;
				piston1.set(grabberState);
				piston2.set(grabberState);
		}
		
	}
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

