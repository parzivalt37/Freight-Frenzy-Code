package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class DemoAuton extends LinearOpMode {
    
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor rl;
    private DcMotor rr;
    private DcMotor Carousel;
    
    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        rl = hardwareMap.get(DcMotor.class, "rearLeft");
        rr = hardwareMap.get(DcMotor.class, "rearRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        
        fr.setDirection(DcMotor.Direction.REVERSE);
        rr.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        if (opModeIsActive()) {
            
            //forward
            fl.setPower(0.5);
            fr.setPower(0.5);
            rl.setPower(0.5);
            rr.setPower(0.5);
            
            sleep(1500);
            
            //backward
            fl.setPower(-0.5);
            fr.setPower(-0.5);
            rl.setPower(-0.5);
            rr.setPower(-0.5);
            
            sleep(3000);
            
            //back to center
            fl.setPower(0.5);
            fr.setPower(0.5);
            rl.setPower(0.5);
            rr.setPower(0.5);
            
            sleep(1500);
            
            //left
            fl.setPower(0.5);
            rr.setPower(0.5);
            fr.setPower(-0.5);
            rl.setPower(-0.5);
            
            sleep(1500);
            
            //right
            fl.setPower(-0.5);
            rr.setPower(-0.5);
            fr.setPower(0.5);
            rl.setPower(0.5);
            
            sleep(3000);
            
            //left to center
            fl.setPower(0.5);
            rr.setPower(0.5);
            fr.setPower(-0.5);
            rl.setPower(-0.5);
            
            sleep(1500);
            
            fl.setPower(0.75);
            rl.setPower(0.75);
            fr.setPower(-0.75);
            rr.setPower(-0.75);
            Carousel.setPower(0.75);
            
            sleep(4000);
            
            fl.setPower(-0.75);
            rl.setPower(-0.75);
            fr.setPower(0.75);
            rr.setPower(0.75);
            Carousel.setPower(-0.5);
            
            sleep(4000);
            
            fl.setPower(0);
            fr.setPower(0);
            rl.setPower(0);
            rr.setPower(0);
            Carousel.setPower(0);
        }
    }
    
}