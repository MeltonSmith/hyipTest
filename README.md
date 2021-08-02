## HYIP test project

### Short desc

A simple spring-boot project which allows users to register, account approval is done by a separate service through a kafka broker.
Separate service to work with: [Kafka-Role-Assigner](https://github.com/NextOne0537/Kafka-Role-assigner)

### User Roles available:
- `REGISTERED` - a row in a table exists, but not yet sent to a Kafka broker.
- `SENT` - row in a table + message in a kafka
- `APPROVED` - user was approved by a separate service
- `REJECTED`- user was rejected by a separate service.

### Registration "workflow":
- Once you've registered an insert operation is done into a table.
- A `@Scheduled` method in UserApprovalService is getting launched every 60 seconds for those users, which have "REGISTERED" status. This method sends messages in @Async
- UserApprovalListener annotated with `@KafkaListener` waits for messages from a [Role-assigner service](https://github.com/NextOne0537/Kafka-Role-assigner) in a specific topic.
- As soon as the message is received the current role is updated in the same `@Transaction` in which the message arrives. This allowes us not to commit the wrong offest if our DB write operation is failed.



**:warning: Use `kafka` profile and finetune `application-kafka.yml` if you have kafka available.**

