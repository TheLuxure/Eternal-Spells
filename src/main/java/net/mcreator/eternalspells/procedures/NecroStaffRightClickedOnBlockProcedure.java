package net.mcreator.eternalspells.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import net.mcreator.eternalspells.entity.PassiveRisenEntity;
import net.mcreator.eternalspells.EternalSpellsModElements;

import java.util.Random;
import java.util.Map;

@EternalSpellsModElements.ModElement.Tag
public class NecroStaffRightClickedOnBlockProcedure extends EternalSpellsModElements.ModElement {
	public NecroStaffRightClickedOnBlockProcedure(EternalSpellsModElements instance) {
		super(instance, 48);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure NecroStaffRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure NecroStaffRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure NecroStaffRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure NecroStaffRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure NecroStaffRightClickedOnBlock!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			ItemStack _ist = (itemstack);
			if (_ist.attemptDamageItem((int) 2, new Random(), null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
		if (world instanceof World && !world.getWorld().isRemote) {
			Entity entityToSpawn = new PassiveRisenEntity.CustomEntity(PassiveRisenEntity.entity, world.getWorld());
			entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
			if (entityToSpawn instanceof MobEntity)
				((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
						SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
			world.addEntity(entityToSpawn);
		}
	}
}
