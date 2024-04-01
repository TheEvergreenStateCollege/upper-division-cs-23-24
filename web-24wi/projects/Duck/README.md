# Project Documentation
#### This is a readme file that specifically provides extra information about the project, how it start and how it goes

## Inspiration
- For this section, I admit I can't gave much information beyond some small thoughts since I only supplies as a backend developer for Annie's front end project, which should provide more information if you check briefly on her sub-folder in the same folder as this project, named ***annieproject***, provide's nothing change in general

- We are teammates that decide on a whim of moment since we knows each other well and have the same nationality, which is undeniably true and she was a great teammate for providing beautiful UI/UX front end code, both HTML/CSS/JS, but relates to the project, the only thing I know is that she likes rabbit, much more than dogs or cats, she even have one named Mopsie, which I don't know what that means but that's probably the main inspiration for her, to build a web site that sell rabbits food for easy access, I didn't object and take that as a challenge, hence this project's born

## Software Design Diagram
- I don't have a design diagram in mind, or maybe I do but I'm not the type of person that usually sketch things out, so here's a rough draft by the least base on our prisma schema

```mermaid
erDiagram
    USER ||--o{ CART : Create
    PRODUCTS }|--|{ CART : Gets_info
    PICTURE_CATEGORY }|..|{ CART : Getting_And_Providing_Category
    PICTURE_CATEGORY }|..|{ PRODUCTS : Getting_And_Providing_Category
    USER {
        id String
        createdAt DateTime
        username String
        password String
        wishlist Wishlist(Optional)
    }
    
    CART {
        id String
        createdAt DateTime
        updatedAt DateTime
        name String
        body String
        productId String
        belongsToProductId Product(In_relation_to_productId)
        belongsToId String
        belongsTo User(In_relation_to_cart_id)
        product Product[]
    }

    PRODUCTS {
        id String
        createAt DateTime
        image PICTURE_CATEGORY
        name String
        description String
        wishlist Wishlist[]
        productBind Wishlist(Optional)
    }

    PICTURE_CATEGORY {
        ID1 Food
        ID2 Utilities
        ID3 Pet_Care
        ID4 Habitat
    }
```

## How to run our demo on our included data
- If by chances you got the copy of this project with missing dependencies, which is very likely when you found out, then there's some step you need to run/check before using it, also this guide's only provides the circumstances that you already have an AWS EC2 server and have basic dependency like express or pnpm (which you can check them out through [here](https://github.com/TheEvergreenStateCollege/upper-division-cs/wiki/AWS-EC2-Setup)) and basic prisma installation (which can obtain through [here](https://hendrixer.github.io/API-design-v4/lessons/intro-to-prisma/db-setup), no need to check the model, just grab the `npx prisma` command and attach each of these prefix to the back of the command in the following order: `format`, `validate`, `generate`, `migrate dev --name init`)
    - Usually you would need to run `npm init` to create a `package.json`, however it's already provided at the code so you only need to run `npm install` to gather all the missing dependencies, this is the recommend way but if you want to make your life miserable then you can create your own
    - Once all of the dependencies has been gather, run the following code to create an ssh tunnel that connects your EC2 psql database to your local machine (this step is **important** to guranteed that you can post request to the database, unless you choose to run straight on the server, then this is not necessary):
    `autossh -M 30001 -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 -i ./id_ecdsa ubuntu@AkinaSS.arcology.builders` (you might need to substitute the arcology.builders domain to your own domain or if you keep it like that, remind me to open the server)
    - If you follows the prisma installation, there should already be a .env file on the folder, if not, create one and put these inside your .env file (this is also an **important** step to be able to post request to the database):
    `DATABASE_URL="postgresql://postgres:lol@localhost:5432/pg"
    JWT_SECRET="32acab03c6fec7604a1e0ecc2eb59d1d8f8fddc5c3eae282a887f46630af7cbd"`
    - there might be more setup/troubleshoot you might need to do but most of them are self-explanatory on the error stack trace and on top of my head, that should be everything, just run `pnpm run dev` and enjoy. Most of these relies on your connection to the prisma database so ensure it works out first

## How we built it (your tech stack)
- Most of the time, frontend work ressemble Annie's work and backend work ressemble mine, but there's some case when I need to modify her code to tailor it for bug fixing or creates a more streamline experience
```mermaid
graph TD
    A[Initial Planing] --> B[Project Start]
    B --> C(Frontend)
    B --> D(Backend)
    C --> E(Working on HTML/CSS/JS)
    D --> F(Working on Prisma/Express server/Postgres)
    E --> G(Constructing API call)
    F --> H(Construction route handler)
    G --> I(Assembling pieces together to make a working website)
    H --> I
    I --> J[Tailoring/Bug Fixing]
    J --> K[Finish product]
```

## Challenges we ran into
- It's mostly time constraint/consumming, procrastination and for my part, I need to brush my JS skills to connect what Annie's have to my code for it being able to work

## Accomplishments that we're proud of
- With that says, I got most of basic functionality working, including login/logout, user wishlist/cart system working. There're some more functionality I want to implement but with the limited time we have and I got burnt out for the last 10 weeks, I can't simply do so unless it got mentions again next semester, these include adding multiple product to 1 wishlist, having multiple wishlist for different styles, delete account, and the addition of a working product-adding based on a "suggestion" system

## What we learned
I learned a lot when I got a chance to participate in this project, including how to setup my own server, working database and using relational database, which is a new breath for me since I haven't got a chance to learn SQL in general, prisma helps me shorten the workload, I also learn more about using Docker by interaction

## What's next for the project next quarter (as an app engineered for the web)
Since next quarter we're going to learn AI/software construction, I can't say much on what's going to happen, but if there's any, I could imple an AI model to my web page as a help desk, I also want to learn how to import CSV data to Postgres database, which connects to my prisma for easy accessibility (Paul did shows us some [instruction](https://github.com/TheEvergreenStateCollege/upper-division-cs/wiki/Uploading-data-to-Docker) on this topic, thanks to him, as I mention)