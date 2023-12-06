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

------------------ @pswish -----------------
1. I learned this week that it was better to write a csv filepath validation method for handling the hardcoded csv filepaths.
2. Generic type has such a nonchalant name but it sure case be consuming and demanding. The strongly typed language model of Java is more like you must know your types.
3. Sometimes, it is boring to just be this_method_does this so to add a bit of flair into the code, you can add some comedy (or attempt sat it). For example, I called my class name in the config file theConfigurator.
4. I finally decided to go with more convention in my function names and so version 1.3 had this change. Sometimes breaking with convention is ok until other programmers need to decipher your code. 
5. I think I finally got my head wrapped around sibling methods in java, something so easy can be confusing sometimes.
Readings:
* What does it mean for software to be licensed as open source or closed source?
	Basically, it means do you want share your code with others to use normally with little restrictions or do you want to keep it secret and potentially charge users to use it. I don't think this is a binary choice since both the big producers in the gaming industry need to be closed source due to the length of projects and assets at stake where a sector like machine learning is heavily dependent on open source. Can you imagine every AI programmer needing to write the millions of lines of code to create AI sight? An then test it over and over. Since technology like that was shared open source, AI advancements have been monumental.
* Compare and contrast your favorite open source software and your favorite closed source software.
	My Favorite open source software has to be Linux. As a Windows user until 2013, and then a Mac user, Linux is by far my choice OS. My favorite closed source software would have to be Steam.
* How are they alike and different?
	They are similar that they both run on a code base, and different because one is an OS and the other is a software marketplace.
* Why do you like them?
	Ease of use and reliability is common for both of my use-cases. 
* Why do you think it was released under its license?
	Linux probably to change the world, where Steam is in the business to make money and wants to create a monopoly.
* How do you think detractors of these pieces of software and the way they are licensed would describe their objections to it?
	Linux was created by one guy so I think he may not be a detractor. Steam however, has created a sort of monopoly and they make it hard on indie game developers by demanding too much in fees.
* How should we license our co-created work in the DSA class, and how do you wish to license your individual project, if at all?
	I don’t think we are creating anything we should be super protective of. Take MIT for example, do they share everything? I'd say we share ours as open source. If students don't want to share, maybe they can create a private repository. 
    I plan to have my code continue as open source unless I make a Nobel prize winning algorithm one day.
    
----------------------------- /@pswish --------------------------

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
