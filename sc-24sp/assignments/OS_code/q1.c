#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    printf("hello world (pid:%d)\n", (int)getpid());
    int val = 100;
    int rc = fork();
    if (rc < 0)
    {
        // fork failed; exit
        fprintf(stderr, "fork failed\n");
        exit(1);
    }
    else if (rc == 0)
    {
        // child (new process)
        val = 200;
        printf("hello, I am child (pid:%d) val = %d\n", (int)getpid(), val);
    }
    else
    {
        // parent goes down this path (original process)
        val = 300;
        printf("hello, I am parent of %d (pid:%d) val = %d\n",
               rc, (int)getpid(), val);
    }
    return 0;
}
