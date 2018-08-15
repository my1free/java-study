
启动参数
>java -Xmx300m -Xmn100m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:+PrintSafepointStatistics  -XX:PrintSafepointStatisticsCount=1 -Xloggc:./logs/spring.boot.web.gc.log -jar target/spring-boot-web.jar

请求脚本
while true; do curl "http://localhost:8080/mem?size=20"; sleep 1; done;