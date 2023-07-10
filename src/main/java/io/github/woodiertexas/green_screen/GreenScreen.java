package io.github.woodiertexas.green_screen;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class GreenScreen implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Green Screen");

	public static final Block RED_SCREEN = new Block(QuiltBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.1f).luminance(value -> 15).emissiveLighting((state, getter, pos) -> true));
	public static final Block GREEN_SCREEN = new Block(QuiltBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.1f).luminance(value -> 15).emissiveLighting((state, getter, pos) -> true));
	public static final Block BLUE_SCREEN = new Block(QuiltBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.1f).luminance(value -> 15).emissiveLighting((state, getter, pos) -> true));

	@Override
	public void onInitialize(ModContainer mod) {
		HashMap<Block, String> blocks = new HashMap<>();

		blocks.put(RED_SCREEN, "red_screen_block");
		blocks.put(GREEN_SCREEN, "green_screen_block");
		blocks.put(BLUE_SCREEN, "blue_screen_block");

		for (Block block : blocks.keySet()) {
			Registry.register(Registries.BLOCK, new Identifier("green_screen", blocks.get(block)), block);
			Registry.register(Registries.ITEM, new Identifier("green_screen", blocks.get(block)),
					new BlockItem(block, new QuiltItemSettings()));
			ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL_BLOCKS).register(entries -> entries.addItem(block.asItem()));
		}

		RecipeManagerHelper.registerStaticRecipe(
				VanillaRecipeBuilders.shapedRecipe(
								"RRR",
								"RRR",
								"RRR")
						.output(new ItemStack(GREEN_SCREEN))
						.ingredient('R', Items.RED_DYE)
						.build(new Identifier("red_screen", "blue_screen_block"), "")
		);

		RecipeManagerHelper.registerStaticRecipe(
				VanillaRecipeBuilders.shapedRecipe(
						"GGG",
								"GGG",
								"GGG")
						.output(new ItemStack(GREEN_SCREEN))
						.ingredient('G', Items.GREEN_DYE)
						.build(new Identifier("green_screen", "green_screen_block"), "")
		);

		RecipeManagerHelper.registerStaticRecipe(
				VanillaRecipeBuilders.shapedRecipe(
						"BBB",
								"BBB",
								"BBB")
						.output(new ItemStack(GREEN_SCREEN))
						.ingredient('B', Items.BLUE_DYE)
						.build(new Identifier("blue_screen", "blue_screen_block"), "")
		);
	}
}
