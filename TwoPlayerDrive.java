import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public class TwoPlayerDrive extends LinearOpMode {

    private DcMotor fl;
    private DcMotor fr;
    private DcMotor rl;
    private DcMotor rr;
    private DcMotor Carousel;
    //private DcMotor intake;
    //private DcMotor arm;
    //private CRServo intArm;

    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        rl = hardwareMap.get(DcMotor.class, "rearLeft");
        rr = hardwareMap.get(DcMotor.class, "rearRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        //intake = hardwareMap.get(DcMotor.class, "intake");
        //arm = hardwareMap.get(DcMotor.class, "arm");
        //intArm = hardwareMap.get(Servo.class, "intArm");
        //intArm.setScaleRange(0, 1);

        waitForStart();
        while(opModeIsActive()) {
            //Left Stick: General driving, includes straffing
            frontLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(gamepad1.left_stick_y);
            rearRight.setPower(-gamepad1.left_stick_y);
            rearLeft.setPower(-gamepad1.left_stick_y);

            frontLeft.setPower(-gamepad1.left_stick_x);
            frontRight.setPower(gamepad1.left_stick_x);
            rearRight.setPower(-gamepad1.left_stick_x);
            rearLeft.setPower(gamepad1.left_stick_x);

            //Right Stick: Robot rotation
            frontLeft.setPower(-gamepad1.right_stick_x);
            frontRight.setPower(gamepad1.right_stick_x);
            rearRight.setPower(gamepad1.right_stick_x);
            rearLeft.setPower(-gamepad1.right_stick_x);


            if (gamepad2.left_trigger > 0) {
                Carousel.setPower(0.5);
            } else if (gamepad2.right_trigger > 0) {
                Carousel.setPower(-0.5);
            } else {
                Carousel.setPower(0);
            }

            /*


            //intake activation
            if (gamepad1.a) {
                intake.setPower(0.6);
            } else if (gamepad1.b) {
                intake.setPower(-0.6);
            } else {
                intake.setPower(0);
            }


            //intake rotation
            if (gamepad1.left_bumper) {

            }
             */
        }
    }
}