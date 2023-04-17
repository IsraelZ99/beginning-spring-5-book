### TO RUN:

```shell
username@localhost:~$ ./gradlew :chapter6:tomcatStart
```

### ENDPOINTS TO TEST MVC WITH JSON

* http://localhost:8080/chapter6/greeting

### ENDPOINTS TO TEST MVC WITH JTWIG

#### GET

* http://localhost:8080/chapter6/greeting/Monse
* http://localhost:8080/chapter6/greetingMap/Israel/Monse
* http://localhost:8080/chapter6/greetingMV/Israel/Garcia
* http://localhost:8080/chapter6/artists/matching?prefix=Cami
* http://localhost:8080/chapter6/songs/matching?artist=Camilo+Septimo&prefix=M

#### GET WITH EXCEPTION
* http://localhost:8080/chapter6/artists/error/Camilo

#### POST
* http://localhost:8080/chapter6/songs/vote
  * { "artist": "Camilo Septimo", "name": "Maya" }