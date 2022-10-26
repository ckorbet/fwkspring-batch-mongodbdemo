# fwkspring-batch-mongodbdemo

## Intro
Project to play with Spring Boot + Spring Batch + MongoDb

### Programming languaje(s)
* Java 11

### Fwks.
* Spring Boot 2.7.3
* Spring Batch 4.3.6
* Lombok 1.18.24

## _Containerized_ MongoDb database

### Please see:

* [mongo](https://hub.docker.com/_/mongo) _(hub.docker.com)_
* [Docker and MongoDB](https://www.mongodb.com/compatibility/docker) _(mongodb.com)_

### Docker command:

```
docker run -d --name mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo
```
