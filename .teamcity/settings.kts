import jetbrains.buildServer.configs.kotlin.v2019_2.version
import jetbrains.buildServer.configs.kotlin.v2019_2.project

version = "2021.1"

project {
    params {
        param("teamcity.ui.settings.readOnly", "true")  // disable changing project via UI
    }
    subProject(MainProject())
}
