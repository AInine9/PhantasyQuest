package hugu1026.com.github.phantasyquest.quest.objective;

import hugu1026.com.github.phantasyquest.quest.event.EventExecuter;
import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import org.bukkit.entity.Player;

public class ObjectiveCompleter {

    private String objectiveID, questFileName;
    private Player player;

    public ObjectiveCompleter(String objectiveID, String questFileName, Player player) {
        this.objectiveID = objectiveID;
        this.questFileName = questFileName;
        this.player = player;
    }

    public void completeObjective() {
        PlayerQuestDataUtil.deleteObjective(questFileName + " " + this.objectiveID, this.player);

        if (objectiveID.contains("events:")) {
            //exist events to execute after player completes the objective.
            int beginIndex = objectiveID.indexOf("events:");
            int endIndex = objectiveID.length();
            String[] eventNumbers = objectiveID.substring(beginIndex, endIndex).replace("events:", "").split(", ");

            for (String eventNumber : eventNumbers) {
                String eventID = QuestYAMLReaderUtil.getEvents(questFileName).get(Integer.parseInt(eventNumber) - 1);

                EventExecuter executer = new EventExecuter(eventID, player, questFileName);
                executer.ExecuteEvent();
            }
        }
    }
}
