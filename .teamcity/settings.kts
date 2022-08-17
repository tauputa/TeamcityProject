import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven

//import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs     

version = "2021.1"
project {
    params {
        param("teamcity.ui.settings.readOnly", "true")
    }
    description = "Maven 3.6 java project forked from anewtodolist"
    buildType(NewList_Build)  
}

object NewList_Build : BuildType({ 
    id("Build_ID") 
    name = "Build_Name" 

    vcs {
        root(DslContext.settingsRoot) 
    }

    steps {
        maven { 
            goals = "clean test" 
            runnerArgs = "-Dmaven.test.failure.ignore=true" 
            mavenVersion = bundled_3_6() 
        }
    }
    requirements {
        contains("teamcity.agent.name", "linux")
    }
//    triggers {    
//        vcs {    
//        }
//    }
})
