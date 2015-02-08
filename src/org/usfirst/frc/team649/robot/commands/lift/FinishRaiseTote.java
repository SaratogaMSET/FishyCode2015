package org.usfirst.frc.team649.robot.commands.lift;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FinishRaiseTote extends Command {

	private PIDController liftPID;

    public FinishRaiseTote(boolean up) {
    	liftPID =  FishyRobot2015.chainLiftSubsystem.getPIDController();
		if (up) {
			 FishyRobot2015.chainLiftSubsystem.setpointHeight += ChainLiftSubsystem.PIDConstants.INTERMEDIATE_TO_STORE_DIFFERENCE;
		} else {
			 FishyRobot2015.chainLiftSubsystem.setpointHeight -= ChainLiftSubsystem.PIDConstants.INTERMEDIATE_TO_STORE_DIFFERENCE;
		}
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	liftPID.enable();
		liftPID.setSetpoint(FishyRobot2015.chainLiftSubsystem.setpointHeight +  FishyRobot2015.chainLiftSubsystem.offsetHeight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return true; // ((FishyRobot2015.commandBase.chainLiftSubsystem.setpointHeight +  FishyRobot2015.commandBase.chainLiftSubsystem.offsetHeight) ==  FishyRobot2015.commandBase.chainLiftSubsystem.getHeight() ||  FishyRobot2015.commandBase.chainLiftSubsystem.isMaxLimitPressed() ||  FishyRobot2015.commandBase.chainLiftSubsystem.isResetLimitPressed());
    }

    // Called once after isFinished returns true
    protected void end() {
    	FishyRobot2015.chainLiftSubsystem.isAtBase = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
