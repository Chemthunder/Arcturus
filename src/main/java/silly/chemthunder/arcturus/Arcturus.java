package silly.chemthunder.arcturus;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silly.chemthunder.arcturus.index.ArcturusEffects;
import silly.chemthunder.arcturus.index.ArcturusEntities;
import silly.chemthunder.arcturus.index.ArcturusItems;

public class Arcturus implements ModInitializer {
	public static final String MOD_ID = "arcturus";

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path); }

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ArcturusItems.initialize();
        ArcturusEffects.initialize();
        ArcturusEntities.initialize();

	}
}