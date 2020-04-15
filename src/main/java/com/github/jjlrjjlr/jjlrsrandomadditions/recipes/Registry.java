package com.github.jjlrjjlr.jjlrsrandomadditions.recipes;

import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class Registry {

    public static Map<Item, SoulfireAlterRecipe> SOULFIREALTER_RECIPES;

    public static void registerSoulfireAlterRecipe() {
        SOULFIREALTER_RECIPES.put(Items.OBSIDIAN, new SoulfireAlterRecipe().setInputItem(new ItemStack(Items.OBSIDIAN, 1)).addOutputItem(new ItemStack(Items.APPLE, 5)).setBurnLength(50));
        
    }
    
}