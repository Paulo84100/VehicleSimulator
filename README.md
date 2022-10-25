# VehicleSimulator
A java program to see the evolution of a vehicle according to its engine's speed. Customizable gearbox and engine.

Author : Paul JEANGERARD <paul.jeangerard.pro@gmail.com>
Version : 1.01


All the documentation is in the code.


Here is the How it works from the compiled program's README.txt :

----------	How it works ----------

Part 1 :

The program will begin by asking you what speed (in rpm) your vehicle's engine will go at minimum and maximum (example : 500 for minimum, 7000 for maximum).

Part 2.1 :

Then you will have to choose between an already set up vehicle (by typing 0) or a custom one (by typing 1).
If you chose an already set up vehicle just skip to part 3.

Part 2.2 :

This is only if you chose to make a custom car.

You will be asked the number of available gear shifts in your gearbox.



You will have to choose between entering known gear ratios of your vehicle (in your vehicle's manual) (by typing 0) (example : 3.4 for first gear, 1.9 for second gear, 1.3 for third gear, 1 for fourth gear, 0.8 for fifth gear and 0.7 for sixth gear)..

OR

Calculating your own (you will be asked between two types of gearbox : 2 trees or 3 trees), by counting the number of teeth on each gear of the gearbox and entering them. (Example : 20 teeth for engine gear, 30 teeth to the one connected to it, 15 teeth for the input 1st gear and 38 teeth for the output first gear and so on for the next available gears).



Then you will be asked the bridge of your vehicle and its wheels diameter (again in your vehicle's manual)

Part 3 :

You will enter the main loop of the program :
You will have to type :
	- The desired command : a for accelerating the engine speed, d for decelerating the engine speed, c to change to a specific gear, n to switch to the next gear if existing, p to switch to the previous gear if existing, s to see the state of the vehicle (its speed, gear, engine speed...), and e to exit the program.
	and ONLY IF NECESSARY, add a SPACE at the end of the letter you chose and follow it by an argument :
	- The argument : (a number of rounds per minute (like 1200) if it is the command a or d, a number indexing the gear to change to (from 1 to 6 with default vehicle and from 1 to [The number of gears you chose] for a custom vehicle)).
		/!\ All the commands not stated in the previous sentence don't need arguments.
