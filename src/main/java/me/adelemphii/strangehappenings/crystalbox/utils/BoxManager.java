package me.adelemphii.strangehappenings.crystalbox.utils;

import me.adelemphii.strangehappenings.StrangeHappenings;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.HashMap;
import java.util.Map;

public class BoxManager {

    Map<Integer, BoundingBox> boxes = new HashMap<>();

    StrangeHappenings plugin;

    public BoxManager(StrangeHappenings plugin) {
        this.plugin = plugin;
    }

    public void addBox(int id, BoundingBox box) {
        boxes.put(id, box);
    }

    public BoundingBox getBox(int id) {
        return boxes.get(id);
    }

    public void removeBox(int id) {
        boxes.remove(id);
    }

    public void clearBoxes() {
        boxes.clear();
    }

    public int getBoxCount() {
        if(boxes.isEmpty()) return 0;

        return boxes.size();
    }

    public boolean isBox(int id) {
        return boxes.containsKey(id);
    }

    public BoundingBox createBox(Location location, int x, int y, int z) {
        BoundingBox box = BoundingBox.of(location, x, y, z);

        this.addBox(getBoxCount() + 1, box);

        return box;
    }
}
