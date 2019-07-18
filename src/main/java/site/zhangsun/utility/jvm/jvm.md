### [JVM 监控工具](https://docs.oracle.com/javase/8/docs/technotes/tools/unix)
#### 1. jps 
```
jps -l 
8976 sun.tools.jps.Jps
20564 site.zhangsun.example.jtaatomikos.JtaAtomikosApplication 
20584 org.jetbrains.idea.maven.server.RemoteMavenServer
5944 org.jetbrains.jps.cmdline.Launcher
```
#### 2. jstat
#### 3. jmap
#### 4. jinfo
#### 5. jstack

#### 6. 开启Springboot内置Tomcat JMX

```
java -Djava.rmi.server.hostname=192.168.155.1 -Dcom.sun.management.jmxremote  -Dcom.sun.management.jmxremote.port=8061  -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar spring-boot-0.0.1-SNAPSHOT.jar
```
