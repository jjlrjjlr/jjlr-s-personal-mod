package com.github.jjlrjjlr.jjlrsrandomadditions.client;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.BlocksRegistry;
import com.github.jjlrjjlr.jjlrsrandomadditions.client.block.Render_Soulfire_Alter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;

public class ClientMain implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        System.out.println("test client init.........");
        BlockEntityRendererRegistry.INSTANCE.register(BlocksRegistry.Entity_Soulfire_Alter, Render_Soulfire_Alter::new);

    }

    
}