package net.mcreator.eternalspells.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.eternalspells.EternalSpellsModElements;

import java.util.Map;

@EternalSpellsModElements.ModElement.Tag
public class MagmaLordEntityIsHurtProcedure extends EternalSpellsModElements.ModElement {
	public MagmaLordEntityIsHurtProcedure(EternalSpellsModElements instance) {
		super(instance, 51);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MagmaLordEntityIsHurt!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MagmaLordEntityIsHurt!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MagmaLordEntityIsHurt!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MagmaLordEntityIsHurt!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double rand = 0;
		rand = (double) Math.random();
		if (((rand) < 0.2)) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.FIRE.getDefaultState(), 3);
		} else if (((rand) < 0.4)) {
			for (int index0 = 0; index0 < (int) (3); index0++) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new BlazeEntity(EntityType.BLAZE, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			}
		} else if (((rand) < 0.6)) {
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world.getWorld(), (int) x, (int) y, (int) z, false));
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world.getWorld(), (int) x, (int) y, (int) z, false));
			if (world instanceof ServerWorld)
				((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world.getWorld(), (int) x, (int) y, (int) z, false));
		} else if (((rand) < 0.8)) {
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new TNTEntity(EntityType.TNT, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new TNTEntity(EntityType.TNT, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
		} else {
			world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.LAVA.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.LAVA.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.LAVA.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.LAVA.getDefaultState(), 3);
		}
	}
}
