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

Torsten:
A generic type allows you to create classes, interfaces, and methods that operate on a type that is specified as a parameter when the code is used. Generics provide a way to create reusable code that can work with different types while ensuring type safety. 

A function call in programming refers to the execution of a function in a program. When you call a function, you're asking the program to execute a specific block of code that is encapsulated within that function. Here is a chat GPT example of a functio call:
ublic class RectangleAreaCalculator {

    // Function to calculate the area of a rectangle
    public static int calculateArea(int width, int height) {
        return width * height;
    }

    public static void main(String[] args) {
        int width = 5;
        int height = 10;

        // Function call to calculate the area of a rectangle
        int area = calculateArea(width, height);

        System.out.println("Area of the rectangle: " + area);
    }
}
In main, the function calculateArea() is called which runs teh code for that function. 

A method is a block of code that performs a specific task and is defined within a class. It's similar to a function in other programming languages. Methods are used to define the behavior of objects, perform operations, and facilitate code reusability.

A static method is a method that belongs to the class itself, rather than to instances of the class (objects). It's associated with the class and can be called directly using the class name without the need to create an instance of the class.

A function parameter (a parameter) is a variable that is listed in the method's declaration. It represents the data that a method expects to receive when it is called. Parameters allow you to pass values to a method so that the method can perform its task using these values.
Here is a GPT example:
public class MyClass {

    // Method that takes two integers as parameters and returns their sum
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        
        int num1 = 5;
        int num2 = 3;
        
        // Calling the add method and passing num1 and num2 as arguments
        int result = obj.add(num1, num2);
        
        System.out.println("Result: " + result); // Output: Result: 8
    }
}

Torsten:
A generic type allows you to create classes, interfaces, and methods that operate on a type that is specified as a parameter when the code is used. Generics provide a way to create reusable code that can work with different types while ensuring type safety. 

A function call in programming refers to the execution of a function in a program. When you call a function, you're asking the program to execute a specific block of code that is encapsulated within that function. Here is a chat GPT example of a functio call:
ublic class RectangleAreaCalculator {

    // Function to calculate the area of a rectangle
    public static int calculateArea(int width, int height) {
        return width * height;
    }

    public static void main(String[] args) {
        int width = 5;
        int height = 10;

        // Function call to calculate the area of a rectangle
        int area = calculateArea(width, height);

        System.out.println("Area of the rectangle: " + area);
    }
}
In main, the function calculateArea() is called which runs teh code for that function. 

A method is a block of code that performs a specific task and is defined within a class. It's similar to a function in other programming languages. Methods are used to define the behavior of objects, perform operations, and facilitate code reusability.

A static method is a method that belongs to the class itself, rather than to instances of the class (objects). It's associated with the class and can be called directly using the class name without the need to create an instance of the class.

A function parameter (a parameter) is a variable that is listed in the method's declaration. It represents the data that a method expects to receive when it is called. Parameters allow you to pass values to a method so that the method can perform its task using these values.
Here is a GPT example:
public class MyClass {

    // Method that takes two integers as parameters and returns their sum
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        MyClass obj = new MyClass();
        
        int num1 = 5;
        int num2 = 3;
        
        // Calling the add method and passing num1 and num2 as arguments
        int result = obj.add(num1, num2);
        
        System.out.println("Result: " + result); // Output: Result: 8
    }
}


**Faulkdf Week9**

Opensource vs closed source:
The software that I will contrast are two very similar pieces of software that I have experience using. One is G.I.M.P, the Gnu Image Manipulation Software (opensource) and Adobe Photoshop. 
This software is alike in the way that they are both image editing programs, and they both have a very similar slew of functions and features. They have a slightly different UI, and in my opinion, GIMP is harder to use. However GIMP is free, forever, and Photoshop costs $240 per year. 
I like both of these apps, because they are both relatively easy to use to edit images. I personally have more expeience with Photoshop, but the skills traslated to GIMP easily. 
Photoshop was relesed they way it is because it is a product. Adobe is a company that is making money. That is their goal. GIMP was relesed for the common good; as an image manipulation tool that is accessible for anyone with a computer. 
I really don't understand what this next bullet point is asking. 
I also don't really have opinions on what the license should be for DSA. I would maybe prefer the GitHub repo to be private, but I don't have very strong opinions on this. I am not intending to make money off of the code I submitted for this class. 


Uuuhhh... I'm slightly confused on if we (as students) are supposed to answer the town hall questions, or if those were from the town hall that WORMs hosted. However, the questions seem intereseting so I will answer them breifly:

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

**Jonah Eadie Week 9**

- A function signature defines the return and paramater types of a function.
- A function declaration adds a function with a given return type and paramter types to the namespace the declaration is made in.
- A return type is the type of the object or data returned by a method.
- An array index is a location in an array. Arrays are a segment of memory in which objects of a particularly type are arranged sequentually. An index of an array is the nth object of this type within this segment.
- A method is a function declared within a class namespace, often implicitly taking a pointer or reference to the class as an implied first paramaters.


Question: What does it mean for software to be licensed as open source or closed source?


Answer: Open source software is typically free (both "as in beer" and "as in freedom, as is often said in the open source software community), meaning that it is freely available and free of charge. Anyone can access and modify the source code of open source projects, those open source licenses differ on how those modifications may be distributed, most requiring crediting the original authors. Closed sourced software, on the other hand, does not freely distribute its source code or allow users to modify it. Most closed source projects are developed by corporations rather than volunteers, and often (though not always) users are required to buy the software before use.


Question: Compare and contrast your favorite open source software and your favorite closed source software.


Answer: My favorite open source software is the Emacs text editor, released by the GNU project, while my favorite closed source project is the VMWare virtualization suite. While the two applications are massively different in functionality, they each benefit from their respective licenses. Emacs is highly extensible and modifiable, and its features evolve according to community requests, oftentimes with the community supplying the code to meet these feature requests itself. There have been a large number of forks of the Emacs project, each fulfilling niche use case for different kinds of users. VMWare, on the other hand, does not feature much community involvement as it is a closed source project developed for profit. However, the profit motiviation in this case does come with the benefit that the application is designed to be incredibly simple and straightforward for users to use. While Emacs, as is typical with open source projects, emphasized extensibility and modifiability sometimes to the detriment of easy-use, VMWare tends to "just work" as it was designed to be accessible and to appeal to large number of customers.


Question: How are they alike and different?


Answer: EMACs and VMWare each have sizable user-bases who are committed to the products. They each do a good job in fulfilling their niches within their respective fields. They are different, however, in that the EMACs user community tends to be much more involved in the development of the project than VMWare's.


Question: Why do you like them?


Answer: I like EMACs because it gives users tremendous control over their text-editing. It's possible to use EMACs as an IDE, an email client, a web browser, a terminal emulator, et.c. VMWare is very effective at virtualizing a large host of operating systems.


Question: Why do you think it was released under its license?


Answer: EMACs was released under the GPL to align with the GNU foundation's commitment to free and libre software, allowing users to read, modify, and redistribute modifications to source code. VMWare is closed source as its creators intended to release it as a product for money.


Question: How do you think detractors of these pieces of software and the way they are licensed would describe their objections to it?


Answer: EMACs detractors would probably argue that its being an open source project often times leads the code base to emphasize features intended for super users to the neglect of ease-of-use for beginners, whereas VMWare detractors would argue that its being closed source prevents users from knowing fully how the software works, what it may be doing on their system, and prevents users from adding or modifying features to meet their use case.


Question: How should we license our co-created work in the DSA class, and how do you wish to license your individual project, if at all?


Answer: I think that we should license our projects under the GPL, so that they remain publicly accessible (if authors consent) and available to whoever may need to use the material. I'd like to license my project under the same license.


------------------David Dunston----------


What's enjoyable to me about computer science is I'm actually able to control my environment or be of some type of sorting an algorithmic data chaser that organizes and puts files where they need to be that's something that's psychologically just satisfying to me.

What would you like to do with a computer science, electronics engineering, and liberal arts education after graduating from Evergreen?

I would like to go on to help nonprofits organize their data and be a competitor in the global market where they would not normally be able to afford the infrastructure or the back end I would be I would like to be able to set it up for them in a philanthropist way. 

    What question, book, article, quote, movie about computation, electronics, and society is interesting to you at the moment?

It may not be completed related to computer science but a reoccurring version I have is of the 1970s version of the movie the Day the Earth stood still where technology was introduced into society and we quite literally attacked it because of the lack of understanding.

    What do you wish you had known before beginning your CS or electronics studies at Evergreen, that you can tell incoming students to Computer Science Foundations or Electronics and Music?

I wish I would've known that I was over complicating everything and that it wasn't that complicated when I was trying to understand it was more so the anxiety that I put on needing to understand everything all at once. So in shorter words to not be so hard on yourself.

    Name all faculty members that you know of, or can search in the course catalog or faculty directory, who practice and teach computation at Evergreen?

I can name Paul Richard and all the other faculties use computers to do their programs but it's not necessarily computer science faculty there more music science faculty and that would be Andrew Bockman been Grenada I don't know their last names unfortunately that would be a total of five but this is just my first quarter out here at Evergreen.

    What changes would you like to see in the computing or electronics culture (including academic computing support, electronic music programs, clubs, CS programs) at Evergreen if any?

I would like to see more language-based workshops and just computer science computer literacy technology assistance workshops in general. Possibly a field trip or more data scientist who work at the capital building to come and speak on their experience local computer science experts to come speak.

    What independent CS- or electronics- related projects or research would you like to pursue at Evergreen?

I would like to pursue a course cataloging infotainment map/app of some sorts that allow people to find their classes easily and I would also like to build an app that archived in cataloged my independent music catalog

    Student-led clubs like WORMS have done strong work in engaging with the CS community, having fun with computation, and helping them find employment in the tech industry. What ideas do you have to cultivate an even more welcoming atmosphere and environment for diversity newcomers entering the CS or electronics field? (I confess, I don't know much about women and underrepresented minorities in electronic music fields, but I suspect it is more diverse)

I would like to come up with like a welcome video that you could scan and be given a direct re-of resources even if no one is available to speak to you directly in the worms club you can be caught up on any events or resources that may have been spoken about.

    What program or CS / electronics material would you like to learn at Evergreen that we currently do not offer?



    What communication do you feel is missing, or could be strengthened, between Evergreen CS / electronics faculty and students?

As a new student I was overwhelmed by just being immersed in a community of belonging or better yet like I've understanding not to be so high strong on completing tasks but more of the experience of learning and being in an environment that fosters growth in mistakes as well as perfection.

Explaining to students that perfection would come after participation will highly be recommended to make a core value because I know a lot of students can feel left out sometimes going into week three and four hours things can seem very abstract still at that point.

    How can we better market or present our brand as Evergreen CS / electronics to prospective students in the PNW area?

Creating more front end user experiences and user interfaces that allow people to connect and interact remotely with the program would get people excited about the program definitely

    What other discipline would you like to see combined with CS / electronics in a multi-disciplinary future program?

I would definitely like to see a combination of hardware-based programming that could take a programmable logic controllers in various industry standard tools that are used every day to work on different hardware that we use software on.

    What challenges do you see facing the CS / electronics curriculum in the future at Evergreen? How should we meet it?

Communication is an ongoing challenge in every industry and disciplinarian and society is in a growing of knowledge and technology so to continue to meet that need is a continue to have open dialogue and a real open door policy and information and resources but truly fostering a community of belonging helps people feel comfortable sharing their opinions and thoughts that grow communication overall.

    If there were a dedicated space at Evergreen for CS / electronics projects and events, what features would you want from it?
    What questions do you have about CS / electronics classes in Winter quarter for faculty?

    Who in the tech field would you like to invite to teach at Evergreen? It can be an example or archetypal person, or someone with influential work that you've read about.

I would love to invite you on musk to Evergreen to speak to students and faculty but there are also some people who work in AutoCAD and unreal engine that I would love to have come down especially anyone who works at Nintendo

    What question related to CS / electronics education at Evergreen has not been asked on this survey that you would like to answer? (and please also include your answer)

I would ask a question what did you like most about computer science and the electronics education at Evergreen
And my answer would be that people were very passionate about their projects and where they see themselves in the future in the community. 
