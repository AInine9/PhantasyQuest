package hugu1026.com.github.phantasyquest.quest.event;

import org.bukkit.entity.Player;

public class EventExecuter extends Event {

    public EventExecuter(String eventID, Player player, String questFileName) {
        super(eventID, player, questFileName);
    }

    public EventExecuter(String eventID, Player player) {
        super(eventID, player);
    }

    @Override
    public void ExecuteEvent() {
        String event = getEvent();
        Event eventClass = null;

        switch (event) {
            case "giveItem":
                eventClass = new GiveItem(getEventID(), getPlayer());
                break;
            case "giveTag":
                eventClass = new GiveTag(getEventID(), getPlayer());
                break;
            case "startObjective":
                if (getQuestFileName() != null) {
                    eventClass = new StartObjective(getEventID(), getPlayer(), getQuestFileName());
                    break;
                }
                break;
        }
        if (eventClass != null) {
            eventClass.ExecuteEvent();
        }
    }
}
