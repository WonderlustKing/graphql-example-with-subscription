# graphql-example-with-subscription

A GraphQL example using:
```
 -Graphql-java library 
 -Spring Boot Framework
 -H2 db
 -Hybernate framework
 -RxJava 2
 ```
This is a basic GraphQL example which demonstrate a book store functionality.
More specific there are three entities:

```
1) Author:
 -id::Long
 -firstName::String
 -lastName::String

2) Book:
-id::Long
-title::String
-isbn::String
-pageCount::Integer
-price:: Integer
-author::Author

3) Stock:
-id::Long
-numberOfStocks:: int
-price:: int
-book::Book
```

For the above entities the following GraphQL operations can be applyied:
```
1) Query:
 -findAllAuthors: [Author]!
 -countAuthors: Long!
 -findAllBooks: [Book]!
 -countBooks: Long!
 -findAllStocks: [Stock]!
 -findAllStocks: [Stock]!
 
2) Mutation:
 -addAuth(firstName: String!, lastName: String!): Author
 -addBook(title: String!, isbn: String!, pageCount: Int, price: Int!, author: ID!): Book!
 -updateBookPageCount(id: ID!, pageCount: Int!): Book!
 -deleteBook(id: ID!): Boolean
 -addStock(numberOfStocks: Int!, price: Int!, book: ID!): Stock!
 
3)Subscription:
 -stockQuotes: Stock!
 ```

To build the example code type:
```
./gradlew build
```

To run the example code type:
```
./gradlew bootRun
```

To execute a GraphQL query navigate to:
```
localhost:8080/graphiql
```

<b>Query example</b>:

To find all books, run the following command in the graphiql(localhost:8080/graphiql) editor:
```
{
  findAllBooks {
    id,
    title,
    isbn,
    price,
    pageCount
    author {
      firstName,
      lastName
    }
 }
}
```

<b>Mutation example</b>:

To create a book, run the following command in the graphiql editor:
```
mutation {
  addBook(
    title: "Java: The Complete Reference, Tenth Edition",
    isbn: "1259589331",
    price: 26,
    author: 1) {
      id, title
  }
}
```

<b>Subscription example</b>:

To listen for stock updates, first you have to run the following Subscription command in the graphiql editor:
```
subscription {
  stockQuotes {
    id,
    numberOfStocks,
    price,
    book {
     title 
    }
  }
}
```

Then, from another graphiql editor, create a new stock entity for an existing book with the following command:

```
mutation {
  addStock(numberOfStocks: 10, price: 102, book: 4) {
    id, price, book {
      title
    }
  }
}
```

Go back to the graphiql editor with the subcription and check the response field, you should see the 
stock entity that you created in the previous step
