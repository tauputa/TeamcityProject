import jetbrains.buildServer.configs.kotlin.v2019_2.*
version = "2021.1"
version = "2021.1"

project {
    subProject(MainProject)
}

object MainProject : Project({    
    name = "Main"
    id(name.toId())
})
