package hugu1026.com.github.phantasyquest.citizens.listener;

import hugu1026.com.github.phantasyquest.quest.QuestSuggester;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class NPCRightClick implements Listener {
    @EventHandler
    public void NPCClick(NPCRightClickEvent event) {
        if (event.isCancelled()) {
            return;
        }
        int id = event.getNPC().getId();
        Player player = event.getClicker();

        QuestSuggester questSuggester = new QuestSuggester(id, player);
        Map<String, String> suggestedQuests = questSuggester.getSuggestedQuests();
        Map<String, Integer> startPoints = questSuggester.getConvStartPoint();

        suggestedQuests.keySet().forEach(questName -> {
            String questFileName = suggestedQuests.get(questName);
            int startPoint = startPoints.get(questName);
            TextComponent component = new TextComponent(ChatColor.GREEN + "[Quest] " + ChatColor.GOLD + questName);
            component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quest start " + questFileName + player + startPoint));
            component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + "クリックして受注").create()));
            player.spigot().sendMessage(component);
        });
    }
}
