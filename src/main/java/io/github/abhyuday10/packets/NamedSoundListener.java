package io.github.abhyuday10.packets;

import java.util.Set;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import io.github.abhyuday10.AmongUsMC;
import io.github.abhyuday10.Tags;

// Listener that prevents ingame players from hearing sound when assigning map
// to player's offhand
public class NamedSoundListener extends PacketAdapter {
    public NamedSoundListener(AmongUsMC plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_SOUND_EFFECT);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        Player player = event.getPlayer();
        Set<String> playerTags = player.getScoreboardTags();
        if (playerTags.contains(Tags.INGAME)
                && event.getPacket().getSoundEffects().readSafely(0) == Sound.ITEM_ARMOR_EQUIP_GENERIC) {

            event.setCancelled(true);

        }
    }
    
    @Override
    public void onPacketReceiving(PacketEvent event) {
        
    }
}


