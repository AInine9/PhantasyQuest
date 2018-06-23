package hugu1026.com.github.phantasyquest.quest;

import hugu1026.com.github.phantasyquest.PhantasyQuest;
import hugu1026.com.github.phantasyquest.quest.condition.ConditionChecker;
import hugu1026.com.github.phantasyquest.quest.conversation.Conversation;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class Quest {
    private String questName, NPCName, questFileName;
    private Player player;
    private NPC npc;
    private List<String> conversations, events, conditions, objectives, journals;
    private int NPCId;

    public Quest(String questFileName, Player player, NPC npc) {
        this.questFileName = questFileName;
        this.questName = QuestYAMLReaderUtil.getQuestName(questFileName);
        this.conversations = QuestYAMLReaderUtil.getConversations(questFileName);
        this.events = QuestYAMLReaderUtil.getEvents(questFileName);
        this.conditions = QuestYAMLReaderUtil.getConditions(questFileName);
        this.objectives = QuestYAMLReaderUtil.getObjectives(questFileName);
        this.journals = QuestYAMLReaderUtil.getJournals(questFileName);
        this.player = player;
        this.npc = npc;
        this.NPCId = npc.getId();
        this.NPCName = npc.getName();
    }

    public void startQuest(int startPoint) {
        Conversation conversation = new Conversation(conversations.get(startPoint - 1), player);

        //check conditions
        if (conversation.getConditionNumbers() != null) {
            conversation.getConditionNumbers().forEach(number -> {
                ConditionChecker checker = new ConditionChecker(conditions.get(number));
                checker.checkMeetCondition(player);
            });
        }

        if (conversation.getSpeakerPlayer() == null) {
            //speaker is a NPC
            player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.RED + NPCName + ": " + ChatColor.GOLD + conversation.getText());
        } else {
            //speaker is a player
            player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.RED + player.getName() + ": " + ChatColor.GOLD + conversation.getText());
        }

        if (conversation.getReplyNumbers().size() == 0) {
            //exist next conversation
            Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyQuest.getPlugin(PhantasyQuest.class), () ->
                            startQuest(conversation.getNextNumbers())
                    , 40L);

        } else {
            //exist reply
            Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyQuest.getPlugin(PhantasyQuest.class), ()
                    -> conversation.getReplyNumbers().forEach(number -> {
                Conversation conv = new Conversation(conversations.get(number - 1), player);
                player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.RED + player.getName() + ": " + ChatColor.GOLD + conv.getText());
            }), 40L);
        }

    }

    public String getQuestName() {
        return this.questName;
    }

    public String getQuestFileName() {
        return this.questFileName;
    }

    public List<String> getConversations() {
        return this.conversations;
    }

    public List<String> getEvents() {
        return this.events;
    }

    public List<String> getConditions() {
        return this.conditions;
    }

    public int getNPCId() {
        return this.NPCId;
    }

    public List<String> getJournals() {
        return this.journals;
    }

    public List<String> getObjectives() {
        return this.objectives;
    }

    public NPC getNpc() {
        return this.npc;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getNPCName() {
        return this.NPCName;
    }
}
