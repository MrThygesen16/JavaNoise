# Java Noise

A project where I will produce a grid of squares and add noise etc. to them. 

This project has been built from the ground up in Java using `Swing` and `AWT`. 

> Swing is a GUI widget toolkit for Java.

> Abstract Window Toolkit (AWT) is Java's original platform-dependent  windowing, graphics, and user-interface widget toolkit, preceding Swing.

Initially this project was planned to be a random noise generator. Instead, I will attempt to re-create **Conway's Game of Life**.  This document will be a mix of explaining my approach/design, how the code was implemented, and notes on Conway's Game of Life. Essentially, this is logbook that will be updated based on the current stage of development for the project. I decided on keeping the name "Java Noise", as it seemed fitting. At a first glance The Game of Life seems to be nothing more than a random mess of pixels on the screen. Hopefully this project can show that it's much more than that.



## Table of Contents

1. [Introduction](#Quick-Intro-To-The-Game-of-Life)

	1. [Cell Arrangement](#Cell-arrangement)

	2. [Rules to Life](#Rules)

2. [Core Features](#Core-features-and-Functionality)
  1. [Button Assignments](#Key/Button-Assignments) 
	  
  2. [Considerations](#Considerations-for-the-Grid-and-Cells)
	  
  3. [State Machine](#State-Machine)

## Quick intro to The Game of Life

Essentially Conway's Game of Life is a type of cellular automaton in which an initial state or configuration is given, and then evolves over time based on said initial state. Essentially we have a square grid populated by black squares, known as cells. The behaviour of these cells are determined by a simple set of rules.

![Alt Text](https://upload.wikimedia.org/wikipedia/commons/e/e5/Gospers_glider_gun.gif)

The above GIF is an example of The Game of Life in practice. This specific example is what's known as "Gosper's Glider Gun". This is just one of the many patterns that frequently occur in the Game of Life. The three most common patterns that emerge are "Still Lifes", "Oscillators", and "Spaceships".

N.B. Cellular Automaton's are defined as: 

> A mathematical model that contains a of a set of units which have simple rules governing their replication and destruction,  used to model complex systems composed of simple units such as living  things or parallel processors.

### Cell arrangement

As mentioned earlier, The Game of Life takes place on a two-dimensional grid of square cells. A cell has two possible states: live or dead. Every cell interacts with its eight neighbours: horizontal (left and right), vertical (above and below), and its diagonals. The diagram below shows a cell `p` and its eight possible neighbours.

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



## Core features and Functionality

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

  

### Considerations for the Grid and Cells

First of all, the grid will be stored using a 2-dimensional array of values. For example, if we have a 4x4 grid the x and y coordinates of these cells will correspond to their position in the Array. It is in the form `(row, column)`:

|   (0, 0)   |   (0, 1)   |   (0, 2)   |   (0, 3)   |
| :--------: | :--------: | :--------: | :--------: |
| **(1, 0)** | **(1, 1)** | **(1, 2)** | **(1,3)**  |
| **(2, 0)** | **(2, 1)** | **(2, 2)** | **(2, 3)** |
| **(3, 0)** | **(3, 1)** | **(3, 2)** | **(3, 3)** |

The plan is for each redraw that takes place, we iterate through every cell and check the status of their neighbours. Each cell will only handle the drawing and state of itself, so doing this via the grid seems like the easiest approach. As mentioned earlier, we check the eight neighbours for each cell. However, there are a few cases where we cannot do so. For example `(0, 0)`, `(1, 3)`, and `(3, 1)`. The cells in the middle `(1, 1)`, `(1, 2)`, `(2, 1)`, and `(2, 2)` do not have this issue. From this we can see there are a few conditions we need to account for.

![](/home/mike/Code/JavaNoise/docs/GOL.png)

As we can see, in the diagram above, there are four cases to be accounted for :

* Corners (Purple)

* Left and Right edges (Red)

* Top and Bottom edges (Blue)

* Centre squares (Green) 

  

For example, we don't want the top left square checking for a cell above it, as it doesn't exist and will throw an error. For each of these cases they can check only a portion of the normal eight neighbours:

* Corners [`(0, 0)`,`(0, 3)`, `(3, 0)`, and `(3, 3) `]can only check 3 neighbours
* Left and Right Sides [`(1,0)`, `(2, 0)`, `(1, 3)`, and `(2, 3)`] can check 5 neighbours
* Top and Bottom rows [`(0,1)`, `(0, 2)`, `(3, 1)`, and `(3, 2)`]   can check 5 neighbours



We can form an equation/algorithm to easily find all the corners & edges:

* `(0, 0)` is the easiest since it's the first cell in the grid
* The top right corner is just `(0, number of columns - 1)`
* Bottom left corner is `(number of rows - 1, 0)`
* Bottom right corner is `(number of rows - 1, number of columns - 1)`

* The top row is the range: `(0, 1)` through to `(0, number of columns - 2)`
* The bottom row is the range: `(number of rows - 1, 1)` through to `(number of rows - 1, number of columns - 2)`
* The left column is the range: `(1, 0)` through to `(number of rows - 2, 0)`
*  The right column is the range: `(1, number of columns-1)` through to `(number of rows - 2, number of columns - 1)`

That way it can work for a grid of any size (just in case I decide to add in that feature).



With that said, for a green square, let's say `(1, 1)`, we can form a standard equation/algorithm to find each of its eight neighbours:

* 1 square above: `(row - 1, 1)` = `(0, 1)` or simply add `(-1, 0)`
* 1 square below: `(row + 1, 1)` = `(2, 1)` or simply add `(1, 0)`

* 1 square left: `(1, column - 1)` = `(1, 0)` or simply add `(0, -1)`
* 1 square right: `(1, column + 1)` = `(1, 2)` or simply add `(0, 1)`

* Top left diagonal: `(row - 1, column -1)` = `(0, 0)` or simply add `(-1, -1)`

* Top right diagonal: `(row - 1, column + 1)`  = `(0, 2)` or simply add `(-1, 1)`

* Bottom left diagonal: `(row + 1, column -1)` = `(2, 0)` or simply add `(1, -1)`

* Bottom right diagonal: `(row + 1, column + 1)` = `(2, 2)` or simply add `(1, 1)`

  

And as mentioned earlier, the edge and corner cells will only be able to access a portion of these.



### State Machine

