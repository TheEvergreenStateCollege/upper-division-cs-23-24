# Testing a list of integer, print true if the first or last element is 6

def start_end_6(testlist):
    if (testlist[0] == 6 or testlist[-1] == 6):
        return True
    else:
        return False


if __name__ == '__main__':
    # Test case 1
    list1 = [6, 42, 0, -37]
    #start_end_6 = list1
    
    # Tese case 2
    list2 = [7, 5, 87, 8]
    
    # Test case 3
    list3 = [5, 4, 3, 6]
    
    if (start_end_6(list1)):
        print('pass')
    
    if not (start_end_6(list2)):
        print('fail')
    else:
        print('pass')