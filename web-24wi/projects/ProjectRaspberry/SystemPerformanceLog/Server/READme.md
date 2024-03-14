---
MacN server
---
The MacN server was designed with application security in mind.<br>
I wanted to try and create a server that would be able to sanitaize or guard against code injections.<br> Using the Express and then migrating the project to Flask framework and various middleware for enhanced security, data handling, and static file serving. It's designed to serve web pages, handle user registrations, logins, and contact form submissions with an emphasis on security and data integrity.

## How it works
1. Security First: Utilizing Helmet middleware, the server enforces strict Content Security Policies (CSP), XSS protection, and sniffing prevention, making it resilient against common web vulnerabilities.<br>

2. Data Handling: Through express.json and express.urlencoded, it adeptly processes JSON and URL-encoded payloads, ensuring smooth form submissions.<br>

3. Static File Serving: Serves static files from designated directories, providing a structured approach to resource management.<br>

4. Input Sanitization: Implements custom middleware for sanitizing incoming query and body parameters to prevent injection attacks.<br>

5. Password Handling: Uses bcryptjs for secure password hashing, ensuring user credentials are stored safely.<br>

6. Dynamic Resource Management: Dynamically handles user registrations and contact form submissions, storing data in CSV format after thorough validation and sanitization.<br>

## Strengths
- Enhanced Security: The use of Helmet and custom sanitization middleware significantly elevates the server's defense mechanisms against web-based attacks.<br>

- Modular Design: The clear separation of functionalities (user authentication, static file serving) facilitates easy maintenance and scalability.<br>

- Comprehensive Data Handling: By supporting both JSON and URL-encoded data, the server caters to a wide range of client-side interactions.<br>

## Areas for Improvement
- Database Integration: Transitioning from CSV files to a database system could enhance data management, scalability, and performance.
- Asynchronous Error Handling: Implementing more robust error handling for asynchronous operations could improve reliability and user experience.
- API Documentation: Developing comprehensive API documentation would aid developers in integrating with the server or extending its capabilities.




---
Index page
---

![MacNserver_2 24 (1)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/b0afdc18-ebb2-4b10-8bba-1336bab60928)
I plan to change the logo I wanted a logo that would once logged in would be used as the home button to return to the main menu page.<br>
The colors do not match.

---
Login page
---

![MacNserver_2 24 (2)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/bc73cd91-f735-45a7-8acd-6ef4bbb308b7)

I have the main menu accessible on the index page so that I don't have to log in each time.<br> 
I have logged in once!!! It was a challange to get the profile creation and then the user login features to function correctly...<br> 
I needed a break from stress. lol :P<br>
I was successful at creating a user session token that is used to authenticate and varify the user from the database.<br>


---
Main Menu or Landing page
---

![mainmenu](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/3c9fdbda-c4f7-450c-9893-69c2e09aaa31)


The symbols on the left of the image from the top are:<br>
House = the Home button<br>
Smiley Face = The About page<br>
Paper with lines = Link to GitHub profile<br>
Main Icon = Contact me page with link to my LinkdenIn profile<br>

The little raspberry-humanoid image is a link to my RaspberryHealth project.<br>
The calculator image is a link to a basic calculator...

---
About page
---

![MacNserver_2 24 (4)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/d4a598e8-78ff-4edb-beaa-d02130fac409)


---
Contact me page
---
![Screenshot (458)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/632d1585-6b89-4d3b-8233-8ea1b5f87468)




Currently (2.24.24) I care more about the function of everthing. The color scheme feels too bland,<br> 
and I'm not sure I like the verticle footer, maybe make it thinner... or not have a verticle footer... 


---
CPU Temperature
---

Building routes within the server to direct the frontend to the backend, so that the data can be rendered.<br>
Currently I have been able to render up-to the CPU Temperature button. (see screenshot).<br>
the data rendered is from the CSV of collected data, and the HeatMap is a graph renderd,<br> 
that currently does not clearly describe the CPU Temperature clearly.


![Screenshot (484)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/0fa500c6-98c2-46e6-8934-b5e88ce8d3d1)


---
Welcome to my Flask server
---
[![Watch the video](https://img.youtube.com/vi/Zty0E6f0Os0/maxresdefault.jpg)](https://youtu.be/Zty0E6f0Os0)


