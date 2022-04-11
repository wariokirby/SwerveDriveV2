// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDrivetrain extends SubsystemBase {
  //locations assuming 32 x 28 robot or .831 x .711 m
  //max theoretical speed 12 ft/sec or 3.658 m/s
  //max rotational speed: circumference 100.53in or 2.553m, .69s for 2pi radians or 9.1 radians/sec
  private final double MAX_SPEED = 3.7;//m/s
  private final double MAX_ROTATIONAL_SPEED = 9.1;// radians/s
  Translation2d frontLeftLocation;
  Translation2d frontRightLocation;
  Translation2d backLeftLocation;
  Translation2d backRightLocation;

  SwerveDriveKinematics kinematics = new SwerveDriveKinematics(frontLeftLocation , frontRightLocation , backLeftLocation , backRightLocation);
  /** Creates a new SwerveDrivetrain. */
  public SwerveDrivetrain() {
    frontLeftLocation = new Translation2d(.4 , .35);
    frontRightLocation = new Translation2d(.4 , -.35);
    backLeftLocation = new Translation2d(-.4 , .35);
    backRightLocation = new Translation2d(-.4 , -.35);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void drive(double move , double strafe , double turn){
    move *= MAX_SPEED;
    strafe *= -MAX_SPEED;
    turn *= -MAX_ROTATIONAL_SPEED;
    ChassisSpeeds speeds = new ChassisSpeeds(move , strafe , turn);
    SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);
  
  }
}
