# reactor-benchmark
This is an experiment to benchmark consumption of AMQP messages from RabbitMQ and publishing to a Reactor Event Bus. The intention is to asynchronously process AMQP messages. Reactor is used to 
 - Decouple processing from message consumption. 
 - Asynchronously process messages. 
Introducing a 30ms delay in the processing code it would take approximately 6.5 hours to process 6.5 million messages on a quad core Windows 7 machine with 8GB of RAM. It is yet to be run on a Linux node. 

The code also includes a test case to generate messages to a non durable queue in RabbitMQ. Java 8 parallel streams are used to split the collection and parallely invoke a Spring integration message gateway.  
