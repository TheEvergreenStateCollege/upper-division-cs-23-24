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
- An external links page for easy navigation to other sites
Follow this tutorial:
- [Database Setup Tutorial](https://frontendmasters.com/courses/api-design-nodejs-v4/)

1. clone library
`` git clone https://github.com/TheEvergreenStateCollege/upper-division-cs.git ``

2. run 'npm i' in finalproject/api and finalproject/client
  - navigate to ``/web-24wi/projects/Hostile/finalproject/api`` and run npm i
  - navigate to ``/web-24wi/projects/Hostile/finalproject/client`` and run npm i and npm i react-modal

3. Create a .env in /web-24wi/projects/Hostile/finalproject/api and paste the text below into it
   ``
   DATABASE_URL="postgresql://postgres:lol@localhost:5432/dev"
   JWT_SECRET="cookies" 
  ``
4. migrate prisma
   Note: Ensure you have a tunnel set up for your database for remote access before running this command
  ``npx prisma migrate dev --name init``
5. Push your Database
   ``npx prisma db push``

6. build and run
   - run this command in the /client/ directory to build the components to ../dist/ 
   ``npm run build``
   - run this command in the /api/ directory
     ``npm run dev``

Provide step-by-step instructions on how to install and set up Hostile. Include any dependencies that need to be 
installed and how to install them.

## Usage

Hostile is intuitive to use, following the familiar conventions of social media platforms while offering a unique aesthetic experience.
1. Login:
2. Post to your feed:
3. View the feed:

## Documentation

Link to video

## Contributing


We welcome contributions to Hostile! If you're interested in improving the platform or adding new features, please check out our contribution guidelines for how to submit issues, feature requests, and pull requests.


If you want others to contribute to your project, provide guidelines on how they can do so. Include information about 
how to report issues, submit feature requests, and make pull requests.

## License

Hostile is distributed under MIT license, which outlines the terms and conditions for its use, reproduction, and distribution.

## Acknowledgements

If Hostile includes third-party libraries, tools, or resources, give credit to the respective authors or projects.

## Contact

Provide contact information for users and contributors to reach out to you or the project team.

## Frequently Asked Questions (FAQ)

Address common questions or issues that users might encounter.

## Tests
Include tests for app and provide examples of how to run them.

## Roadmap

Outline future plans and features for the project.
