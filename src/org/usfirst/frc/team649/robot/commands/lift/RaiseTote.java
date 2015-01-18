package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.commands.CommandBase;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem.PIDConstants;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseTote extends Command {

	private int liftState;
	private PIDController liftPID;
    public RaiseTote(int state) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	liftState = state;
    	liftPID = CommandBase.chainLiftSubsystem.getPIDController();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	liftPID.enable();
    	if(liftState == ChainLiftSubsystem.PIDConstants.SCORE_STATE){
    		liftPID.setSetpoint(CommandBase.chainLiftSubsystem.getHeight() + ChainLiftSubsystem.PIDConstants.SCORE_LEVEL_DIFFERANCE);
    	}
    		
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
