package io.github.hiiragi283.api.extension

object ExtendedOreDictionary {

    /*private val idToName: BiMap<Int, String> = HashBiMap.create()
    private val idToStack: MutableList<NonNullList<ItemStack>> = mutableListOf()
    private val stackToId: MutableMap<ItemWithMeta, MutableList<Int>> = hashMapOf()

    const val UNKNOWN_NAME: String = "Unknown"

    @JvmStatic
    fun getOreID(name: String): Int {

    }

    @JvmStatic
    fun getOreName(id: Int): String = when {
        id in (0 until idToName.size) -> idToName[id]
        else -> null
    } ?: UNKNOWN_NAME

    @JvmStatic
    fun getOreIDs(stack: ItemStack): IntArray {
        check(!stack.isEmpty) {
            "Stack is empty!"
        }
        if (stack.item.delegate.name() == null) {
            FMLLog.log.debug(
                "Attempted to find the oreIDs for an unregistered object ({}). This won't work very well.",
                stack
            )
            return IntArray(0)
        }
        val id = Item.REGISTRY.getIDForObject(stack.item.delegate.get())

    }

    @JvmStatic
    fun getOres(name: String): NonNullList<ItemStack> = getOres(getOreID(name))

    @JvmStatic
    fun getOres(name: String, alwaysCreateEntry: Boolean): NonNullList<ItemStack> = when {
        alwaysCreateEntry || idToName.inverse().containsKey(name) -> getOres(name)
        else -> OreDictionary.EMPTY_LIST
    }

    @JvmStatic
    fun doesOreNameExist(name: String): Boolean = name in idToName.inverse()

    @JvmStatic
    fun getOreNames(): Set<String> = idToName.values

    @JvmStatic
    fun getOres(id: Int): NonNullList<ItemStack> = idToStack.getOrNull(id) ?: OreDictionary.EMPTY_LIST

    @JvmStatic
    fun registerOre(name: String, item: Item) {
        ItemWithMeta.ofItem(item).forEach { registerOre(name, it) }
    }

    @JvmStatic
    fun registerOre(name: String, block: Block) {
        ItemWithMeta.ofBlock(block).forEach { registerOre(name, it) }
    }

    @JvmStatic
    fun registerOre(name: String, stack: ItemStack) {
        ItemWithMeta.ofStack(stack).forEach { registerOre(name, it) }
    }

    private fun registerOre(name: String, itemWithMeta: ItemWithMeta) {
        if (name == UNKNOWN_NAME) return
        val oreId: Int = getOreID(name)

        val list: MutableList<Int>? = stackToId[itemWithMeta]
        if (list?.contains(oreId) == true) return
        else {
            stackToId[itemWithMeta] = mutableListOf(oreId)
        }
        idToStack[oreId].add(itemWithMeta.getStack())
        MinecraftForge.EVENT_BUS.post(OreDictionary.OreRegisterEvent(name, itemWithMeta.getStack()))
    }*/

}