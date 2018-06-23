package hugu1026.com.github.phantasyquest.quest.conversation;

import java.util.ArrayList;
import java.util.List;

public class ConversationFormatter {
    private String text;
    private int speakerNPCID;
    private List<Integer> eventNumbers = new ArrayList<>();
    private List<Integer> conditionNumbers = new ArrayList<>();
    private List<Integer> replyNumbers = new ArrayList<>();
    private List<Integer> nextNumbers = new ArrayList<>();

    public ConversationFormatter(String conversation, String questFileName) {
        if (conversation.contains("; ")) {
            String strings[] = conversation.split("; ");

            String conv[] = strings[0].split(">");
            speakerNPCID = Integer.parseInt(conv[0]);
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
                    if (string.contains(", ")) {
                        String numbers[] = string.split(", ");
                        for (String number : numbers) {
                            nextNumbers.add(Integer.valueOf(number));
                        }
                    } else {
                        nextNumbers.add(Integer.valueOf(string));
                    }
                }
            }
        }
    }

    public List<Integer> getConditions() {
        return this.conditionNumbers;
    }

    public List<Integer> getEvents() {
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
