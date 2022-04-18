import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.lang.Thread;

@Autonomous
public class FourWheelAutonomous extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearLeft;
    private DcMotor rearRight;
    private DcMotor Carousel;
    
    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.REVERSE);
        
        waitForStart();
        if (opModeIsActive()) {
            
            
            frontLeft.setPower(0.5);
            frontRight.setPower(0.5);
            rearLeft.setPower(0.5);
            rearRight.setPower(0.5);
            
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            
            frontLeft.setPower(0);
            frontRight.setPower(0);
            rearLeft.setPower(0);
            rearRight.setPower(0);
            
            frontLeft.setPower(1);
            frontRight.setPower(-1);
            rearRight.setPower(1);
            rearLeft.setPower(-1);
            
            try {
                Thread.sleep(800);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            
            
            
            frontLeft.setPower(0);
            frontRight.setPower(0);
            rearLeft.setPower(0);
            rearRight.setPower(0);
            
            
            /*Carousel.setPower(0.5);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            
            Carousel.setPower(0.7);
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }*/
        }
    }



    
}