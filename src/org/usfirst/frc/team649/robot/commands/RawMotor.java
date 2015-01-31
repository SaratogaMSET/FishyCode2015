package org.usfirst.frc.team649.robot.commands;

import org.usfirst.frc.team649.robot.FishyRobot2015;

import edu.wpi.first.wpilibj.command.Command;


//runs motor of a given group of motors, allows for commandGroup raw control in debugging
public class RawMotor extends Command {

	//for drivetrain
	public RawMotor(double left, double right) {
		org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.drivetrainSubsystem.rawDrive(left, right);
	}
	
	public RawMotor(double chainPower){
		org.usfirst.frc.team649.robot.FishyRobot2015.commandBase.chainLiftSubsystem.setPower(chainPower);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
}
