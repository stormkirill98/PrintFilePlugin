import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.fileChooser.ex.FileChooserDialogImpl
import com.intellij.openapi.project.Project
import javax.swing.Action
import javax.swing.JButton

class PrintFile : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val descriptor = FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor()
        val files = FileChooser.chooseFiles(descriptor, e.project, null)

        Notifier.info("Open ${files.size} files", e.project)

        if (files.isNullOrEmpty()) {
            Notifier.error("Cannot open file", e.project)
            return
        }
    }
}


class FileChooser(descriptor: FileChooserDescriptor, project: Project) : FileChooserDialogImpl(descriptor, project) {
    override fun createActions(): Array<Action> {
        return super.createActions()
    }

}