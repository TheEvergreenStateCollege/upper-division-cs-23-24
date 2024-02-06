Assigment partner: Cassidy

Questions for Deedee
1. What the heck is "intlinks"?
2. What is "int li and "ext li"?
3. Third Q
4. Fourth Q
5. Fifth Q

type User {
    
    id: ID!
    username: String!
    email: String!
    password: String!
    createdAt: DateTime!
    updatedAt: DateTime!

}

type Product {

    id: ID!
    name: String!
    description: String!
    price: Float!
    image: String
    createdAt: DateTime!
    updatedAt: DateTime!
}

type Order {

    id: ID!
    user: User!
    products: [Product!]!
    total: Float!
    status: String!
    createdAt: DateTime!
    updatedAt: DateTime!
}

type Query {
  `# Define your queries here`
}

type Mutation {
  `# Define your mutations here`
}


USER TABLE 

| id | username |
|-----------------|-----------------|
| 1 | Row 1, Column 2 |
| 2 | Row 2, Column 2 |
| 3 | Row 3, Column 2 |

PRODUCT TABLE

| id | name | belongsToUserId |
|-----------------|-----------------|-----------------|
| 1 | Row 1, Column 2 | Row 1, Column 3 |
| 2 | Row 2, Column 2 | Row 2, Column 3 |
| 3 | Row 3, Column 2 | Row 3, Column 3 |


UPDATE TABLE

| id | updatedAt | title | body | status | productId |
|-----------------|-----------------|-----------------|-----------------|-----------------|-----------------|
| 1 | Row 1, Column 2 | Row 1, Column 3 | Row 1, Column 4 | Row 3, Column 3 | Row 3, Column 4 |
| 2 | Row 2, Column 2 | Row 2, Column 3 | Row 2, Column 4 | Row 3, Column 3 | Row 3, Column 4 |
| 3 | Row 3, Column 2 | Row 3, Column 3 | Row 3, Column 4 | Row 3, Column 3 | Row 3, Column 4 |
