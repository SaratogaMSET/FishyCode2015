package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.EncoderBasedDriving;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ChainLiftSubsystem extends PIDSubsystem implements PIDOutput, PIDSource{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor[] motors;
	Encoder[] encoders;
	PIDController pid;
	
	public static class PIDConstants {
		
		public static final double LIFT_P_VALUE = 0.0;
		public static final double LIFT_I_VALUE = 0.0;
		public static final double LIFT_D_VALUE = 0.0;
		public static final double ENCODER_DISTANCE_PER_PULSE = 0;
		public static final double ABS_TOLERANCE = 0;


	}

	public ChainLiftSubsystem() {
		super("Lift PID", PIDConstants.LIFT_P_VALUE, PIDConstants.LIFT_I_VALUE, PIDConstants.LIFT_D_VALUE);
	 	for (int i = 0; i < RobotMap.CHAIN_LIFT.MOTORS.length; i++) {
            motors[i] = new Victor(RobotMap.CHAIN_LIFT.MOTORS[i]);
        }
    	pid = CommandBase.chainLiftSubsystem.getPIDController();
    	pid.setAbsoluteTolerance(PIDConstants.ABS_TOLERANCE);
    	encoders = new Encoder[RobotMap.CHAIN_LIFT.ENCODERS.length / 2];
        for (int x = 0; x < RobotMap.CHAIN_LIFT.ENCODERS.length; x += 2) {
            encoders[x / 2] = new Encoder(RobotMap.CHAIN_LIFT.ENCODERS[x], RobotMap.CHAIN_LIFT.ENCODERS[x + 1], x == 0, EncodingType.k2X);
            encoders[x / 2].setDistancePerPulse(PIDConstants.ENCODER_DISTANCE_PER_PULSE);
        }
        
    }
		
		
		

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
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

