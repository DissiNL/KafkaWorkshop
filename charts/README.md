From:
https://strimzi.io/docs/operators/0.22.1/full/using.html#proc-config-kafka-str

helm repo add strimzi https://strimzi.io/charts/
kubectl create namespace kafka
helm install kafka-operator strimzi/strimzi-kafka-operator --namespace kafka --version 0.30.0 --set watchAnyNamespace=enabled

kubectl create namespace petshop
kubectl apply -f KafkaAdmin.yaml --namespace petshop
kubectl apply -f Kafka.yaml --namespace petshop
kubectl delete namespace petshop


https://strimzi.io/blog/2019/04/23/accessing-kafka-part-2/

`config.properties content`

```properties
security.protocol=SASL_PLAINTEXT
sasl.mechanism=SCRAM-SHA-512
sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required username="admin" password="<password from test>";
```

./bin/kafka-console-producer.sh --topic pets --bootstrap-server kafka.cluster.dissi.me:32100 --producer.config config.properties

./bin/kafka-console-consumer.sh --topic pets --bootstrap-server kafka.cluster.dissi.me:32100 --from-beginning --consumer.config config.properties --group admin-pets-group

kubectl delete namespace petshop

helm install petservice --namespace petshop .
