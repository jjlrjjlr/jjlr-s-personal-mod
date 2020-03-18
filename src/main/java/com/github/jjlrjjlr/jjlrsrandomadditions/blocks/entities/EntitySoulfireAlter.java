package com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.BlocksRegistry;
import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.interfaces.ImplementedInventory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;

public class EntitySoulfireAlter extends BlockEntity implements ImplementedInventory, BlockEntityClientSerializable, Tickable {
    
    private static Logger logger = (Logger) LogManager.getLogger("jjlrsrandomadditions");
    public final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public EntitySoulfireAlter() {
        super(BlocksRegistry.Entity_Soulfire_Alter);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public void tick() {
        if(!this.world.isClient()){
            
        }
        
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        super.fromTag(tag);
        this.items.clear();
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }

    public void placeItemInInventory(ItemStack item) {
        if(item!=null && this.getInvStack(0).isEmpty()){
            this.setInvStack(0, item);
            this.markDirty();
            
        } else{
            logger.warn("A problem occured while trying to add item to blockentity at" + this.getPos().toShortString());
        }
    }

    public void removeItemInInventory(PlayerEntity player) {

        player.inventory.offerOrDrop(world, this.removeInvStack(0));
        this.markDirty();
    }

    @Override
    public void markDirty() {
        if(!this.world.isClient){
            super.markDirty();
            this.sync();
            logger.info("marked");
        }
    }
}