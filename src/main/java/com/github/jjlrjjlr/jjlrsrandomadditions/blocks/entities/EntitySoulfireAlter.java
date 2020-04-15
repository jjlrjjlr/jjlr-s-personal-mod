package com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities;

import java.util.function.Predicate;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.BlocksRegistry;
import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.interfaces.ImplementedInventory;
import com.github.jjlrjjlr.jjlrsrandomadditions.recipes.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;

public class EntitySoulfireAlter extends BlockEntity implements ImplementedInventory, BlockEntityClientSerializable, Tickable {
    
    private static Logger logger = (Logger) LogManager.getLogger("jjlrsrandomadditions");
    public final DefaultedList<ItemStack> items = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private static final TargetPredicate playertarget = (new TargetPredicate()).setBaseMaxDistance((double)25).includeInvulnerable().includeTeammates().ignoreEntityTargetRules();

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
        if(Registry.SOULFIREALTER_RECIPES.containsKey(this.getInvStack(0).getItem())){
            Registry.SOULFIREALTER_RECIPES.get(this.getInvStack(0).getItem()).recipeTick(this.world.getClosestPlayer(this.playertarget, this.pos.getX(), this.pos.getY(), this.pos.getZ()), this.world, this.pos, this);
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