package hugu1026.com.github.phantasyquest.quest.conversation;

import java.util.List;

public class Conversation {
    private String text;
    private int speakerNPCID;
    private List<Integer> eventNumbers;
    private List<Integer> conditionNumbers;
    private List<Integer> replyNumbers;
    private List<Integer> nextNumbers;

    public Conversation(String conversation, String questFileName) {
        ConversationFormatter formatter = new ConversationFormatter(conversation, questFileName);
        this.text = formatter.getText();
        this.speakerNPCID = formatter.getSpeakerNPCID();
        this.eventNumbers = formatter.getEventNumbers();
        this.conditionNumbers = formatter.getConditionNumbers();
        this.replyNumbers = formatter.getReplyNumbers();
        this.nextNumbers = formatter.getNextNumbers();
    }

    public List<Integer> getConditionNumbers() {
        return this.conditionNumbers;
    }

    public List<Integer> getEventNumbers() {
        return this.eventNumbers;
    }

    public List<Integer> getNextNumbers() {
        return this.nextNumbers;
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
}
