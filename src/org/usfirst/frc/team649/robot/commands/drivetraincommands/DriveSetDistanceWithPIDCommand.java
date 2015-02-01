package org.usfirst.frc.team649.robot.commands.drivetraincommands;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.EncoderBasedDriving;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 * DriveSetDistanceCommand
 *
 * Attempts to drive the robot using a trapezoidal motion profile. This means
 * that it accelerates at a constant rate, holds that speed for a set time, and
 * then decelerates at a constant rate. This should ideally provide a much more
 * consistent drive (in both time and distance) than a motion profile that
 * accelerates as fast as possible to start, and has trouble finishing (i.e. a
 * PID loop).
 *
 * @author alex@renda.org
 */
public class DriveSetDistanceWithPIDCommand extends Command {

    public static final int ON_TARGET_TIME = 250;
    private final double distance;
    private PIDController pid;
    private long onTargetStartTime;
    private boolean finishedChecker;
    private double minDriveSpeed;

    /**
     * Construct a DriveSetDistanceCommand. Immutable, but can safely be reused
     * for multiple executions of the same speed/distance.
     *
     * @param speed The speed to drive at. Always positive.
     * @param distance The distance in inches to drive. Negative to drive
     * backwards.
     */
    public DriveSetDistanceWithPIDCommand(double distance) {
        this.distance = distance;
        this.minDriveSpeed = 0.25;
    }

    public DriveSetDistanceWithPIDCommand(double distance, double minDriveSpeed) {
        this.distance = distance;
        this.minDriveSpeed = minDriveSpeed;
    }

    public DriveSetDistanceWithPIDCommand(double distance, double minDriveSpeed, boolean finishedChecker) {
        this.distance = distance;
        this.finishedChecker = finishedChecker;
        this.minDriveSpeed = minDriveSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        EncoderBasedDriving.MIN_MOTOR_POWER = minDriveSpeed;
        this.pid = FishyRobot2015.commandBase.drivetrainSubsystem.pid;
        pid.setPID(EncoderBasedDriving.AUTO_P, EncoderBasedDriving.AUTO_I, EncoderBasedDriving.AUTO_D);
        pid.setSetpoint(distance);
        FishyRobot2015.commandBase.drivetrainSubsystem.resetEncoders();
        pid.enable();
        onTargetStartTime = -1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        if (pid.onTarget()) {
//            Display.printToOutputStream("On Target");
//            if (onTargetStartTime == -1) {
//                Display.printToOutputStream("Set to curr time");
//                onTargetStartTime = System.currentTimeMillis();
//            } else if (System.currentTimeMillis() - onTargetStartTime > ON_TARGET_TIME) {
//                return true;
//            }
//        } else {
//            if (onTargetStartTime != -1) {
//                Display.printToOutputStream("Off target");
//            }
//            onTargetStartTime = -1;
//        }
//
//        return false;
        return Math.abs(FishyRobot2015.commandBase.drivetrainSubsystem.getDistance()) > Math.abs(distance);
    }

// Called once after isFinished returns true
    protected void end() {
        pid.disable();
        FishyRobot2015.commandBase.drivetrainSubsystem.driveFwdRot(0, 0);
        finishedChecker = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}