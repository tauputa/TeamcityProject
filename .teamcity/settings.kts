import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs     

version = "2021.1"
project {

    val bts = sequential {
        buildType(Maven("Build","clean compile","-Dmaven.test.failure.ignore=true","Build"))
	parallel{
        buildType(Maven("Unit","clean test","-Dmaven.test.failure.ignore=true -Dtest=*.unit.*Test","Unit"))
        buildType(Maven("Integration","clean test","-Dmaven.test.failure.ignore=true -Dtest=*.integration.*Test","Integration"))
	}
        buildType(Maven("Package","clean package","-Dmaven.test.failure.ignore=true -DskipTests","Package"))
    }.buildTypes()
    bts.forEach{buildType(it)}
    bts.Last().triggers{
        vcs {
            
        }
    }
}

class Maven(name:String,goals:String,runnerArgs:String?=null,ID: String): BuildType({
    //id("UnitTest")
    id(this.ID)
    this.name = name
    vcs {
        root(DslContext.settingsRoot)
    }
    steps {
        maven {
            this.goals = goals
            this.runnerArgs = runnerArgs
            mavenVersion = bundled_3_6()
        }
    }
    requirements {
        contains("teamcity.agent.name", "linux")
    }
})
