# First Tech Challenge code for team 20313

There are several classes, each with their own purposes:

**Autonomous classes**: Designated by the color of the alliance and its position in relation to the carousel, each autonomous program is intended to deliver the duck from the carousel, park in the warehouse, and park in the alliance storage unit

**ArmTesting**: Designed for testing out the main arm on the robot

**FourWheelAutonomous**: An autonomous class designed to calibrate the robot from a specific position, used to test code before it is placed in its respective autonomous class

**FourWheelDrive**: The main driving TeleOp: this class contains code for basic robot movement, moving the carousel spinner, lifting and lowering the arm on the robot, and opening and closing the servo claw

**Methods**: A work in progress, this class was designed for abstraction of use of the Thread.sleep() method (use of this method requires a try/catch statement, and having all of those try/catches listed out throughout the autonomous classes makes it very difficult to sift through all the code for troubleshooting)

**RobotHardware**: This class creates a HardwareMap object that fetches the positions of which ports each device is plugged into on the Control and Expansion Hubs, and links that to the Motor/Servo objects that are declared in any code that will move the robot

**ServoTesting**: A class designed for testing the servos on the end of the arm that create the claw

**StrafeTest**: A short class used to test the strafing capabilities of the Mecanum wheels

**TwoWheelDrive**: A deprecated TeleOp that acts as the equivalent of FourWheelDrive, but for two wheels
