import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.lang.Thread;

@Autonomous
public class RedFar extends LinearOpMode {

    private DcMotor fL;
    private DcMotor fR;
    private DcMotor rL;
    private DcMotor rR;
    private DcMotor Carousel;

    @Override
    public void runOpMode() {
        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        rR = hardwareMap.get(DcMotor.class, "rearRight");
        rL = hardwareMap.get(DcMotor.class, "rearLeft");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");

        fR.setDirection(DcMotorSimple.Direction.REVERSE);
        rR.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(opModeIsActve()) {

        }
    }
}