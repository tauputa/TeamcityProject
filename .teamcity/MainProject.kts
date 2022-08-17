import jetbrains.buildServer.configs.kotlin.v2019_2.*

class MainProject() : Project() {
    init {
        name = "Main"
        id(name.toId())
    }
}
