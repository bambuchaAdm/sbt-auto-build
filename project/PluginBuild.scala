/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import de.heikoseeberger.sbtheader.AutomateHeaderPlugin
import de.heikoseeberger.sbtheader.HeaderKey._
import sbt.Keys._
import sbt._
import uk.gov.hmrc.DefaultBuildSettings._
import uk.gov.hmrc.SbtBuildInfo
import uk.gov.hmrc.versioning.SbtGitVersioning

object PluginBuild extends Build {

  val pluginName = "sbt-auto-build"

  private val standardSettings: Seq[Setting[_]] =
    scalaSettings ++
      SbtBuildInfo() ++
      defaultSettings() ++
      PublishSettings() ++
      Resolvers() ++
      ArtefactDescription()

  lazy val root = (project in file("."))
    .enablePlugins(AutomateHeaderPlugin, SbtGitVersioning)
    .settings(standardSettings)
    .settings(
      name := pluginName,
      sbtPlugin := true,
      organization := "uk.gov.hmrc",
      scalaVersion := "2.10.5",
      targetJvm := "jvm-1.7",
      headers := HeaderSettings(),
      resolvers += Resolver.url("hmrc-sbt-plugin-releases", url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns),
      addSbtPlugin("de.heikoseeberger" % "sbt-header" % "1.8.0"),
      addSbtPlugin("uk.gov.hmrc" % "sbt-settings" % "3.7.0"),
      addSbtPlugin("com.typesafe.sbt" % "sbt-twirl" % "1.1.1"),
      libraryDependencies ++= Seq(
        "org.eclipse.jgit" % "org.eclipse.jgit.pgm" % "3.7.0.201502260915-r",
        "org.scalatest" %% "scalatest" % "2.2.6" % "test",
        "org.pegdown" % "pegdown" % "1.5.0" % "test"
      )
    )
}

object Resolvers {
  def apply() =
    resolvers := Seq(
      Opts.resolver.sonatypeReleases,
      Resolver.bintrayRepo("hmrc", "releases")
    )
}

object PublishSettings {
  def apply() = Seq(
    publishArtifact := true,
    publishArtifact in Test := false,
    publishArtifact in IntegrationTest := false,
    publishArtifact in(Test, packageDoc) := false,
    publishArtifact in(Test, packageSrc) := false,
    publishArtifact in(IntegrationTest, packageDoc) := false,
    publishArtifact in(IntegrationTest, packageSrc) := false
  )
}

object HeaderSettings {

  import de.heikoseeberger.sbtheader.license.Apache2_0
  import org.joda.time.DateTime

  def apply() = Map("scala" -> Apache2_0(DateTime.now().getYear.toString, "HM Revenue & Customs"))
}


object ArtefactDescription {

  def apply() =
    pomExtra := <url>https://www.gov.uk/government/organisations/hm-revenue-customs</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git@github.com:hmrc/sbt-auto-build.git</connection>
        <developerConnection>scm:git@github.com:hmrc/sbt-auto-build.git</developerConnection>
        <url>git@github.com:hmrc/sbt-auto-build.git</url>
      </scm>
      <developers>
        <developer>
          <id>charleskubicek</id>
          <name>Charles Kubicek</name>
          <url>http://www.equalexperts.com</url>
        </developer>
        <developer>
          <id>duncancrawford</id>
          <name>Duncan Crawford</name>
          <url>http://www.equalexperts.com</url>
        </developer>
      </developers>
}
