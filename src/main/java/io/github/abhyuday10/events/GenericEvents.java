package io.github.abhyuday10.events;

import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GenericEvents implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Set<String> playerTags = player.getScoreboardTags();
        // Bukkit.broadcastMessage(player.getName() + " clicked inv with tags " +
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

    // Set player hunger to 1
    @EventHandler
    public void whenMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE){
            player.setFoodLevel(1);
        }
    }
}