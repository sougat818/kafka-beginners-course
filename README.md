# Steps to Run the Tutorial Demo code

## Start docker

```bash
docker-compose up
```

## Start consumer

```bash
docker exec -ti kafka-1 bash
kafka-console-consumer --bootstrap-server 127.0.1:9092 --topic first_topic --group application
```

## Run ProducerDemo 

This will send "hello world" messages to kafka, which will appear in the console consumer screen

## Run ProducerDemoWithCallback

This will send 10 numbered "hello world" messages to kafka, Also prints detailed logs 

## Run ProducerDemoKeys

This will send 10 numbered "hello world" messages to kafka, But they will always go to the same partition for multiple runs of the program