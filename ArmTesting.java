package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.Thread;

@TeleOp
public class ArmTesting extends LinearOpMode {

    private DcMotor arm;
    
    @Override
    public void runOpMode() {
       arm = hardwareMap.get(DcMotor.class, "arm");
       arm.setMode(DcMotor.runMode.STOP_AND_RESET_ENCODER);

       waitForStart();
       arm.setMode(DcMotor.runMode.RUN_TO_POSITION);

       while(opModeIsActive()) {
           if (gamepad1.x) {
               arm.setTargetPosition(100);
               arm.setVelocity(100);
               telemetry.addData("Arm target position: ", arm.getTargetPosition());
               telemetry.addData("Arm actual position: ", arm.getPosition());
           }
           else if (gamepad1.y) {
               arm.setTargetPosition(0);
               arm.setVelocity(-100);
               telemetry.addData("Arm target position: ", arm.getTargetPosition());
               telemetry.addData("Arm actual position: ", arm.getPosition());
           } else {
               arm.setVelocity(0);
               telemetry.addData("Arm target position: ", arm.getTargetPosition());
               telemetry.addData("Arm actual position: ", arm.getPosition());
           }

           telemetry.update();
       }
    }
}