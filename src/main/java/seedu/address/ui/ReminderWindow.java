package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.company.Company;

/**
 * Represents a window for displaying reminders about application deadlines.
 */
public class ReminderWindow extends UiPart<Stage> {
    private static final String REMINDER_MESSAGE =
            "Reminder!!! These applications are closing soon! Apply now or else you'll regret!";
    private static final String NO_REMINDER =
            "Good Job! You have no applications closing soon!";

    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String FXML = "ReminderWindow.fxml";
    private Logic logic;
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    @FXML
    private Label reminderMessage;
    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a new ReminderWindow
     *
     * @param root Stage to use as the root of the ReminderWindow.
     */
    public ReminderWindow(Stage root, Logic logic) {
        super(FXML, root);
        this.logic = logic;
    }

    /**
     * Creates a new ReminderWindow
     */
    public ReminderWindow(Logic logic) {
        this(new Stage(), logic);
    }

    void fillInnerParts() {
        ObservableList<Company> filteredList = logic.getFilteredCompaniesRemindersList();
        personListPanel = new PersonListPanel(filteredList);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();

        if (filteredList.isEmpty()) {
            resultDisplay.setFeedbackToUser(NO_REMINDER);
        } else {
            resultDisplay.setFeedbackToUser(REMINDER_MESSAGE);
        }

        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    /**
     * Shows the reminder window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage
     *         </li>
     *         <li>
     *             if (@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing reminder page about the applications.");
        if (logic.getReminderStatus()) {
            getRoot().show();
            getRoot().centerOnScreen();
        }
    }

    /**
     * Returns true if the reminder window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the reminder window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the reminder window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
