name := "thesisapp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJpa,
  javaCore,
  javaJdbc,
  "javax.persistence" % "persistence-api" % "1.0-rev-1"
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.28"

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "4.3.0.Final"

libraryDependencies += "org.hibernate" % "hibernate-annotations" % "3.5.6-Final"

libraryDependencies += "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final"

play.Project.playJavaSettings
