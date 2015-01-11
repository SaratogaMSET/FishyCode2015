package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends Subsystem implements PIDOutput, PIDSource{
    
    private SpeedController[] motors;
    private Encoder[] encoders;
    private PIDController pid;
    
    public static class PIDConstants {
    	private static final double ENCODER_DISTANCE_PER_PULSE = -4 * Math.PI / 128;
        public static final double MAX_MOTOR_POWER = 0.5;
        public static final double MIN_MOTOR_POWER = 0.25;
        public static final double AUTONOMOUS_DRIVE_DISTANCE = -14 * 12;
    	public static final double AUTO_P = 0.4;
    	public static final double AUTO_I = 0.0;
    	public static final double AUTO_D = 0.0;
    	public static final double ABS_TOLERANCE = 0.0;
    	public static final double OUTPUT_RANGE = 0.0;
    }
    
    public static class RampingConstants {
    	public static double output;
    	public static double currentInput;
    	public static double oldInput;
    	public static final double DELTA_LIMIT = .34;
    	public static final int RAMPING_UP = 0;
    	public static final int RAMPING_DOWN = 1;
    }
    
    public DrivetrainSubsystem() {
    	motors = new SpeedController[RobotMap.DRIVE_TRAIN.Motors.length];
    	for (int i = 0; i < RobotMap.DRIVE_TRAIN.Motors.length; i++) {
            motors[i] = new Victor(RobotMap.DRIVE_TRAIN.Motors[i]);
        }
    	pid.setAbsoluteTolerance(PIDConstants.ABS_TOLERANCE);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void driveFwdRot(double fwd, double rot) {
        double left = fwd + rot, right = fwd - rot;
        double max = Math.max(1, Math.max(Math.abs(left), Math.abs(right)));
        left /= max;
        right /= max;
        rawDrive(left, right);
    }
    

    public void rawDrive(double left, double right) {
        int i = 0;
        for (; i < motors.length / 2; i++) {
            motors[i].set(left);
        }

        for (; i < motors.length; i++) {
            motors[i].set(-right);
        }
    }
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

