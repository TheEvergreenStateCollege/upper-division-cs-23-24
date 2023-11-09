# show that a * b can be less than min(a,b)

def is_product_less_than_minimum(a, b):
    product = a * b  # var1
    minimum = min(a, b) # var2
    return product < minimum # return the boolean condition

# inputs hardcoded
a = 3
b = -4

# call the funtion with args
results = is_product_less_than_minimum(a, b)

# print true or false
print(results) 
