#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    printf("hello world (pid:%d)\n", (int)getpid());
    int rc = fork();
    if (rc < 0) {
        // fork failed; exit
        fprintf(stderr, "fork failed\n");
        exit(1);
    } else if (rc == 0) {
        // child (new process)
        sleep(1); // Sleep for 1 second to ensure child prints first
        printf("hello, I am child (pid:%d)\n", (int)getpid());
    } else {
        // parent goes down this path (original process)
        printf("goodbye, I am parent (pid:%d)\n", (int)getpid());
    }
    return 0;
}