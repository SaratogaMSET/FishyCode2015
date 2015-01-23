package org.usfirst.frc.team649.robot.commands.drivetraincommands;

import org.usfirst.frc.team649.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RampMotors extends Command {

	double inputVal; 
	double outputVal;
    public RampMotors() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	inputVal = org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.currentInput;
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
}
