# What is it?

twigMAK is a Washington winter twig identification website and tool that holds information on 30 common trees and shrubs found in the Washington area. 

It has a built-in ID system to help narrow down identifying twigs with definitions and images showing what to look for and multiple choice questions that then sort the twig cards by best matches. 

You can also look at all of the twigs on the twig page and click on a twig card to look at more info including, scientific name and common name, images of key identifying features, and description broken into three parts, twig bud and leaf scar.

# File tree
```
│   .env 
│   .gitignore
│   error.js
│   package-lock.json
│   package.json
│   tsconfig.json
│
├───.vscode
│   	launch.json
│
├───api-server
│   └───src
│   	│   db.ts
│   	│   index.ts
│   	│   router.ts
│   	│   server.ts
│   	│
│   	├───handlers
│   	│   	product.ts
│   	│   	update.ts
│   	│   	updatePoint.ts
│   	│   	user.ts
│   	│
│   	└───modules
│           	auth.ts
│           	middleware.ts
│
├───auth
│   │   login.html
│   │   profile.html
│   │   register.html
│   │
│   └───scripts
│       	API.js
│       	app.js
│       	Auth.js
│       	Router.js
│
├───js
│   	generalMake.js
│   	ID.js
│   	server.js
│   	twigCards.js
│   	twigInfo.js
│   	twigsData.js
│
├───pages
│   	about.html
│   	graph.html
│   	ID.html
│   	index.html
│   	info.html
│   	styles.css
│   	twigs.html
│
└───prisma
	│   schema.prisma
	│
	└───migrations
    	│   migration_lock.toml
    	│
    	├───20240529185154_init
    	│   	migration.sql
    	│
    	└───20240602212752_
            	migration.sql
```
## credits and resources 

### Photo credits
* Burke Herbarium Image Collection
* GBIF | Global Biodiversity Information Facility
* evergreen winter twig id class site
* L. H. Bailey Hortorium
* university of wisconsin Green Bay herbarium
* Naturally Curious with Mary Holland
* JAMES MADISON UNIVERSITY
* feral foraging
* crows path
### Resources
* Burke Herbarium Image Collection
* GBIF | Global Biodiversity Information Facility
* evergreen winter twig id class site
* Winter Twigs, Revised Edition: A Wintertime Key to Deciduous Trees and Shrubs of Northwestern Oregon and Western Washington by Helen Gilkey
