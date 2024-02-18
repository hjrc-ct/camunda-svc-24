# camunda-svc-24

Platform Snapshot

`Camunda 7.20.0`
> 
`Open JDK 21`
>
`Spring Boot 3.2.2`
>
`Spring Security 6.2.2`
>
`Apache Maven 3.9.6`
>      

## Compile and Run

Use IntelliJ IDEA or your favorite dev tool.

Open terminal window and run below commands.

`
$ mvn clean install
`
> 
`
$ mvn spring-boot:run 
`
>
> 
Note: Make sure to edit mail properties in file <code>mail-config.properties</code> under <code>resources</code> folder.
>
## Validate
>
Open browser to login to the Camunda 7 platform. Use the default credentials as mentioned in the <code>application.yaml</code>.
> (http://localhost:8080/camunda-welcome/index.html)

## References
Camunda 7 mail connector by Community Hub -> https://github.com/camunda-community-hub/camunda-platform-7-mail/tree/main

