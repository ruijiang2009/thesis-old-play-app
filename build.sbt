name := "thesisapp"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJpa,
//  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),
  javaCore,
  cache,
  javaJdbc,
  "javax.persistence" % "persistence-api" % "1.0-rev-1"
)

// the following is to fix this problem based on this url
// http://stackoverflow.com/questions/20734540/nosuchmethoderror-in-javax-persistence-table-indexesljavax-persistence-index
//javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"),

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.28"

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "4.2.8.Final"

//libraryDependencies += "org.hibernate" % "hibernate-annotations" % "4.3.0-Final"

//libraryDependencies += "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final"

//libraryDependencies += "org.hibernate" % "hibernate-core" % "4.3.0.Final"


play.Project.playJavaSettings


// include bootstrap 3.0
//play.Keys.lessEntryPoints <<= baseDirectory { base =>
//  (base / "app" / "assets" / "stylesheets" / "bootstrap" * "bootstrap.less") +++
//    (base / "app" / "assets" / "stylesheets" / "bootstrap" * "responsive.less") +++
//    (base / "app" / "assets" / "stylesheets" * "*.less")
//}
