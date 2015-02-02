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
	
	public ContainerGrabberSubsystem() {
		piston1 = new DoubleSolenoid(RobotMap.CONTAINER_GRABBER.PISTON_1_FORWARD_CHANNEL, RobotMap.CONTAINER_GRABBER.PISTON_1_REVERSE_CHANNEL);
		piston2 = new DoubleSolenoid(RobotMap.CONTAINER_GRABBER.PISTON_2_FORWARD_CHANNEL, RobotMap.CONTAINER_GRABBER.PISTON_2_REVERSE_CHANNEL);
		grabberState = GRABBER_DEFAULT_STATE;
		piston1.set(GRABBER_DEFAULT_STATE);
		piston2.set(GRABBER_DEFAULT_STATE);
	}
	
	public void ToggleGrabberState() {
		if(grabberState.equals(DoubleSolenoid.Value.kReverse)) {
			piston1.set(DoubleSolenoid.Value.kForward);
			piston2.set(DoubleSolenoid.Value.kForward);
			grabberState = DoubleSolenoid.Value.kForward;
		} else
			piston1.set(DoubleSolenoid.Value.kReverse);
			piston2.set(DoubleSolenoid.Value.kReverse);
			grabberState = DoubleSolenoid.Value.kReverse;
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

