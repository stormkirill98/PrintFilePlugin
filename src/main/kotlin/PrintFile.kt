import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.model.GradleProject
import java.io.File

class PrintFile : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val descriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor()
        val files = FileChooser(descriptor, e.project).choose(e.project)

        Notifier.info("Open ${files.size} files", e.project)

        if (files.isNullOrEmpty()) {
            Notifier.error("Cannot open file", e.project)
            return
        }

        val projectPath = File(e.project!!.basePath!!)
        val gradleConnection = GradleConnector
            .newConnector()
            .forProjectDirectory(projectPath)
            .connect()

        gradleConnection.use {
            val project = gradleConnection.getModel(GradleProject::class.java)
            project.tasks.forEach { println(it.name) }
        }
    }
}
