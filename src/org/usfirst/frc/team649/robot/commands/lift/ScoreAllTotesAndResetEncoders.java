package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ScoreAllTotesAndResetEncoders extends Command {

    public ScoreAllTotesAndResetEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	CommandBase.chainLiftSubsystem.setPower(ChainLiftSubsystem.PIDConstants.UNLOAD_TOTES_MOTOR_POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return CommandBase.chainLiftSubsystem.isResetLimitPressed();
    }

    // Called once after isFinished returns true
    protected void end() {
    	CommandBase.chainLiftSubsystem.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
