package org.usfirst.frc.team649.robot.subsystems;

import java.util.Vector;

import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivetrainSubsystem extends PIDSubsystem {
    
    private SpeedController[] motors;
    public Encoder[] encoders;
    public PIDController pid;
	public  double currentInput;
	public  double oldInput;
	private Vector lastRates;
	
    public static class EncoderBasedDriving {
    	private static final double ENCODER_DISTANCE_PER_PULSE = -4 * Math.PI / 128;
        public static final double MAX_MOTOR_POWER = 0.5;
        public static double MIN_MOTOR_POWER = 0.25;
        
        public static final double AUTO_WINCH_DRIVE_DISTANCE = -14 * 12;
    	public static final double UNHOOK_BACKWARDS_DISTANCE = -100;
    	public static final double AUTO_START_TO_CONTAINER = 100;
        public static final double AUTO_CONTAINER_TO_AUTO_ZONE = 222;
        public static final double AUTO_CONTAINER_TO_TOTE = 649;
        
    	public static final double AUTO_P = 0.4;
    	public static final double AUTO_I = 0.0;
    	public static final double AUTO_D = 0.0;
    	public static final double ABS_TOLERANCE = 0.0;
    	public static final double OUTPUT_RANGE = 0.0;
    }
    
    public static class RampingConstants {
    	public static final double ACCELERATION_LIMIT = .34;
    	public static final double DECELERATION_LIMIT = .34;
    	public static final double RAMP_UP_CONSTANT = .20;
    	public static final double RAMP_DOWN_CONSTANT = 1;
    	private static boolean rampMotors = false;

    }
    
    public DrivetrainSubsystem() {
    	super("Drivetrain", EncoderBasedDriving.AUTO_P, EncoderBasedDriving.AUTO_I, EncoderBasedDriving.AUTO_D);
    	motors = new SpeedController[RobotMap.DRIVE_TRAIN.MOTORS.length];
    	for (int i = 0; i < RobotMap.DRIVE_TRAIN.MOTORS.length; i++) {
            motors[i] = new Victor(RobotMap.DRIVE_TRAIN.MOTORS[i]);
        }
    	pid = this.getPIDController();
    	pid.setAbsoluteTolerance(EncoderBasedDriving.ABS_TOLERANCE);
    	pid.setOutputRange(EncoderBasedDriving.MIN_MOTOR_POWER, EncoderBasedDriving.MAX_MOTOR_POWER);
    	encoders = new Encoder[RobotMap.DRIVE_TRAIN.ENCODERS.length / 2];
        for (int x = 0; x < RobotMap.DRIVE_TRAIN.ENCODERS.length; x += 2) {
            encoders[x / 2] = new Encoder(RobotMap.DRIVE_TRAIN.ENCODERS[x], RobotMap.DRIVE_TRAIN.ENCODERS[x + 1], x == 0, EncodingType.k2X);
            encoders[x / 2].setDistancePerPulse(EncoderBasedDriving.ENCODER_DISTANCE_PER_PULSE);
        }
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    

    public boolean isMotorRamping() {
    	return RampingConstants.rampMotors;
    }
    
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
    
    public double getVelocity() {
        int numEncoders = encoders.length;
        double totalRate = 0;
        for (int i = 0; i < numEncoders; i++) {
            totalRate += encoders[i].getRate();
        }
        final double rate = totalRate / numEncoders;
        return rate;
    }
    
    public double getDistance() {
        int numEncoders = encoders.length;
        double totalVal = 0;
        for (int i = 0; i < numEncoders; i++) {
            totalVal += encoders[i].getDistance();
        }
        return totalVal / numEncoders;
    }

    public void resetEncoders() {
        for (int x = 0; x < encoders.length; x++) {
            encoders[x].reset();
        }
    }
    
	protected double returnPIDInput() {
		return this.getDistance();
	}

	protected void usePIDOutput(double output) {
        output = (output < 0 ? -1 : 1) * Math.max(Math.abs(output), EncoderBasedDriving.MIN_MOTOR_POWER);
        driveFwdRot(output, 0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

