package com.github.jjlrjjlr.jjlrsrandomadditions;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.BlocksRegistry;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		BlocksRegistry.registerBlocks();
		BlocksRegistry.registerBlockItems();
		BlocksRegistry.registerTransparentBlocks();
		System.out.println("Starting...");
	}
}
