#  Goszakupki Monitoring System

This repository contains the source code for the server module of Goszakupki Monitoring System.

The module monitors information about tenders of government procurement of Belarus and other countries using parsing of the official [web-site][Government procurement site link] of Government procurement in Belarus. 
And depending on users' preferences informs them about trends of their interest with preferable for them frequency.
The information can be represented as RSS-feed or e-mail message, for both of formats templating is used. 

## Acknowledgements

The list of used libraries is shown below:

* [Spring Framework][Spring Framework link]
* [Spring Security][Spring Security link]
* [Jersrsey][Jersrsey link]
* [Hibernate][Hibernate link]
* [Jackson][Jackson link]
* [Log4J][Log4J link]
* [Quartz][Quartz link]
* [jsoup][jsoup link]
* [Velocity][Velocity link]
* [Rome][Rome link]

As a servelt container [Apache Tomcat][Apache Tomcat link] was used.

## Building

You will need JDK 8+ and Apache Tomcat 8+ installed.

In oкder to download all dependencies use the following Maven command:

```
$ mvn clean install
```

## License

The source code is published under [GPLv2][GPLv2 link] license.

  [Government procurement site link]: https://www.icetrade.by
   
  [Apache Tomcat link]: https://tomcat.apache.org/
  
  [GPLv2 link]: https://www.gnu.org/licenses/gpl-2.0.html

  [Spring Framework link]: https://spring.io/
  [Spring Security link]: http://projects.spring.io/spring-security/
  [Jersrsey link]: https://jersey.java.net/
  [Hibernate link]: http://hibernate.org/
  [Jackson link]: https://github.com/FasterXML/jackson
  [Log4J link]: https://logging.apache.org/log4j/2.x/
  [Quartz link]: https://quartz-scheduler.org/
  [jsoup link]: http://jsoup.org/
  [Velocity link]: http://velocity.apache.org/
  [Rome link]: http://rometools.github.io/rome/




