### TO GENERATE JAR:

```shell
username@localhost:~$ ./gradlew :chapter7:bootJar
```

### TO RUN:

```shell
username@localhost:~$ java -jar chapter7/build/libs/bsg5-chapter7-1.0.0.jar
```

#### GET REQUEST'S

* http://localhost:8080/artist/1
* http://localhost:8080/artist/search/Clancy In Silt
* http://localhost:8080/artist/match/Th
* http://localhost:8080/artist/match/

#### POST REQUEST'S

* http://localhost:8080/artist
    * Body: { "name": "Threadbare Loaf" }