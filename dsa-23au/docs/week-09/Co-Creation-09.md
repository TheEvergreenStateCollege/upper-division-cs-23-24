# Co-Creation 09

## Townhall Questions

Include your answers below each question:

How did you become interested in computer science or electronics?

    Day-to-day, what is enjoyable about studying and practicing CS or electronics to you?

    What would you like to do with a computer science, electronics engineering, and liberal arts education after graduating from Evergreen?

    What question, book, article, quote, movie about computation, electronics, and society is interesting to you at the moment?

    What do you wish you had known before beginning your CS or electronics studies at Evergreen, that you can tell incoming students to Computer Science Foundations or Electronics and Music?

    Name all faculty members that you know of, or can search in the course catalog or faculty directory, who practice and teach computation at Evergreen?

    What changes would you like to see in the computing or electronics culture (including academic computing support, electronic music programs, clubs, CS programs) at Evergreen if any?

    What independent CS- or electronics- related projects or research would you like to pursue at Evergreen?

    Student-led clubs like WORMS have done strong work in engaging with the CS community, having fun with computation, and helping them find employment in the tech industry. What ideas do you have to cultivate an even more welcoming atmosphere and environment for diversity newcomers entering the CS or electronics field? (I confess, I don't know much about women and underrepresented minorities in electronic music fields, but I suspect it is more diverse)

    What program or CS / electronics material would you like to learn at Evergreen that we currently do not offer?

    What communication do you feel is missing, or could be strengthened, between Evergreen CS / electronics faculty and students?
    How can we better market or present our brand as Evergreen CS / electronics to prospective students in the PNW area?

    What other discipline would you like to see combined with CS / electronics in a multi-disciplinary future program?

    What challenges do you see facing the CS / electronics curriculum in the future at Evergreen? How should we meet it?
    If there were a dedicated space at Evergreen for CS / electronics projects and events, what features would you want from it?
    What questions do you have about CS / electronics classes in Winter quarter for faculty?

    Who in the tech field would you like to invite to teach at Evergreen? It can be an example or archetypal person, or someone with influential work that you've read about.

    What question related to CS / electronics education at Evergreen has not been asked on this survey that you would like to answer? (and please also include your answer)




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



Jonathan Rodriguez
 "Open source" software makes its source code freely accessible, encouraging collaboration and transparency, while "closed source" keeps the source code private, maintaining control and enabling revenue generation. The choice depends on project goals, with open source emphasizing community contributions, and closed source prioritizing proprietary control and financial sustainability.

 Certainly! Let's delve deeper into the code review with specific examples to illustrate the concepts mentioned:

1. **Function Signature:**
   - Example: `public List<String> processData(int[] inputArray) {...}`
   - In this function signature, the return type is a `List` of `String`, the function name is `processData`, and it takes an `int` array as a parameter.

2. **Function Declaration and Parameters:**
   - Example: 
     ```java
     public void manipulateData(String[] data, int index) {
         // Function implementation
     }
     ```
   - Here, the function is declared as `manipulateData`, and it takes a `String` array (`data`) and an `int` (`index`) as parameters.

3. **Array Index:**
   - Example: `int element = inputArray[index];`
   - In this line, the array index (`index`) is used to access a specific element in the `inputArray`.

4. **Class Name:**
   - Example: `public class DataProcessor {...}`
   - The class is named `DataProcessor`, reflecting its role in processing data.

5. **Sibling Methods:**
   - Example:
     ```java
     public void processDataA() {...}
     public void processDataB() {...}
     ```
   - Here, `processDataA` and `processDataB` are sibling methods within the same class, likely performing related tasks.

6. **Instance Data Member and Function Member:**
   - Example:
     ```java
     public class DataManipulator {
         private int count;

         public void incrementCount() {
             count++;
         }
     }
     ```
   - The `count` is an instance data member, and `incrementCount` is a function member that operates on this instance data.

7. **Static Method:**
   - Example:
     ```java
     public class Utility {
         public static void performTask() {
             // Static method implementation
         }
     }
     ```
   - The `performTask` method is static, indicating it can be called on the class itself rather than an instance of the class.

In the context of the code review, these examples collectively showcase the significance of clear function signatures, well-defined classes, effective use of parameters and array indices, and the organization of related functionalities within sibling methods. The presence of instance data and function members emphasizes the importance of encapsulation and maintaining state within a class. The static method showcases scenarios where functionality is independent of specific instances.  


**Faulkdf Week9**

Uuuhhh... I'm slightly confused on if we (as studends) are supposed to answer the town hall questions, or if those were from the town hall that WORMs hosted. However, the questions seem intereseting so I will answer them breifly:

    I became interested in computer science and electronics from a young age. I have always loved taking apart appliances and computers and learning how they work from a hands on perspective. 

    It is enjoyable to have an understanding of how computers behave. We use them every day, we carry around a small metal and glass computer in our pocket. I am fascinated by the machines that have become essential within society. 

    Honestly I don’t know what I want to do. I didn’t want to graduate early and I decided to pursue a dual degree to extend my time at evergreen. I have been looking at some computer diagnostic and repair jobs for airlines. I love to travel, and this would afford me an opportunity to travel less expensively if I work for an airline. 

    The question that I ponder in the CS field quite a lot is “is AI the future, and how so?”

    I would tell incoming students to the CS field at evergreen, that knowing a programming language ahead of time will be very helpful. Especially java. 

    The ones I can think of off the top of my head, are Richard Weiss (the GOAT) Paul Pham, Arun Chandra, and perhaps Ben Kamen (not sure if he teaches code but im pretty sure he has some knowledge of coding)

    I would love to see more hardware related classes. I really wanted to study hardware and hardware repair as part of my degree, but it fundamentally isn’t something thats offered at evergreen. 

    If I do any independent studies, I would like them to be hardware related (I’m a hardware guy okay?)

    Honestly I don’t have any better ideas than WORMs. A proper computer science club is a great idea for engagement. 

    Honestly communication for assignments for this class feel like they have been very chaotic and hard to understand. It seems like there are many places to submit things, and trying to navigate between both canvas *and* GitHub is confusing. Im behind on assignments partially because of how disorganised things are from my prospective. 

    Honestly Evergreen as a whole needs to do a better job about marketing itself. I don’t think the CS department has any responsibility in that. 

    I really wish I took the computer science and linguistics programme. Thats a combo that seems very interesting to me. Seeing CS combined with stagecraft/lighting design would also be neat :)

    Biggest challenge is not having a centralised campus server for CS. I feel like every other university has a centralised server for their department, and Evergreen does not. Out of all the departments the CS classes need it the most. 

    This one is predictable from my past answers but I’d love to see a hardware specialist come and teach a full programme.
    I don’t believe I can think of any other questions at this time. This survey was relatively comprehensive. 