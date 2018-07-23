package hugu1026.com.github.phantasyquest.quest.event;

import org.bukkit.entity.Player;

public class EventExecuter extends Event {

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
        }
        if (eventClass != null) {
            eventClass.ExecuteEvent();
        }
    }
}
