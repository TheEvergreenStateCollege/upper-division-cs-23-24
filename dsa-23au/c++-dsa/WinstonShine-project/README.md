## Binary Exploitation fall-2023 final project

The project goal was to create a c++ binary exploitation challenge similar to a challenge
I solved earlier in the quarter.
The challenge involves exploiting a vtable of a c++ class.

### Learning Goals
    
* gain a better understanding of what vtables are.
* analyze program memory of data allocated in the heap.
* re-create a challenge i've encountered as a means to solidify my understanding of binary exploitation

### How-to
     
from the root of the project directory the program can be compiled with  
`g++ vtables.cpp -o vtables`.  
Run the challenge with `./vtables`.

the program consists of a menu giving 4 options:
* store a message
* display a message
* display heap memory
* exit

the source code has a function called win and the address is given as 
part of the prompt.

the goal is to make use of a buffer overflow exploit to overwrite
the vtable of the object that stores the message you've entered into the program, and force the option that display's the message to instead call the `win` function as opposed to the `Recorder.print_message` function it normally calls.  
Here is the code for the class you will be trying to exploit:  
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


    




