package hugu1026.com.github.phantasyquest.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestYAMLReaderUtil {
    public static File getQuestFile(String questFileName) {
        File questData = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "quests");
        return new File(questData, File.separator + questFileName + ".yml");
    }

    public static FileConfiguration getQuestData(String questFileName) {
        File questDatas = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "quests");
        File questFile = new File(questDatas, File.separator + questFileName + ".yml");
        return YamlConfiguration.loadConfiguration(questFile);
    }

    public static List<String> getAllQuestsFilesNames() {
        File questFile = new File(Bukkit.getServer().getPluginManager().getPlugin("PhantasyQuest").getDataFolder(), File.separator + "quests");
        List<File> questFiles = Arrays.asList(questFile.listFiles());
        List<String> questFilesNames = new ArrayList<>();
        questFiles.forEach(file -> {
            String questName = file.getName().replace("plugins\\PhantasyQuest\\quests\\", "");
            questName = questName.replace(".yml", "");
            questFilesNames.add(questName);
        });
        return questFilesNames;
    }

    public static String getQuestName(String questFileName) {
        return getQuestData(questFileName).getString("Name");
    }

    public static List<String> getNPCNames(String questFileName) {
        List<String> NPCs = getQuestData(questFileName).getStringList("NPCs");
        List<String> NPCNames = new ArrayList<>();
        NPCs.forEach(NPC -> {
            String strings[] = NPC.split(":");
            NPCNames.add(strings[0]);
        });
        return NPCNames;
    }

    public static List<Integer> getNPCIDs(String questFileName) {
        List<String> NPCs = getQuestData(questFileName).getStringList("NPCs");
        List<Integer> NPCIDs = new ArrayList<>();
        NPCs.forEach(NPC -> {
            String strings[] = NPC.split(":");
            NPCIDs.add(Integer.valueOf(strings[1]));
        });
        return NPCIDs;
    }

    public static List<String> getVaildQuestsFilesNames(int NPCId) {
        List<String> allQuestsFileNames = getAllQuestsFilesNames();
        List<String> vaildQuestsFilesNames = new ArrayList<>();
        for (String questFileName : allQuestsFileNames) {
            List<Integer> NPCs = getNPCIDs(questFileName);
            if (NPCs.contains(NPCId)) {
                vaildQuestsFilesNames.add(questFileName);
            }
        }
        return vaildQuestsFilesNames;
    }

    public static List<String> getConversations(String questFileName) {
        return getQuestData(questFileName).getStringList("Conversation");
    }

    public static List<Integer> getStartPoints(String questFileName) {
        return getQuestData(questFileName).getIntegerList("StartPoints");
    }

    public static List<String> getObjectives(String questFileName) {
        return getQuestData(questFileName).getStringList("Objectives");
    }

    public static List<String> getEvents(String questFileName) {
        return getQuestData(questFileName).getStringList("Events");
    }

    public static List<String> getConditions(String questFileName) {
        return getQuestData(questFileName).getStringList("Conditions");
    }

    public static List<String> getJournals(String questFileName) {
        return getQuestData(questFileName).getStringList("Journals");
    }
}
