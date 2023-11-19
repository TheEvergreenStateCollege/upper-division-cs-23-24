

Project:

    Using ocean tide data from the coasts around Seattle,
    I am creating a set of tools that will help people
    extract useful data.

    I will be using the Python array.array package, which
    simulates a more C-like array structure and gets near-C-like
    efficiency because it is type enforced.

    To get the data, I will store the set in a python dict,
    then create classes to implement different types of structures
    and operations to go with the structures.

    By combining the low-level array with a dict lookup,
    I can get better efficiency, and approach theoretical optimums

    Includes:
      - Binary float search tree
      - Heaps
      - possibly more

    the TideSearch class 
    
    usage:  ts.findRange (lowRange, highRange)

    expected output:  a list of tree node IDs for which their pred is within the range

    I decided to store the 'pred' value on the nodes which would be used for 
    search comparisons.  it will not be possible to search the tree by ID, but that would
    be pointless anyway, since you can search the dict by ID

