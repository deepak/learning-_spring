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
