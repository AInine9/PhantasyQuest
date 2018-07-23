package hugu1026.com.github.phantasyquest.quest.event;

import hugu1026.com.github.phantasyquest.util.PlayerQuestDataUtil;
import org.bukkit.entity.Player;

public class GiveTag extends Event {
    public GiveTag(String eventID, Player player) {
        super(eventID, player);
    }

    @Override
    public void ExecuteEvent() {
        Player player = getPlayer();
        String tag = getArg(1);

        PlayerQuestDataUtil.addTags(player, tag);
    }
}
