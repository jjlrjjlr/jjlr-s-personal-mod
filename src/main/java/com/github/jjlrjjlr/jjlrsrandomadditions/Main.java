package com.github.jjlrjjlr.jjlrsrandomadditions;

import com.github.jjlrjjlr.jjlrsrandomadditions.blocks.BlocksRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import net.fabricmc.api.ModInitializer;

public class Main implements ModInitializer {

	private static Logger logger = (Logger) LogManager.getLogger("jjlrsrandomadditions");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		BlocksRegistry.registerBlocks();
		BlocksRegistry.registerBlockEntities();
		BlocksRegistry.registerBlockItems();
		BlocksRegistry.registerTransparentBlocks();
		logger.info("jjlrsrandomadditions Started.");
	}
}
