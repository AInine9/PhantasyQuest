package hugu1026.com.github.phantasyquest.quest.event;

import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import org.bukkit.entity.Player;

import java.util.List;

public class StartObjective extends Event {

    public StartObjective(String eventID, Player player, String questFileName) {
        super(eventID, player, questFileName);
    }

    @Override
    public void ExecuteEvent() {
        String objectiveNumber = getArg(1);
        List<String> objectives = QuestYAMLReaderUtil.getObjectives(getQuestFileName());
        String objective = objectives.get(Integer.parseInt(objectiveNumber) - 1);

        PlayerQuestDataUtil.addObjective(getPlayer(), objective);
    }
}
