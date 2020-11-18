package ru.fewizz.crawl;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class Crawl implements ModInitializer {
    public static final Identifier CRAWL_IDENTIFIER = new Identifier("crawl:identifier");

	@Override
	public void onInitialize() {
		ServerSidePacketRegistry.INSTANCE.register(CRAWL_IDENTIFIER, (context, buf) -> {
			boolean val = buf.readBoolean();
			context.getTaskQueue().execute(() -> context.getPlayer().getDataTracker().set(Shared.CRAWLING_REQUEST, val));
		});
	}
	
	public static class Shared {
		public static final EntityPose CRAWLING = ClassTinkerers.getEnum(EntityPose.class, EntityPoseHack.CRAWLING);
		public static final EntityDimensions CRAWLING_DIMENSIONS = new EntityDimensions(0.6F, 0.6F, false);
		public static final TrackedData<Boolean> CRAWLING_REQUEST = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	}
}
