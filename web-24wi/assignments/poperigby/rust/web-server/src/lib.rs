use std::{
    sync::{mpsc, Arc, Mutex},
    thread,
};

pub struct ThreadPool {
    workers: Vec<Worker>,
    sender: mpsc::Sender<Job>,
}

type Job = Box<dyn FnOnce() + Send + 'static>;

impl ThreadPool {
    /// Create a new ThreadPool.
    ///
    /// The size is the number of threads in the pool.
    ///
    /// # Panics
    ///
    /// The `new` function will panic if the size is zero.
    pub fn new(size: usize) -> ThreadPool {
        assert!(size > 0);

        let (sender, receiver) = mpsc::channel();

        // Putting the receiver into an Arc<Mutex>> allows all of the Workers to share a handle to
        // the receiving end of the channel.
        let receiver = Arc::new(Mutex::new(receiver));

        let mut workers = Vec::with_capacity(size);

        for id in 0..size {
            workers.push(Worker::new(id, Arc::clone(&receiver)));
        }

        ThreadPool { workers, sender }
    }

    pub fn execute<F>(&self, f: F)
    where
        F: FnOnce() + Send + 'static,
    {
        // Create a new Job from the closure we receive
        let job = Box::new(f);

        // Send the job down the channel to be picked up by a worker
        self.sender.send(job).unwrap();
    }
}

impl Drop for ThreadPool {
    fn drop(&mut self) {
        for worker in &mut self.workers {
            println!("Shutting down worker: {}", worker.id);

            // Replace threads that were cleaning up with None
            if let Some(thread) = worker.thread.take() {
                thread.join().unwrap();
            }
        }
    }
}

struct Worker {
    id: usize,
    thread: Option<thread::JoinHandle<()>>,
}

impl Worker {
    fn new(id: usize, receiver: Arc<Mutex<mpsc::Receiver<Job>>>) -> Worker {
        Worker {
            id,
            thread: Some(thread::spawn(move || loop {
                //  The Worker's thread loops forever, operating on any Jobs that get sent down the
                //  channel.
                let job = receiver
                    .lock()
                    .expect("Another Worker thread panicked while holding the lock, instead or releasing it.")
                    .recv()
                    .expect("The thread holding the receiver has shut down.");

                println!("Worker {id} got a job; executing.");

                job();
            })),
        }
    }
}
