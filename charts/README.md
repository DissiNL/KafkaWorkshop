helm repo add strimzi https://strimzi.io/charts/
kubectl create namespace kafka
kubectl create namespace petshop
helm install kafka-operator strimzi/strimzi-kafka-operator --namespace kafka --version 0.30.0 --set watchAnyNamespace=enabled
kubectl apply -f kafka.yaml --namespace petshop

https://strimzi.io/blog/2019/04/23/accessing-kafka-part-2/


./bin/kafka-console-producer.sh --topic temp --bootstrap-server kafka.cluster.dissi.me:32100

./bin/kafka-console-consumer.sh --topic temp --bootstrap-server kafka.cluster.dissi.me:32100 --from-beginning
