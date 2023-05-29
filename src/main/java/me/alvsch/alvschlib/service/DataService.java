package me.alvsch.alvschlib.service;

import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * I "borrowed" the code
 *
 * @author TheBusyBiscuit href=github.com/Slimefun/Slimefun4
 */
public class DataService implements Keyed {

    /**
     * This is the {@link NamespacedKey} used to store/read data.
     */
    private final NamespacedKey namespacedKey;

    /**
     * This creates a new {@link DataService} for the given {@link Plugin} and the
     * provided data key.
     *
     * @param plugin
     *            The {@link Plugin} for this service to use
     * @param key
     *            The key under which to store data
     */
    public DataService(@NotNull Plugin plugin, @NotNull String key) {
        // Null-Validation is performed in the NamespacedKey constructor
        namespacedKey = new NamespacedKey(plugin, key);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return namespacedKey;
    }

    /**
     * This method stores the given id on the provided {@link ItemStack} via
     * persistent data.
     *
     * @param item
     *            The {@link ItemStack} to store data on
     * @param value
     *            The id to store on the {@link ItemStack}
     */
    public void setItemData(@NotNull ItemStack item, @NotNull String value) {
        if(item == null) throw new IllegalArgumentException("Argument cannot be null (arg-1)");
        if(value == null) throw new IllegalArgumentException("Argument cannot be null (arg-2)");

        ItemMeta im = item.getItemMeta();
        if(im == null) throw new NullPointerException("ItemMeta cannot be null item.getItemMeta()");

        setItemData(im, value);
        item.setItemMeta(im);
    }

    /**
     * This method stores the given id on the provided {@link ItemMeta} via
     * persistent data.
     *
     * @param meta
     *            The {@link ItemMeta} to store data on
     * @param value
     *            The id to store on the {@link ItemMeta}
     */
    public void setItemData(@NotNull ItemMeta meta, @NotNull String value) {
        if(meta == null) throw new IllegalArgumentException("Argument cannot be null (arg-1)");
        if(value == null) throw new IllegalArgumentException("Argument cannot be null (arg-2)");

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(namespacedKey, PersistentDataType.STRING, value);
    }

    /**
     * This method returns an {@link Optional} holding the data stored on the given {@link ItemStack}.
     * The {@link Optional} will be empty if the given {@link ItemStack} is null, doesn't have any {@link ItemMeta}
     * or if the requested data simply does not exist on that {@link ItemStack}.
     *
     * @param item
     *            The {@link ItemStack} to check
     *
     * @return An {@link Optional} describing the result
     */
    public @NotNull Optional<String> getItemData(@NotNull ItemStack item) {
        if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) {
            return Optional.empty();
        }

        assert item.getItemMeta() != null;
        return getItemData(item.getItemMeta());
    }

    /**
     * This method returns an {@link Optional}, either empty or holding the data stored
     * on the given {@link ItemMeta}.
     *
     * @param meta
     *            The {@link ItemMeta} to check
     *
     * @return An {@link Optional} describing the result
     */
    public @NotNull Optional<String> getItemData(@NotNull ItemMeta meta) {
        if(meta == null) throw new IllegalArgumentException("Cannot read data from null");

        PersistentDataContainer container = meta.getPersistentDataContainer();
        return Optional.ofNullable(container.get(namespacedKey, PersistentDataType.STRING));
    }

    /**
     * This method compares the custom data stored on two {@link ItemMeta} objects.
     * This method will only return {@literal true} if both {@link ItemMeta}s contain
     * custom data and if both of their data values are equal.
     *
     * @param meta1
     *            The first {@link ItemMeta}
     * @param meta2
     *            The second {@link ItemMeta}
     *
     * @return Whether both metas have data on them, and it's the same.
     */
    public boolean hasEqualItemData(@NotNull ItemMeta meta1, @NotNull ItemMeta meta2) {
        if(meta1 == null) throw new IllegalArgumentException("Argument cannot be null (arg-1)");
        if(meta2 == null) throw new IllegalArgumentException("Cannot read data from null (arg-2)");

        Optional<String> data1 = getItemData(meta1);

        // Check if the first data is present
        if (data1.isPresent()) {
            // Only retrieve the second data where necessary.
            Optional<String> data2 = getItemData(meta2);

            /*
             * Check if both are present and equal.
             * Optional#equals(...) compares their values, so no need
             * to call Optional#get() here.
             */
            return data2.isPresent() && data1.equals(data2);
        } else {
            // No value present, we can return immediately.
            return false;
        }
    }


}
