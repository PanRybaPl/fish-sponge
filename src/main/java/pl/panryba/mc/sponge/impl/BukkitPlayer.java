package pl.panryba.mc.sponge.impl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.panryba.mc.sponge.interfaces.FishItemStack;
import pl.panryba.mc.sponge.interfaces.FishPlayer;

public class BukkitPlayer implements FishPlayer {
    private final Player player;

    public BukkitPlayer(Player player) {
        this.player = player;
    }

    @Override
    public FishItemStack getItemInHand() {
        return new BukkitItemStack(this.player.getItemInHand());
    }

    @Override
    public boolean hasInventory() {
        return this.player.getInventory() != null;
    }

    @Override
    public boolean hasSponge() {
        return this.player.getInventory().first(Material.SPONGE) != -1;
    }

    @Override
    public boolean removeSponge() {
        PlayerInventory inv = player.getInventory();
        int spongeSlot = inv.first(Material.SPONGE);

        if(spongeSlot == -1) {
            return false;
        }

        ItemStack spongeStack = inv.getItem(spongeSlot);
        if(spongeStack == null) {
            return false;
        }

        if(spongeStack.getAmount() > 1) {
            spongeStack.setAmount(spongeStack.getAmount() - 1);
        }
        else {
            inv.remove(spongeStack);
        }

        return true;
    }

    @Override
    public void sendMessage(String msg) {
        this.player.sendMessage(msg);
    }
}
