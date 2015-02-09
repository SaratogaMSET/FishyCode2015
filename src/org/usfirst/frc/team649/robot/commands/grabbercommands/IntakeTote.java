package org.usfirst.frc.team649.robot.commands.grabbercommands;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.subsystems.IntakeLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeRightSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeTote extends Command {

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		FishyRobot2015.intakeLeftSubsystem.roller.set(IntakeLeftSubsystem.INTAKE_ROLLER_SPEED);
		FishyRobot2015.intakeRightSubsystem.roller.set(IntakeRightSubsystem.INTAKE_ROLLER_SPEED);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
		//so the tote will only stop moving when both triggers have been hit
		if (FishyRobot2015.intakeLeftSubsystem.touchSensor.get()){
			FishyRobot2015.intakeLeftSubsystem.roller.set(0);
		}
		if (FishyRobot2015.intakeRightSubsystem.touchSensor.get()){
			FishyRobot2015.intakeRightSubsystem.roller.set(0);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return FishyRobot2015.intakeLeftSubsystem.touchSensor.get() && FishyRobot2015.intakeRightSubsystem.touchSensor.get();
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		FishyRobot2015.intakeLeftSubsystem.roller.set(IntakeLeftSubsystem.INTAKE_ROLLER_OFF_SPEED);
		FishyRobot2015.intakeRightSubsystem.roller.set(IntakeLeftSubsystem.INTAKE_ROLLER_OFF_SPEED);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
