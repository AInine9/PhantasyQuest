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

    public String getPlayerName(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getString("name");
    }

    public List<String> getAcceptedQuests(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("accepted_quests");
    }

    public List<String> getClearedQuests(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("cleared_quests");
    }

    public List<String> getTags(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("tags");
    }

    public List<String> getObjectives(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("objectives");
    }

    public List<String> getJournals(Player player) {
        FileConfiguration playerData = getPlayerData(player);

        return playerData.getStringList("journals");
    }
}
