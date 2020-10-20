// ref:
// https://zh.wikipedia.org/wiki/%E5%AF%B9%E8%B1%A1%E6%B1%A0%E6%A8%A1%E5%BC%8F
package design_pattern.creational_pattern.object_pool_pattern

import java.util.*

class PooledObjectPool {
    private val expTime: Long = 6000 // 6 seconds

    var available = HashMap<PooledObject, Long>()
    var inUse = HashMap<PooledObject, Long>()

    @Synchronized
    fun getObject(): PooledObject? {
        val now = System.currentTimeMillis()
        if (available.isNotEmpty()) {
            for ((key, value) in available) {
                if (now - value > expTime) { //object has expired
                    popElement(available)
                } else {
                    val po = popElement(available, key)
                    push(inUse, po, now)
                    return po
                }
            }
        }

        // either no PooledObject is available or each has expired, so return a new one
        return createPooledObject(now)
    }

    @Synchronized
    private fun createPooledObject(now: Long): PooledObject? {
        val po = PooledObject()
        push(inUse, po, now)
        return po
    }

    @Synchronized
    private fun push(
        map: HashMap<PooledObject, Long>,
        po: PooledObject, now: Long
    ) {
        map[po] = now
    }

    fun releaseObject(po: PooledObject) {
        cleanUp(po)
        available[po] = System.currentTimeMillis()
        inUse.remove(po)
    }

    private fun popElement(map: HashMap<PooledObject, Long>): PooledObject? {
        val entry: Map.Entry<PooledObject, Long> = map.entries.iterator().next()
        val key = entry.key
        //Long value=entry.getValue();
        map.remove(entry.key)
        return key
    }

    private fun popElement(map: HashMap<PooledObject, Long>, key: PooledObject): PooledObject {
        map.remove(key)
        return key
    }

    fun cleanUp(po: PooledObject) {
        po.temp1 = null
        po.temp2 = null
        po.temp3 = null
    }
}