package net.mcreator.eternalspells.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.eternalspells.item.SpellShaperWandItem;
import net.mcreator.eternalspells.item.EssentialTomeItem;
import net.mcreator.eternalspells.entity.MagmaLordEntity;
import net.mcreator.eternalspells.entity.EarthLordEntity;
import net.mcreator.eternalspells.block.MagewoodBlock;
import net.mcreator.eternalspells.EternalSpellsModElements;

import java.util.Map;

@EternalSpellsModElements.ModElement.Tag
public class SpellShapingTableOnBlockRightClickedProcedure extends EternalSpellsModElements.ModElement {
	public SpellShapingTableOnBlockRightClickedProcedure(EternalSpellsModElements instance) {
		super(instance, 20);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SpellShapingTableOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SpellShapingTableOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SpellShapingTableOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SpellShapingTableOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure SpellShapingTableOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack iteminhand = ItemStack.EMPTY;
		double phase = 0;
		if (((iteminhand).getItem() == new ItemStack(Items.BOOK, (int) (1)).getItem())) {
			if (((phase) == 0)) {
				phase = (double) 1;
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Items.BOOK, (int) (1)).getItem() == p.getItem(), (int) 1);
				world.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 1, 0);
				world.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0, 1);
				world.addParticle(ParticleTypes.BUBBLE, x, y, z, 1, 0, 0);
			}
		}
		if (((iteminhand).getItem() == new ItemStack(Blocks.OAK_LOG, (int) (1)).getItem())) {
			if (((phase) == 0)) {
				phase = (double) 2;
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Blocks.OAK_LOG, (int) (1)).getItem() == p.getItem(),
							(int) 1);
				world.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, 1, 0, 0);
				world.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, 0, 1, 0);
				world.addParticle(ParticleTypes.ENCHANTED_HIT, x, y, z, 0, 0, 1);
			}
		}
		if (((iteminhand).getItem() == new ItemStack(Items.LAVA_BUCKET, (int) (1)).getItem())) {
			if (((phase) == 0)) {
				phase = (double) 3;
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Items.LAVA_BUCKET, (int) (1)).getItem() == p.getItem(),
							(int) 1);
				world.addParticle(ParticleTypes.FALLING_LAVA, x, y, z, 1, 0, 0);
				world.addParticle(ParticleTypes.FALLING_LAVA, x, y, z, 0, 1, 0);
				world.addParticle(ParticleTypes.FALLING_LAVA, x, y, z, 0, 0, 1);
			}
		}
		if (((iteminhand).getItem() == new ItemStack(Blocks.GRASS_BLOCK, (int) (1)).getItem())) {
			if (((phase) == 0)) {
				phase = (double) 4;
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Blocks.GRASS_BLOCK, (int) (1)).getItem() == p.getItem(),
							(int) 1);
				world.addParticle(ParticleTypes.HEART, x, y, z, 1, 0, 0);
				world.addParticle(ParticleTypes.HEART, x, y, z, 0, 1, 0);
				world.addParticle(ParticleTypes.HEART, x, y, z, 0, 0, 1);
			}
		}
		if (((iteminhand).getItem() == new ItemStack(SpellShaperWandItem.block, (int) (1)).getItem())) {
			if (((phase) == 1)) {
				if (!world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(EssentialTomeItem.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					world.addEntity(entityToSpawn);
				}
			}
			if (((phase) == 2)) {
				if (!world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(MagewoodBlock.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					world.addEntity(entityToSpawn);
				}
			}
			if (((phase) == 3)) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new MagmaLordEntity.CustomEntity(MagmaLordEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, (y - 1), z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			}
			if (((phase) == 4)) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new EarthLordEntity.CustomEntity(EarthLordEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, (y - 1), z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
			}
			phase = (double) 0;
		}
	}
}
