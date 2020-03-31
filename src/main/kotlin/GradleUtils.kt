import com.intellij.openapi.project.Project
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ProjectConnection
import org.gradle.tooling.model.GradleProject
import org.gradle.tooling.model.GradleTask
import java.io.File

fun getGradleConnection(project: Project?): ProjectConnection? {
    val projectPath = project?.basePath

    if (projectPath.isNullOrEmpty()) return null

    return try {
        GradleConnector
            .newConnector()
            .forProjectDirectory(File(projectPath))
            .connect()
    } catch (e: Exception) { null }
}
