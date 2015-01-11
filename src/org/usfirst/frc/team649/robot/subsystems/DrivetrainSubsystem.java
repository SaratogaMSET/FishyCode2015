package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem implements PIDOutput, PIDSource{
    
    private SpeedController[] motors;
    private Encoder[] encoders;

    
    public DrivetrainSubsystem() {
    	motors = new SpeedController[RobotMap.DRIVE_TRAIN.Motors.length];
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}

