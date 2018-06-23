package hugu1026.com.github.phantasyquest.quest.condition;

import org.bukkit.entity.Player;

public class ConditionChecker extends Condition {

    public ConditionChecker(String conditionID) {
        super(conditionID);
    }

    @Override
    public boolean checkMeetCondition(Player player) {
        String condition = getCondition();
        Condition conditionClass = null;

        switch (condition) {
            case "hasItem":
                conditionClass = new HasItem(getConditionID());
        }
        if (conditionClass != null) {
            return conditionClass.checkMeetCondition(player);
        }
        return false;
    }
}
