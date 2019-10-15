# Steps to Run the Tutorial

## Start docker

```bash
docker-compose up
```

## Start consumer

```bash
docker exec -ti kafka-1 bash
kafka-console-consumer --bootstrap-server 127.0.1:9092 --topic first_topic --group application
```

## Run tutorial1 

This will send "hello world" messages to kafka, which will appear in the console consumer screen