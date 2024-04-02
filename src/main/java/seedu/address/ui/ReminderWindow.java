package seedu.address.ui;

import java.awt.*;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import javafx.scene.control.Label;

public class ReminderWindow extends UiPart<Stage> {
    public static final String REMINDER_MESSAGE =
            "Reminder!!! These applications are closing soon! Apply now or else you'll regret!";

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
        reminderMessage.setText(REMINDER_MESSAGE);
    }

    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredCompaniesRemindersList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

//        resultDisplay = new ResultDisplay();
//        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());
    }

    /**
     * Creates a new ReminderWindow
     */
    public ReminderWindow(Logic logic) {
        this(new Stage(), logic);
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
        getRoot().show();
        getRoot().centerOnScreen();
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
