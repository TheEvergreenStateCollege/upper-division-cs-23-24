# Advanced File Operations

## find

Search for files and directories based on various criteria. Examples:

- Find a file named `tecmint.txt` in the current directory: `find . -name tecmint.txt`
- Find a file named `tecmint.txt` in the `/home` directory: `find /home -name tecmint.txt`
- Find a file named `tecmint.txt` in the `/home` directory (case-insensitive): `find /home -iname tecmint.txt`
- Find files by type, including files, directories, symlinks, named pipes, and sockets: `find <directory_path> -type <file_type>`

## grep

Search for specific patterns in files. Examples:

- Search for text in a file: `grep "search_text" file.txt`
- Perform a case-insensitive search: `grep -i "search_text" file.txt`
- Search for exact words only: `grep -w "word" file.txt`
- Search for a pattern in subdirectories: `grep -r "pattern" /path/to/directory`

## tar

Archive files and directories. Examples:

- Create a tar archive of a directory: `tar -cvf archive.tar directory_name`
- Extract files from a tar archive: `tar -xvf archive.tar`
- Create a compressed tar archive (gzip): `tar -czvf archive.tar.gz directory_name`
- Extract files from a compressed tar archive (gzip): `tar -xzvf archive.tar.gz`

## rsync

Synchronize files and directories between systems.

# System Monitoring and Performance

## htop

Interactive process viewer.

- Incremental Process Filtering: Type in part of a process command line, and only processes whose names match will be displayed. To cancel filtering, simply enter the filter.
- Search for Specific Process Name: Press F3 or `/` and start typing the process name to search. This action highlights the closest matching result among all entries, allowing for efficient process identification.

## iostat

Input/output statistics for devices and partitions.

- Basic Usage: Simply run `iostat` in the terminal without any options to display a summary of CPU and I/O usage.
- Specific Device Monitoring: To monitor a specific device, use `iostat -d <device_name>` to focus on the I/O statistics of that particular device.
- Interval Refresh: Set a specific time interval for updates by using `iostat -t <interval_in_seconds>` to refresh the statistics at regular intervals.

## vmstat

Virtual memory statistics.

- Basic Usage: Simply run `vmstat` in the terminal without any options to display a summary of processes, memory, paging, block IO, traps, disks, and CPU activity.
- Customize Output: Specify the refresh interval and the number of iterations by using `vmstat <interval> <count>` to monitor system statistics at regular intervals.
- Focus on Specific Aspects: Concentrate on specific aspects like memory or CPU activity using options like `-s` for memory statistics or `-S` for swapping details.

## sar

System activity reporter for performance monitoring.

- Display CPU Utilization: View CPU utilization statistics using `sar -u` to show CPU usage at different intervals.
- Monitor Memory Usage: Check memory usage with `sar -r` to display memory statistics like free, used, and swap memory.
- Analyze Disk Activity: Monitor disk activity, including read and write statistics for different disks, with `sar -d`.
- Track Network Activity: Observe network activity by running `sar -n DEV` to display network interface statistics.

# Network Analysis and Security

## tcpdump

Network packet analyzer.

- Capture Network Traffic: Use `tcpdump` to capture network packets for analysis by running `sudo tcpdump -i <interface>` to monitor and analyze network traffic.
- Filter Specific Traffic: Apply filters to focus on specific traffic types or sources using options like `-s` for snaplen and `-n` for displaying IP addresses instead of hostnames.
- Save Captured Data: Save captured packets to a file for further analysis by appending `-w <filename>` to the `tcpdump` command.
- Analyze Captured Data: After capturing data, analyze it using tools like Wireshark for in-depth packet inspection and security analysis.

## nmap

Network discovery and security auditing tool.

- Scan Target Systems: Conduct network scans by specifying the target IP addresses or ranges using commands like `nmap <target>` to identify open ports and services.
- Stealthy Scans: Perform stealthy scans to evade detection by using options like `-sS` for SYN scan or `-sT` for TCP connect scan.
- Service Version Detection: Utilize service version detection with `-sV` to identify the versions of services running on open ports.
- Operating System Detection: Employ OS detection with `-O` to determine the operating system of target systems.
- Output Results: Save scan results to a file for analysis by appending `-oN <filename>` to the `nmap` command.

## netstat

Network statistics and connections.

- Display All Listening Ports: Use `netstat -a` to list all listening ports for both TCP and UDP connections.
- View Network Connections: Check active network connections with `netstat -tuln` to display TCP and UDP connections along with the associated port numbers.
- Show Routing Table: Use `netstat -r` to view the routing table, which displays information about the network routing paths.
- Monitor Network Statistics: Analyze network statistics like packet counts and errors by running `netstat -s` to get a summary of network statistics.

## iptables

Firewall configuration tool.

- View Current Rules: Check the current firewall rules by running `iptables -L` to list all rules in place.
- Allow Specific Ports: Allow incoming traffic on specific ports using commands like `iptables -A INPUT -p tcp --dport <port_number> -j ACCEPT`.
- Block IP Addresses: Block specific IP addresses by adding rules like `iptables -A INPUT -s <ip_address> -j DROP`.
- Enable NAT: Configure Network Address Translation (NAT) with commands like `iptables -t nat -A POSTROUTING -o eth0 -j MASQUERADE` for outbound traffic.
- Save Configuration: Save your firewall configuration to persist across
