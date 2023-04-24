import org.gradle.api.tasks.wrapper.Wrapper.DistributionType

tasks {
    wrapper {
        distributionType = DistributionType.ALL
        version = "8.1"
    }
}
