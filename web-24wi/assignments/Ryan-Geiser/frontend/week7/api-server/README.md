# Setup Instructions

For our in-class activity, you're going to implement an API server in your own assignments directory
following the [Frontend Masters Course on NodeJS API Design](https://frontendmasters.com/courses/api-design-nodejs-v4/).

## Database Setup

First, you'll install Docker and postgres using the [AWS Setup Week 4](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/web-24wi/docs/AWS-EC2-Setup.md#week-3).
Then you'll start the Docker container

```
$ docker ps
CONTAINER ID   IMAGE         COMMAND                  CREATED         STATUS         PORTS                                       NAMES
6bde315849e9   postgres:14   "docker-entrypoint.s…"   3 seconds ago   Up 2 seconds   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp   pg
```

## Prisma Setup

Instructions after running

```
npx prisma init
```

✔ Your Prisma schema was created at prisma/schema.prisma
  You can now open it in your favorite editor.

Next steps:
1. Set the DATABASE_URL in the .env file to point to your existing database. If your database has no tables yet, read https://pris.ly/d/getting-started
2. Set the provider of the datasource block in schema.prisma to match your database: postgresql, mysql, sqlite, sqlserver, mongodb or cockroachdb.
3. Run npx prisma db pull to turn your database schema into a Prisma schema.
4. Run npx prisma generate to generate the Prisma Client. You can then start querying your database.

More information in our documentation:
https://pris.ly/d/getting-started

## Prisma Schema

In the file `prisma/schema.prisma` we will begin entering the schema from
[the Product Changelog example](https://hendrixer.github.io/API-design-v4/lessons/data-modeling/creating-models).

into the file created at `prisma/schema.prisma` in the previous section.
Enter it below the auto-generated config.

