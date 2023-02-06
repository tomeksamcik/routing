# Read Me First
This project has been implemented as a test assignment

* It implements a tree search algorithm, please note that it finds the first shortest route in terms of hops between countries. If there's more then one route, it will select the first it finds based on the order of countries in the input file.
* Project compiles with Java17, but uses Java11 api as that's the version I have been using recently

# Getting Started

### Building & testing
```
mvn clean test
```

### Running
```
mvn spring-boot:run
```

### Example calls

Route found:
```
curl -v localhost:8080/routing/POL/ITA
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /routing/POL/ITA HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.79.1
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 06 Feb 2023 08:49:26 GMT
< 
* Connection #0 to host localhost left intact
{"route":["POL","CZE","AUT","ITA"]}
```
Route not found:

```
curl -v localhost:8080/routing/POL/IRL
*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /routing/POL/IRL HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.79.1
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 400 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 06 Feb 2023 08:50:27 GMT
< Connection: close
< 
* Closing connection 0
{"error":"Route POL - IRL has not been found"}
```

### Further reading

* [Breadth-first search (BFS)](https://en.wikipedia.org/wiki/Breadth-first_search)

