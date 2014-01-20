/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/



//___
// | |_  o  _     o  _    _|_|_  _    __  _  o __     _  |  _  _  _
// | | | | _>     | _>     |_| |(/_   |||(_| | | |   (_  | (_|_> _>
//The main class is under control of Ali Nazzal & Ben Rose. DO NOT EDIT WITHOUT EXPLICIT PERMISSION!

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;

/**
 * This class connects the data from all the other classes and defines the overall flow of the robot program.
 * 
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author AliNazzal, BenRose
 */
public class RobotMain extends IterativeRobot {
    
    /** Drive Settings */
    RobotDrive drive;
    /** Left Joystick Parameters */
    Joystick leftstick;
    /** Right Joystick Parameters */
    Joystick rightstick;
    MotorControl MC;
    Simulator Sim;
    RobotMap RM;
    InputManager IM;
    ImageProcessing IP;
    Communication Com;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        drive = new RobotDrive(1, 2);
        leftstick = new Joystick(1);
        rightstick = new Joystick(2);
        MC = new MotorControl();
        Sim = new Simulator();
        RM = new RobotMap();
        IM = new InputManager();
        IP = new ImageProcessing();
        Com = new Communication();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        while(true && isOperatorControl() && isEnabled()) {
            drive.tankDrive(leftstick, rightstick);
            Timer.delay(.05);
        }
        while (true && isOperatorControl() && isEnabled()) {
            //Undefined names are placeholders
            double driveX = IM.getAxis(IM.LEFT_X);
            double driveY = IM.getAxis(IM.LEFT_Y);
            double aimX = IM.getAxis(IM.RIGHT_X);
            double aimY = IM.getAxis(IM.LEFT_Y);
            MC.drive(driveX,driveY);
            if (IM.getButton(IM.SHOOT)) {
                MC.shoot(1.0); //Placeholder value
            }
            if (IM.getButton(IM.PASS)) {
                MC.shoot(0.75); //Another placeholder
            }
        }
    }
}
