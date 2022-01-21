package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;



@TeleOp
public class TwoWheelDrive extends LinearOpMode {

    private DcMotor MRight;
    private DcMotor MLeft;
    //private DcMotor Carousel;
    //private Servo claw;
    
    @Override
    public void runOpMode() {
        MRight = hardwareMap.get(DcMotor.class, "frontRight");
        MLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        //Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        //claw = hardwareMap.get(Servo.class, "claw");
        
        MRight.setDirection(DcMotorSimple.Direction.REVERSE);
        //claw.setScaleRange(0, 0.5);
        waitForStart();
        while(opModeIsActive()) {
            MRight.setPower(gamepad1.right_stick_y);
            telemetry.addData("MRight power: ", MRight.getPower());
            MLeft.setPower(gamepad1.left_stick_y);
            telemetry.addData("Mleft power: ", MLeft.getPower());
            
            /*if (gamepad1.a) {
                Carousel.setPower(0.3);
            } 
            else if (gamepad1.b) {
                Carousel.setPower(-0.3);
            }
            else {
                Carousel.setPower(0);
            }*/
            
            /*
            if (gamepad1.right_bumper) {
                if (claw.getPosition() == 0.5) {
                    claw.setPosition(0);
                } else if (claw.getPosition() == 0) {
                    claw.setPosition(0.5);
                }
            }
            telemetry.addData("Servo position: ", claw.getPosition());
            */
            
            telemetry.update();
        }
    }
}
