package hugu1026.com.github.phantasyquest.quest.objective;

import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import org.bukkit.entity.Player;

public class ObjectiveProgressor {

    private String objectiveID = null;
    private boolean available = false;
    private String[] args = null;

    public ObjectiveProgressor(Player player, String objectiveName) {
        for (String string : PlayerQuestDataUtil.getObjectives(player)) {
            if (string.contains(objectiveName)) {
                this.available = true;
                this.objectiveID = string;
                this.args = objectiveID.split(" ");
            }
        }
    }

    public String getObjectiveID() {
        return this.objectiveID;
    }

    public String[] getArgs() {
        return this.args;
    }

    public boolean checkAvailable() {
        return available;
    }

    public String getArg(int number) {
        if (number > 0) {
            return this.args[number];
        } else {
            return null;
        }
    }
}
