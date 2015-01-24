package org.usfirst.frc.team649.robot.commands.grabbercommands;

import org.usfirst.frc.team649.robot.FishyRobot2015;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeTote extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		FishyRobot2015.commandBase.grabberLeftSubsystem.roller.set(1.0);
		FishyRobot2015.commandBase.grabberRightSubsystem.roller.set(1.0);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		//so the tote will only stop moving when both triggers have been hit
		if (FishyRobot2015.commandBase.grabberLeftSubsystem.touchSensor.get()){
			FishyRobot2015.commandBase.grabberLeftSubsystem.roller.set(0);
		}
		if (FishyRobot2015.commandBase.grabberRightSubsystem.touchSensor.get()){
			FishyRobot2015.commandBase.grabberRightSubsystem.roller.set(0);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return FishyRobot2015.commandBase.grabberLeftSubsystem.touchSensor.get() && FishyRobot2015.commandBase.grabberRightSubsystem.touchSensor.get();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		FishyRobot2015.commandBase.grabberLeftSubsystem.roller.set(0);
		FishyRobot2015.commandBase.grabberRightSubsystem.roller.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
