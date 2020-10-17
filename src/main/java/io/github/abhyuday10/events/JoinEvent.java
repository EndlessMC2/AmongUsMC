package io.github.abhyuday10.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    /**
     * 
     * 
     * @param e event
     * 
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        int maxPlayers = Bukkit.getMaxPlayers();
        int normalPlayersAllowed = maxPlayers / 2;
        int totalNormalPlayers = 0;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (!online.hasPermission("amongus.playerlimit")) {
                totalNormalPlayers++;
            }
        }
        if (totalNormalPlayers >= normalPlayersAllowed) {
            p.kickPlayer("Player limit reached! Please join another game.");
        }
    }
}
