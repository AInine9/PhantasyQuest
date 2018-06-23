package hugu1026.com.github.phantasyquest.quest.condition;

import org.bukkit.entity.Player;

public abstract class Condition {
    private String conditionID, condition;
    private String[] args;

    public Condition(String conditionID) {
        this.conditionID = conditionID;
        this.args = conditionID.split(" ");
        this.condition = this.args[0];
    }

    //conditionID includes condition name and arguments.
    public String getConditionID() {
        return this.conditionID;
    }

    //condition is condition name.
    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public abstract boolean checkMeetCondition(Player player);

    public boolean checkConditionName(String conditon) {
        return conditon.equals(getCondition());
    }

    public String getArg(int number) {
        if (number > 0) {
            return this.args[number];
        } else {
            return null;
        }
    }
}
