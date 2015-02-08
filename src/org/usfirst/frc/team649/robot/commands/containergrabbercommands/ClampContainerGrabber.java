package org.usfirst.frc.team649.robot.commands.containergrabbercommands;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.subsystems.ContainerGrabberSubsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class ClampContainerGrabber extends Command {

	
	private Value referencePos;
	
	public ClampContainerGrabber(boolean clamp){
		referencePos = clamp ? ContainerGrabberSubsystem.GRABBER_CLOSED_STATE : ContainerGrabberSubsystem.GRABBER_OPENED_STATE;
		FishyRobot2015.containerGrabberSubsystem.setGrabberState(referencePos);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return FishyRobot2015.containerGrabberSubsystem.getGrabberState() == referencePos.value;
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
