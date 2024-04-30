// Write an infinite loop to get a line of input from stdin.
// fork a new process.
// in the child, call exec
// In the parent call wait(NULL).
// write a function to split the input on white space.

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>

#define MAX_INPUT_LENGTH 1024

// This function takes a string input and splits it into an array of strings
// based on whitespace (space, tab, newline) delimiters.
// It returns the array of strings.
char **split_input(char *input)
{
    // Allocate memory for the array of strings
    char **args = malloc(MAX_INPUT_LENGTH * sizeof(char *));
    int arg_count = 0;           // Initialize the argument count to 0
    char *token;                 // Declare a pointer to hold the current token
    const char *delim = " \t\n"; // Define the delimiter characters

    // Use strtok to split the input string into tokens
    token = strtok(input, delim);
    while (token != NULL)
    {
        args[arg_count++] = token;   // Add the current token to the args array
        token = strtok(NULL, delim); // Get the next token
    }
    args[arg_count] = NULL; // Terminate the args array with a NULL pointer
    return args;
}

int main()
{
    char input[MAX_INPUT_LENGTH];

    // Continuously prompt the user for input and execute the commands
    while (1)
    {
        printf("Enter a command: ");
        if (fgets(input, MAX_INPUT_LENGTH, stdin) == NULL) // Read the user's input
        {
            perror("fgets"); // Handle any errors with fgets
            exit(1);
        }

        // Create a new child process to execute the command
        pid_t pid = fork();
        if (pid == 0) // Child process
        {
            char **args = split_input(input); // Split the input into an array of arguments
            execvp(args[0], args);            // Execute the command using execvp
            perror("execvp");                 // Handle any errors with execvp
            exit(1);
        }
        else if (pid > 0) // Parent process
        {
            int status;
            waitpid(pid, &status, 0); // Wait for the child process to finish
            if (WIFEXITED(status))    // Check if the child process exited normally
            {
                printf("Child exited with status %d\n", WEXITSTATUS(status));
            }
            else
            {
                printf("Child terminated abnormally\n");
                exit(1);
            }
        }
    }
    return 0;
}