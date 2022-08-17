import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs     

version = "2021.1"
project {

    val bts = sequential {
        buildType(Maven("Build","clean compile","-Dmaven.test.failure.ignore=true"))
	parallel{
        buildType(Maven("Unit","clean test","-Dmaven.test.failure.ignore=true -Dtest=*.unit.*Test"))
        buildType(Maven("Integration","clean test","-Dmaven.test.failure.ignore=true -Dtest=*.integration.*Test"))
	}
        buildType(Maven("Package","clean package","-Dmaven.test.failure.ignore=true -DskipTests"))
    }.buildTypes()
    bts.forEach{buildType(it)}

    bts.last().triggers{  // add vcs trigger for last element in list - ie package
      vcs {
        }
    }

}

class Maven(name:String,goals:String,runnerArgs:String?=null): BuildType({
    //id(name)            // use the name as the unique build id
    id(name.toExtID())
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
