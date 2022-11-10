To reproduce:

1. ./gradlew quarkusDev
2. initialize database called "test" on 3306 port

Run sequence of curl requests:

2. curl -d '{"name":"myBook"}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/book
3. curl -d '{"book_id":1}' -H "Content-Type: application/json" -X POST http://localhost:8080/api/page
4. curl -X GET http://localhost:8080/api/book/1