package io.github.abhyuday10.packets;

import java.util.List;
import java.util.Set;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot;
import com.comphenix.protocol.wrappers.Pair;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.abhyuday10.AmongUsMC;
import io.github.abhyuday10.Tags;

// Listener to hide in hand item update packets
// NOTE: Must add tags before items are placed in hand for it to update properly
public class EntityEquipmentListener extends PacketAdapter {
    public EntityEquipmentListener(AmongUsMC plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_EQUIPMENT);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        Player player = event.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains("au_dead") || playerTags.contains("au_vent")) {
            event.setCancelled(true);
        } else if (playerTags.contains(Tags.INGAME)) {
            List<Pair<ItemSlot, ItemStack>> slotStacks = event.getPacket().getSlotStackPairLists().read(0);
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
}
