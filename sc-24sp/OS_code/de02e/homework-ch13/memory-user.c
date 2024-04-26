#include <stdio.h>
#include <stdlib.h>
#include "common.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: memory-user <megabytes of memory>");
        return(0);
    }
    int mem = atoi(argv[1]);
    int *array = malloc(mem*1000000);
    for(;;) {
        for(int i = 0; i < sizeof(array); i++) {
            array[i] = array[i];
        }
    }
    free(array);
    return(0);
}