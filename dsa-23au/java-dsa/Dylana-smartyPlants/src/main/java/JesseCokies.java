import java.util.PriorityQueue;

public class JesseCookies {
    /*
     * while smallest element is less than k
	if there is less than two elements
		return -1
	take smallest two cookies out of queue
	combine first cookie with double second cookie
	add the new cookie to the queue
	increment operation count
return operation number
     */

    
    public static int Cookies(int[] array, int k){ 
        PriorityQueue<Integer> CookieQueue = new PriorityQueue<Integer>();
        for(int i = 0; i < array.length;i++){
            CookieQueue.push(array[i]);
        }
        operationCount = 0;
        while(CookieQueue.peek() < k){
            if(CookieQueue.length < 2){
                return -1;
            }
            int smallestCookie = CookieQueue.pop();
            int secondSmallestCookie = CookieQueue.pop();
            int v = smallestCookie + secondSmallestCookie*6;
            CookieQueue.push(v);
            operationCount +=1;
        }
        return operationCount;
        }
    }

