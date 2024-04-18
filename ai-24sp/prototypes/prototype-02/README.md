## Prototype 02 week 2

there was no previous documentation so I am having to guess what was done. It seems that all the code is there that we need, but I have no clue where load_images.py came from. 

we coppied over load_mnist.py

load_mnist.py needs to be updated from prototype-00, network.py is not finished.

# Prototype 02 week 3

1. Moved last weeks network.py to a hidden file - network_week02.py, and copied in the network.py from prototype-00. 
2. Copied in requirements.txt, train.py, and pbjson.py. Added pbjson to requirements file. 
3. Added a .gitignore file, edited it to include *.pbjson. 
4. Adjusted input/output of run_model.py.  

To run this version you must: 
    1. Copy .gz files (t10k-images, t10k-labels, train-images, train-labels)
    2. Unzip .gz files
        >> gunzip *.gz
    3. Install requirements
        >> pip3 install -r requirements.txt
    4. Run training.py
        >> python3 training.py
    5. This will produce a .pbjson file. 
    6. This file can be fed into run_model.py, by changing line 4. 