## Telnet-like serever in Java

This was an interview assignment for one well-known tech company.
The task was to to write a simple Java server application that will support "telnet"-like connections.
The server must support multiple concurrent connections and it will have to respond to very basic commands like "ls", "cd", "mkdir", "pwd".

To run the server use the following command

```
mvn exec:java -Dexec.mainClass="com.scansafe.test.ScansafeServer" -Dexec.args="8088 10"
```
