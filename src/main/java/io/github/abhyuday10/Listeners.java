package io.github.abhyuday10;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Listeners implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Set<String> playerTags = player.getScoreboardTags();
        // Bukkit.broadcastMessage(player.getName() + " clicked inv with tags " +
        // playerTags.toString());
        if (playerTags.contains("au_inGame")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains("au_inGame")) {
            e.setCancelled(true);
        }
    }
}