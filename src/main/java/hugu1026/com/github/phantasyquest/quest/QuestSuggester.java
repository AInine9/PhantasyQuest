package hugu1026.com.github.phantasyquest.quest;

import hugu1026.com.github.phantasyquest.quest.condition.ConditionChecker;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestSuggester {
    private List<String> conversations, conditions;
    private List<Integer> conditionNumbers = new ArrayList<>();
    private List<Integer> startPoint;
    private Map<String, String> suggestedQuests = new HashMap<>(); //Quest Name, Quest File name.

    public QuestSuggester(int NPCID, Player player) {
        QuestYAMLReaderUtil.getVaildQuestsFilesNames(NPCID).forEach(questFileName -> {
            this.conversations = QuestYAMLReaderUtil.getConversations(questFileName);
            this.conditions = QuestYAMLReaderUtil.getConditions(questFileName);
            this.startPoint = QuestYAMLReaderUtil.getStartPoints(questFileName);
            suggestQuest(conversations, conditions, questFileName, player);
        });
    }

    public void suggestQuest(List<String> conversations, List<String> conditions, String questFileName, Player player) {
        String questName = QuestYAMLReaderUtil.getQuestName(questFileName);
        startPoint.forEach(point -> {
            String conversation = conversations.get(point - 1);
            if (conversation.contains("; ")) {
                setNumbers(conversation);
                for (int number : conditionNumbers) {
                    ConditionChecker conditionChecker = new ConditionChecker(conditions.get(number - 1));
                    if (!conditionChecker.checkMeetCondition(player)) {
                        return;
                    }
                }
                for (String q : suggestedQuests.keySet()) {
                    if (q.equals(questName)) {
                        return;
                    }
                }
                suggestedQuests.put(questName, questFileName);

            } else {
                for (String q : suggestedQuests.keySet()) {
                    if (q.equals(questName)) {
                        return;
                    }
                }
                suggestedQuests.put(questName, questFileName);
            }
        });
    }

    public void setNumbers(String conversation) {
        String strings[] = conversation.split("; ");
        setConditionNumbers(strings);
    }

    public void setConditionNumbers(String strings[]) {
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
        }
    }

    public Map<String, String> getSuggestedQuests() {
        return this.suggestedQuests;
    }
}
