package org.usfirst.frc.team649.robot.commands.grabbercommands;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.RobotMap;
import org.usfirst.frc.team649.robot.subsystems.AutoWinchSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeRightSubsystem;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

public class GrabberArmPosition extends Command {
	double relevantSetpointRight;
	double relevantSetpointLeft;
	PIDController pidLeft, pidRight;
	
	public GrabberArmPosition(int st){
		//0 is grabbing, 1 is releasing, 2 is storage
		 if (st == 0){
			 relevantSetpointRight = IntakeRightSubsystem.PIDConstants.ARM_POS_GRABBING;
			 relevantSetpointLeft = IntakeLeftSubsystem.PIDConstants.ARM_POS_GRABBING;
		 }
		 else if (st == 1){
			 relevantSetpointRight = IntakeRightSubsystem.PIDConstants.ARM_POS_RELEASE;
			 relevantSetpointLeft = IntakeLeftSubsystem.PIDConstants.ARM_POS_RELEASE;
		 }
		 else{
			 relevantSetpointRight = IntakeRightSubsystem.PIDConstants.ARM_POS_STORING;
			 relevantSetpointLeft = IntakeLeftSubsystem.PIDConstants.ARM_POS_STORING;
		 }
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		pidRight = FishyRobot2015.commandBase.grabberRightSubsystem.getPIDController();
		pidLeft = FishyRobot2015.commandBase.grabberLeftSubsystem.getPIDController();
	}

	@Override
	protected void execute() {
		//for different positions for each arm, this command will need 2 state inputs
		pidRight.setSetpoint(relevantSetpointRight);
		pidLeft.setSetpoint(relevantSetpointLeft);
		
	}

	@Override
	protected boolean isFinished() {
		//when both have reached
		return FishyRobot2015.commandBase.grabberLeftSubsystem.getPot() == relevantSetpointLeft && FishyRobot2015.commandBase.grabberRightSubsystem.getPot() == relevantSetpointRight;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
