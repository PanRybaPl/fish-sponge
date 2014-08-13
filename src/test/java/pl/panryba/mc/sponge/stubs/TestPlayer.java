package pl.panryba.mc.sponge.stubs;

import pl.panryba.mc.sponge.interfaces.FishItemStack;
import pl.panryba.mc.sponge.interfaces.FishPlayer;

public class TestPlayer implements FishPlayer {
    public FishItemStack itemInHands;
    public int sponges;
    public String lastMessage;
    public boolean hasInventory;

    public TestPlayer() {
        this.hasInventory = true;
    }

    @Override
    public FishItemStack getItemInHand() {
        return itemInHands;
    }

    @Override
    public boolean hasInventory() {
        return hasInventory;
    }

    @Override
    public boolean hasSponge() {
        return sponges > 0;
    }

    @Override
    public boolean removeSponge() {
        if(sponges <= 0) {
            return false;
        }

        --sponges;
        return true;
    }

    @Override
    public void sendMessage(String msg) {
        this.lastMessage = msg;
    }
}