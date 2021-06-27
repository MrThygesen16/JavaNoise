# Java Noise

A project where I will produce a grid of squares and add noise etc. to them. 

This project has been built from the ground up in Java using `Swing` and `AWT`. 

> Swing is a GUI widget toolkit for Java.

> Abstract Window Toolkit (AWT) is Java's original platform-dependent  windowing, graphics, and user-interface widget toolkit, preceding Swing.

Initially this project was planned to be a random noise generator. Instead, I will attempt to re-create **Conway's Game of Life**.  This document will be a mix of explaining my approach/design, how the code was implemented, and notes on Conway's Game of Life. Essentially, this is logbook that will be updated based on the current stage of development for the project. I decided on keeping the name "Java Noise", as it seemed fitting. At a first glance The Game of Life seems to be nothing more than a random mess of pixels on the screen. 



## Table of Contents

[toc]

## Quick intro to The Game of Life

Essentially Conway's Game of Life is a type of cellular automaton in which an initial state or configuration is given, and then evolves over time based on said initial state. Essentially we have a square grid populated by black squares, known as cells. The behaviour of these cells are determined by a simple set of rules.

![Alt Text](https://upload.wikimedia.org/wikipedia/commons/e/e5/Gospers_glider_gun.gif)

The above GIF is an example of The Game of Life in practice. This specific example is what's known as "Gosper's Glider Gun". This is just one of the many patterns that frequently occur in the Game of Life. The three most common patterns that emerge are "Still Lifes", "Oscillators", and "Spaceships".

N.B. Cellular Automaton's are defined as: 

> A mathematical model that contains a of a set of units which have simple rules governing their replication and destruction,  used to model complex systems composed of simple units such as living  things or parallel processors.

### Cell arrangement

As mentioned earlier, The Game of Life takes place on a two-dimensional grid of square cells. A cell has two possible states: live or dead. Every cell interacts with its eight neighbours: horizontal (left and right), vertical (above and below), and its diagonals. The diagram below shows a cell $p$ and its eight possible neighbours.

![Alt text](https://www.researchgate.net/publication/351086124/figure/fig1/AS:1016163270135809@1619283611284/The-two-studied-adjacency-relations-on-the-square-grid-The-four-points-marked.png)



### Rules

Essentially for each step in time the cells re-arrange themselves based on the following rules:

> 1. Any live cell with fewer than two live neighbours dies, as if by under-population.
> 2. Any live cell with two or three live neighbours lives on to the next generation.
> 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
> 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.



We can simplify these rules further:

1. Any live cell with two or three live neighbours survives.
2. Any dead cell with three live neighbours becomes a live cell.
3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.



That's essentially the fundamentals of The Game of Life. Hopefully this serves as a solid foundation into what this project is about. Part of what makes The Game of Life so interesting is that we can observe the emergence of complex behaviour from a  relatively simple set of rules. The next section of this document will detail the core functionality of the project.

![](https://cdn-images-1.medium.com/proxy/1*lI0IaLqlpXjh96Z-_RX9Dw.gif)



## Core features & Functionality

Essentially this section will serve as a collection of must-have features that are to be implemented for this project. That being said, the program will work in the following way:

1.  On start-up an empty grid of squares will appear on the screen.
2.  Setting an initial state by editing the grid will be possible through:
   * Adding squares by left clicking.
   * Removing squares by right clicking.
3.  Once set, starting the simulation will be possible by clicking a particular button.
4.  If unhappy with how they are set, resetting the grid will be possible by clicking a particular button.
5.  Once the simulation has started, pausing/stopping the simulation will be possible by clicking a particular button.
6.  Editing of the tiles will not be possible when the simulation has started.
7.  Restarting the simulation will be possible by pressing a particular button, **only** if the simulation has been paused.



### Key/Button Assignments

As stated above, there will be user-interaction with the program. The following list details keys and their respective functions:

* `Left Click`: Add cell

* `Right Click`: Remove cell

* `SPACE-BAR`: Start/Stop simulation

* `R-key`: Reset simulation/clear grid 

  

### Further Considerations