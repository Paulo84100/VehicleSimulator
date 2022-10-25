package vehicle;


/** A class with methods instanced in the main, working in pair with the VehicleMain.java class
 * @author Paul JEANGERARD <paul.jeangerard.pro@gmail.com>
 * @version 1.04
 * @since 1.01
 */


public class Vehicle {

    /**
     * The minimum speed of the vehicle's engine will be stored here as a constant.
     */
    private final int MIN_ENGINE_SPEED;
    /**
     * The maximum speed of the vehicle's engine will be stored here as a constant.
     */
    private final int MAX_ENGINE_SPEED;
    /**
     * The number of gears of the vehicle's gearbox will be stored here as a constant.
     */
    private final int GEARS_NUM;
    /**
     * The array of the different gears ratios will be stored here as a constant.
     */
    private final double[] GEAR_RATIOS;
    /**
     * The bridge of the vehicle will be stored here as a constant.
     */
    private final double BRIDGE;
    /**
     * The diameter of the wheels will be stored here as a constant.
     */
    private final double WHEEL_DIAMETER;
    /**
     * The speed of the vehicle's engine along the program will be stored here and has a getter and a setter.
     */
    private int currentEngineSpeed;
    /**
     * The gear of the vehicle's gearbox along the program will be stored here and has a getter and a setter.
     */
    private int currentGear;
    /**
     * The top speed of the vehicle (in kph) will be calculated in the constructor of this class and stored here.
     */
    private double topSpeed;


    /**
     * Constructor of this class
     * <p>
     * Requires ALL the parameters (no supercharge).
     * Then calculates the top speed of the vehicle according to the different parameters.
     *
     * @param minEngineSpeed The minumum speed of the vehicle's engine.
     * @param maxEngineSpeed The maximum speed of the vehicle engine.
     * @param gearsNum The number of different gears of the vehicle.
     * @param gearRatios The ratios from the first gear to the last one for each gear the vehicle has.
     * @param bridge The bridge of the vehicle.
     * @param wheelDiameter The diameter of the wheel of the vehicle
     */
    public Vehicle(int minEngineSpeed, int maxEngineSpeed, int gearsNum, double[] gearRatios, double bridge, double wheelDiameter) {
        this.MIN_ENGINE_SPEED = minEngineSpeed;
        this.MAX_ENGINE_SPEED = maxEngineSpeed;
        this.GEARS_NUM = gearsNum;
        this.GEAR_RATIOS = gearRatios;
        this.currentEngineSpeed = 0;
        this.currentGear = 1;
        this.BRIDGE = bridge;
        this.WHEEL_DIAMETER = wheelDiameter;
        updateTopSpeed();
    }


    /**
     * Overrides a method to call this function whenever it's needed to print the state of the vehicle.
     * @return The string printed.
     */
    public String toString() {
        return "Vehicle:" +
                "\nminEngineSpeed = " + MIN_ENGINE_SPEED +
                "\nmaxEngineSpeed = " + MAX_ENGINE_SPEED +
                "\ngearsNum = " + GEARS_NUM +
                "\ncurrentEngineSpeed = " + currentEngineSpeed +
                "\ncurrentGear = " + currentGear +
                "\nbridge = " + BRIDGE +
                "\nwheelDiameter = " + WHEEL_DIAMETER +
                "\ncurrentSpeed = " + toKph() +
                "\ntopSpeed = " + topSpeed;
    }


    /**
     * Increase the engine speed with an input.
     * <p>
     * Change the engine speed to the current one plus the one given as argument.
     * If it is lower than the minimum engine speed, then the engine speed will be set to the minimum engine speed.
     * If it is higher than the maximum engine speed, then the engine speed will be set to the maximum engine speed.
     *
     * @param engineSpeed The speed  (in RPM) to add to the current speed.
     */
    public void accelerate(int engineSpeed) {
        if (getCurrentEngineSpeed() + engineSpeed > getMaxEngineSpeed()) {
            System.out.println("The engine speed is too high");
            setCurrentEngineSpeed(getMaxEngineSpeed());

        } else if (getCurrentEngineSpeed() + engineSpeed < getMinEngineSpeed()) {
            System.out.println("The engine speed is too low");
            setCurrentEngineSpeed(getMinEngineSpeed());

        } else {
            setCurrentEngineSpeed(getCurrentEngineSpeed() + engineSpeed);
        }
    }


    /**
     * Lower the engine speed with an input.
     * <p>
     * Change the engine speed to the current one minus the one given as argument.
     * If it is lower than the minimum engine speed, then the engine speed will be set to the minimum engine speed.
     * If it is higher than the maximum engine speed, then the engine speed will be set to the maximum engine speed.
     * @param engineSpeed The speed (in RPM) to subtract from the current speed.
     */
    public void decelerate(int engineSpeed) {
        if (getCurrentEngineSpeed() - engineSpeed > getMaxEngineSpeed()) {
            System.out.println("The engine speed is too high");
            setCurrentEngineSpeed(getMaxEngineSpeed());

        } else if (getCurrentEngineSpeed() - engineSpeed < getMinEngineSpeed()) {
            System.out.println("The engine speed is too low");
            setCurrentEngineSpeed(getMinEngineSpeed());

        } else {
            setCurrentEngineSpeed(getCurrentEngineSpeed() - engineSpeed);
        }
    }


    /**
     * Return the RPM of the wheels according to the current engine speed, the current gear, the gear ratios and the bridge with a formula.
     * @return The RPM of the wheels
     */
    public double toWheelSpeed() {
        return getCurrentEngineSpeed() / getGearRatios()[getCurrentGear() - 1] / getBridge();   // t(wheels) = t / r / p {Where t(wheels) is the RPM of the wheels, t is the engine speed, r is the gear ratio and p is the bridge}
    }


    /**
     * Return the speed of the vehicle (in kph) according to the wheels' RPM and their diameter with a formula.
     * @return the speed of the vehicle (in kph)
     */
    public double toKph() {
        return toWheelSpeed() * (getWheelDiameter() * Math.PI) * 0.06;   // v = t * c * 0.06    {Where v is the speed of the vehicle, t is the RPM of the wheels, c is the diameter of the wheel}
    }


    // Getters


    /**
     * Getter of the minimum engine speed.
     * @return The minimum engine speed.
     */
    public int getMinEngineSpeed() {
        return MIN_ENGINE_SPEED;
    }


    /**
     * Getter of the maximum engine speed.
     * @return The maximum engine speed.
     */
    public int getMaxEngineSpeed() {
        return MAX_ENGINE_SPEED;
    }


    /**
     * Getter of the number of gears.
     * @return The number of gears.
     */
    public int getGearsNum() {
        return GEARS_NUM;
    }


    /**
     * Getter of the gear ratios.
     * @return The gear ratios.
     */
    public double[] getGearRatios() {
        return GEAR_RATIOS;
    }


    /**
     * Getter of the current engine speed.
     * @return The current engine speed.
     */
    public int getCurrentEngineSpeed() {
        return currentEngineSpeed;
    }


    /**
     * Getter of the current gear.
     * @return The current gear.
     */
    public int getCurrentGear() {
        return currentGear;
    }


    /**
     * Getter of the bridge.
     * @return The bridge.
     */
    public double getBridge() {
        return BRIDGE;
    }


    /**
     * Getter of the wheel diameter.
     * @return The wheel diameter.
     */
    public double getWheelDiameter() {
        return WHEEL_DIAMETER;
    }


    // Complicated setters and updaters


    /**
     * Sets the current engine speed to the one given in argument.
     * <p>
     * Tries to set the current engine speed to the one given in argument.
     * If the engine speed is lower than the minimum engine speed, then the engine speed will be set to the minimum engine speed.
     * If the engine speed is higher than the maximum engine speed, then the engine speed will be set to the maximum engine speed.
     *
     * @param currentEngineSpeed The engine speed to set to.
     */
    public void setCurrentEngineSpeed(int currentEngineSpeed) {

        if (currentEngineSpeed > getMaxEngineSpeed()) {
            System.out.println("The engine speed is too high");
            this.currentEngineSpeed = getMaxEngineSpeed();

        } else if (currentEngineSpeed < getMinEngineSpeed()) {
            System.out.println("The engine speed is too low");
            this.currentEngineSpeed = getMinEngineSpeed();

        } else {
            this.currentEngineSpeed = currentEngineSpeed;
        }
    }


    /**
     * Sets the current gear to the one given in argument.
     * <p>
     * Tries to set the current gear to the one given in argument.
     * If the gear is lower than 1, then the gear will be set to 1.
     * If the gear is higher than the number of gears, then the gear will be set to the number of gears.
     *
     * @param currentGear The gear to set to.
     */
    public void setCurrentGear(int currentGear) {
        double oldSpeed = toKph();
        if (currentGear > getGearsNum()) {
            System.out.println("The gear is too high");
            this.currentGear = getGearsNum();

        } else if (currentGear < 1) {
            System.out.println("The gear is too low");
            this.currentGear = 1;

        } else {
            this.currentGear = currentGear;
        }
        updateEngineSpeed(oldSpeed);
    }


    /**
     * Updates the engine speed according to the current gear and the speed before gear change given in argument with a formula.
     * @param oldSpeed The speed before gear change.
     */
    public void updateEngineSpeed(double oldSpeed) {
        setCurrentEngineSpeed((int) (getBridge() * getGearRatios()[getCurrentGear() - 1] * (oldSpeed / 0.06) / (getWheelDiameter() * Math.PI)));    // t = pr (v / 0.06 / c)    {Where t is the engine speed, p is the bridge, r is the gear ratio, v is the speed before gear change, c is the wheel perimeter}
    }


    /**
     * Updates the top speed of the vehicle according to the current gear and the engine speed with a formula.
     */
    public void updateTopSpeed() {
        this.topSpeed = (getMaxEngineSpeed() / getGearRatios()[getGearsNum() - 1]) / getBridge() * getWheelDiameter() * Math.PI * 0.06;     // v = t / r / p * c * 0.06    {Where v is the top speed, t is the maximum engine speed, r is the gear ratio of the maximum gear, p is the bridge, c is the wheel perimeter}
    }
}
