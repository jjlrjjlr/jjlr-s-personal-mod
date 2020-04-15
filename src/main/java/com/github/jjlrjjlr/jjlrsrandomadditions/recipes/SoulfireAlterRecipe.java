package com.github.jjlrjjlr.jjlrsrandomadditions.recipes;

import java.util.List;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities.EntitySoulfireAlter;

import net.minecraft.command.EntitySelector;
import net.minecraft.command.arguments.MobEffectArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.predicate.entity.EntityEffectPredicate.EffectData;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SoulfireAlterRecipe {

    private ItemStack inputitem;
    private DefaultedList<ItemStack> outputitems = DefaultedList.of();
    private Integer burnlength;
    private DefaultedList<StatusEffectInstance> recipeeffects = DefaultedList.of();
    
    public SoulfireAlterRecipe addStatusEffect(StatusEffectInstance effect) {
        recipeeffects.add(effect);
        return this;
    }

    public SoulfireAlterRecipe setInputItem(ItemStack itemIn) {
        this.inputitem = itemIn;
        return this;
    }

    public SoulfireAlterRecipe addOutputItem(ItemStack itemOut) {
        this.outputitems.add(itemOut);
        return this;
    }

    public SoulfireAlterRecipe setBurnLength(int length){
        this.burnlength = length;
        return this;
    }

    public List<ItemStack> getOutputItems() {
        return this.outputitems;
    }

    public int getBurnLength(){
        return this.burnlength;
    }

    /**
     * Decrement {@link #burnlength} by one per call.
     * To be used to decrease recipe time.
     */
    public void tickBurnLength(){
        this.burnlength = this.burnlength - 1;
    }

    public ItemStack getInputItem(){
        return this.inputitem;
    }

    public List<StatusEffectInstance> getStatusEffects(){
        return this.recipeeffects;
    }

    /**
     * Used to tick the recipe and apply effects to player/entities, or to make changes to the world.
     * This method is fine to override and apply custom behavior for a specific recipe.
     * @param playerIn Instance of nearby player(s) to be given recipe StatuEffect.
     * @param worldIn Instance of World.
     */
    public void recipeTick(PlayerEntity playerIn, World worldIn, BlockPos pos, EntitySoulfireAlter blockEntity){
        for (StatusEffectInstance effect : recipeeffects) {
            if(effect != null && effect instanceof StatusEffectInstance){
                playerIn.addStatusEffect(effect);
            }
        }
        if(burnlength<=0){
            blockEntity.clear();
            blockEntity.setInvStack(0, this.getInputItem());
        }
        this.tickBurnLength();
    }
}