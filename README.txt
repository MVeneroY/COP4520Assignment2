Miguel Venero Yupanqui
Professor Parra
COP 4520
February 23, 2023

### Part 1 ###

To compile:
    $ cd part1
    $ javac Runner.java 

To run:
    $ java Runner x
    where x is the number of guests (threads) you want to initialize


-------------------------------------------------------------------------------

The approach I used makes the assumption that there will be N guests and N-1 
cupcakes, and that there will be an initial cupcake already placed in the middle
of the labyrinth at the start.

The guests' strategy is that every guest that enters the labyrinth will ask for
a new cupcake if they don't find one, and will eat it if it's the first time they
go in the labyrinth. If a guest goes in another time, they will leave the cupcake
alone. 

If at any point a guest goes in the labyrinth, doesn't find a cupcake, asks for
one but doesn't get it, they can assume that all the cupcakes have been eaten,
which means that all the guests including themselves have been inside the 
labyrinth at least once.

The protocol above guarantees correctness, although it relies on the assumption
that there aren't as many cupcakes as there are guests. The program is efficient
as well as each thread goes to the labyrinth as soon as they are chosen, and 
there's an upper bound of 100 guests, so the process overall is a quick one.

Experimental Evaluation:
8 guests            120ms
16 guests           112ms
32 guests           130ms
64 guests           131ms
100 guests          140ms


### Part 2 ###

to compile:
    $ cd part2 
    $ javac Runner.java 

To run:
    $ java Runner x
    where x is the number of guests (threads) you want to initialize


-------------------------------------------------------------------------------

The approach I used was the third option, where the guests line up in a queue
waiting for the guest in the vase room to notify them that they may go next.
The guests may go back into the queue. I chose this strategy as it was the 
simpler option when it came to managing the different threads.

It would've been easier to work with the threads using the first strategy,
although it would've caused threads to all attempt to visit the vase at the
same time, and no thread would be guaranteed to ever seeing the thread. 

The second strategy avoids strategy 1's issue when multiple guests try to visit
the vase at the same time, since they would need the sign to be set to available
before they even attempt to visit the vase, but it still doesn't guarantee that 
all threads will ever make it to the vase in the overall run.

The escond strategy does guarantee that all the guests will see the vase, as
there is a detemined order that includes all guests. Then, it would just be a 
matter of going through every guest on the queue.

The program is correct as every guest, before going to the vase, check for the 
previous guest's notification that they may go in. This way, the order of the 
queue is respected by all threads for the duration of the program's execution.
The program is also efficient, as the guests go into the vase room as soon as 
they are notified. The only inefficient aspect of the program is the fact that
any guest can choose to go back into the queue, which I implemented as a random 
probability. That means that, in rare situations, a program might run for too 
long if at least one guest refuses to leave the queue. However, the experimental
evaluation shows an approximately linear runtime growth.


Experimental Evaluation:
8 guests            168ms
16 guests           280ms
32 guests           536ms
64 guests           1080ms
100 guests          1567ms