// Chapter 15: Threads and Locks
// pp. 174-179. 15.3


// Dining Philosophers: In the famous dining philosopher’s problem,
// a bunch of philosophers are sitting around a circular table with one chopstick between each of them.
// A philosopher needs both chopsticks to eat and always picks up the left chopstick before the right one.
// A deadlock could potentially occur if all the philosophers reached for the left chopstick at the same time.
// Using threads and locks, implement a simulation of the dining philosopher’s problem that prevents deadlocks.

// Dining Philosophers Problem simulation to prevent deadlocks.
class Chopstick {
    private Lock lock;

    // The term "reentrant" means that the lock can be acquired multiple times by the same thread. 
    public Chopstick() {
        lock = new ReentrantLock();
    }

    // Method to attempt to pick up a chopstick using tryLock().
    public boolean pickUp() {
        return lock.tryLock(); // Returns true if lock is acquired successfully.
    }

    // Method to release the chopstick's lock.
    public void putDown() {
        lock.unlock();
    }
}

// Represents a philosopher in the dining philosophers problem.
class Philosopher extends Thread {
    private int id; // Unique identifier for each philosopher.
    private Chopstick leftChopstick; // The chopstick on the philosopher's left.
    private Chopstick rightChopstick; // The chopstick on the philosopher's right.

    // Constructor assigns IDs and corresponding left and right chopsticks.
    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.leftChopstick = left;
        this.rightChopstick = right;
    }

    // The main action of the philosopher thread.
    public void run() {
        while (true) { // Infinite loop to simulate continuous dining table scenario.
            think(); // Philosopher thinks.
            if (pickUpChopsticks()) { // Attempts to pick up both chopsticks.
                eat(); // Eats if successful.
                putDownChopsticks(); // Puts down chopsticks after eating.
            }
        }
    }

    // Simulates the philosopher thinking.
    private void think() {
        System.out.println("Philosopher " + id + " is thinking.");
    }

    // Simulates the philosopher eating.
    private void eat() {
        System.out.println("Philosopher " + id + " is eating.");
    }

    // Attempts to pick up both left and right chopsticks in a specific order to avoid deadlock.
    private boolean pickUpChopsticks() {
        Chopstick first = id % 2 == 0 ? leftChopstick : rightChopstick;
        Chopstick second = id % 2 == 0 ? rightChopstick : leftChopstick;

        // Picks up the first chopstick.
        if (first.pickUp()) {
            // Attempts to pick up the second chopstick.
            if (second.pickUp()) {
                return true; // Returns true if both chopsticks are picked up.
            } else {
                // Releases the first chopstick if the second one is not available.
                first.putDown();
            }
        }
        return false; // Returns false if unable to pick up both chopsticks.
    }

    // Method to put down both chopsticks.
    private void putDownChopsticks() {
        leftChopstick.putDown(); // Puts down the left chopstick.
        rightChopstick.putDown(); // Puts down the right chopstick.
    }
}

// Main class to simulate the dining philosophers problem.
public class DiningPhilosophers {
    public static void main(String[] args) {
        int numOfPhilosophers = 5; // Total number of philosophers.
        Philosopher[] philosophers = new Philosopher[numOfPhilosophers];
        Chopstick[] chopsticks = new Chopstick[numOfPhilosophers];

        // Initialize chopsticks.
        for (int i = 0; i < numOfPhilosophers; i++) {
            chopsticks[i] = new Chopstick();
        }

        // Create and start philosopher threads.
        for (int i = 0; i < numOfPhilosophers; i++) {
            // Assign left and right chopstick to each philosopher.
            Chopstick leftChopstick = chopsticks[i];
            Chopstick rightChopstick = chopsticks[(i + 1) % numOfPhilosophers];
            philosophers[i] = new Philosopher(i, leftChopstick, rightChopstick);
            philosophers[i].start(); // Starts the philosopher thread.
        }
    }
}
