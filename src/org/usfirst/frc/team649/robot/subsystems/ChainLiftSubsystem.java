package org.usfirst.frc.team649.robot.subsystems;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ChainLiftSubsystem extends PIDSubsystem{
    
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor[] motors;
	public Encoder encoder;
	PIDController pid;

	DigitalInput limitMax;
	DigitalInput limitReset;
	
	public double setpointHeight;
	public double offsetHeight;
	
	//true for platform, false for step
	public boolean platformOrStepOffset;
	public boolean isAtBase;

	
	public static class PIDConstants {
		//PID
		public static final double P_VALUE = 0.5;
		public static final double I_VALUE = 0.0;
		public static final double D_VALUE = 0.0;
		public static final double ENCODER_DISTANCE_PER_PULSE = ((22*(3/8))/300);
		public static final double ABS_TOLERANCE = 1;
		//In inches
		public static final double STORE_TO_STEP_LEVEL_DIFFERENCE = 5.0;
		
		//MUST add up to 16 (hook separation)
		public static final double STORE_TO_INTERMEDIATE_DIFFERENCE = 12.0;
		public static final double INTERMEDIATE_TO_STORE_DIFFERENCE = 4;
		
		//MUST be distance between reset position and intermediate step
		public static final double FIRST_TOTE_STORE_TO_INTERMEDIATE = 7;
		
		//MUST add up to intermediate height difference
		public static final double CONTAINER_PICK_UP_RAISE_HEIGHT = 18;
		public static final double CONTAINER_REGRIP_LOWER_HEIGHT = -6;
		
		//TIMEOUTS
		public static final double HAL_COMPENSATION_TIME_OUT = 0.5; //in seconds
		public static final double RESET_TIME_OUT = 10;
		
		public static final double PLATFORM_DRIVE_OFFSET = 3;
		public static final double STEP_OFFSET = 8;
		public static final boolean UP = true;
		public static final boolean DOWN = false;
		public static final boolean PLATFORM_HEIGHT = true;
		public static final boolean STEP_HEIGHT = false;
		//Other
		public static final double UNLOAD_TOTES_MOTOR_POWER = -.5;
	    private static final double CURRENT_CAP = 10;


	}

	public ChainLiftSubsystem() {
		super("Lift PID", PIDConstants.P_VALUE, PIDConstants.I_VALUE, PIDConstants.D_VALUE);
		motors = new Victor[RobotMap.CHAIN_LIFT.MOTORS.length];
		for (int i = 0; i < RobotMap.CHAIN_LIFT.MOTORS.length; i++) {
            motors[i] = new Victor(RobotMap.CHAIN_LIFT.MOTORS[i]);
        }
    
    	platformOrStepOffset = true;
    	
    	//TODO: ALTER FOR DEFNED NUM OF ENCODERS
    	//encoders = new Encoder[RobotMap.CHAIN_LIFT.ENCODERS.length / 2];
        encoder = new Encoder(RobotMap.CHAIN_LIFT.ENCODERS[0], RobotMap.CHAIN_LIFT.ENCODERS[1]);
        encoder.setDistancePerPulse(PIDConstants.ENCODER_DISTANCE_PER_PULSE);
        
//        encoders[1] = new Encoder(RobotMap.CHAIN_LIFT.ENCODERS[2], RobotMap.CHAIN_LIFT.ENCODERS[3]);
//        encoders[1].setDistancePerPulse(PIDConstants.ENCODER_DISTANCE_PER_PULSE);
        
        /* issue see intake left subsystem
        pid = FishyRobot2015.chainLiftSubsystem.getPIDController();
    	pid.setAbsoluteTolerance(PIDConstants.ABS_TOLERANCE);
    	*/
        
        limitMax = new DigitalInput(RobotMap.CHAIN_LIFT.MAX_LIM_SWITCH);
        limitReset = new DigitalInput(RobotMap.CHAIN_LIFT.RESET_LIM_SWITCH);
        
        isAtBase = false; //TODO we hopefully call reset at the beginning of the program

        
		pid = this.getPIDController();
    	pid.setAbsoluteTolerance(PIDConstants.ABS_TOLERANCE);
	}
	
    public void setPower(double power) {
        motors[0].set(power);
        motors[1].set(-power);
    }
    
    //HalEffect Sensors
    public boolean isMaxLimitPressed() {
    	return true; //!limitMax.get();
    }
    
    public boolean isResetLimitPressed() {
    	return true; //!limitReset.get();
    }
    
    public double getHeight() {
//    	//returns the highest encoder value
//    	double encDist1 = encoders[0].getDistance(), encDist2 = encoders[1].getDistance();
//    	return encDist1>encDist2 ? encDist1: encDist2;
//    	int numEncoders = encoders.length;
//        double totalVal = 0;
//        for (int i = 0; i < numEncoders; i++) {
//            totalVal += encoders[i].getDistance();
//        }
        return encoder.getDistance();
    }
    
    public double getVelocity() {
//    	double enc1Speed = Math.abs(encoders[0].getRate());
//    	double enc2Speed = Math.abs(encoders[1].getRate());
//    	return enc1Speed > enc2Speed ? enc1Speed : enc2Speed;
    	return encoder.getRate();
    }

    public void resetEncoders() {
//        for (int x = 0; x < encoders.length; x++) {
//            encoders[x].reset();
//        }
    	encoder.reset();
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.getHeight();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		//if(FishyRobot2015.pdp.getCurrent(channel))
		double avgCurrent = ((FishyRobot2015.pdp.getCurrent(13) + FishyRobot2015.pdp.getCurrent(12)) / 2); 
    	if(avgCurrent > PIDConstants.CURRENT_CAP && Math.abs(this.getVelocity()) < 0.2 ) {
    		this.setPower(0);
    	} else{
    		this.setPower(output);
    	}
	}
}

