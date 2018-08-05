package hugu1026.com.github.phantasyquest.quest.objective;

import hugu1026.com.github.phantasyquest.quest.event.EventExecuter;
import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Objective {

    private String objectiveName, questFileName, objectiveID;
    private String[] args;
    private List<String> objectiveIDs = new ArrayList<>();
    private Player player;

    Objective(String objectiveName) {
        registerListener();
        this.objectiveName = objectiveName;
    }

    //register bukkit lister here.
    public abstract void registerListener();

    void proceedObjective(String originalObjectiveID, String editedObjectiveID, Player player) {
        PlayerQuestDataUtil.replaceObjective(originalObjectiveID, editedObjectiveID, player);
    }

    void completeObjective(String objectiveID, String questFileName, Player player) {
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

    //please call this method in the event handler
    void setArguments(Player player) {
        this.setPlayer(player);
        this.setObjectiveIDs();
    }

    private void setObjectiveIDs() {
        for (String objectiveID : PlayerQuestDataUtil.getObjectives(player)) {
            if (objectiveID.contains(objectiveName)) {
                objectiveIDs.add(objectiveID);
            }
        }
    }

    List<String> getObjectiveIDs() {
        return this.objectiveIDs;
    }

    void formatObjectiveID(String objectiveID) {
        this.questFileName = objectiveID.split(" ")[0];
        this.objectiveID = objectiveID.replace(questFileName + " ", "");
        this.args = objectiveID.replace(questFileName + " ", "").split(" ");
    }

    String getArg(int number) {
        if (number > 0) {
            return this.args[number];
        } else {
            return null;
        }
    }

    public String getObjectiveID() {
        return this.objectiveID;
    }

    public String getObjectiveName() {
        return this.objectiveName;
    }

    public String getQuestFileName() {
        return this.questFileName;
    }

    public String[] getArgs() {
        return this.args;
    }

    public Player getPlayer() {
        return this.player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }
}
