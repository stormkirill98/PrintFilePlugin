import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.fileChooser.ex.FileChooserDialogImpl
import com.intellij.openapi.project.Project
import javax.swing.Action

class FileChooser(descriptor: FileChooserDescriptor, project: Project?) : FileChooserDialogImpl(descriptor, project) {
    override fun createActions(): Array<Action> {
        setOKButtonText("Run")

        return if (helpAction === myHelpAction && helpId == null)
            arrayOf(okAction)
        else arrayOf(okAction, helpAction)
    }

    override fun createActionGroup(): DefaultActionGroup {
        val actionGroup = super.createActionGroup()
        val actionManager = ActionManager.getInstance()

        actionGroup.apply {
            remove(actionManager.getAction("FileChooser.NewFile"))
            remove(actionManager.getAction("FileChooser.NewFolder"))
            remove(actionManager.getAction("FileChooser.Delete"))
        }

        return actionGroup
    }
}