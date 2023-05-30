package me.alvsch.alvschlib.classes.menu;

import lombok.Getter;
import lombok.Setter;
import me.alvsch.alvschlib.classes.menu.buttons.CloseButton;
import me.alvsch.alvschlib.classes.menu.buttons.PageButton;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageMenu extends Menu {

    private int currentPage = 1;
    private final List<MenuItem> items = new ArrayList<>();

    public PageMenu(String title, int rows, MenuItem... items) {
        super(title, rows);
        if(rows < 2) throw new IllegalArgumentException("Rows cannot be less than 2");

        this.items.addAll(List.of(items));
        addButtons();
    }

    public Inventory getPage(int page) {
        Inventory inventory = this.getInventory();

        int amount = getSize() - 9;
        if (items.size() / amount < page) {
            return inventory;
        }
        inventory.addItem(
                items.subList(amount*page,
                        Math.min(items.size(), amount*page+amount)
                ).toArray(new MenuItem[0])
        );

        return inventory;
    }

    public int getPages() {
        return items.size() / (getSize()-9);
    }

    private void addButtons() {
        int size = this.getInventory().getSize();
        int close = size - 5;
        int left = size - 9;
        int right = size - 1;

        MenuItem placeholder = new MenuItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1);
        for(int i = size - 9; i != size; i++) {
            this.setItem(i, placeholder);
        }

        MenuItem closeItem = new CloseButton("&cClose", Material.BARRIER);
        MenuItem leftItem = new PageButton("&fLeft", Material.ARROW, this, true);
        MenuItem rightItem = new PageButton("&fRight", Material.ARROW, this, false);

        this.setItem(close, closeItem);
        this.setItem(left, leftItem);
        this.setItem(right, rightItem);

    }
}
