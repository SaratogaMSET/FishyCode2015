package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AutoWinchSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Victor motor;
	DigitalInput hal;
	public static final double WINCH_DRIVE_POWER = .4;
	public static final double WINCH_OFF_POWER = 0.0;
	
	public AutoWinchSubsystem() {
		motor = new Victor(RobotMap.AUTO_WINCH.MOTOR);
		hal = new DigitalInput(RobotMap.AUTO_WINCH.LIM);
	}
	
	public void setPower(double power) {
		motor.set(power);
	}
	
	public void releaseCable() {
		//unlink the cable
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

