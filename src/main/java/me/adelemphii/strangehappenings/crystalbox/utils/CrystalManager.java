package me.adelemphii.strangehappenings.crystalbox.utils;

import me.adelemphii.strangehappenings.StrangeHappenings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BoundingBox;

import java.util.HashMap;
import java.util.Map;

public class CrystalManager {

    Map<Integer, EnderCrystal> crystals = new HashMap<>();

    BukkitTask crystalTask;

    StrangeHappenings plugin;

    public CrystalManager(StrangeHappenings plugin) {
        this.plugin = plugin;

        crystalTask = plugin.getServer().getScheduler().runTaskTimer(plugin, () -> {
            if(crystals.isEmpty()) return;

            for(Integer id : crystals.keySet()) {
                EnderCrystal crystal = crystals.get(id);
                BoundingBox box = plugin.getBoxManager().getBox(id);

                boolean isInBox = false;

                for(Player player : crystal.getWorld().getPlayers()) {
                    if(box.contains(player.getLocation().toVector())) {

                        Location playerChest = player.getLocation().add(0, -1, 0);
                        player.sendMessage(player.getLocation().toString());
                        player.sendMessage(player.getLocation().toVector().toString());

                        crystal.setBeamTarget(playerChest);

                        isInBox = true;
                        break;
                    }
                }

                if(!isInBox) {
                    crystal.setBeamTarget(null);
                }
            }
        }, 0L, 1L);
    }

    public void addCrystal(int id, EnderCrystal crystal) {
        crystals.put(id, crystal);
    }

    public EnderCrystal getCrystal(int id) {
        return crystals.get(id);
    }

    public void removeCrystal(int id) {
        crystals.remove(id);
    }

    public void clearCrystals() {
        crystals.clear();
    }

    public int getCrystalCount() {

        if(crystals.isEmpty()) return 0;

        return crystals.size();
    }

    public boolean isCrystal(int id) {
        return crystals.containsKey(id);
    }

    public EnderCrystal createCrystal(Location center) {
        EnderCrystal crystal = (EnderCrystal) center.getWorld().spawnEntity(center, EntityType.ENDER_CRYSTAL);
        crystal.setShowingBottom(false);

        this.addCrystal(getCrystalCount() + 1, crystal);

        return crystal;
    }

    public void cancelCrystalTask() {
        crystalTask.cancel();
    }

}
