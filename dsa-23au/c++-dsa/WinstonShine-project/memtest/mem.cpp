#include <cstring>
#include <iostream>
#include <typeinfo>
class Memtest {
  public:
    Memtest(char *message) : _message(message) {}
    char *&message() { return _message; }
    virtual void dialogue();

  private:
    char *_message;
};

void Memtest::dialogue() { std::cout << "vtable?" << std::endl; };
int main() {
    // test
    int stack = 8;
    std::cout << stack << "\t" << &stack << std::endl;

    // class object
    Memtest *memtest = new Memtest(new char[0x10]());
    void *vtable = (void *)(*(size_t *)memtest);
    void *mes = (void *)memtest->message();
    std::cout << std::endl << vtable << std::endl;
    std::cout << mes << std::endl;
    // message
    std::strcpy(memtest->message(), "AAAAAAAAABBBBBBBB");
    size_t *mptr = (size_t *)memtest->message();
    std::cout << mptr << " | " << *mptr << std::endl << std::endl;

    std::cout << sizeof(Memtest) << std::endl;
    std::cout << std::endl;
    size_t ptr = (size_t)mes - 0x10;
    std::cout << "obj size :" << sizeof(Memtest) << std::endl;
    for (size_t i = 0; i < 10; i++, ptr += 8) {
        std::cout << (void *)ptr << "\t|\t" << std::hex << *(size_t *)ptr
                  << std::endl;
    }
    return 0;
}
