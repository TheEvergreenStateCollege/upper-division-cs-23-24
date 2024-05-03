#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "common.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv[]) {
    for(;;) {
        char input[100];
        fgets(input,100,stdin);
        if (argc == 1) {
            int rc = fork();
            if (rc != 0) {
                wait(NULL);
            } else {
                execv(argc, argv[0]);
            }
        }
        char *copyInput = input;
        char *sepInput;
        while ((sepInput = strsep(&copyInput," ")) != NULL) {
            printf("%s\n", sepInput);
        };
    }
}