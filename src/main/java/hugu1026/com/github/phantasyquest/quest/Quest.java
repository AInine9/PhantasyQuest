package hugu1026.com.github.phantasyquest.quest;

import hugu1026.com.github.phantasyquest.PhantasyQuest;
import hugu1026.com.github.phantasyquest.quest.condition.ConditionChecker;
import hugu1026.com.github.phantasyquest.quest.conversation.Conversation;
import hugu1026.com.github.phantasyquest.quest.event.EventExecuter;
import hugu1026.com.github.phantasyquest.util.QuestYAMLReaderUtil;
import net.citizensnpcs.api.npc.NPC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
                ConditionChecker checker = new ConditionChecker(conditions.get(number - 1));
                checker.checkMeetCondition(player);
            });
        }

        if (conversation.getSpeakerPlayer() == null) {
            //speaker is a NPC
            player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.RED + NPCName + ": " + ChatColor.GOLD + conversation.getText());
        } else {
            //speaker is a player
            player.sendMessage(ChatColor.GREEN + "[Quest] " + ChatColor.BLUE + player.getName() + ": " + ChatColor.GOLD + conversation.getText());
        }

        if (conversation.getEventNumbers() != null) {
            //exist events
            conversation.getEventNumbers().forEach(number -> {
                EventExecuter executer = new EventExecuter(events.get(number - 1), player, questFileName);
                executer.ExecuteEvent();
            });
        }

        if (conversation.getNextNumber() != 0) {
            //exist next conversation
            Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyQuest.getPlugin(PhantasyQuest.class), () ->
                            startQuest(conversation.getNextNumber())
                    , 40L);

        } else if (conversation.getReplyNumbers().size() != 0) {
            //exist reply
            Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyQuest.getPlugin(PhantasyQuest.class), () -> {
                player.sendMessage(ChatColor.GREEN + "=====選択=====");
                conversation.getReplyNumbers().forEach(number -> {
                    Conversation conv = new Conversation(conversations.get(number - 1), player);
                    TextComponent component = new TextComponent(ChatColor.BLUE + player.getName() + ": " + ChatColor.GOLD + conv.getText());
                    component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest start " + questFileName + " " + player.getName() + " " + number + " " + NPCId));
                    component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "クリックして答える").create()));
                    player.spigot().sendMessage(component);
                });
                player.sendMessage(ChatColor.GREEN + "=====選択=====");
            }, 40L);

        } else { //end conversation
            return;
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
