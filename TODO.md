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
