package hugu1026.com.github.phantasyquest.quest.objective;

import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ObjectiveProgressor {

    private String objectiveID = null;
    private String questFileName = null;
    private boolean available = false;
    private String[] args = null;
    private List<String> objectiveIDs = new ArrayList<>();

    public ObjectiveProgressor(Player player, String objectiveName) {
        for (String string : PlayerQuestDataUtil.getObjectives(player)) {
            if (string.contains(objectiveName)) {
                this.available = true;
                this.objectiveIDs.add(string);
            }
        }
    }

    public void objectiveIDFormat(String objectID) {
        this.available = true;
        this.questFileName = objectID.split(" ")[0];
        this.objectiveID = objectID.replace(questFileName + " ", "");
        this.args = objectiveID.split(" ");
    }

    public String getObjectiveID() {
        return this.objectiveID;
    }

    public List<String> getObjectiveIDs() {
        return this.objectiveIDs;
    }

    public String[] getArgs() {
        return this.args;
    }

    public boolean checkAvailable() {
        return available;
    }

    public String getQuestFileName() {
        return this.questFileName;
    }

    public String getArg(int number) {
        if (number > 0) {
            return this.args[number];
        } else {
            return null;
        }
    }
}
