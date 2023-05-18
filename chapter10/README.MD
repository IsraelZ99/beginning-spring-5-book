### TO RUN:

```shell
username@localhost:~$ ./gradlew :chapter10:tomcatStartWar
```

### REQUESTS
* http://localhost:8080/chapter10/login
  * Username: admin
  * Password: admin123
  * After login, you need to access http://localhost:8080/chapter10/dashboard
* http://localhost:8080/chapter10/home
* http://localhost:8080/chapter10/dashboard
  * If you aren't login, this will redirect to **/login**
