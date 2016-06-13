# Bouncing-Balls

This project features an environment in which the user can insert different colored balls that bounce according to the laws of physics. Different sounds are made depending on the types of collisions that occur: for example, the collision of a yellow ball and the wall plays a part of a harp sample sequence, while the collision of a blue ball and a purple ball plays a random note. The user can specify what color of ball he wants to add, and the initial speed he wants it to have.

The environment is simulated in Java, while the sound generation and rendering is done in Max/MSP. OSC packets are used to communicate between Max and Java.

Dependencies: the algs4 library from edu.princeton.cs.algs4.
