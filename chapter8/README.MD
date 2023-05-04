### TO RUN:

* Click on play button that are in the JdbcConfiguration.class (Main class)

#### GET REQUEST'S

* http://localhost:8080/artists/1
* http://localhost:8080/artists/search/Clancy In Silt
* http://localhost:8080/artists/match/Th
* http://localhost:8080/artists/match/
* http://localhost:8080/artists/Threadbare Loaf/song/What Happened to Our First CD
* http://localhost:8080/artists/Threadbare Loaf/vote/What Happened to Our First CD
* http://localhost:8080/artists/Threadbare Loaf/songs
* http://localhost:8080/artists/Threadbare Loaf/match/W

#### POST REQUEST'S

* http://localhost:8080/artist
    * Body: { "name": "Threadbare Loaf" }
    * Body: { "name": "Therapy Zeppelin" }
    * Body: { "name": "Clancy In Silt" }