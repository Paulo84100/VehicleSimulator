import vehicle.Vehicle;
import java.util.Scanner;


/** A program to see the evolution of vehicle depending on its gear and its engine speed, working in pair with the Vehicle.java class
 * @author Paul JEANGERARD <paul.jeangerard.pro@gmail.com>
 * @version 1.04
 * @since 1.01
 */


public class Main {

    /**
     * An array of information of int type about the vehicle will be stored here as a constant.
     */
    static final int[] info = new int[4];
    /**
     * The number of gears of the vehicle's gearbox will be stored here as a constant.
     */
    static int gearsNum;
    /**
     * The array of type double of the different gears ratios will be stored here.
     */
    static double[] gearsRatios = {3.363, 1.947, 1.300, 1.029, 0.837, 0.680};
    /**
     * The bridge of the vehicle will be stored here.
     */
    static double bridge;
    /**
     * The diameter of the wheels will be stored here.
     */
    static double wheelDiameter;


    /**
     * The main method of this program
     * <p>
     * Requires the user to enter the different parameters of the vehicle in the init function.
     * Then creates a new instance of the Vehicle class with the parameters entered by the user.
     * Then displays the state of the vehicle.
     *
     * @param args The arguments of the main method (None are utilized here).
     */
    public static void main(String[] args) {

        init();
        Vehicle myVehicle = new Vehicle(info[0], info[1], gearsNum, gearsRatios, bridge, wheelDiameter);
        mainLoop(myVehicle);
    }


    /**
     * The main loop of this program
     * <p>
     * requires the user to enter the action he wants to do with the vehicle.
     * a : accelerate
     * d : decelerate
     * c : change gear
     * n : next gear
     * p : previous gear
     * s : display the state of the vehicle
     * e : exit the program
     *
     * @param vehicle The vehicle to be used in this program.
     */
    static void mainLoop(Vehicle vehicle) {

        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!input.equals("e")) {
            System.out.println("Enter a command followed by a number (of engine turns for accelerating or decelerating or the selected gear to change to a specific gear) if needed separated by a space :\n[a]ccelerate, [d]ecelerate, [c]hange gear, [n]ext gear shift, [p]revious gear shift, [s]tate of the vehicle, [e]xit");
            input = sc.nextLine().toLowerCase();
            String[] inputArray = input.split(" ");
            switch (inputArray[0]) {
                case "a" -> vehicle.accelerate(Integer.parseInt(inputArray[1]));
                case "d" -> vehicle.decelerate(Integer.parseInt(inputArray[1]));
                case "c" -> vehicle.setCurrentGear(Integer.parseInt(inputArray[1]));
                case "n" -> vehicle.setCurrentGear(vehicle.getCurrentGear() + 1);
                case "p" -> vehicle.setCurrentGear(vehicle.getCurrentGear() - 1);
                case "s" -> System.out.println(vehicle);
                case "e" -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid command");
            }
        }

    }


    /**
     * A method to initialize the different parameters of the vehicle
     * <p>
     * Requires the user to enter the different parameters of the vehicle.
     * Then stores them in the info array.
     * If the user wants a specific vehicle, stores the number of gears of the vehicle's gearbox in the gearsNum variable and gear ratios.
     * if the user wants to even further, stores the number of teeth of each gear and translates and stores it as gear ratios.
     *
     */
    public static void init() {
        /*
        Create a new Scanner object to read user input including engine speed and gear ratios if wanted.
         */

        int[] gearsTeeth;

        String[] vars = {"minimum engine speed", "maximum engine speed", "0 if you want a generic car or 1 to customize your own", "0 if you have specifics gears ratios for each gear or 1 if you have only the number of gear teeth for each gear"};
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < info.length; i++) {
            System.out.print("Enter " + vars[i] + ": ");
            info[i] = sc.nextInt();
            if (info[2] == 0 && i == 2) {     // If the user wants a generic car
                gearsNum = 6;
                bridge = 3.3;
                wheelDiameter = 0.57;
                break;
            }
        }

        if (info[2] != 0 && info[3] == 0) {     // If the user wants to customize his car and has the gear ratios

            System.out.print("Enter the number of gears: ");
            gearsNum = sc.nextInt();
            gearsRatios = new double[gearsNum];
            for (int i = 0; i < gearsNum; i++) {
                System.out.print("Enter the gear ratio for gear " + (i + 1) + ": ");
                gearsRatios[i] = sc.nextDouble();
            }
        }

        if (info[2] != 0 && info[3] != 0) {     // If the user wants to customize his car and has the number of gear teeth

            System.out.print("Enter the number of gears: ");
            gearsNum = sc.nextInt();

            System.out.print("Enter the type of gearbox (0 for 3 trees, 1 for 2 trees): ");
            int type = sc.nextInt();

            int y = 0;

            if (type == 0) {
                gearsTeeth = new int[2 + gearsNum * 2];
                System.out.print("Enter the number of gear teeth for the engine gear: ");
                gearsTeeth[0] = sc.nextInt();
                System.out.print("Enter the number of gear teeth for the gear connected to the engine gear: ");
                gearsTeeth[1] = sc.nextInt();
                y = 2;
            } else {
                gearsTeeth = new int[2 * gearsNum];
            }

            int x = 1;

            for (; x <= gearsNum; y += 2) {
                System.out.print("Enter the number of teeth for gear " + x + " in input: ");
                gearsTeeth[y] = sc.nextInt();
                System.out.print("Enter the number of teeth for gear " + x + " in output: ");
                gearsTeeth[y + 1] = sc.nextInt();
                x++;
            }

            gearsRatios = new double[gearsNum];
            int l = 0;
            for (int k = 0; k < gearsNum; k++) {
                if (type == 0) {
                    gearsRatios[k] = (double) (gearsTeeth[0] * gearsTeeth[l+2]) / (gearsTeeth[1] * gearsTeeth[l + 3]);
                }
                else {
                    gearsRatios[k] = (double) gearsTeeth[l] / gearsTeeth[l + 1];
                }
                l += 2;
            }
        }

        if (info[2] != 0) {
            System.out.println("Enter the bridge: ");
            bridge = sc.nextDouble();
            System.out.println("Enter the wheels' diameter: ");
            wheelDiameter = sc.nextDouble();
        }
    }
}