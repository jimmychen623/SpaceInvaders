Final project for ECE 3140.
We implemented a variation of Space Invaders with a FRDM64 Board and a simple 2-axis joystick.

We used C for the polling of joystick values as well as interrupts for ammo spawning. We made use of the debug_printf() function in the Kinetics SDK to write joystick values to a serial port.

We then used pySerial to read the joystick values from serial.

The game and GUI were made in Java.
