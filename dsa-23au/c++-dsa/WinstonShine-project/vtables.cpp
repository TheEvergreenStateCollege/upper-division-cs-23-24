#include <iomanip>
#include <iostream>
// ideally change to grant user a shell
void win() { std::cout << "congrats you win!\n"; };

class Recorder {
  public:
    Recorder(char *message) : message_(message) {}
    char *&message() { return message_; }
    virtual void print_message();

  private:
    char *message_;
};

void Recorder::print_message() { std::cout << message_ << std::endl; };

void display_heap(Recorder *obj, void *message_addr, void *vtable_addr) {
    std::string separator = "                -------------------";
    std::cout << std::endl
              << std::right << "  [ address ]    [ heap data ]" << std::endl;

    size_t *p = (size_t *)message_addr - 2;
    std::cout << separator << std::endl;
    for (int i = 0; i < 12; i++, p++) {
        std::cout << std::setfill('0') << std::setw(12) << p << " | "
                  << std::setw(16) << std::hex << *(size_t *)p << " | "<< std::endl;
        std::cout << separator << std::endl;
    }
};

int main() {
    std::setvbuf(stdin, NULL, _IONBF, 0);
    std::setvbuf(stdout, NULL, _IONBF, 0);

    Recorder *recorder = new Recorder(new char[0x20]());
    void *vtable_addr = (void *)(*(size_t *)recorder);
    void *message_addr = (void *)recorder->message();

    std::cin >> recorder->message();
    std::cout << "message address: " << message_addr << std::endl;
    std::cout << "vtable address: " << vtable_addr << " | " << std::hex
              << *(size_t *)vtable_addr << " | " << std::hex
              << *(size_t *)*(size_t *)vtable_addr << std::endl;

    display_heap(recorder, message_addr, vtable_addr);
}
