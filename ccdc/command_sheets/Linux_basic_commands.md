# Linux Commands Cheat Sheet

## Basic Commands

- `ls`: List files and directories.
- `ls -l`: List files with detailed information.
- `ls -al`: Same as above but shows hidden files.
- `cd`: Change directory.
  - `cd`: Takes you home.
  - `cd ..`: Goes up a directory.
  - `cd /`: Takes you to the root directory (top level).
- `pwd`: Show current directory path.
- `mkdir`: Create a new directory.
  - **Create Nested Directories**: Use the `-p` option to create parent directories if they do not exist. For example, `mkdir -p directory/subdirectory` will create both "directory" and "subdirectory" if they are not already present.
  - **Set Permissions**: Utilize the `-m` option to set permissions for the newly created directory. For instance, `mkdir -m 755 new_directory` will create "new_directory" with permissions set to 755.
- `rm`: Remove files or directories.
  - **Interactive Deletion**: Use the `-i` option to prompt for confirmation before deleting each file. For example, `rm -i file.txt` will ask for confirmation before removing "file.txt".
  - **Recursive Deletion**: Employ the `-r` or `-R` option to delete directories and their contents recursively. This is useful for deleting entire directory structures.
  - **Force Deletion**: The `-f` option can be used to force deletion without prompting for confirmation, which can be handy when deleting multiple files or directories.
- `cp`: Copy files and directories.
  - **Interactive Copy**: Utilize the `-i` option to prompt for confirmation before overwriting existing files, ensuring data safety.
- `mv`: Move or rename files and directories.
  - **Interactive Copy**: Utilize the `-i` option to prompt for confirmation before overwriting existing files, ensuring data safety.

## File and Directory Operations

- `cat filename`: Display file content.
- `touch file`: Create a new file.
- `cp -r source_directory destination`: Copy directory contents.
- `mv file1 file2`: Rename or move files.
- `ln -s /path/to/file linkname`: Create symbolic link.

## Process Management

- `ps`: Display currently running processes. Also `ps au` and `ps aux`.
- `top`: Display and manage top processes.
- `kill PID`: Terminate a process by ID.
  - When you use `kill PID` without specifying a signal, it sends the default `TERM` signal (`SIGTERM`) to the process, allowing it to perform cleanup operations before terminating. This signal gives the process a chance to shut down gracefully. On the other hand, `kill -9 PID` sends the `SIGKILL` signal, which forcefully terminates the process without allowing it to clean up or release resources. While `SIGTERM` is considered more graceful as it allows the process to exit cleanly, there may be situations where a process does not respond to `SIGTERM`, requiring the use of `SIGKILL` for immediate termination.

## Networking Commands

- `ip a`: Display network interfaces and IP addresses.
- `ping host`: Send ICMP echo requests to a host.

## User Management

- `adduser username`: Add a new user.
- `passwd -l 'username'`: Change user password.
- `userdel -r 'username'`: Remove a user.
