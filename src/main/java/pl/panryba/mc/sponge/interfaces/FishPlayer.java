package pl.panryba.mc.sponge.interfaces;

public interface FishPlayer {
    FishItemStack getItemInHand();

    boolean hasInventory();
    boolean hasSponge();
    boolean removeSponge();

    void sendMessage(String msg);
}
