package hugu1026.com.github.phantasyquest.quest.event;

import org.bukkit.entity.Player;

public abstract class Event {
    private String eventID, event;
    private String[] args;
    private Player player;

    public Event(String eventID, Player player) {
        this.eventID = eventID;
        this.args = eventID.split(" ");
        this.event = args[0];
        this.player = player;
    }

    public abstract void ExecuteEvent();

    public String getEvent() {
        return this.event;
    }

    public String getEventID() {
        return this.eventID;
    }

    public String[] getArgs() {
        return this.args;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getArg(int number) {
        if (number > 0) {
            return this.args[number];
        } else {
            return null;
        }
    }
}
