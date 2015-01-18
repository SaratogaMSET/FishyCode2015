package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerTotes extends Command {

	private PIDController liftPID;
    public LowerTotes() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	liftPID = CommandBase.chainLiftSubsystem.getPIDController();
    	CommandBase.chainLiftSubsystem.currentHeight -= ChainLiftSubsystem.PIDConstants.STORE_TO_NEXT_LEVEL_DIFFRERANCE;
  
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      	liftPID.enable();
    	liftPID.setSetpoint(CommandBase.chainLiftSubsystem.currentHeight);
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
