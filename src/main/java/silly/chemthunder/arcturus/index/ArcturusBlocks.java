package silly.chemthunder.arcturus.index;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import silly.chemthunder.arcturus.Arcturus;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ArcturusBlocks {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();



    static <T extends Block> T create(String name, T block) {
        BLOCKS.put(block, Arcturus.id(name));
        return block;
    }

    static void initialize() {
        BLOCKS.forEach((item, id) -> Registry.register(Registries.BLOCK, id, item));
    }
}
