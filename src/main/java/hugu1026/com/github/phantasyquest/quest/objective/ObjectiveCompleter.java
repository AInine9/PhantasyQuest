package hugu1026.com.github.phantasyquest.quest.objective;

import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import org.bukkit.entity.Player;

public class ObjectiveCompleter {

    private String objectiveID;
    private Player player;

    public ObjectiveCompleter(String objectiveID, Player player) {
        this.objectiveID = objectiveID;
        this.player = player;
    }

    public void completeObjective() {
        PlayerQuestDataUtil.deleteObjective(this.objectiveID, this.player);
    }
}
