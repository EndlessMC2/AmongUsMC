package io.github.abhyuday10.events;

import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import io.github.abhyuday10.Tags;

public class GenericEvents implements Listener {

    /**
     * Disable moving items in inventory when players are au_inGame unless they are
     * au_inTask
     * 
     * @param e event
     * 
     */
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME) && !playerTags.contains(Tags.INTASK)) {
            e.setCancelled(true);
        }
    }

    /**
     * Prevent dropping inventory items au_inGame
     * 
     * @param e event
     */
    @EventHandler
    public void onInvDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME)) {
            e.setCancelled(true);
        }
    }

    /**
     * Keep setting player hunger to 1
     * 
     * @param e event
     */
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        Player player = (Player) e.getEntity();
        if (player.getGameMode() == GameMode.ADVENTURE) {
            player.setFoodLevel(1);
            e.setCancelled(true);
        }
    }

    /**
     * Prevent interacting with blocks unless au_inTask
     * 
     * @param e event
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        Block block = e.getClickedBlock();
        if (block == null) {
            return;
        }
        if (playerTags.contains(Tags.INGAME) && !playerTags.contains(Tags.INTASK)
                && !(block.getState() instanceof Sign)) {
            e.setCancelled(true);
        }
    }

    /**
     * Prevent interacting with entities like levers or frames unless au_inTask
     * 
     * @param e event
     */
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME) && !playerTags.contains(Tags.INTASK)) {
            e.setCancelled(true);
        }
    }

    /**
     * Prevent destroying stuff like item frames with anything unless au_inTask
     * 
     * @param e event
     */
    @EventHandler
    public void onHangingBreak(HangingBreakByEntityEvent e) {
        Entity entity = e.getRemover();
        Set<String> playerTags = entity.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME) && !playerTags.contains(Tags.INTASK)) {
            e.setCancelled(true);
        }
    }

    /**
     * Prevent removing stuff from item frames unless au_inTask
     * 
     * @param e event
     */
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        Set<String> playerTags = entity.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME) && !playerTags.contains(Tags.INTASK)) {
            e.setCancelled(true);
        }
    }

    /**
     * Prevent moving stuff between offhand when in-game
     * 
     * @param e event
     */
    @EventHandler
    public void onSwapHandItem(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME)) {
            e.setCancelled(true);
        }
    }

}