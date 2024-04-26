#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include "common.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv[]) {
    int fd[2],holder;
    char message[] = "I come from another process, beware!";
    char buffer[strlen(message)+1];
    pipe(fd);
    int rc1 = fork();
    int rc2 = -1;
    if (rc1 != 0) {
        rc2 = fork();
    }
    if (rc1 != 0 && rc2 != 0) {
    } else if (rc1 == 0 && rc2 == -1) {
        close(fd[0]);
        write(fd[1],message, strlen(message)+1);
    } else {
        close(fd[1]);
        holder = read(fd[0], buffer, sizeof(buffer));
        printf("recieved message: %s\n", buffer);
    }
}