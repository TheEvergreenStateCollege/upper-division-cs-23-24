# Co-Creation 09

Add your sentences below. Each person must write at least five sentences.
Use five of the following terms:

* Function call
* Function signature
* Function declaration
* Return type
* Function name
* Class name
* Array index
* Generic type
* Sibling method
* Instance data member
* Function member
* Method
* Static method
* Function parameter

Nathan McL
Static method: Static methods can be used as a utility or helper methods that can be accessed without needing to instantiate the class they are defined in. 
here is a simple example: 
public class MathUtility {

    // This static method is used to calculate the sum of two integers
    public static int calculateSum(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        // Calling the static method without creating an instance of MathUtility
        int result = MathUtility.calculateSum(10, 20);
        System.out.println("The sum is: " + result);
    }
}
Sibling methods can be described as methods in a class that are at the same level of hierarchy and have a related purpose or functionality.

I thought Function declarations are interesting, I think what a function declaration is  like a precheck for the compiler. Here is a better explation. Function declaration is a statement that specifies the name, return type, and parameters of a function, but does not include the function body. It's a way to inform the compiler about a function's existence before its actual implementation.