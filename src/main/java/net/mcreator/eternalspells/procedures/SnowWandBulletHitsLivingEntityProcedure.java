package net.mcreator.eternalspells.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.eternalspells.EternalSpellsModElements;

import java.util.Map;

@EternalSpellsModElements.ModElement.Tag
public class SnowWandBulletHitsLivingEntityProcedure extends EternalSpellsModElements.ModElement {
	public SnowWandBulletHitsLivingEntityProcedure(EternalSpellsModElements instance) {
		super(instance, 13);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SnowWandBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SnowWandBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SnowWandBulletHitsLivingEntity!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SnowWandBulletHitsLivingEntity!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 2), (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 3), (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) (z + 1)), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) (z + 1)), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 3), (int) (z + 1)), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 2), (int) (z + 1)), Blocks.ICE.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) (z + 1)), Blocks.ICE.getDefaultState(), 3);
	}
}
