#include <iomanip>
#include <iostream>

void win() {
    std::cout << "congrats you win!\n"; 
    exit(0);
};

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

void display_heap(Recorder *obj, void *message_addr, void *vtable_addr) {
    std::cout << message_addr << std::endl;
    std::cout << vtable_addr << std::endl;
    std::string separator = "                -------------------";
    std::cout << std::endl
              << std::right << "  [ address ]    [ heap data ]" << std::endl;

    size_t *p = (size_t *)message_addr - 2;
    std::cout << separator << std::endl;
    for (int i = 0; i < 12; i++, p++) {
        std::cout << std::setfill('0') << std::setw(12) << p << " | "
                  << std::setw(16) << std::hex << *(size_t *)p << " | ";
        if (p == message_addr) {
            std::cout << "  <--- message: " << obj->message();
        }
        if (p == vtable_addr) {
            std::cout << "vtable | " << *(size_t *)vtable_addr << " -> "
                      << *(size_t *)*(size_t *)vtable_addr << " |";
            if((void*)*(size_t*)*(size_t *)vtable_addr == (void*)win){
                std::cout << " <- address to win";
            } 
        }

        std::cout << std::endl << separator << std::endl;
    }
};

int main() {
    std::setvbuf(stdin, NULL, _IONBF, 0);
    std::setvbuf(stdout, NULL, _IONBF, 0);

    Recorder *recorder = new Recorder(new char[0x18]());
    void *vtable_addr = (void *)recorder;
    void *message_addr = (void *)recorder->message();

    std::cout << "win function address: " << (void*)win << std::endl;
    int opt;
    bool exit = false;
    while (!exit) {
        std::cout << std::endl
                  << "1: record message" << std::endl
                  << "2: print message" << std::endl
                  << "3: display heap" << std::endl
                  << "4: exit" << std::endl
                  << std::endl;
        std::cin >> opt;
        switch (opt) {
        case 1:
            std::cout << "Enter a mesage: ";
            std::cin >> recorder->message();
            break;
        case 2:
            recorder->print_message();
            break;
        case 3:
            display_heap(recorder, message_addr, vtable_addr);
            break;
        case 4:
            exit = true;
            break;
        default:
            std::cout << "Invalid option." << std::endl;
            return 0;
        }
    }
    return 0;
}
