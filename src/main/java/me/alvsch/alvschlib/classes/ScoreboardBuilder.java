package me.alvsch.alvschlib.classes;

import lombok.Builder;
import me.alvsch.alvschlib.util.Utils;
import org.bukkit.scoreboard.*;

public class ScoreboardBuilder {

    private final Scoreboard scoreboard;

    public ScoreboardBuilder(ScoreboardManager manager) {
        this.scoreboard = manager.getNewScoreboard();
    }

    public ScoreboardBuilder setTitle(String title) {
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        if (objective == null) {
            objective = scoreboard.registerNewObjective(title, Criteria.DUMMY, Utils.color(title));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        } else {
            objective.setDisplayName(title);
        }
        return this;
    }

    public ScoreboardBuilder setScore(int score, String value) {
        Objective objective = scoreboard.getObjective(DisplaySlot.SIDEBAR);
        if (objective != null) {
            Score playerScore = objective.getScore(value);
            playerScore.setScore(score);
        }
        return this;
    }


    public Scoreboard build() {
        return scoreboard;
    }

}
