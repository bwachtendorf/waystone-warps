package dev.mizarc.waystonewarps.infrastructure.services

import dev.mizarc.waystonewarps.application.services.WorldGroupService
import java.util.UUID


/**
 * Implementation of WorldGroupService used when world group functionality is not supported.
 *
 * Returns `false` for everything.
 */
class WorldGroupBukkitUnsupported() : WorldGroupService {
    override fun supportsWorldGroups(): Boolean {
        return false
    }

    override fun inSameGroup(current: String, target: String): Boolean {
        return false
    }

    override fun inSameGroup(current: UUID, target: UUID): Boolean {
        return false
    }
}