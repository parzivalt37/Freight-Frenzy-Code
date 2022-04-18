import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.Thread;

@Autonomous
public class AutonTest extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearLeft;
    private DcMotor rearRight;
    private DcMotor Carousel;

  private int frPos;
  private int rrPos;
  private int flPos;
  private int rlPos;

  @Override

  public void runOpMode(){
    frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    frontRight = hardwareMap.get(DcMotor.class, "frontRight");
    rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
    rearRight = hardwareMap.get(DcMotor.class, "rearRight");
    Carousel = hardwareMap.get(DcMotor.class, "Carousel");

    frontRight(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    frontLeft(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rearRight(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    rearLeft(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    frPos = 0;
    flPos = 0;
    rrPos = 0;
    rlPos = 0;



    waitForStart();

    drive(2, 2, 2, 2, 2.5); 
    drive(2, -2, -2, 2, 2.5);
    
  }


private void drive(int frTarget, int flTarget, int rrTarget, int rlTarget, double speed) {

frPos += frTarget;
flPos += flTarget;
rrPos += rrTarget;
rlPos += rlTarget;

frontRight.setTargetPosition(frPos);
frontLeft.setTargetPosition(flPos);
rearRight.setTargetPosition(rrPos);
rearLeft.setTargetPosition(rlPos);

frontRight.setPower(speed);
frontLeft.setPower(speed);
rearRight.setPower(speed);
rearLeft.setPower(speed);

while(opModeIsActive() && frontRight.isBusy() && frontLeft.isBusy() && rearRight.isBusy() && rearLeft.isBusy())
  {
    idle();
  }

}
}

