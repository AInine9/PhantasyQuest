package hugu1026.com.github.phantasyquest.quest.condition;

import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import org.bukkit.entity.Player;

public class HasTag extends Condition {
    public HasTag(String conditionID) {
        super(conditionID);
    }

    @Override
    public boolean checkMeetCondition(Player player) {
        String tag = getArg(1);

        return PlayerQuestDataUtil.getTags(player).contains(tag);
    }
}
