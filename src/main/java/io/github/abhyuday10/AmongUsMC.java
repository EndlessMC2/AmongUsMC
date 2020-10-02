package io.github.abhyuday10;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.abhyuday10.events.GenericEvents;
import io.github.abhyuday10.events.RightClickEvents;

import java.util.List;
import java.util.Set;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.Pair;
import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot;

/**
 * AmongUsMC
 */
public class AmongUsMC extends JavaPlugin {
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        getLogger().info("AmongUsMC Plugin has loaded.");

        // Register listeners
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new GenericEvents(), this);
        manager.registerEvents(new RightClickEvents(), this);

        protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(
                new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_EQUIPMENT) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        Player player = event.getPlayer();
                        Set<String> playerTags = player.getScoreboardTags();
                        if (playerTags.contains("au_dead") || playerTags.contains("au_vent")) {
                            event.setCancelled(true);
                        } else if (playerTags.contains(Tags.INGAME)) {
                            List<Pair<ItemSlot, ItemStack>> slotStacks = event.getPacket().getSlotStackPairLists()
                                    .read(0);
                            for (Pair<ItemSlot, ItemStack> pair : slotStacks) {
                                ItemSlot slot = pair.getFirst();
                                ItemStack stack = pair.getSecond();
                                if (slot == ItemSlot.MAINHAND || slot == ItemSlot.OFFHAND) {
                                    // event.setCancelled(true);
                                    stack.setAmount(0);
                                }
                            }

                        }
                    };
                });
    }

    @Override
    public void onDisable() {
        getLogger().info("AmongUsMC Plugin has been disabled :(");
    }
}