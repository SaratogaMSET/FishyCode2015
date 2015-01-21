package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.Robot;
import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeLevelOfTotes extends Command {

	private PIDController liftPID;

	public ChangeLevelOfTotes(boolean up) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		liftPID =  Robot.commandBase.chainLiftSubsystem.getPIDController();
		if (up) {
			 Robot.commandBase.chainLiftSubsystem.setpointHeight += ChainLiftSubsystem.PIDConstants.STORE_TO_NEXT_LEVEL_DIFFRERANCE;
		} else {
			 Robot.commandBase.chainLiftSubsystem.setpointHeight += ChainLiftSubsystem.PIDConstants.STORE_TO_NEXT_LEVEL_DIFFRERANCE;
		}
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		liftPID.enable();
		liftPID.setSetpoint( Robot.commandBase.chainLiftSubsystem.setpointHeight +  Robot.commandBase.chainLiftSubsystem.offsetHeight);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (( Robot.commandBase.chainLiftSubsystem.setpointHeight +  Robot.commandBase.chainLiftSubsystem.offsetHeight) ==  Robot.commandBase.chainLiftSubsystem.getHeight() ||  Robot.commandBase.chainLiftSubsystem.isMaxLimitPressed() ||  Robot.commandBase.chainLiftSubsystem.isResetLimitPressed());
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
