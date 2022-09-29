package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp

public class ArmTest extends LinearOpMode {

    private DcMotor arm;
    
    @Override
    public void runOpMode() {
        arm = hardwareMap.get(DcMotor.class, "arm");
        
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.a) {
               arm.setPower(0.3); 
            } else if (gamepad1.b) {
               arm.setPower(0.3);
            } else {
                arm.setPower(0);
            }
        }
    }
}