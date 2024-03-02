#!/bin/bash

# Fixed log file name
LOG_FILE="os_log.csv"

# Insert a blank line after every third log entry
LINE_COUNT=$(wc -l < "$LOG_FILE" 2>/dev/null || echo "0") # Get the current line count or default to 0 if the file doesn't exist
((LINE_COUNT++)) # Increment line count for this entry
INSERT_BLANK_LINE_AFTER=4 # Insert a blank line after every 4 log entries

# Check if the log file exists, and create it with headers if it does not
if [ ! -f "$LOG_FILE" ]; then
    echo "OS Name,Date Time,CPU Temp,Wi-Fi -dBm,CPU Usage,Memory Usage,Disk Usage,Net Traffic Rx,Net Traffic Tx" > "$LOG_FILE"
    LINE_COUNT=1
fi

# The Operating System name
OS_NAME=$(cat /etc/os-release | grep PRETTY_NAME | awk -F= '{print $2}')

# Time and Date
DATE_TIME=$(date +"%Y-%m-%d %H:%M:%S")

# Temperature
CPU_TEMP=$(cat /sys/class/thermal/thermal_zone0/temp)
CPU_TEMP=$(echo "scale=2; $CPU_TEMP / 1000" | bc)

# WI-FI signal strength
WIFI_DBM=$(iwconfig wlan0 | grep 'Signal level' | awk -F '=' '{print $3}' | awk '{print $1}')

# CPU Usage Calculation
# This script reads the first line from /proc/stat to obtain time spent by the CPU in different modes: user, nice, system, and idle.
# 'user' time is CPU time spent on user processes.
# 'nice' time is CPU time spent on low-priority user processes.
# 'system' time is CPU time spent on system/kernel processes.
# 'idle' time is when the CPU was not in use.
# It then calculates CPU usage as the percentage of time the CPU was active (user, nice, system) out of the total time (including idle).
read cpu a b c idle rest < /proc/stat
CPU_USAGE=$(awk "BEGIN {print ($a+$b+$c) / ($a+$b+$c+$idle) * 100}")

# Memory usage
MEM_USAGE=$(free -m | awk '/Mem:/ {print ($3-$7)/$2 * 100.0}')

# Disk usage
DISK_USAGE=$(df -h / | awk '/\/$/ {print $5}')

# Network Traffic (received/sent bytes)
NET_TRAFFIC_RX=$(ip -s link show wlan0 | awk '/^\s*RX:/{getline; print $1}')
NET_TRAFFIC_TX=$(ip -s link show wlan0 | awk '/^\s*TX:/{getline; print $1}')

# Log data to CSV file
{
    echo "$OS_NAME,$DATE_TIME,$CPU_TEMP,$WIFI_DBM,$CPU_USAGE,$MEM_USAGE,$DISK_USAGE,$NET_TRAFFIC_RX,$NET_TRAFFIC_TX"
    # Check if we need to insert a blank line
    if ((LINE_COUNT % INSERT_BLANK_LINE_AFTER == 0)); then
        echo ""
    fi
} >> "$LOG_FILE"

echo "Log saved to $LOG_FILE"