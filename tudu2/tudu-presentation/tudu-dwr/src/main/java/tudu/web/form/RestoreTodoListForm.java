package tudu.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

public class RestoreTodoListForm extends ActionForm {

    private static final long serialVersionUID = -5691132857568831408L;

    private String listId;

    private FormFile backupFile;

    private String restoreChoice;

    /**
     * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        ActionErrors errors = new ActionErrors();
        if (this.listId == null || this.listId.equals("")) {
            ActionMessage message = new ActionMessage("errors.required",
                    "Todo List ID");

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        }
        if (this.backupFile == null || this.backupFile.equals("")
                || this.backupFile.getFileName() == null
                || this.backupFile.getFileName().equals("")) {

            ActionMessage message = new ActionMessage("errors.required",
                    "Backed up file");

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        }
        if (this.restoreChoice == null || this.restoreChoice.equals("")) {
            ActionMessage message = new ActionMessage("errors.required",
                    "An action");

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        } else if (!this.restoreChoice.equals("create")
                && !this.restoreChoice.equals("replace")
                && !this.restoreChoice.equals("merge")) {

            ActionMessage message = new ActionMessage("errors.invalid",
                    "The selected action");

            errors.add(ActionMessages.GLOBAL_MESSAGE, message);
        }
        return errors;
    }

    public FormFile getBackupFile() {
        return backupFile;
    }

    public void setBackupFile(FormFile backupFile) {
        this.backupFile = backupFile;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getRestoreChoice() {
        return restoreChoice;
    }

    public void setRestoreChoice(String restoreChoice) {
        this.restoreChoice = restoreChoice;
    }
}
