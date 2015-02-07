package org.usfirst.frc.team649.robot.commands.drivetraincommands;

import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardRotate extends Command {

	private double forwardVal;
	private double rotateVal;
    public DriveForwardRotate(double driveForward, double driveRotate) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	forwardVal = driveForward;
    	rotateVal = driveRotate;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	if(org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.isMotorRamping()) {
//    		//linearRamping(forwardVal);
//    	}
    	org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.driveFwdRot(forwardVal, rotateVal);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
//    protected void linearRamping(double toRamp) {
//    	if(org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.currentInput - org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.oldInput > DrivetrainSubsystem.RampingConstants.ACCELERATION_LIMIT) {
//    		forwardVal = org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.getVelocity() + DrivetrainSubsystem.RampingConstants.RAMP_UP_CONSTANT;
//    	}
//    	else if(org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.currentInput - org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.oldInput > DrivetrainSubsystem.RampingConstants.DECELERATION_LIMIT) {
//    		forwardVal = org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.getVelocity() + DrivetrainSubsystem.RampingConstants.RAMP_DOWN_CONSTANT;
//    	}
//    	else
//    		forwardVal = forwardVal;
//    }
}
