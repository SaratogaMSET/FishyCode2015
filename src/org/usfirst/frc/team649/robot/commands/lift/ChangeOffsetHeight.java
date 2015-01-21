package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.Robot;
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
    	liftPID =  Robot.commandBase.chainLiftSubsystem.getPIDController();
    	if(platformOrDriveHeight) {
    		 Robot.commandBase.chainLiftSubsystem.offsetHeight = ChainLiftSubsystem.PIDConstants.PLATFORM_DRIVE_OFFSET;
    	}
    	else
    		 Robot.commandBase.chainLiftSubsystem.offsetHeight = ChainLiftSubsystem.PIDConstants.STEP_OFFSET;
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
		return (( Robot.commandBase.chainLiftSubsystem.setpointHeight + Robot.commandBase.chainLiftSubsystem.offsetHeight) ==  Robot.commandBase.chainLiftSubsystem.getHeight() ||  Robot.commandBase.chainLiftSubsystem.isMaxLimitPressed() ||  Robot.commandBase.chainLiftSubsystem.isResetLimitPressed());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
