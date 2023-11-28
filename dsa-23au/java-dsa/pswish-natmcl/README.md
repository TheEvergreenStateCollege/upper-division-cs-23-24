## How to use my code

### To run the stand-alone, command-line application do the following.
    
1. Make sure you are on the main branch version or on my pipeline for the latest version
        
2. Check your ability to switch cleanly:
    
    a. run  `git status`, if you have uncommitted changes, you may want to `git stash` them (restore with `git stash pop 0`)

3. Switch to a branch with a working copy
    `git switch main`,  or   `git switch pswish-nathnmcl`

4. run `/usr/local/bin/python3.10 /home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/python/Version1_3/DriverFiles/DriverMain.py`. If you get a file not found, drill down into the directory in the file path and run it with `python3.10 DriverMain.py`

if you get a syntax error, you may not have python 3.10 installed. You can still run my program with >>> `python3 BackupLogic.py`

