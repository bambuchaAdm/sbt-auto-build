resolvers ++=
  Seq(Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns),
      Resolver.bintrayRepo("hmrc", "releases"))

addSbtPlugin("uk.gov.hmrc" % "sbt-utils" % "2.7.0")

addSbtPlugin("de.heikoseeberger" % "sbt-header" % "1.4.1")
