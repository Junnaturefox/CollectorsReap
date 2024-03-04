package net.brdle.collectorsreap.common.entity;

import net.brdle.collectorsreap.common.item.CRItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;

public class Urchin extends WaterCreature {

	public Urchin(EntityType<? extends WaterCreature> type, Level level) {
		super(type, level);
	}

	public static AttributeSupplier.@NotNull Builder createAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.2D)
			.add(Attributes.MAX_HEALTH, 10.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 1.25D, 1.35D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 0.5D, 40));
	}


	@Override
	public @NotNull ItemStack getBucketItemStack() {
		return CRItems.URCHIN_BUCKET.get().getDefaultInstance();
	}

	@Override
	public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
		return false;
	}

	@Override
	public void knockback(double pStrength, double pX, double pZ) {
	}

	@Override
	public boolean canSwimInFluidType(FluidType type) {
		return false;
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean isPushedByFluid(FluidType type) {
		return false;
	}

	@Override
	public void jumpInFluid(FluidType type) { }

	@Override
	public void aiStep() {
		if (this.isInWater() && !this.isOnGround()) {
			this.sinkInFluid(ForgeMod.WATER_TYPE.get());
		}
		super.aiStep();
	}

	@Override
	public boolean hurt(@NotNull DamageSource source, float pAmount) {
		Entity entity = source.getEntity();
		if (
			!this.getLevel().isClientSide() &&
				entity instanceof LivingEntity living &&
				!this.isInvulnerableTo(source) &&
				source.getDirectEntity() == entity
		) {
			DamageSource thorns =  DamageSource.thorns(this);
			if (!living.isInvulnerableTo(thorns)) {
				living.hurt(thorns, 3.0F);
			}
			living.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0), this);
		}
		return super.hurt(source, pAmount);
	}
}