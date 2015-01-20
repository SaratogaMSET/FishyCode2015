package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeOffsetHeight extends Command {

	PIDController liftPID;
    public ChangeOffsetHeight(boolean platformOrDriveHeight) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	liftPID = CommandBase.chainLiftSubsystem.getPIDController();
    	if(platformOrDriveHeight) {
    		CommandBase.chainLiftSubsystem.offsetHeight = ChainLiftSubsystem.PIDConstants.PLATFORM_DRIVE_OFFSET;
    	}
    	else
    		CommandBase.chainLiftSubsystem.offsetHeight = ChainLiftSubsystem.PIDConstants.STEP_OFFSET;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	liftPID.enable();
    	liftPID.setSetpoint(CommandBase.chainLiftSubsystem.setpointHeight + CommandBase.chainLiftSubsystem.offsetHeight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return ((CommandBase.chainLiftSubsystem.setpointHeight + CommandBase.chainLiftSubsystem.offsetHeight) == CommandBase.chainLiftSubsystem.getHeight() || CommandBase.chainLiftSubsystem.isMaxLimitPressed() || CommandBase.chainLiftSubsystem.isResetLimitPressed());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
