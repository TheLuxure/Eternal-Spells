
package net.mcreator.eternalspells.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.eternalspells.procedures.EarthLordEntityDiesProcedure;
import net.mcreator.eternalspells.itemgroup.SpellShaperItemGroup;
import net.mcreator.eternalspells.EternalSpellsModElements;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@EternalSpellsModElements.ModElement.Tag
public class EarthLordEntity extends EternalSpellsModElements.ModElement {
	public static EntityType entity = null;
	public EarthLordEntity(EternalSpellsModElements instance) {
		super(instance, 52);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(128).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f))
						.build("earth_lord").setRegistryName("earth_lord");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16751104, -16711936, new Item.Properties().group(SpellShaperItemGroup.tab))
				.setRegistryName("earth_lord"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelErath(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("eternal_spells:textures/earth.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			enablePersistence();
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}
				public boolean shouldExecute() {
					if (CustomEntity.this.getAttackTarget() != null && !CustomEntity.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null
							&& CustomEntity.this.getAttackTarget().isAlive();
				}

				@Override
				public void startExecuting() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					Vec3d vec3d = livingentity.getEyePosition(1);
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void tick() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					if (CustomEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
						CustomEntity.this.attackEntityAsMob(livingentity);
					} else {
						double d0 = CustomEntity.this.getDistanceSq(livingentity);
						if (d0 < 16) {
							Vec3d vec3d = livingentity.getEyePosition(1);
							CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
						}
					}
				}
			});
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.3, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Blocks.STONE, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.gravel.hit"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sand.fall"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof ArrowEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity sourceentity = source.getTrueSource();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				EarthLordEntityDiesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(670);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.GREEN, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelErath extends EntityModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer stick1;
		private final ModelRenderer stick2;
		private final ModelRenderer stick3;
		private final ModelRenderer stick4;
		private final ModelRenderer stick5;
		private final ModelRenderer stick6;
		private final ModelRenderer stick7;
		private final ModelRenderer stick8;
		private final ModelRenderer stick9;
		private final ModelRenderer stick10;
		private final ModelRenderer stick11;
		private final ModelRenderer stick12;
		public ModelErath() {
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.setTextureOffset(0, 0).addBox(-4.0F, 3.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			stick1 = new ModelRenderer(this);
			stick1.setRotationPoint(7.0F, -2.0F, -7.0F);
			stick1.setTextureOffset(8, 36).addBox(-15.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick2 = new ModelRenderer(this);
			stick2.setRotationPoint(-7.0F, -2.0F, -7.0F);
			stick2.setTextureOffset(0, 36).addBox(13.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick3 = new ModelRenderer(this);
			stick3.setRotationPoint(-7.0F, -2.0F, 7.0F);
			stick3.setTextureOffset(32, 0).addBox(13.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick4 = new ModelRenderer(this);
			stick4.setRotationPoint(7.0F, -2.0F, 7.0F);
			stick4.setTextureOffset(16, 32).addBox(-15.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick5 = new ModelRenderer(this);
			stick5.setRotationPoint(5.0F, 2.0F, -5.0F);
			stick5.setTextureOffset(30, 30).addBox(-11.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick6 = new ModelRenderer(this);
			stick6.setRotationPoint(-5.0F, 2.0F, -5.0F);
			stick6.setTextureOffset(28, 16).addBox(9.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick7 = new ModelRenderer(this);
			stick7.setRotationPoint(-5.0F, 2.0F, 5.0F);
			stick7.setTextureOffset(8, 26).addBox(9.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick8 = new ModelRenderer(this);
			stick8.setRotationPoint(5.0F, 2.0F, 5.0F);
			stick8.setTextureOffset(0, 26).addBox(-11.0F, 7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick9 = new ModelRenderer(this);
			stick9.setRotationPoint(3.0F, 10.0F, -3.0F);
			stick9.setTextureOffset(22, 24).addBox(-6.0F, -11.0F, 0.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick10 = new ModelRenderer(this);
			stick10.setRotationPoint(-3.0F, 10.0F, -3.0F);
			stick10.setTextureOffset(16, 16).addBox(5.0F, -11.0F, 0.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick11 = new ModelRenderer(this);
			stick11.setRotationPoint(-3.0F, 10.0F, 3.0F);
			stick11.setTextureOffset(8, 16).addBox(5.0F, -11.0F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			stick12 = new ModelRenderer(this);
			stick12.setRotationPoint(3.0F, 10.0F, 3.0F);
			stick12.setTextureOffset(0, 16).addBox(-6.0F, -11.0F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			head.render(matrixStack, buffer, packedLight, packedOverlay);
			stick1.render(matrixStack, buffer, packedLight, packedOverlay);
			stick2.render(matrixStack, buffer, packedLight, packedOverlay);
			stick3.render(matrixStack, buffer, packedLight, packedOverlay);
			stick4.render(matrixStack, buffer, packedLight, packedOverlay);
			stick5.render(matrixStack, buffer, packedLight, packedOverlay);
			stick6.render(matrixStack, buffer, packedLight, packedOverlay);
			stick7.render(matrixStack, buffer, packedLight, packedOverlay);
			stick8.render(matrixStack, buffer, packedLight, packedOverlay);
			stick9.render(matrixStack, buffer, packedLight, packedOverlay);
			stick10.render(matrixStack, buffer, packedLight, packedOverlay);
			stick11.render(matrixStack, buffer, packedLight, packedOverlay);
			stick12.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
