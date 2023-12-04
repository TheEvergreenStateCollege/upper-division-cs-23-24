/*
Instantiate priorityqueue "cookieQueue"
Add set of cookies to cookieQueue
instantiate integer name Operations equal to 0

Create while loop, while cookieQueue peek <k 
    if cookieQueue size < 2
        return -1
     set integer firstCookie equal to cookieQueue pop
     set integer secondCookie equal to cookieQueue pop
     set integer newCookie equal to firstCookie + 2 * secondCookie
     add newCookie to cookieQueue
     increase Operations by 1

return Operations
*/