### RUN FROM COMMAND LINE

```shell
username@localhost:~$ ./gradlew :chapter10jpa:bootRun
```

### REQUEST THROW CURL

#### POST
```shell
username@localhost:~$ curl \
  -u user:user123 \
  --header "Content-Type: application/json" --request POST \
  --data '{"name":"Someone Stole The Flour","artist":{"name":"Threadbare Loaf"}}' http://localhost:8080/songs
```

#### GET
```shell
username@localhost:~$ curl \
 -u user:user123 \
 --header "Content-Type: application/json" http://localhost:8080/songs/2
```

#### GET FORBIDDEN
```shell
username@localhost:~$ curl \
 -u admin:admin123 \
 --header "Content-Type: application/json" http://localhost:8080/songs/2
```