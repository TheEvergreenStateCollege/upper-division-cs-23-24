## How to use this code

### To run the stand-alone, command-line application do the following.
    
1. Make sure you are on the main branch version or on my pipeline for the latest version
        
2. Check your ability to switch cleanly:
    
    a. run  `git status`, if you have uncommitted changes, you may want to `git stash` them (restore with `git stash pop 0`)

3. Switch to a branch with a working copy
    `git switch main`,  or   `git switch pswish-nathanmcl`

4. run `python3 DriverData.py`. If you get a file not found, drill down into the directory `upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/LatestVersion` and run it with `python3 DriverData.py`

### To run the web based version, perform steps 1-2 above and continue. 
3. Switch to a branch with a working copy
    `git switch main`,  or   `git switch pswish-pipeline`

4. run the following from upper-division-cs directory: `python3 dsa-23au/java-dsa/pswish-pipeline/DjangoPr/try2/pswishsite/manage.py runserver 0.0.0.0:8001`

5. Once the server is running ignore the popup and navigate to Public IP viewable: http://18.144.90.119:8001/polls/