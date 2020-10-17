package io.github.abhyuday10.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.abhyuday10.Tags;

public class CheckAndHidePlayers implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboardTags().contains(Tags.INGAME)) {
                for (Player other : Bukkit.getOnlinePlayers()) {
                    if (player.getWorld() == other.getWorld()) {
                        if (other.getLocation().distance(player.getLocation()) > 20) {
                            player.hidePlayer(other);
                        } else {
                            player.showPlayer(other);
                        }
                    }

                }
            }
        }

    }

}
