# Hostile

A hostile user interface for posting chats, inspired by the bloated, retro-ugly Windows 95 aesthetic and vibe. 
Brought to you by Linux users.

## Table of Contents

- [Hostile](#hostile)
- [Table of Contents](#table-of-contents)
- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Contact](#contact)
- [Frequently Asked Questions (FAQ)](#frequently-asked-questions-faq)
- [Tests](#tests)
- [Roadmap](#roadmap)

## Description
Hostile reimagines the social media experience, blending the iconic Windows 95 interface with modern web technologies. It serves as a bridge connecting the simplicity and charm of the past with today's digital capabilities. This platform pioneers a post and feed mechanism, utilizing an array of technologies such as Markdown, HTML, CSS, JavaScript, TypeScript, JSON, Tailwind CSS, React, and Prisma.

Upon accessing Hostile, users are greeted with a straightforward login page, guiding them to their personal profile space. Here, customization is key; users can update their username, password, and even opt to delete their account. Developing Hostile was an adventure in learning and innovation, especially our venture into React and integrating JSON data efficiently. One notable challenge was extracting specific elements from JSON requests using a map function in React, showcasing our tailored approach to data handling.

## Features
- A distinctive Windows 95 aesthetic for all pages.
- A user-friendly login interface.
- A dedicated posting page for sharing thoughts and updates.
- A dynamic post feed for browsing content from other users.

  ## Installation
- An external links page for easy navigation to other sites
Follow this tutorial:
- [Database Setup Tutorial](https://frontendmasters.com/courses/api-design-nodejs-v4/)


1. clone library
  - `` git clone https://github.com/TheEvergreenStateCollege/upper-division-cs.git ``
<p>&nbsp;   </p>

2. run 'npm i' in finalproject/api and finalproject/client
   
  - navigate to ``/web-24wi/projects/Hostile/finalproject/api`` and run npm i
  - navigate to ``/web-24wi/projects/Hostile/finalproject/client`` and run npm i and npm i react-modal
<p>&nbsp;   </p>
3. Create a .env in /web-24wi/projects/Hostile/finalproject/api and paste the lines below into it:

 - `` DATABASE_URL="postgresql://postgres:lol@localhost:5432/dev"``
 -  ``JWT_SECRET="cookies" ``

   
 <p>&nbsp;   </p>

 
4. migrate prisma
 -  **Note: Ensure you have a tunnel set up for your database for remote access before running this command**
 - `` npx prisma migrate dev --name init ``

<p>&nbsp;   </p>

5. Push your Database

 - ``npx prisma db push``

<p>&nbsp;   </p>
   


6. build and run
   - run this command in the /client/ directory to build the components to ../dist/ 
     - ``npm run build``
   - run this command in the /api/ directory
     - ``npm run dev``
<p>&nbsp;   </p>
## Usage

Hostile is intuitive to use, following the familiar conventions of social media platforms while offering a unique aesthetic experience.
1. Login:
 ![Pasted image 20240313132249](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/7bdf5e93-5795-4c34-ac8f-8358d92611be)
2. Post to your feed:
  ![Pasted image 20240313132104](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/9820201d-ca13-4c49-8590-ac7671395dc0)
3. Invert your colors!
   ![Pasted image 20240313132123](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/d1d0fdbe-59c0-487c-9b73-0c9cde5f2b0b)
4. View your feed:
   ![Pasted image 20240313131601](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/019ec40c-cbc5-4a26-9f15-b7f5bc59b1b6)
5. View your profile and all posts that you've made:
   ![Pasted image 20240313131628](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/a903d086-2448-4c47-b582-d7b3974800e7)
6. Modify your Account to change the username, password, or delete your account:
   ![Pasted image 20240313131825](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/d3245898-9796-474b-b702-aec2e11d1349)
7. explore other's projects:
   ![image](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/114445048/426bf6e6-5073-49c0-a647-26a3b4170ff1)




<p>&nbsp;   </p>


## Documentation

![Video of Hostile Usage and Functionality](./final-project.webm)
- ButtonPicker.tsx - Randomly selects two buttons to be displayed on the browser
- CatchAll.tsx - Under construction animation using CSS
- Post.tsx - Uses /api/makepost to post to the feed and to your profile history
- WindowButtons.tsx - creates the onMinimize, onMaximize, and onClose button's for the browser window
- Browser.tsx - main login page for sign-in and registering
- feed.tsx - adds the abyss with the feed of all user posts
- menu.tsx - links to external projects
- Profile.tsx adds the ability to change your username, password, and delete your account. It also loads all your previous code
- users.tsx - Fetches the user list from the /api/users
- Desktop.tsx - Inverts the colors with the bottom left button and allows for the desktop app transitions
- main.tsx - Renders the page

  <p>&nbsp;   </p>

### Wireframe
- Below is our initial wireframe created during the middle of our quarter
![wireframe.jpg](./wireframe/images/wireframe.jpg)
![dataSchema.jpg](./wireframe/images/dataSchema.jpg)

### Testing and unit tests
- Please refer to our amazing dev diary located [here](./dev_diary/tests.md)

  <p>&nbsp;   </p>

## Contributing

- We do not welcome contributions to Hostile! If you're interested please fire a signal flare and we'll get back to you.
  <p>&nbsp;   </p>

## License

- Our website is VERY hostile please do not alter it, it WILL fight back and we cannot control it.
- If you find ANY intuitive user design please report immediately to the head contributors
  
<p>&nbsp;   </p>

## Acknowledgements
Thank you to:
- Rain
- Dee Dee
- Ellie
- Paul Pham

<p>&nbsp;   </p>

## Contact
- Interested in contacting us? Reach out to either us or our mentor on discord!
- discord: ferralforrest
- discord: sn34k3rn3t
- discord: spockmeow

<p>&nbsp;   </p>

- mentor's discord: life_long_learner

<p>&nbsp;   </p>

## Roadmap
<p>
  In the upcoming phases of our React application development, we're planning to introduce a range of innovative and nostalgic features to enhance user experience and engagement. Firstly, we aim to incorporate the iconic dial-up audio over the classic blue screen of death when you attempt to exit the application. In a nod to operating systems of yesteryear, we will introduce a React alert system that emulates the familiar Windows 95 pop-up messages, blending old-school charm with modern functionality. To further emphasize the theme of technological excess, we plan to integrate fake advertisements that evoke the era of early internet bloatware, providing both humor and commentary on digital culture. The application's aesthetic will be boldly reimagined with an intentionally ugly background for posts, deliberately clashing with modern design norms to create a distinct and memorable visual experience. Lastly, we're looking to enhance our application's capabilities by adding the ability to store images directly in the database, significantly improving the way users share and interact with visual content. These updates are designed to offer a blend of nostalgia, innovation, and functionality, making our React application a unique and engaging platform for users.
</p>

<p>&nbsp;   </p>
