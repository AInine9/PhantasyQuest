package hugu1026.com.github.phantasyquest.quest.condition;

import org.bukkit.entity.Player;

public class HasPermission extends Condition {

    public HasPermission(String conditionID) {
        super(conditionID);
    }

    @Override
    public boolean checkMeetCondition(Player player) {
        String permission = getArg(1);

        return player.hasPermission(permission);
    }
}
