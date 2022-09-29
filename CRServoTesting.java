package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class CRServoTesting extends LinearOpMode {

    private CRServo intArm;
    
    @Override
    public void runOpMode() {
        intArm = hardwareMap.get(CRServo.class, "intArm");
        
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.left_bumper) {
                intArm.setPower(0.5);
            } else if (gamepad1.right_bumper) {
                intArm.setPower(-0.5);
            } else {
                intArm.setPower(0);
            }
        }
    }
}