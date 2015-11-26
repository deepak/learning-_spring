## TODO

- could not use idRef in spring.xml ?
  how to write typesafe spring.xml ?
  are some error being caught by Intellij
  which spring will not catch ?
  any config to throw error on warning for parsing spring.xml ?
- usecase for local in spring.xml ?
- scope is determined by the individual bean scope
  eg. Person (scope: prototype) -> Address (scope: singleton by default)
  then person will not be the same, but person1.address equals person2.address
  can we enforce singleton or prototype,  based on the topmost bean ?
  eg. if person is of scope prototype then any referenced/linked beans are also
  of the same scope ?
  can we reflect of the scope of the returned object ?
  no - i guess as we that that object, not a wrapper
- take a look at other scopes. request, session, global, application
  http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-factory-scopes
- not sure where ApplicationContextAware would be useful
  maybe in legacy applictions ?
  where it needs some manual wiring and extra hand-holding
  https://spring.io/understanding/application-context
- order in which destroy hook is called is not deterministic

  triangle created
  Nov 20, 2015 5:44:36 PM org.springframework.context.support.FileSystemXmlApplicationContext doClose
  INFO: Closing org.springframework.context.support.FileSystemXmlApplicationContext@67424e82: startup date [Fri Nov 20 17:44:35 IST 2015]; root of context hierarchy
  Triangle{pointA=Point{x=0, y=0}, pointB=Point{x=0, y=11}, pointC=Point{x=0, y=-20}}
  triangle destroyed

  triangle created
  Triangle{pointA=Point{x=0, y=0}, pointB=Point{x=0, y=11}, pointC=Point{x=0, y=-20}}
  Nov 20, 2015 5:47:50 PM org.springframework.context.support.FileSystemXmlApplicationContext doClose
  INFO: Closing org.springframework.context.support.FileSystemXmlApplicationContext@67424e82: startup date [Fri Nov 20 17:47:49 IST 2015]; root of context hierarchy
  triangle destroyed

  triangle created
  Triangle{pointA=Point{x=0, y=0}, pointB=Point{x=0, y=11}, pointC=Point{x=0, y=-20}}
  triangle destroyed
  Nov 20, 2015 5:48:16 PM org.springframework.context.support.FileSystemXmlApplicationContext doClose
  INFO: Closing org.springframework.context.support.FileSystemXmlApplicationContext@67424e82: startup date [Fri Nov 20 17:48:16 IST 2015]; root of context hierarchy

- MessageSource has some rules
  - argument needs to be a string, cannot pass-in an object
    eg. cannot pass a Point and prnt it out
  - can we have maps as arguments ie. like keyword arguments ?
  - can we pass anything other than Object[], array of a different class, generics, list etc ?
  need to escape { with single quotes ie. '{'
  http://www.mscharhag.com/java/resource-bundle-single-quote-escaping
  what is the importance of MessageSource being a java resource bundle ?
  what is a good/widely-used lib for I18n in spring ?
  how to maintain common translations ?
  how to typecheck the translation keys. ie. rather than throwing a NoSuchMessageException at runtime
  compiler typechecks at compile time that message key does not exist ?
- if a class has multiple Listeners, how to stop the same event from being processed by the same class
  ie. BlacklistEvent < ApplicationEvent
  and if we have a listener for both
  then both will read the event.
- refactoring did not pick up config in spring xml

  bean config was:
  <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basenames">
      <list>
        <value>org.deepak.springdemo.resources.messages</value>
      </list>
    </property>
  </bean>

  refactored to move it under the namespace simple
  so changed to org.deepak.springdemo.di.resources.messages
- how/can we convert the whole of spring.xml, other than context
  into AppConfig using the @Configuration annotation
  read http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-autowired-annotation-qualifiers
- what is the unicode situation in property files, \u<code> worked for me
  http://stackoverflow.com/questions/4659929/how-to-use-utf-8-in-resource-properties-with-resourcebundle
- how to load config from ENV variable eg. in jdbc.properties
  for deploying on heroku. 12factor deployment
- how will sql-injection work in JdbcTemplate#query
  query: "select id, name, email, text from offers where id='1'; delete from offers where id='2'"
  returns an error
  class org.springframework.dao.DataIntegrityViolationException StatementCallback; Multiple ResultSets were returned by the query.;
  nested exception is org.postgresql.util.PSQLException: Multiple ResultSets were returned by the query.
- how to do "delete IN" ?
  delete from offers where id IN (2, 3)
