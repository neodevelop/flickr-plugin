grails.project.dependency.resolution = {
    inherits("global") {
    }
    log "warn"
    plugins{
      runtime(':jquery:latest.integration')
      runtime(':jquery-ui:latest.integration')
    }
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
    }
    dependencies {
    }
}
