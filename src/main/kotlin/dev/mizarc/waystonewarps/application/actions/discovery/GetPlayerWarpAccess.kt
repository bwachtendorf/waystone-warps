package dev.mizarc.waystonewarps.application.actions.discovery

import dev.mizarc.waystonewarps.domain.discoveries.DiscoveryRepository
import dev.mizarc.waystonewarps.domain.warps.Warp
import dev.mizarc.waystonewarps.domain.warps.WarpRepository
import java.util.UUID
import org.bukkit.Bukkit

class GetPlayerWarpAccess(
    private val discoveryRepository: DiscoveryRepository,
    private val warpRepository: WarpRepository,
) {
    fun execute(playerId: UUID): List<Warp> {
        val warps = mutableListOf<Warp>()
        val player = Bukkit.getPlayer(playerId) ?: return warps

        if (player.hasPermission("waystonewarps.teleport.undiscovered")) {
            // add all waystones if player is allowed to teleport to undiscovered
            for (w in warpRepository.getAll()) {
                warps.add(w)
            }
        } else {
            // iterate over discovered waypoints only
            val discoveries = discoveryRepository.getByPlayer(playerId)
            for (discovery in discoveries) {
                val warp = warpRepository.getById(discovery.warpId)
                if (warp != null) {
                    warps.add(warp)
                }
            }
        }
        return warps
    }
}