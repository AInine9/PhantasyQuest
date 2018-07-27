package hugu1026.com.github.phantasyquest.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlayerQuestDataUtil {

    public static void createPlayerDataYml(Player player) {
        String playerUUID = String.valueOf(player.getUniqueId());
        File userData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "player_data");
        File file = new File(userData, File.separator + playerUUID + ".yml");
        FileConfiguration playerData = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                playerData.createSection("name");
                playerData.createSection("accepted_quests");
                playerData.createSection("cleared_quests");
                playerData.createSection("tags");
                playerData.createSection("objectives");
                playerData.createSection("journals");

                playerData.set("name", player.getName());

                playerData.save(file);
            } catch (IOException expection) {
                expection.printStackTrace();
            }
        }
    }

    public static File getPlayerFile(Player player) {
        String playerUUID = String.valueOf(player.getUniqueId());
        File userData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "player_data");
        return new File(userData, File.separator + playerUUID + ".yml");
    }

    public static FileConfiguration getPlayerData(Player player) {
        String playerUUID = String.valueOf(player.getUniqueId());
        File userData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "player_data");
        File file = new File(userData, File.separator + playerUUID + ".yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void savePlayerData(File playerFile, FileConfiguration playerData, Player player) {
        try {
            playerData.save(playerFile);
        } catch (IOException exception) {
            Bukkit.getServer().getLogger().severe(player.getDisplayName() + "のデータを保存できませんでした");
            exception.printStackTrace();
        }
    }

    public static String getPlayerName(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getString("name");
    }

    public static List<String> getAcceptedQuests(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("accepted_quests");
    }

    public static List<String> getClearedQuests(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("cleared_quests");
    }

    public static List<String> getTags(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("tags");
    }

    public static List<String> getObjectives(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("objectives");
    }

    public static List<String> getJournals(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("journals");
    }

    public static void addTags(Player player, String tag) {
        File playerFile = getPlayerFile(player);
        FileConfiguration playerData = getPlayerData(player);

        List<String> tags = getTags(player);
        tags.add(tag);
        playerData.set("tags", tags);
        savePlayerData(playerFile, playerData, player);
    }

    public static void addObjective(Player player, String objective) {
        File playerFile = getPlayerFile(player);
        FileConfiguration playerData = getPlayerData(player);

        List<String> objectives = getObjectives(player);
        objectives.add(objective);
        playerData.set("objectives", objectives);
        savePlayerData(playerFile, playerData, player);
    }

    public static void replaceObjective(String stringToReplace, String newString, Player player) {
        List<String> objectives = getObjectives(player);
        for (int i = 0; i < objectives.size(); i++) {
            String string = objectives.get(i);
            if (string.equals(stringToReplace)) {
                objectives.set(i, newString);

                FileConfiguration playerData = getPlayerData(player);
                File playerFile = getPlayerFile(player);

                playerData.set("objectives", objectives);
                savePlayerData(playerFile, playerData, player);
            }
        }
    }
}
