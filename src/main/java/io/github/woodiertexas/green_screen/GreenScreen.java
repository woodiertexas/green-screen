package io.github.woodiertexas.green_screen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.recipe.api.RecipeManagerHelper;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreenScreen implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Green Screen");

	public static final Block GREEN_SCREEN = new Block(QuiltBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(0.1f).luminance(15).emissiveLighting((state, getter, pos) -> true));
	public static final Block BLUE_SCREEN = new Block(QuiltBlockSettings.of(Material.WOOL).sounds(BlockSoundGroup.WOOL).strength(0.1f).luminance(15).emissiveLighting((state, getter, pos) -> true));

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		Registry.register(Registry.BLOCK, new Identifier("green_screen", "green_screen_block"), GREEN_SCREEN);
		Registry.register(Registry.ITEM, new Identifier("green_screen", "green_screen_block"),
				new BlockItem(GREEN_SCREEN, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));

		Registry.register(Registry.BLOCK, new Identifier("green_screen", "blue_screen_block"), BLUE_SCREEN);
		Registry.register(Registry.ITEM, new Identifier("green_screen", "blue_screen_block"),
				new BlockItem(BLUE_SCREEN, new QuiltItemSettings().group(ItemGroup.DECORATIONS)));

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
