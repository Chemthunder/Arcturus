package silly.chemthunder.arcturus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import silly.chemthunder.arcturus.index.ArcturusEntities;

public class ArcturusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ArcturusEntities.CREATURE, EmptyEntityRenderer::new);
    }
}
