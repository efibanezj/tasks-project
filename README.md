# Tasks-project
Simple project to handle task. This project implements missing partof the challenge with the following considerations:

* Spring 2.5.4
* Java 11

This project implements two API's with the following considerations:
* Use flyway to the migration of database (with an initial data)
* Use cache notation to avoid executing twice when the task id is requested more than once with the same information.
* Use cachePut annotation to update the cache.
* Use transactional annotation to update the task.
* Use JUnit and Mockito to the unit test.
* Implemented internalization messages.




