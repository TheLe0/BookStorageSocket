
## INTRODUCTION ##

Build an application clint-server that comunicates with each other by a TCP Socket, is
like a REST API, but building everything without framework ore broker.

## SPECIFICATIONS ##

The client will only get from the user the operations and parameters to do the call. And
the server will receive, do all the necessaries
business rules and send to the database and send to the client the response.

## REQUIREMENTS ##

```
PostgreSQL
```

## INSTALLATION ##

On the postgreSQL terminal(ore in a postgre client like pgAdmin) run:

```
psql -f create_database.sql
psql -f create_tables.sql
psql -f insert_livros.sql
psql -f insert_autor.sql
psql -f insert_edicao.sql
psql -f insert_livroautor.sql
psql -f insert_livrostemp.sql
```

## ENDPOINTS ##

- Create a book, return a boolean
- Find a book by name(complete and with like)
- List an author's books(complete and with like)
- List books per year and edition number 
- Remove a book by the name(complete), return a boolean
- Edit a book by name(complete), edition and year, return a boolean 

#### Note ####
* If the list/find doesn't find anything, return a error message
