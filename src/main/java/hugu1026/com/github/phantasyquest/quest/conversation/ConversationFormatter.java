package hugu1026.com.github.phantasyquest.quest.conversation;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConversationFormatter {
    private String text;
    private int speakerNPCID;
    private Player player = null;
    private List<Integer> eventNumbers = new ArrayList<>();
    private List<Integer> conditionNumbers = new ArrayList<>();
    private List<Integer> replyNumbers = new ArrayList<>();
    private int nextNumber;

    public ConversationFormatter(String conversation, Player player) {
        if (conversation.contains("; ")) {
            String strings[] = conversation.split("; ");

            String conv[] = strings[0].split(">");
            if (conv[0].equals("player")) {
                this.player = player;
            } else {
                speakerNPCID = Integer.parseInt(conv[0]);
            }
            text = conv[1];

            for (String string : strings) {

                if (string.contains("conditions:")) {
                    string = string.replace("conditions:", "");
                    if (string.contains(", ")) {
                        String numbers[] = string.split(", ");
                        for (String number : numbers) {
                            conditionNumbers.add(Integer.valueOf(number));
                        }
                    } else {
                        conditionNumbers.add(Integer.valueOf(string));
                    }
                }

                if (string.contains("events:")) {
                    string = string.replace("events:", "");
                    if (string.contains(", ")) {
                        String numbers[] = string.split(", ");
                        for (String number : numbers) {
                            eventNumbers.add(Integer.valueOf(number));
                        }
                    } else {
                        eventNumbers.add(Integer.valueOf(string));
                    }
                }

                if (string.contains("reply:")) {
                    string = string.replace("reply:", "");
                    if (string.contains(", ")) {
                        String numbers[] = string.split(", ");
                        for (String number : numbers) {
                            replyNumbers.add(Integer.valueOf(number));
                        }
                    } else {
                        replyNumbers.add(Integer.valueOf(string));
                    }
                }

                if (string.contains("next:")) {
                    string = string.replace("next:", "");
                    nextNumber = Integer.parseInt(string);
                }
            }
        } else {
            String conv[] = conversation.split(">");
            if (conv[0].equals("player")) {
                this.player = player;
            } else {
                speakerNPCID = Integer.parseInt(conv[0]);
            }
            text = conv[1];
        }
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

    public Player getPlayer() {
        return this.player;
    }
}
