package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTesting extends LinearOpMode{

    private Servo lClaw;
    private Servo rClaw;
    
    @Override
    public void runOpMode() {
        lClaw = hardwareMap.get(Servo.class, "lClaw");
        rClaw = hardwareMap.get(Servo.class, "rClaw");
        
        rClaw.setDirection(Servo.Direction.REVERSE);
        lClaw.scaleRange(0, 1);
        rClaw.scaleRange(0, 1);
        
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.left_bumper) {
                rClaw.setPosition(0);
                lClaw.setPosition(0);
            }
            
            if (gamepad1.right_bumper) {
                rClaw.setPosition(1);
                lClaw.setPosition(1);
            }
        }
    }
}