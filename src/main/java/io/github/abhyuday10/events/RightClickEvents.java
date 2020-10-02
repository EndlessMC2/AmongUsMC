package io.github.abhyuday10.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class RightClickEvents implements Listener{
    // Assigns a tag when right clicking an item.
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        switch (e.getMaterial()) {
            // Slot 1 Everyone - USE
            case LIME_CONCRETE: {
                player.addScoreboardTag("au_useTask");
                break;
            } // Slot 1 Imposter - VENT
            case IRON_TRAPDOOR: {
                player.addScoreboardTag("au_useVent");
                break;
            } // Slot 2 Everyone - REPORT
            case RED_CONCRETE: {
                player.addScoreboardTag("au_reported");
                break;
            } // Slot 3 Imposter - KILL
            case DIAMOND_SWORD: {
                player.addScoreboardTag("au_useKill");
                break;
            } // Slot 4 Imposter - SAB O2
            case OAK_SAPLING: {
                player.addScoreboardTag("au_useSab_o2");
                break;
            } // Slot 5 Imposter - SAB REACTOR
            case LAVA_BUCKET: {
                player.addScoreboardTag("au_useSab_reactor");
                break;
            } // Slot 6 Imposter - SAB LIGHTS
            case TORCH: {
                player.addScoreboardTag("au_useSab_lights");
                break;
            } // Slot 7 Imposter - SAB COMMS
            case REDSTONE_TORCH: {
                player.addScoreboardTag("au_useSab_comms");
                break;
            } // Slot 8 Imposter - SAB DOORS
            case IRON_DOOR: {
                player.addScoreboardTag("au_useSab_doors");
                break;
            } // Slot 9 Imposter - TOGGLE DOOR
            case COMPARATOR: {
                player.addScoreboardTag("au_useToggle");
                break;
            }
            case FEATHER: {
                player.addScoreboardTag("au_useMove");
                break;
            }
            default:
                break;

        }
    }
}
