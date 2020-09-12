package net.smelly.seedgetter.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.network.play.server.SJoinGamePacket;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.smelly.seedgetter.SeedGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetHandler.class)
public final class ClientPlayNetHandlerMixin {
	@Shadow
	private Minecraft client;

	@Inject(at = @At("RETURN"), method = "handleJoinGame")
	private void getHashedWorldSeed(SJoinGamePacket packet, CallbackInfo info) {
		long hashedSeed = packet.getHashedSeed();
		ClientPlayerEntity playerEntity = client.player;
		String hashedSeedString = String.valueOf(hashedSeed);
		playerEntity.sendMessage(new TranslationTextComponent("seed_getter.messages.hashed_seed",
				TextComponentUtils.toTextComponent((new StringTextComponent(hashedSeedString)).modifyStyle((style) -> {
					return style.setFormatting(TextFormatting.GREEN).setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, hashedSeedString)).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslationTextComponent("chat.copy.click"))).setInsertion(hashedSeedString);
				}))
			),
			playerEntity.getUniqueID()
		);
		SeedGetter.LOGGER.info("Seed Getter has received the world's hashed seed: {}", hashedSeed);
		SeedGetter.setHashedSeed(hashedSeed);
	}
}
