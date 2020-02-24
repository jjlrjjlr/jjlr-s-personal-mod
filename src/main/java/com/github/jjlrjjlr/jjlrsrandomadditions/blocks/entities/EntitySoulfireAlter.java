package com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.BlocksRegistry;
import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.interfaces.ImplementedInventory;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

public class EntitySoulfireAlter extends BlockEntity implements ImplementedInventory, BlockEntityClientSerializable {

    public final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public EntitySoulfireAlter() {
        super(BlocksRegistry.Entity_Soulfire_Alter);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
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
        Inventories.fromTag(tag, items);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        Inventories.toTag(tag, items);
        return super.toTag(tag);
    }
    
}