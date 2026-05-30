package com.woodiertexas;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class GreenScreen implements ModInitializer {
	public static final String MOD_ID = "green_screen";
	public static final Logger LOGGER = LoggerFactory.getLogger("Green Screen");
	
	private static ResourceKey<Item> itemKey(String name) {
		return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, name));
	}

	private static ResourceKey<Block> blockKey(String name) {
		return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, name));
	}
	
	private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties blockSettings, boolean shouldRegisterItem) {
		ResourceKey<Block> blockKey = blockKey(name);
		Block block = blockFactory.apply(blockSettings.setId(blockKey));
		
		if (shouldRegisterItem) {
			ResourceKey<Item> itemKey = itemKey(name);
			BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
			Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
		}

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}
	
	static final BlockBehaviour.Properties WOOL_SOUND = BlockBehaviour.Properties.of().sound(SoundType.WOOL);
	public static final Block RED_SCREEN = registerBlock("red_screen_block", Block::new, WOOL_SOUND, true);
	public static final Block GREEN_SCREEN = registerBlock("green_screen_block", Block::new, WOOL_SOUND, true);
	public static final Block BLUE_SCREEN = registerBlock("blue_screen_block", Block::new, WOOL_SOUND, true);
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register((creativeTab) -> {
			creativeTab.accept(RED_SCREEN.asItem());
			creativeTab.accept(GREEN_SCREEN.asItem());
			creativeTab.accept(BLUE_SCREEN.asItem());
		});
	}
}
