//SERIOUSLY UNECCESSARY (well not really...useful for command groups) BUT I MADE IT ANYWAYS

package org.usfirst.frc.team649.robot.commands.grabbercommands;

import org.usfirst.frc.team649.robot.FishyRobot2015;

import edu.wpi.first.wpilibj.command.Command;

public class RunRoller extends Command {

	//allows for a command to stop
	public RunRoller(double speed){
		FishyRobot2015.commandBase.grabberLeftSubsystem.roller.set(speed);
		FishyRobot2015.commandBase.grabberRightSubsystem.roller.set(speed);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

}
