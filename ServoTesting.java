package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTesting extends LinearOpMode{

    private Servo intArm;
    
    @Override
    public void runOpMode() {
        intArm = hardwareMap.get(Servo.class, "intArm");
        
        intArm.scaleRange(0, 1);
        
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.left_bumper) {
                intArm.setPosition(0);
            }
            
            if (gamepad1.right_bumper) {
                intArm.setPosition(1);
            }
        }
    }
}