package hugu1026.com.github.phantasyquest.quest.conversation;

import org.bukkit.entity.Player;

import java.util.List;

public class Conversation {
    private String text;
    private int speakerNPCID;
    private Player speakerPlayer;
    private List<Integer> eventNumbers;
    private List<Integer> conditionNumbers;
    private List<Integer> replyNumbers;
    private int nextNumber;

    public Conversation(String conversation, Player player) {
        ConversationFormatter formatter = new ConversationFormatter(conversation, player);
        this.text = formatter.getText();
        this.speakerNPCID = formatter.getSpeakerNPCID();
        this.eventNumbers = formatter.getEventNumbers();
        this.conditionNumbers = formatter.getConditionNumbers();
        this.replyNumbers = formatter.getReplyNumbers();
        this.nextNumber = formatter.getNextNumber();
        this.speakerPlayer = formatter.getPlayer();
    }

    public List<Integer> getConditionNumbers() {
        return this.conditionNumbers;
    }

    public List<Integer> getEventNumbers() {
        return this.eventNumbers;
    }

    public int getNextNumber() {
        return this.nextNumber;
    }

    public List<Integer> getReplyNumbers() {
        return this.replyNumbers;
    }

    public String getText() {
        return this.text;
    }

    public int getSpeakerNPCID() {
        return this.speakerNPCID;
    }

    public Player getSpeakerPlayer() {
        return this.speakerPlayer;
    }
}
