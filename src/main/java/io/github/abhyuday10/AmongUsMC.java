package io.github.abhyuday10;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.abhyuday10.events.GenericEvents;
import io.github.abhyuday10.events.RightClickEvents;
import io.github.abhyuday10.packets.EntityEquipmentListener;
import io.github.abhyuday10.packets.NamedSoundListener;

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
        protocolManager.addPacketListener(new EntityEquipmentListener(this));
        protocolManager.addPacketListener(new NamedSoundListener(this));

    }

    @Override
    public void onDisable() {
        getLogger().info("AmongUsMC Plugin has been disabled :(");
    }
}