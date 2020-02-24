package com.github.jjlrjjlr.jjlrsrandomadditions.blocks;

import com.github.jjlrjjlr.jjlrsrandomadditions.References;
import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.entities.EntitySoulfireAlter;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksRegistry {

    public static final Block MARBLE_BRICKS = new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.WHITE).build());
    public static final Block SMOOTH_BASALT = new Block(
            FabricBlockSettings.of(Material.STONE, MaterialColor.GRAY).build());
    public static final Block SOULFIRE_ALTER = new Soulfire_Alter(FabricBlockSettings.of(Material.METAL).nonOpaque().strength(3.0f, 0.6f).lightLevel(5).build());

    //Block Entities

    public static BlockEntityType<EntitySoulfireAlter> Entity_Soulfire_Alter;

    public static void registerBlocks() {

        Registry.register(Registry.BLOCK, new Identifier(References.MODID, "marble_bricks"), MARBLE_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier(References.MODID, "smooth_basalt"), SMOOTH_BASALT);
        Registry.register(Registry.BLOCK, new Identifier(References.MODID, "soulfire_alter"), SOULFIRE_ALTER);
    }

    public static void registerBlockItems() {

        Registry.register(Registry.ITEM, new Identifier(References.MODID, "marble_bricks"),
                new BlockItem(MARBLE_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(References.MODID, "smooth_basalt"),
                new BlockItem(SMOOTH_BASALT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier(References.MODID, "soulfire_alter"), new BlockItem(SOULFIRE_ALTER, new Item.Settings().group(ItemGroup.DECORATIONS)));

        
    }

    public static void registerTransparentBlocks() {
            
        BlockRenderLayerMap.INSTANCE.putBlock(SOULFIRE_ALTER, RenderLayer.getCutout());
    }

    public static void registerBlockEntities() {
            
        Entity_Soulfire_Alter = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(References.MODID, "entitysoulfirealter"), BlockEntityType.Builder.create(EntitySoulfireAlter::new, SOULFIRE_ALTER).build(null));
    }
}
