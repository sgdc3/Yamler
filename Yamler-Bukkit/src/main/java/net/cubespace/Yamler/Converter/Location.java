package net.cubespace.Yamler.Converter;

import net.cubespace.Yamler.Config.ConfigSection;
import net.cubespace.Yamler.Config.Converter.Converter;
import net.cubespace.Yamler.Config.InternalConverter;
import org.bukkit.Bukkit;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geNAZt (fabian.fassbender42@googlemail.com)
 */
public class Location implements Converter {
    public Location(InternalConverter converter) {}

    @Override
    public Object toConfig(Class<?> type, Object obj, ParameterizedType genericType) throws Exception {
        org.bukkit.Location location = (org.bukkit.Location) obj;
        Map<String, Object> saveMap = new HashMap<>();
        saveMap.put("world", location.getWorld().getName());
        saveMap.put("x", location.getX());
        saveMap.put("y", location.getY());
        saveMap.put("z", location.getZ());
        saveMap.put("yaw", location.getYaw());
        saveMap.put("pitch", location.getPitch());

        return saveMap;
    }

    @Override
    public Object fromConfig(Class type, Object section, ParameterizedType genericType) throws Exception {
        Map<String, Object> locationMap;
        if ( section instanceof Map ) {
            locationMap = (Map<String, Object>) section;
        } else {
            locationMap = (Map<String, Object>) ( (ConfigSection) section ).getRawMap();
        }

        return new org.bukkit.Location(Bukkit.getWorld((String) locationMap.get("world")), (Double) locationMap.get("x"), (Double) locationMap.get("y"), (Double) locationMap.get("z"), (Float) locationMap.get("yaw"), (Float) locationMap.get("pitch") );
    }

    @Override
    public boolean supports(Class<?> type) {
        return org.bukkit.Location.class.isAssignableFrom(type);
    }

}
