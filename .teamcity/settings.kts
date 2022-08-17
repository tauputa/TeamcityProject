import jetbrains.buildServer.configs.kotlin.v2019_2.*
//import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
//import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs     

version = "2021.1"
project {
    params {
        param("teamcity.ui.settings.readOnly", "true")
    }
    description = "Maven 3.6 java project forked from anewtodolist"
//    buildType(NewList_Build)  
}


