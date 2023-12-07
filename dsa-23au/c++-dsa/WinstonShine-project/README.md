## Binary Exploitation fall-2023 final project

    The project goal was to create a c++ binary exploitation challenge similar to a challenge
    I solved earlier in the quarter.
    The challenge involes exploiting a vtable of a c++ class.

### Learning Goals
    
    gain a better understanding of what vtables are.
    analyze program memory of data allocated in the heap.
    re-create a challenge i've encountered as a means to learn more about
    why these exploits work, and hopefully be able to create some cool challenges
    in the future.

### How-to
     
    from the root of the project directory the program can be compiled with
    `g++ vtables.cpp -o vtables`
    and run the challenge `./vtables`
    code for the class you will be trying to exploit:
    ```
    class Recorder {
  public:
    Recorder(char *message) : message_(message) {}
    char *&message() { return message_; }
    virtual void print_message();

  private:
    char *message_;
};

void Recorder::print_message() {
    std::cout << std::endl << "Recorded message is: " << message_ << std::endl;
};

    ```
    the program consists of a menu giving 4 options:
        - store a message
        - display a message
        - display heap memory
        - exit
    
    the source code has a function called win and the address is given as 
    part of the prompt.
    
    the goal is to make use of a buffer overflow exploit to overwrite
    the c++ objects vtable holds the message you've entered into the program
    and force the option to `display a message` call the win function as opposed
    to the Recorder.print_message function it normally calls
    

    




