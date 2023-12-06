

# /* Method to reverse an array of integers. Argument "a" is the list to be reversed
#    public static List<Integer> reverseArray(List<Integer> a) {
#     Instantiate new list of integers "lst", setting its elements equal to those of the argument "a"

#     Iterate through elements of a until reaching the index which is half of its largest index. "i" is current index
#     Instantiate a temporary variable "temp," set equal to the largest index of a minus "i"
#     Set element of lst[largest_index - i] equal to a[i]
#     Set lst[i] equal to "temp"

#     Return the list
# }*/

# // Modification for 11/28 class, Tuesday morning

# /* Method to reverse an array of integers and double them. Argument "a" is the list to be reversed
#    public static List<Integer> reverseArrayAndDouble(List<Integer> a) {
#     Instantiate new list of integers "lst", setting its elements equal to those of the argument "a"

#     Iterate through elements of a until reaching the index which is half of its largest index. "i" is current index
#     Instantiate a temporary variable "temp," set equal to the largest index of a minus "i"
#     Set element of lst[largest_index - i] equal to a[i] * 2
#     Set lst[i] equal to "temp" * 2

#     Return the list
# }*/


def reverseIt(arr):
    
    halfDex = (len(arr))//2
    lst = arr.copy()

    for i in range(halfDex):
        temp = arr[len(arr)-1-i]
        lst[len(arr)-1-i] = arr[i]
        lst[i] = temp
        
    print (lst)
    return lst        


reverseIt([1,2,3,4,5])