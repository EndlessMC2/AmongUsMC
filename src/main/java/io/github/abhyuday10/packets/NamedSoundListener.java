package io.github.abhyuday10.packets;

import java.util.Set;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers.SoundCategory;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import io.github.abhyuday10.AmongUsMC;
import io.github.abhyuday10.Tags;


public class NamedSoundListener extends PacketAdapter {
    public NamedSoundListener(AmongUsMC plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.NAMED_SOUND_EFFECT);
    }

    @Override
    public void onPacketSending(PacketEvent e) {
        Player receiver = e.getPlayer();
        Set<String> playerTags = receiver.getScoreboardTags();
        // prevents ingame players from hearing sound when assigning map
        // to player's offhand1
        if (playerTags.contains(Tags.INGAME)
                && e.getPacket().getSoundEffects().readSafely(0) == Sound.ITEM_ARMOR_EQUIP_GENERIC) {

            e.setCancelled(true);

        }
        // Silence sounds if dead player is very close: probably a dead player sound
        if (e.getPacket().getSoundCategories().readSafely(0) == SoundCategory.PLAYERS) {
            Location packetLoc = new Location(e.getPlayer().getWorld(), (e.getPacket().getIntegers().read(0) / 8.0),
                    (e.getPacket().getIntegers().read(1) / 8.0), (e.getPacket().getIntegers().read(2) / 8.0));

            for (Player player : Bukkit.getOnlinePlayers()) {
                Location playerLoc = player.getLocation();
                if (player.getScoreboardTags().contains("au_dead") && playerLoc.distance(packetLoc) < 0.5)
                    e.setCancelled(true);
            }
        }
    }
}
